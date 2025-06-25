package view.swing;

import controller.ClienteController;
import model.entities.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class CadastroClienteSwing extends JDialog {

    private JTextField tfNome;
    private JTextField tfCpf;
    private JTextField tfTelefone;
    private JTextField tfEndereco;

    public CadastroClienteSwing(JFrame parent) {
        super(parent, "Cadastro de Cliente", true);
        setSize(350, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel painelCampos = new JPanel(new GridLayout(5, 2, 5, 5));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelCampos.add(new JLabel("Nome:"));
        tfNome = new JTextField();
        painelCampos.add(tfNome);

        painelCampos.add(new JLabel("CPF:"));
        tfCpf = new JTextField();
        painelCampos.add(tfCpf);

        painelCampos.add(new JLabel("Telefone:"));
        tfTelefone = new JTextField();
        painelCampos.add(tfTelefone);

        painelCampos.add(new JLabel("Endereço:"));
        tfEndereco = new JTextField();
        painelCampos.add(tfEndereco);

        painelCampos.add(new JLabel("Data Cadastro:"));
        JLabel lblData = new JLabel(java.time.LocalDate.now().toString());
        painelCampos.add(lblData);

        add(painelCampos, BorderLayout.CENTER);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarCliente());

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnSalvar);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void salvarCliente() {
        String nome = tfNome.getText().trim();
        String cpf = tfCpf.getText().trim();
        String telefone = tfTelefone.getText().trim();
        String endereco = tfEndereco.getText().trim();

        if (nome.isEmpty() || cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome e CPF são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Criar o objeto Cliente
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setCpf(cpf);
            cliente.setTelefone(telefone);
            cliente.setEndereco(endereco);
            cliente.setDataCadastro(java.time.LocalDate.now());

            // Chamar o método estático do controller
            ClienteController.cadastrarCliente(cliente);

            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}