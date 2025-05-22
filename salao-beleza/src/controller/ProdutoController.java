package controller;

import model.dao.ProdutoDAO;
import model.entities.Produto;

import java.sql.SQLException;
import java.util.List;

public class ProdutoController {
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public static void listarProdutos() {
    }

    public static void cadastrarViaPrompt() {
    }

    public void cadastrarProduto(Produto produto) throws SQLException {
        if (produto.getPreco().doubleValue() < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        produtoDAO.inserir(produto);
    }

    public void atualizarEstoque(int produtoId, int novaQuantidade) throws SQLException {
        produtoDAO.atualizarEstoque(produtoId, novaQuantidade);
    }

    public Produto buscarPorId(int id) throws SQLException {
        return produtoDAO.buscarPorId(id);
    }

    public void cadastrarProduto(String nome, int quantidade, double valor) {
    }
}
