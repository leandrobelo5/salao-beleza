package App;

import controller.*;
import model.entities.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);

    private static ClienteController clienteController = new ClienteController();
    private static FuncionarioController funcionarioController = new FuncionarioController();
    private static FornecedorController fornecedorController = new FornecedorController();
    private static ProdutoController produtoController = new ProdutoController();
    private static VendaController vendaController = new VendaController();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n--- Menu Salão de Beleza ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Funcionário");
            System.out.println("3. Cadastrar Fornecedor");
            System.out.println("4. Cadastrar Produto");
            System.out.println("5. Registrar Venda");
            System.out.println("6. Listar Clientes");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = Integer.parseInt(scanner.nextLine());

            try {
                switch (opcao) {
                    case 1: cadastrarCliente(); break;
                    case 2: cadastrarFuncionario(); break;
                    case 3: cadastrarFornecedor(); break;
                    case 4: cadastrarProduto(); break;
                    case 5: registrarVenda(); break;
                    case 6: listarClientes(); break;
                    case 7: System.exit(0);
                    default: System.out.println("Opção inválida.");
                }
            } catch (SQLException e) {
                System.out.println("Erro de banco de dados: " + e.getMessage());
                e.printStackTrace(); // Isso ajuda a depurar o problema
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void cadastrarCliente() throws SQLException {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        clienteController.cadastrarCliente(nome, cpf, telefone, endereco);
        System.out.println("Cliente cadastrado com sucesso.");
    }

    private static void cadastrarFuncionario() throws SQLException {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();

        System.out.print("Salário: ");
        double salario = Double.parseDouble(scanner.nextLine());

        funcionarioController.cadastrarFuncionario(nome, cpf, telefone, cargo, salario);
        System.out.println("Funcionário cadastrado com sucesso.");
    }

    private static void cadastrarFornecedor() throws SQLException {
        System.out.print("Nome da empresa: ");
        String nome = scanner.nextLine();

        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Tipo de produto: ");
        String tipoProduto = scanner.nextLine();

        fornecedorController.cadastrarFornecedor(nome, cnpj, telefone, tipoProduto);
        System.out.println("Fornecedor cadastrado com sucesso.");
    }

    private static void cadastrarProduto() throws SQLException {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());
        System.out.print("Valor: ");
        double valor = Double.parseDouble(scanner.nextLine());

        produtoController.cadastrarProduto(nome, quantidade, valor);
        System.out.println("Produto cadastrado com sucesso.");
    }

    private static void registrarVenda() throws SQLException {
        System.out.print("ID do Cliente (opcional, digite 0 se não quiser): ");
        int clienteId = Integer.parseInt(scanner.nextLine());
        System.out.print("ID do Funcionário: ");
        int funcionarioId = Integer.parseInt(scanner.nextLine());

        List<ItemVenda> itens = new ArrayList<>();
        while (true) {
            System.out.print("ID do Produto (0 para terminar): ");
            int produtoId = Integer.parseInt(scanner.nextLine());
            if (produtoId == 0) break;

            System.out.print("Quantidade: ");
            int quantidade = Integer.parseInt(scanner.nextLine());

            ItemVenda item = new ItemVenda();
            item.setProdutoId(produtoId);
            item.setQuantidade(quantidade);

            itens.add(item);
        }

        vendaController.registrarVenda(clienteId == 0 ? null : clienteId, funcionarioId, itens);
        System.out.println("Venda registrada com sucesso.");
    }

private static void listarClientes() {
    System.out.println("\n===== LISTAGEM DE CLIENTES =====");
    try {
        List<Cliente> clientes = ClienteController.listarClientes();
        
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados no sistema.");
        } else {
            System.out.println("CLIENTES CADASTRADOS:");
            System.out.println("--------------------------------------------");
            System.out.printf("%-5s | %-20s | %-15s | %-15s\n", "ID", "NOME", "CPF", "TELEFONE");
            System.out.println("--------------------------------------------");
            
            for (Cliente cliente : clientes) {
                System.out.printf("%-5d | %-20s | %-15s | %-15s\n", 
                    cliente.getId(), 
                    cliente.getNome(), 
                    cliente.getCpf(), 
                    cliente.getTelefone());
            }
            System.out.println("--------------------------------------------");
            System.out.println("Total de clientes: " + clientes.size());
        }
    } catch (SQLException e) {
        System.out.println("Erro ao listar clientes: " + e.getMessage());
    }
    
    System.out.println("\nPressione ENTER para continuar...");
    scanner.nextLine();
}
}