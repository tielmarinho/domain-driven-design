package com.example.clinic.infra.dao;

import com.example.clinic.domain.model.Consulta;
import com.example.clinic.domain.service.AgendaService;
import com.example.clinic.infra.db.OracleConnectionFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaJdbcRepository implements AgendaService.ConsultaRepository {

    @Override
    public List<Consulta> listarPorMedicoNoIntervalo(long medicoId, LocalDateTime inicio, LocalDateTime fim) {
        String sql = "SELECT id, paciente_id, medico_id, inicio, fim FROM consultas " +
                "WHERE medico_id = ? AND inicio < ? AND fim > ?";
        List<Consulta> lista = new ArrayList<>();
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, medicoId);
            ps.setTimestamp(2, Timestamp.valueOf(fim));
            ps.setTimestamp(3, Timestamp.valueOf(inicio));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar consultas", e);
        }
        return lista;
    }

    @Override
    public Long salvar(Consulta c) {
        String sql = "INSERT INTO consultas (paciente_id, medico_id, inicio, fim) VALUES (?, ?, ?, ?)";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, new String[] {"ID"})) {
            ps.setLong(1, c.getPacienteId());
            ps.setLong(2, c.getMedicoId());
            ps.setTimestamp(3, Timestamp.valueOf(c.getInicio()));
            ps.setTimestamp(4, Timestamp.valueOf(c.getFim()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar consulta", e);
        }
    }

    public Consulta buscarPorId(long id) {
        String sql = "SELECT id, paciente_id, medico_id, inicio, fim FROM consultas WHERE id = ?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar consulta", e);
        }
    }

    public List<Consulta> listarTodas() {
        String sql = "SELECT id, paciente_id, medico_id, inicio, fim FROM consultas";
        List<Consulta> lista = new ArrayList<>();
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(map(rs));
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar consultas", e);
        }
    }

    public void atualizar(Consulta c) {
        String sql = "UPDATE consultas SET paciente_id=?, medico_id=?, inicio=?, fim=? WHERE id=?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, c.getPacienteId());
            ps.setLong(2, c.getMedicoId());
            ps.setTimestamp(3, Timestamp.valueOf(c.getInicio()));
            ps.setTimestamp(4, Timestamp.valueOf(c.getFim()));
            ps.setLong(5, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar consulta", e);
        }
    }

    public void deletar(long id) {
        String sql = "DELETE FROM consultas WHERE id=?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar consulta", e);
        }
    }

    private Consulta map(ResultSet rs) throws SQLException {
        return new Consulta(
                rs.getLong("id"),
                rs.getLong("paciente_id"),
                rs.getLong("medico_id"),
                rs.getTimestamp("inicio").toLocalDateTime(),
                rs.getTimestamp("fim").toLocalDateTime()
        );
    }
}
