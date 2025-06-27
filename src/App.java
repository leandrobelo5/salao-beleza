package App;

import controller.*;
import model.entities.*;



import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ExportadorPDF; // adicione este import

public class App {
    private static ClienteController clienteController = new ClienteController();
    private static FuncionarioController funcionarioController = new FuncionarioController();
    private static FornecedorController fornecedorController = new FornecedorController();
    private static ProdutoController produtoController = new ProdutoController();
    private static VendaController vendaController = new VendaController();

    // Esquema de cores
    private static final Color PRIMARY = new Color(44, 62, 80);
    private static final Color SECONDARY = new Color(52, 152, 219);
    private static final Color BACKGROUND = new Color(236, 240, 241);
    private static final Color BUTTON = new Color(41, 128, 185);
    private static final Color BUTTON_TEXT = Color.WHITE;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::criarMenuPrincipal);
    }

    private static void criarMenuPrincipal() {
        JFrame frame = new JFrame("Salão de Beleza");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND);

        // Menu lateral
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(PRIMARY);
        menuPanel.setPreferredSize(new Dimension(200, 0));
        menuPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbcMenu = new GridBagConstraints();
        gbcMenu.insets = new Insets(12, 10, 12, 10);
        gbcMenu.gridx = 0;
        gbcMenu.fill = GridBagConstraints.HORIZONTAL;

        JLabel menuTitle = new JLabel("Menu");
        menuTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        menuTitle.setForeground(BUTTON_TEXT);
        menuTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbcMenu.gridy = 0;
        menuPanel.add(menuTitle, gbcMenu);

        gbcMenu.gridy++;
        JButton btnCliente = criarBotaoMenu("Cadastrar Cliente");
        menuPanel.add(btnCliente, gbcMenu);
        gbcMenu.gridy++;
        JButton btnFuncionario = criarBotaoMenu("Cadastrar Funcionário");
        menuPanel.add(btnFuncionario, gbcMenu);
        gbcMenu.gridy++;
        JButton btnFornecedor = criarBotaoMenu("Cadastrar Fornecedor");
        menuPanel.add(btnFornecedor, gbcMenu);
        gbcMenu.gridy++;
        JButton btnProduto = criarBotaoMenu("Cadastrar Produto");
        menuPanel.add(btnProduto, gbcMenu);
        gbcMenu.gridy++;
        JButton btnVenda = criarBotaoMenu("Registrar Venda");
        menuPanel.add(btnVenda, gbcMenu);
        gbcMenu.gridy++;
        JButton btnListarClientes = criarBotaoMenu("Listar Clientes");
        menuPanel.add(btnListarClientes, gbcMenu);
        gbcMenu.gridy++;
        JButton btnRelatorios = criarBotaoMenu("Relatórios");
        menuPanel.add(btnRelatorios, gbcMenu);

        gbcMenu.gridy++;
        gbcMenu.weighty = 1;
        menuPanel.add(Box.createVerticalGlue(), gbcMenu);

        gbcMenu.gridy++;
        gbcMenu.weighty = 0;
        JButton btnSair = criarBotaoMenu("Sair");
        menuPanel.add(btnSair, gbcMenu);

        // CardLayout para telas
        JPanel cards = new JPanel(new CardLayout());
        cards.setBackground(BACKGROUND);

        cards.add(criarTelaBoasVindas(), "HOME");
        cards.add(criarTelaCadastroCliente(), "CAD_CLIENTE");
        cards.add(criarTelaCadastroFuncionario(), "CAD_FUNC");
        cards.add(criarTelaCadastroFornecedor(), "CAD_FORN");
        cards.add(criarTelaCadastroProduto(), "CAD_PROD");
        cards.add(criarTelaVenda(), "VENDA");
        cards.add(criarTelaListarClientes(), "LIST_CLIENTE");
        cards.add(criarTelaRelatorios(), "RELATORIOS");

        // Ações de navegação
        btnCliente.addActionListener(e -> mostrarCard(cards, "CAD_CLIENTE"));
        btnFuncionario.addActionListener(e -> mostrarCard(cards, "CAD_FUNC"));
        btnFornecedor.addActionListener(e -> mostrarCard(cards, "CAD_FORN"));
        btnProduto.addActionListener(e -> mostrarCard(cards, "CAD_PROD"));
        btnVenda.addActionListener(e -> mostrarCard(cards, "VENDA"));
        btnListarClientes.addActionListener(e -> {
            atualizarListaClientes(cards);
            mostrarCard(cards, "LIST_CLIENTE");
        });
        btnRelatorios.addActionListener(e -> mostrarCard(cards, "RELATORIOS"));
        btnSair.addActionListener(e -> System.exit(0));

        mainPanel.add(menuPanel, BorderLayout.WEST);
        mainPanel.add(cards, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    // Utilitário para criar botões do menu
    private static JButton criarBotaoMenu(String texto) {
        JButton btn = new JButton(texto);
        btn.setFocusPainted(false);
        btn.setBackground(BUTTON);
        btn.setForeground(BUTTON_TEXT);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return btn;
    }

    // Tela de boas-vindas
    private static JPanel criarTelaBoasVindas() {
        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND);
        JLabel label = new JLabel("Bem-vindo ao Sistema do Salão de Beleza!");
        label.setFont(new Font("Segoe UI", Font.BOLD, 22));
        label.setForeground(PRIMARY);
        panel.add(label);
        return panel;
    }

    // Tela de cadastro de cliente
    private static JPanel criarTelaCadastroCliente() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Cadastro de Cliente");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(SECONDARY);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        panel.add(new JLabel("Nome:"), gbc);
        JTextField nome = criarCampo();
        gbc.gridx = 1;
        panel.add(nome, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("CPF:"), gbc);
        JTextField cpf = criarCampo();
        gbc.gridx = 1;
        panel.add(cpf, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Telefone:"), gbc);
        JTextField telefone = criarCampo();
        gbc.gridx = 1;
        panel.add(telefone, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Endereço:"), gbc);
        JTextField endereco = criarCampo();
        gbc.gridx = 1;
        panel.add(endereco, gbc);

        JButton btnSalvar = criarBotaoAcao("Salvar");
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        btnSalvar.setPreferredSize(new Dimension(120, 35));
        panel.add(btnSalvar, gbc);

        btnSalvar.addActionListener(e -> {
            try {
                clienteController.cadastrarCliente(nome.getText(), cpf.getText(), telefone.getText(), endereco.getText());
                JOptionPane.showMessageDialog(panel, "Cliente cadastrado com sucesso!");
                nome.setText(""); cpf.setText(""); telefone.setText(""); endereco.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro: " + ex.getMessage());
            }
        });

        return panel;
    }

    // Tela de cadastro de funcionário
    private static JPanel criarTelaCadastroFuncionario() {
        JPanel panel = criarPainelFormulario("Cadastro de Funcionário");

        JTextField nome = criarCampo();
        JTextField cpf = criarCampo();
        JTextField telefone = criarCampo();
        JTextField cargo = criarCampo();
        JTextField salario = criarCampo();

        panel.add(new JLabel("Nome:"));
        panel.add(nome);
        panel.add(new JLabel("CPF:"));
        panel.add(cpf);
        panel.add(new JLabel("Telefone:"));
        panel.add(telefone);
        panel.add(new JLabel("Cargo:"));
        panel.add(cargo);
        panel.add(new JLabel("Salário:"));
        panel.add(salario);

        JButton btnSalvar = criarBotaoAcao("Salvar");
        panel.add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            try {
                funcionarioController.cadastrarFuncionario(
                        nome.getText(),
                        cpf.getText(),
                        telefone.getText(),
                        cargo.getText(),
                        Double.parseDouble(salario.getText())
                );
                JOptionPane.showMessageDialog(panel, "Funcionário cadastrado com sucesso!");
                nome.setText(""); cpf.setText(""); telefone.setText(""); cargo.setText(""); salario.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Erro: " + ex.getMessage());
            }
        });

        return panel;
    }

    // Tela de cadastro de fornecedor
    private static JPanel criarTelaCadastroFornecedor() {
        JPanel panel = criarPainelFormulario("Cadastro de Fornecedor");

        JTextField nome = criarCampo();
        JTextField cnpj = criarCampo();
        JTextField telefone = criarCampo();
        JTextField tipoProduto = criarCampo();

        panel.add(new JLabel("Nome da empresa:"));
        panel.add(nome);
        panel.add(new JLabel("CNPJ:"));
        panel.add(cnpj);
        panel.add(new JLabel("Telefone:"));
        panel.add(telefone);
        panel.add(new JLabel("Tipo de produto:"));
        panel.add(tipoProduto);

        JButton btnSalvar = criarBotaoAcao("Salvar");
        panel.add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            try {
                fornecedorController.cadastrarFornecedor(
                        nome.getText(),
                        cnpj.getText(),
                        telefone.getText(),
                        tipoProduto.getText()
                );
                JOptionPane.showMessageDialog(panel, "Fornecedor cadastrado com sucesso!");
                nome.setText(""); cnpj.setText(""); telefone.setText(""); tipoProduto.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Erro: " + ex.getMessage());
            }
        });

        return panel;
    }

    // Tela de cadastro de produto
    private static JPanel criarTelaCadastroProduto() {
        JPanel panel = criarPainelFormulario("Cadastro de Produto");

        JTextField nome = criarCampo();
        JTextField quantidade = criarCampo();
        JTextField valor = criarCampo();

        panel.add(new JLabel("Nome:"));
        panel.add(nome);
        panel.add(new JLabel("Quantidade:"));
        panel.add(quantidade);
        panel.add(new JLabel("Valor:"));
        panel.add(valor);

        JButton btnSalvar = criarBotaoAcao("Salvar");
        panel.add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            try {
                produtoController.cadastrarProduto(
                        nome.getText(),
                        Integer.parseInt(quantidade.getText()),
                        Double.parseDouble(valor.getText())
                );
                JOptionPane.showMessageDialog(panel, "Produto cadastrado com sucesso!");
                nome.setText(""); quantidade.setText(""); valor.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Erro: " + ex.getMessage());
            }
        });

        return panel;
    }

    // Tela de venda
    private static JPanel criarTelaVenda() {
        JPanel panel = criarPainelFormulario("Registrar Venda");

        JTextField clienteId = criarCampo();
        JTextField funcionarioId = criarCampo();

        panel.add(new JLabel("ID do Cliente (opcional, 0 se não quiser):"));
        panel.add(clienteId);
        panel.add(new JLabel("ID do Funcionário:"));
        panel.add(funcionarioId);

        // Painel para os botões de referência
        JPanel painelReferencias = new JPanel();
        painelReferencias.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        painelReferencias.setBackground(BACKGROUND);

        JButton btnVerProdutos = criarBotaoAcao("Ver Produtos");
        JButton btnVerFuncionarios = criarBotaoAcao("Ver Funcionários");
        JButton btnVerClientes = criarBotaoAcao("Ver Clientes");

        painelReferencias.add(btnVerProdutos);
        painelReferencias.add(btnVerFuncionarios);
        painelReferencias.add(btnVerClientes);

        panel.add(painelReferencias);

        DefaultListModel<String> itensModel = new DefaultListModel<>();
        JList<String> itensList = new JList<>(itensModel);

        JButton btnAdicionarItem = criarBotaoAcao("Adicionar Item");
        JButton btnRegistrar = criarBotaoAcao("Registrar Venda");

        panel.add(btnAdicionarItem);
        panel.add(new JScrollPane(itensList));
        panel.add(btnRegistrar);

        List<ItemVenda> itens = new ArrayList<>();

        btnAdicionarItem.addActionListener(e -> {
            JTextField produtoId = new JTextField();
            JTextField quantidade = new JTextField();
            Object[] itemFields = {
                    "ID do Produto:", produtoId,
                    "Quantidade:", quantidade
            };
            int option = JOptionPane.showConfirmDialog(panel, itemFields, "Adicionar Item", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    ItemVenda item = new ItemVenda();
                    item.setProdutoId(Integer.parseInt(produtoId.getText()));
                    item.setQuantidade(Integer.parseInt(quantidade.getText()));
                    itens.add(item);
                    itensModel.addElement("Produto ID: " + produtoId.getText() + " | Qtd: " + quantidade.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Erro: " + ex.getMessage());
                }
            }
        });

        btnRegistrar.addActionListener(e -> {
            try {
                vendaController.registrarVenda(
                        clienteId.getText().equals("0") ? null : Integer.parseInt(clienteId.getText()),
                        Integer.parseInt(funcionarioId.getText()),
                        itens
                );
                JOptionPane.showMessageDialog(panel, "Venda registrada com sucesso!");
                clienteId.setText(""); funcionarioId.setText(""); itens.clear(); itensModel.clear();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Erro: " + ex.getMessage());
            }
        });

        btnVerProdutos.addActionListener(e -> {
            try {
                // Se não existe listarProdutos, apenas avise:
                JOptionPane.showMessageDialog(panel, "Função de listar produtos não implementada.\nConsulte o administrador do sistema.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao listar produtos: " + ex.getMessage());
            }
        });

        btnVerFuncionarios.addActionListener(e -> {
            try {
                List<Funcionario> funcionarios = FuncionarioController.listarFuncionarios();
                StringBuilder sb = new StringBuilder("ID | Nome | Cargo\n");
                sb.append("----------------------------------------\n");
                for (Funcionario f : funcionarios) {
                    sb.append(f.getId()).append(" | ")
                            .append(f.getNome()).append(" | ")
                            .append(f.getCargo()).append("\n");
                }
                JOptionPane.showMessageDialog(panel, sb.length() > 30 ? sb.toString() : "Nenhum funcionário cadastrado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao listar funcionários: " + ex.getMessage());
            }
        });

        btnVerClientes.addActionListener(e -> {
            try {
                List<Cliente> clientes = ClienteController.listarClientes();
                StringBuilder sb = new StringBuilder("ID | Nome | CPF\n");
                sb.append("----------------------------------------\n");
                for (Cliente c : clientes) {
                    sb.append(c.getId()).append(" | ")
                            .append(c.getNome()).append(" | ")
                            .append(c.getCpf()).append("\n");
                }
                JOptionPane.showMessageDialog(panel, sb.length() > 30 ? sb.toString() : "Nenhum cliente cadastrado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao listar clientes: " + ex.getMessage());
            }
        });

        return panel;
    }

    // Tela de listagem de clientes
    private static JPanel criarTelaListarClientes() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND);

        JLabel titulo = new JLabel("Clientes Cadastrados");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(PRIMARY);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        textArea.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        panel.putClientProperty("textArea", textArea); // Para atualizar depois

        return panel;
    }

    // Tela de relatórios
    private static JPanel criarTelaRelatorios() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND);

        JTabbedPane abas = new JTabbedPane();

        // Relatório de Vendas
        JTextArea vendasArea = new JTextArea();
        vendasArea.setEditable(false);
        vendasArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane vendasScroll = new JScrollPane(vendasArea);
        JButton btnAtualizarVendas = criarBotaoAcao("Atualizar");
        btnAtualizarVendas.addActionListener(e -> {
            try {
                List<Venda> vendas = vendaController.listarVendas();
                StringBuilder sb = new StringBuilder("ID | ClienteID | FuncionárioID | Total\n");
                sb.append("-------------------------------------------------------------\n");
                for (Venda v : vendas) {
                    sb.append(v.getId()).append(" | ")
                            .append(v.getClienteId()).append(" | ")
                            .append(v.getFuncionarioId()).append(" | ")
                            .append(v.getValorTotal()).append("\n");
                }
                vendasArea.setText(sb.length() > 40 ? sb.toString() : "Nenhuma venda encontrada.");
            } catch (Exception ex) {
                vendasArea.setText("Erro ao listar vendas: " + ex.getMessage());
            }
        });
        JPanel vendasPanel = new JPanel(new BorderLayout());
        vendasPanel.add(vendasScroll, BorderLayout.CENTER);
        vendasPanel.add(btnAtualizarVendas, BorderLayout.SOUTH);

        // Botão para exportar relatório de vendas
        JButton btnExportarVendas = criarBotaoAcao("Exportar PDF");
        btnExportarVendas.addActionListener(e -> {
            try {
                List<Venda> vendas = vendaController.listarVendas();
                StringBuilder sb = new StringBuilder("ID | ClienteID | FuncionárioID | Total\n");
                sb.append("-------------------------------------------------------------\n");
                for (Venda v : vendas) {
                    sb.append(v.getId()).append(" | ")
                            .append(v.getClienteId()).append(" | ")
                            .append(v.getFuncionarioId()).append(" | ")
                            .append(v.getValorTotal()).append("\n");
                }
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Salvar Relatório de Vendas em PDF");
                if (fileChooser.showSaveDialog(panel) == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getAbsolutePath();
                    if (!path.toLowerCase().endsWith(".pdf")) path += ".pdf";
                    ExportadorPDF.exportarTextoParaPDF(sb.toString(), path, "Relatório de Vendas");
                    JOptionPane.showMessageDialog(panel, "Relatório exportado para PDF com sucesso!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao exportar PDF: " + ex.getMessage());
            }
        });
        vendasPanel.add(btnExportarVendas, BorderLayout.NORTH);

        // Relatório de Vendas por Cliente
        JTextField clienteIdField = criarCampo();
        JTextArea vendasClienteArea = new JTextArea();
        vendasClienteArea.setEditable(false);
        vendasClienteArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane vendasClienteScroll = new JScrollPane(vendasClienteArea);
        JButton btnBuscarCliente = criarBotaoAcao("Buscar");
        JButton btnExportarVendasCliente = criarBotaoAcao("Exportar PDF");

        btnBuscarCliente.addActionListener(e -> {
            try {
                int clienteId = Integer.parseInt(clienteIdField.getText());
                List<Venda> vendas = vendaController.listarVendasPorCliente(clienteId);
                StringBuilder sb = new StringBuilder("ID | FuncionárioID | Total\n");
                sb.append("-------------------------------------------------------------\n");
                for (Venda v : vendas) {
                    sb.append(v.getId()).append(" | ")
                            .append(v.getFuncionarioId()).append(" | ")
                            .append(v.getValorTotal()).append("\n");
                }
                vendasClienteArea.setText(sb.length() > 40 ? sb.toString() : "Nenhuma venda encontrada para este cliente.");
            } catch (Exception ex) {
                vendasClienteArea.setText("Erro: " + ex.getMessage());
            }
        });

        btnExportarVendasCliente.addActionListener(e -> {
            try {
                int clienteId = Integer.parseInt(clienteIdField.getText());
                List<Venda> vendas = vendaController.listarVendasPorCliente(clienteId);
                StringBuilder sb = new StringBuilder("ID | FuncionárioID | Total\n");
                sb.append("-------------------------------------------------------------\n");
                for (Venda v : vendas) {
                    sb.append(v.getId()).append(" | ")
                            .append(v.getFuncionarioId()).append(" | ")
                            .append(v.getValorTotal()).append("\n");
                }
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Salvar Relatório de Vendas do Cliente em PDF");
                if (fileChooser.showSaveDialog(panel) == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getAbsolutePath();
                    if (!path.toLowerCase().endsWith(".pdf")) path += ".pdf";
                    ExportadorPDF.exportarTextoParaPDF(sb.toString(), path, "Relatório de Vendas por Cliente");
                    JOptionPane.showMessageDialog(panel, "Relatório exportado para PDF com sucesso!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao exportar PDF: " + ex.getMessage());
            }
        });

        JPanel vendasClientePanel = new JPanel(new BorderLayout());
        JPanel buscaClientePanel = new JPanel(new FlowLayout());
        buscaClientePanel.add(new JLabel("ID do Cliente:"));
        buscaClientePanel.add(clienteIdField);
        buscaClientePanel.add(btnBuscarCliente);
        buscaClientePanel.add(btnExportarVendasCliente); // <-- Adiciona o botão de exportar
        vendasClientePanel.add(buscaClientePanel, BorderLayout.NORTH);
        vendasClientePanel.add(vendasClienteScroll, BorderLayout.CENTER);

        // Relatório de Estoque
        JTextArea estoqueArea = new JTextArea();
        estoqueArea.setEditable(false);
        estoqueArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane estoqueScroll = new JScrollPane(estoqueArea);
        JButton btnAtualizarEstoque = criarBotaoAcao("Atualizar");
        JButton btnExportarEstoque = criarBotaoAcao("Exportar PDF");

        btnAtualizarEstoque.addActionListener(e -> {
            try {
                List<Produto> produtos = produtoController.listarProdutos();
                StringBuilder sb = new StringBuilder("ID | Nome | Estoque | Preço\n");
                sb.append("-------------------------------------------------------------\n");
                for (Produto p : produtos) {
                    sb.append(p.getId()).append(" | ")
                            .append(p.getNome()).append(" | ")
                            .append(p.getQuantidadeEstoque()).append(" | ")
                            .append(p.getPreco()).append("\n");
                }
                estoqueArea.setText(sb.length() > 40 ? sb.toString() : "Nenhum produto cadastrado.");
            } catch (Exception ex) {
                estoqueArea.setText("Erro ao listar estoque: " + ex.getMessage());
            }
        });

        btnExportarEstoque.addActionListener(e -> {
            try {
                List<Produto> produtos = produtoController.listarProdutos();
                StringBuilder sb = new StringBuilder("ID | Nome | Estoque | Preço\n");
                sb.append("-------------------------------------------------------------\n");
                for (Produto p : produtos) {
                    sb.append(p.getId()).append(" | ")
                            .append(p.getNome()).append(" | ")
                            .append(p.getQuantidadeEstoque()).append(" | ")
                            .append(p.getPreco()).append("\n");
                }
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Salvar Relatório de Estoque em PDF");
                if (fileChooser.showSaveDialog(panel) == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getAbsolutePath();
                    if (!path.toLowerCase().endsWith(".pdf")) path += ".pdf";
                    ExportadorPDF.exportarTextoParaPDF(sb.toString(), path, "Relatório de Estoque");
                    JOptionPane.showMessageDialog(panel, "Relatório exportado para PDF com sucesso!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao exportar PDF: " + ex.getMessage());
            }
        });

        JPanel estoquePanel = new JPanel(new BorderLayout());
        estoquePanel.add(estoqueScroll, BorderLayout.CENTER);

        JPanel botoesEstoque = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoesEstoque.add(btnAtualizarEstoque);
        botoesEstoque.add(btnExportarEstoque);
        estoquePanel.add(botoesEstoque, BorderLayout.SOUTH);

        // Adiciona abas
        abas.addTab("Vendas", vendasPanel);
        abas.addTab("Vendas por Cliente", vendasClientePanel);
        abas.addTab("Estoque", estoquePanel);

        panel.add(abas, BorderLayout.CENTER);
        return panel;
    }

    // Atualiza a lista de clientes na tela de listagem
    private static void atualizarListaClientes(JPanel cards) {
        // Percorre os componentes do CardLayout para encontrar o painel correto
        for (Component comp : cards.getComponents()) {
            if (comp.isVisible() && comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                JTextArea textArea = (JTextArea) panel.getClientProperty("textArea");
                if (textArea != null) {
                    try {
                        List<Cliente> clientes = ClienteController.listarClientes();
                        if (clientes.isEmpty()) {
                            textArea.setText("Não há clientes cadastrados.");
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append(String.format("%-5s | %-20s | %-15s | %-15s\n", "ID", "NOME", "CPF", "TELEFONE"));
                            sb.append("-------------------------------------------------------------\n");
                            for (Cliente cliente : clientes) {
                                sb.append(String.format("%-5d | %-20s | %-15s | %-15s\n",
                                        cliente.getId(),
                                        cliente.getNome(),
                                        cliente.getCpf(),
                                        cliente.getTelefone()));
                            }
                            sb.append("-------------------------------------------------------------\n");
                            sb.append("Total de clientes: ").append(clientes.size());
                            textArea.setText(sb.toString());
                        }
                    } catch (SQLException e) {
                        textArea.setText("Erro ao listar clientes: " + e.getMessage());
                    }
                    break;
                }
            }
        }
    }

    // Utilitários de UI
    private static JPanel criarPainelFormulario(String titulo) {
        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel(titulo);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(SECONDARY);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(label);
        panel.add(Box.createVerticalStrut(20));
        return panel;
    }

    private static JTextField criarCampo() {
        JTextField field = new JTextField(20);
        field.setMaximumSize(new Dimension(300, 30));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return field;
    }

    private static JButton criarBotaoAcao(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(SECONDARY);
        btn.setForeground(BUTTON_TEXT);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        return btn;
    }

    private static void mostrarCard(JPanel cards, String nome) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, nome);
    }
}