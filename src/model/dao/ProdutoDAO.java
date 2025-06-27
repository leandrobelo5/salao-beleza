package model.dao;

import model.entities.Produto;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public void inserir(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (nome, preco, quantidade_estoque, categoria) VALUES (?, ?, ?, ?)";

        try (Connection conn = model.dao.ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setBigDecimal(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidadeEstoque());
            stmt.setString(4, produto.getCategoria());
            stmt.executeUpdate();
        }
    }

    public void atualizarEstoque(int produtoId, int novaQuantidade) throws SQLException {
        String sql = "UPDATE produtos SET quantidade_estoque = ? WHERE id = ?";

        try (Connection conn = model.dao.ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, produtoId);
            stmt.executeUpdate();
        }
    }

    public Produto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM produtos WHERE id = ?";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getBigDecimal("preco"));
                produto.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                if (rs.getString("categoria") != null) {
                    produto.setCategoria(rs.getString("categoria"));
                }
                return produto;
            }
            return null;
        }
    }

    public BigDecimal obterPrecoPorId(int id) throws SQLException {
        Produto produto = buscarPorId(id);
        if (produto != null) {
            return produto.getPreco();
        }
        throw new SQLException("Produto não encontrado com ID: " + id);
    }

    public List<Produto> listarTodos() throws SQLException {
        String sql = "SELECT * FROM produtos";
        List<Produto> produtos = new ArrayList<>();

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getBigDecimal("preco"));
                produto.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                if (rs.getString("categoria") != null) {
                    produto.setCategoria(rs.getString("categoria"));
                }
                produtos.add(produto);
            }
        }

        return produtos;
    }
}
