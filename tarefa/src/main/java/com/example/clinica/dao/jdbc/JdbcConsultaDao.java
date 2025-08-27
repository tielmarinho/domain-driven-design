
package com.example.clinica.dao.jdbc;

import com.example.clinica.config.OracleConnectionFactory;
import com.example.clinica.dao.ConsultaDao;
import com.example.clinica.domain.Consulta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcConsultaDao implements ConsultaDao {

    @Override
    public Long salvar(Consulta c) {
        String sql = "INSERT INTO CONSULTAS (PACIENTE_ID, MEDICO_ID, INICIO, FIM) VALUES (?, ?, ?, ?)";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID"})) {
            // Exemplo de controle transacional explícito (poderia ser auto-commit):
            boolean prevAuto = con.getAutoCommit();
            try {
                con.setAutoCommit(false);
                ps.setLong(1, c.getPacienteId());
                ps.setLong(2, c.getMedicoId());
                ps.setTimestamp(3, Timestamp.valueOf(c.getInicio()));
                ps.setTimestamp(4, Timestamp.valueOf(c.getFim()));
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    Long id = null;
                    if (rs.next()) id = rs.getLong(1);
                    con.commit();
                    return id;
                }
            } catch (SQLException ex) {
                con.rollback();
                throw ex;
            } finally {
                con.setAutoCommit(prevAuto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar consulta", e);
        }
    }

    @Override
    public void atualizar(Consulta c) {
        String sql = "UPDATE CONSULTAS SET PACIENTE_ID=?, MEDICO_ID=?, INICIO=?, FIM=? WHERE ID=?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            boolean prevAuto = con.getAutoCommit();
            try {
                con.setAutoCommit(false);
                ps.setLong(1, c.getPacienteId());
                ps.setLong(2, c.getMedicoId());
                ps.setTimestamp(3, Timestamp.valueOf(c.getInicio()));
                ps.setTimestamp(4, Timestamp.valueOf(c.getFim()));
                ps.setLong(5, c.getId());
                ps.executeUpdate();
                con.commit();
            } catch (SQLException ex) {
                con.rollback();
                throw ex;
            } finally {
                con.setAutoCommit(prevAuto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar consulta", e);
        }
    }

    @Override
    public void excluir(Long id) {
        String sql = "DELETE FROM CONSULTAS WHERE ID=?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            boolean prevAuto = con.getAutoCommit();
            try {
                con.setAutoCommit(false);
                ps.setLong(1, id);
                ps.executeUpdate();
                con.commit();
            } catch (SQLException ex) {
                con.rollback();
                throw ex;
            } finally {
                con.setAutoCommit(prevAuto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir consulta", e);
        }
    }

    @Override
    public Optional<Consulta> porId(Long id) {
        String sql = "SELECT ID, PACIENTE_ID, MEDICO_ID, INICIO, FIM FROM CONSULTAS WHERE ID=?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Consulta(
                        rs.getLong("ID"),
                        rs.getLong("PACIENTE_ID"),
                        rs.getLong("MEDICO_ID"),
                        rs.getTimestamp("INICIO").toLocalDateTime(),
                        rs.getTimestamp("FIM").toLocalDateTime()
                    ));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar consulta", e);
        }
    }

    @Override
    public List<Consulta> listar() {
        String sql = "SELECT ID, PACIENTE_ID, MEDICO_ID, INICIO, FIM FROM CONSULTAS ORDER BY ID";
        List<Consulta> list = new ArrayList<>();
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Consulta(
                    rs.getLong("ID"),
                    rs.getLong("PACIENTE_ID"),
                    rs.getLong("MEDICO_ID"),
                    rs.getTimestamp("INICIO").toLocalDateTime(),
                    rs.getTimestamp("FIM").toLocalDateTime()
                ));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar consultas", e);
        }
    }
}
