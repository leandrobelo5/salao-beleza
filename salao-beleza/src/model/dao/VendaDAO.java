package model.dao;

import model.entities.ItemVenda;
import model.entities.Produto;
import model.entities.Venda;

import java.sql.*;
import java.util.List;

public class VendaDAO {
    public void registrarVenda(Venda venda, List<ItemVenda> itens) throws SQLException {
        String sqlVenda = "INSERT INTO vendas (cliente_id, funcionario_id, data_hora, valor_total) VALUES (?, ?, ?, ?)";
        String sqlItem = "INSERT INTO itens_venda (venda_id, produto_id, quantidade, valor) VALUES (?, ?, ?, ?)";
        String sqlEstoque = "UPDATE produtos SET quantidade_estoque = quantidade_estoque - ? WHERE id = ?";

        try (Connection conn = model.dao.ConexaoDAO.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS)) {
                stmtVenda.setObject(1, venda.getClienteId() == null ? null : venda.getClienteId());
                stmtVenda.setInt(2, venda.getFuncionarioId());
                stmtVenda.setTimestamp(3, Timestamp.valueOf(venda.getDataHora()));
                stmtVenda.setBigDecimal(4, venda.getValorTotal());
                stmtVenda.executeUpdate();

                ResultSet generatedKeys = stmtVenda.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int vendaId = generatedKeys.getInt(1);

                    for (ItemVenda item : itens) {
                        try (PreparedStatement stmtItem = conn.prepareStatement(sqlItem)) {
                            stmtItem.setInt(1, vendaId);
                            stmtItem.setInt(2, item.getProdutoId());
                            stmtItem.setInt(3, item.getQuantidade());
                            stmtItem.setBigDecimal(4, item.getValor());
                            stmtItem.executeUpdate();
                        }

                        try (PreparedStatement stmtEstoque = conn.prepareStatement(sqlEstoque)) {
                            stmtEstoque.setInt(1, item.getQuantidade());
                            stmtEstoque.setInt(2, item.getProdutoId());
                            stmtEstoque.executeUpdate();
                        }
                    }

                    conn.commit();
                } else {
                    conn.rollback();
                    throw new SQLException("Falha ao obter ID da venda.");
                }
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }
}
