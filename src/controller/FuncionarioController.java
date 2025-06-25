package controller;

import model.dao.FuncionarioDAO;
import model.entities.Funcionario;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class FuncionarioController {
    private static FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public static void cadastrarViaPrompt() {
    }

    public void cadastrarFuncionario(Funcionario funcionario) throws SQLException {
        if (funcionario.getCpf() == null || funcionario.getCpf().isBlank()) {
            throw new IllegalArgumentException("CPF não pode estar em branco.");
        }
        funcionarioDAO.inserir(funcionario);
    }

    public static List<Funcionario> listarFuncionarios() throws SQLException {
        return funcionarioDAO.listarTodos();
    }

    public void cadastrarFuncionario(String nome, String cpf, String telefone, String cargo, double salario) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf); // Use o CPF recebido como parâmetro
        funcionario.setTelefone(telefone);
        funcionario.setCargo(cargo);
        funcionario.setSalario(new BigDecimal(salario));
        funcionario.setDataContratacao(LocalDate.now());

        cadastrarFuncionario(funcionario);
    }
}
