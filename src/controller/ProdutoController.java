package controller;

import model.entities.Produto;
import model.dao.ProdutoDAO;
import java.sql.SQLException;
import java.util.List;
import java.math.BigDecimal;

public class ProdutoController {
    private static ProdutoDAO produtoDAO = new ProdutoDAO();

    public static List<Produto> listarProdutos() throws SQLException {
        return produtoDAO.listarTodos(); // ou o método correto do seu DAO
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

    public void cadastrarProduto(String nome, int quantidade, double valor) throws SQLException {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setQuantidadeEstoque(quantidade);
        produto.setPreco(new BigDecimal(valor));
        produto.setCategoria(""); // Categoria padrão ou vazia

        cadastrarProduto(produto);
    }
}
