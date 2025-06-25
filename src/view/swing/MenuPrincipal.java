package view.swing;

import controller.*; // seus controllers importados aqui
import javax.swing.*;
import java.sql.SQLException;

public class MenuPrincipal {

    public static void exibirMenu() throws SQLException {
        int opcao;
        do {
            String menu = """
                ==== MENU PRINCIPAL ====

                1. Cadastro
                2. Vendas
                   2.1 Iniciar venda
                   2.2 Listar vendas
                3. Consultas
                0. Sair
                """;

            String input = JOptionPane.showInputDialog(menu);
            if (input == null) break; // cancelado

            try {
                opcao = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opção inválida!");
                continue;
            }

            switch (opcao) {
                case 1 -> exibirMenuCadastro();
                case 2 -> exibirMenuVendas();
                case 3 -> exibirMenuConsultas();
                case 0 -> JOptionPane.showMessageDialog(null, "Encerrando o sistema.");
                default -> JOptionPane.showMessageDialog(null, "Opção inválida!");
            }

        } while (true);
    }

    private static void exibirMenuCadastro() {
        String[] opcoes = {"Cliente", "Funcionário", "Fornecedor", "Produto", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null,
                "Escolha o tipo de cadastro:",
                "Cadastro",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        switch (escolha) {
            case 0 -> ClienteController.cadastrarViaPrompt();
            case 1 -> FuncionarioController.cadastrarViaPrompt();
            case 2 -> FornecedorController.cadastrarViaPrompt();
            case 3 -> ProdutoController.cadastrarViaPrompt();
            default -> {} // Voltar ou fechar
        }
    }

    private static void exibirMenuVendas() {
        String[] opcoes = {"Iniciar venda", "Listar vendas", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null,
                "Menu de Vendas:",
                "Vendas",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        switch (escolha) {
            case 1 -> VendaController.listarVendas();
            default -> {} // Voltar
        }
    }

    private static void exibirMenuConsultas() throws SQLException {
        String[] opcoes = {"Listar Clientes", "Listar Produtos", "Listar Funcionários", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null,
                "Consultas disponíveis:",
                "Consultas",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]);

        switch (escolha) {
            case 0 -> {
                try {
                    ClienteController.listarClientes();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case 1 -> ProdutoController.listarProdutos();
            case 2 -> FuncionarioController.listarFuncionarios();
            default -> {}
        }
    }
}
