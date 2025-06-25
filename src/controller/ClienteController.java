package controller;

import model.dao.ClienteDAO;
import model.entities.Cliente;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

// Modificar o ClienteController.java para manter consistência
public class ClienteController {
    private static ClienteDAO clienteDAO = new ClienteDAO();

    // Tornar cadastrarCliente estático para consistência
    public static void cadastrarCliente(Cliente cliente) throws SQLException {
        if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
            throw new IllegalArgumentException("CPF não pode estar em branco.");
        }
        clienteDAO.inserir(cliente);
    }

    // Método já está correto
    public static List<Cliente> listarClientes() throws SQLException {
        return clienteDAO.listarTodos();
    }

    // Método para cadastro via App.java
    public static void cadastrarCliente(String nome, String cpf, String telefone, String endereco) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);
        cliente.setDataCadastro(java.time.LocalDate.now());
        cadastrarCliente(cliente);
    }
    public static void cadastrarViaPrompt() {
        try {
            String nome = JOptionPane.showInputDialog("Nome do cliente:");
            if (nome == null || nome.trim().isEmpty()) return; // Cancelado ou vazio

            String cpf = JOptionPane.showInputDialog("CPF do cliente:");
            if (cpf == null || cpf.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "CPF é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String telefone = JOptionPane.showInputDialog("Telefone do cliente:");
            if (telefone == null) return; // Cancelado

            String endereco = JOptionPane.showInputDialog("Endereço do cliente:");
            if (endereco == null) return; // Cancelado

            // Criar e salvar o cliente
            cadastrarCliente(nome.trim(), cpf.trim(), telefone.trim(), endereco.trim());

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao cadastrar cliente: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Dados Inválidos", JOptionPane.WARNING_MESSAGE);
        }
    }

}



