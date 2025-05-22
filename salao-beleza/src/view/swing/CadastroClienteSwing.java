package view.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

        // Aqui você chamaria o DAO para salvar no banco, ex:
        // clienteDAO.save(new Cliente(nome, cpf, telefone, endereco, LocalDate.now()));

        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
        dispose();
    }
}
