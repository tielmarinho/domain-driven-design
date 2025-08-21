package com.example.clinic.infra.dao;

import com.example.clinic.domain.model.Paciente;
import com.example.clinic.infra.db.OracleConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao {
     

    public Paciente buscarPorId(long id) {
        String sql = "SELECT id, nome, email FROM pacientes WHERE id = ?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Paciente(rs.getLong("id"), rs.getString("nome"), rs.getString("email"));
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Paciente> listarTodos() {
        String sql = "SELECT id, nome, email FROM pacientes";
        List<Paciente> lista = new ArrayList<>();
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Paciente(rs.getLong("id"), rs.getString("nome"), rs.getString("email")));
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Paciente p) {
        String sql = "UPDATE pacientes SET nome=?, email=? WHERE id=?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.setLong(3, p.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(long id) {
        String sql = "DELETE FROM pacientes WHERE id=?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
