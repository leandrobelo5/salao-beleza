package controller;

import model.dao.FornecedorDAO;
import model.entities.Fornecedor;

import java.sql.SQLException;
import java.util.List;

public class FornecedorController {
    private FornecedorDAO fornecedorDAO = new FornecedorDAO();

    public static void cadastrarViaPrompt() {
    }

    public void cadastrarFornecedor(Fornecedor fornecedor) throws SQLException {
        if (fornecedor.getCnpj() == null || fornecedor.getCnpj().isBlank()) {
            throw new IllegalArgumentException("CNPJ não pode estar em branco.");
        }
        fornecedorDAO.inserir(fornecedor);
    }

    public List<Fornecedor> listarFornecedores() throws SQLException {
        return fornecedorDAO.listarTodos();
    }

    public void cadastrarFornecedor(String nome, String cnpj, String telefone, String tipoProduto) throws SQLException {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNomeEmpresa(nome);
        fornecedor.setCnpj(cnpj); // Use o CNPJ recebido como parâmetro
        fornecedor.setTelefone(telefone);
        fornecedor.setTipoProduto(tipoProduto);

        cadastrarFornecedor(fornecedor);
    }
}
