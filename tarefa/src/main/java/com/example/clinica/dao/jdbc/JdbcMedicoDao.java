
package com.example.clinica.dao.jdbc;

import com.example.clinica.config.OracleConnectionFactory;
import com.example.clinica.dao.MedicoDao;
import com.example.clinica.domain.Medico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMedicoDao implements MedicoDao {

    @Override
    public Long salvar(Medico m) {
        String sql = "INSERT INTO MEDICOS (NOME, CRM) VALUES (?, ?)";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID"})) {
            ps.setString(1, m.getNome());
            ps.setString(2, m.getCrm());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar médico", e);
        }
    }

    @Override
    public void atualizar(Medico m) {
        String sql = "UPDATE MEDICOS SET NOME=?, CRM=? WHERE ID=?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNome());
            ps.setString(2, m.getCrm());
            ps.setLong(3, m.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar médico", e);
        }
    }

    @Override
    public void excluir(Long id) {
        String sql = "DELETE FROM MEDICOS WHERE ID=?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir médico", e);
        }
    }

    @Override
    public Optional<Medico> porId(Long id) {
        String sql = "SELECT ID, NOME, CRM FROM MEDICOS WHERE ID=?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Medico(
                        rs.getLong("ID"),
                        rs.getString("NOME"),
                        rs.getString("CRM")
                    ));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar médico", e);
        }
    }

    @Override
    public List<Medico> listar() {
        String sql = "SELECT ID, NOME, CRM FROM MEDICOS ORDER BY ID";
        List<Medico> list = new ArrayList<>();
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Medico(
                    rs.getLong("ID"),
                    rs.getString("NOME"),
                    rs.getString("CRM")
                ));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar médicos", e);
        }
    }
}
