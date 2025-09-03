package br.com.fiap.dao.jdbc;

import br.com.fiap.domain.Prato;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.interfaces.IPrato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PratoJdbcDAO implements IPrato {
    private final ConnectionFactory cf;

    public PratoJdbcDAO(ConnectionFactory cf) { this.cf = cf; }

    @Override
    public Long salvar(Prato p) {
        String sql = "INSERT INTO pratos (nome, preco) VALUES (?, ?)";
        try (Connection c = cf.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, new String[]{"ID"})) {
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPreco());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getLong(1) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar prato", e);
        }
    }

    @Override
    public Prato buscarPorId(Long id) {
        String sql = "SELECT id, nome, preco FROM pratos WHERE id = ?";
        try (Connection c = cf.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Prato(rs.getLong("id"), rs.getString("nome"), rs.getDouble("preco"));
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar prato", e);
        }
    }

    @Override
    public List<Prato> listarTodos() {
        String sql = "SELECT id, nome, preco FROM pratos ORDER BY id";
        List<Prato> list = new ArrayList<>();
        try (Connection c = cf.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Prato(rs.getLong("id"), rs.getString("nome"), rs.getDouble("preco")));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pratos", e);
        }
    }

    @Override
    public void deletar(Long id) {
        String sql = "DELETE FROM pratos WHERE id = ?";
        try (Connection c = cf.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar prato", e);
        }
    }
}