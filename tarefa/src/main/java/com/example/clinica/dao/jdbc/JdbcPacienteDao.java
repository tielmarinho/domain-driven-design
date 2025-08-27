
package com.example.clinica.dao.jdbc;

import com.example.clinica.config.OracleConnectionFactory;
import com.example.clinica.dao.PacienteDao;
import com.example.clinica.domain.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcPacienteDao implements PacienteDao {

    @Override
    public Long salvar(Paciente p) {
        String sql = "INSERT INTO PACIENTES (NOME, EMAIL) VALUES (?, ?)";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID"})) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar paciente", e);
        }
    }

    @Override
    public void atualizar(Paciente p) {
        String sql = "UPDATE PACIENTES SET NOME=?, EMAIL=? WHERE ID=?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.setLong(3, p.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar paciente", e);
        }
    }

    @Override
    public void excluir(Long id) {
        String sql = "DELETE FROM PACIENTES WHERE ID=?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir paciente", e);
        }
    }

    @Override
    public Optional<Paciente> porId(Long id) {
        String sql = "SELECT ID, NOME, EMAIL FROM PACIENTES WHERE ID=?";
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Paciente(
                        rs.getLong("ID"),
                        rs.getString("NOME"),
                        rs.getString("EMAIL")
                    ));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar paciente", e);
        }
    }

    @Override
    public List<Paciente> listar() {
        String sql = "SELECT ID, NOME, EMAIL FROM PACIENTES ORDER BY ID";
        List<Paciente> list = new ArrayList<>();
        try (Connection con = OracleConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Paciente(
                    rs.getLong("ID"),
                    rs.getString("NOME"),
                    rs.getString("EMAIL")
                ));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pacientes", e);
        }
    }
}
