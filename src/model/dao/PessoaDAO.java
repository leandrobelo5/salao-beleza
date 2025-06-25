package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.ConexaoDAO;

public class PessoaDAO {

    public boolean existeCpf(String cpf) {
        String sql = "SELECT COUNT(*) FROM clientes WHERE cpf = ? " +
                "UNION ALL " +
                "SELECT COUNT(*) FROM funcionarios WHERE cpf = ?";

        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setString(2, cpf);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
