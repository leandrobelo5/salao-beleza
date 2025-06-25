package model.dao;

import model.entities.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    public void inserir(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionarios (nome, cpf, telefone, cargo, salario, data_contratacao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = model.dao.ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setString(4, funcionario.getCargo());
            stmt.setBigDecimal(5, funcionario.getSalario());
            stmt.setDate(6, Date.valueOf(funcionario.getDataContratacao()));
            stmt.executeUpdate();
        }
    }

    public List<Funcionario> listarTodos() throws SQLException {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";

        try (Connection conn = model.dao.ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setTelefone(rs.getString("telefone"));
                f.setCargo(rs.getString("cargo"));
                f.setSalario(rs.getBigDecimal("salario"));
                f.setDataContratacao(rs.getDate("data_contratacao").toLocalDate());

                lista.add(f);
            }
        }

        return lista;
    }
}
