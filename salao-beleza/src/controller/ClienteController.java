package controller;

import model.dao.ClienteDAO;
import model.entities.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {
    private static ClienteDAO clienteDAO = new ClienteDAO();

    public static void cadastrarViaPrompt() {

    }

    public void cadastrarCliente(Cliente cliente) throws SQLException {
        if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
            throw new IllegalArgumentException("CPF não pode estar em branco.");
        }
        clienteDAO.inserir(cliente);
    }

    public static List<Cliente> listarClientes() throws SQLException {
        return clienteDAO.listarTodos();
    }

    public void cadastrarCliente(String nome, String cpf, String telefone, String endereco) {
    }
}
