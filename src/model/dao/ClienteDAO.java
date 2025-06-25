package model.dao;

import model.entities.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public void inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, cpf, telefone, endereco, data_cadastro) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = model.dao.ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEndereco());
            stmt.setDate(5, Date.valueOf(cliente.getDataCadastro()));
            stmt.executeUpdate();
        }
    }

    public List<Cliente> listarTodos() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = model.dao.ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setDataCadastro(rs.getDate("data_cadastro").toLocalDate());

                lista.add(cliente);
            }
        }

        return lista;
    }
}
// Aqui você chamaria o DAO para salvar no banco, ex:
// clienteDAO.save(new Cliente(nome, cpf, telefone, endereco, LocalDate.now()));