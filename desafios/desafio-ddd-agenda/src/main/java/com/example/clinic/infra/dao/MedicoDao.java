package com.example.clinic.infra.dao;

import com.example.clinic.domain.model.Medico;
import com.example.clinic.infra.db.OracleConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDao {
    public Long salvar(Medico m) {
        String sql = "INSERT INTO medicos (nome, crm) VALUES (?, ?)";
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
            throw new RuntimeException(e);
        }
    }

    public Medico buscarPorId(long id) {
        String sql = "SELECT id, nome, crm FROM medicos WHERE id = ?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Medico(rs.getLong("id"), rs.getString("nome"), rs.getString("crm"));
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Medico> listarTodos() {
        String sql = "SELECT id, nome, crm FROM medicos";
        List<Medico> lista = new ArrayList<>();
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Medico(rs.getLong("id"), rs.getString("nome"), rs.getString("crm")));
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Medico m) {
        String sql = "UPDATE medicos SET nome=?, crm=? WHERE id=?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNome());
            ps.setString(2, m.getCrm());
            ps.setLong(3, m.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(long id) {
        String sql = "DELETE FROM medicos WHERE id=?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
