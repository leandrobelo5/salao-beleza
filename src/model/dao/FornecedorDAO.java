package model.dao;

import model.entities.Fornecedor;
import model.entities.Fornecedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {
    public void inserir(Fornecedor fornecedor) throws SQLException {
        String sql = "INSERT INTO fornecedores (nome_empresa, cnpj, telefone, tipo_produto) VALUES (?, ?, ?, ?)";

        try (Connection conn = model.dao.ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getNomeEmpresa());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getTipoProduto());
            stmt.executeUpdate();
        }
    }

    public List<Fornecedor> listarTodos() throws SQLException {
        List<Fornecedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM fornecedores";

        try (Connection conn = model.dao.ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id"));
                f.setNomeEmpresa(rs.getString("nome_empresa"));
                f.setCnpj(rs.getString("cnpj"));
                f.setTelefone(rs.getString("telefone"));
                f.setTipoProduto(rs.getString("tipo_produto"));

                lista.add(f);
            }
        }

        return lista;
    }
}
