package controller;

import model.dao.FuncionarioDAO;
import model.dao.FuncionarioDAO;
import model.entities.Funcionario;

import java.sql.SQLException;
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

    public void cadastrarFuncionario(String nome, String cargo, double salario) {

    }
}
