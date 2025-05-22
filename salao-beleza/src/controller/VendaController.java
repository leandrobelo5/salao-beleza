package controller;

import model.dao.VendaDAO;
import model.entities.Produto;
import model.entities.Venda;
import model.entities.ItemVenda;

import java.sql.SQLException;
import java.util.List;

public class VendaController {
    private VendaDAO vendaDAO = new VendaDAO();

    public static void iniciarVendaViaPrompt() {
    }

    public static void listarVendas() {
    }

    public void registrarVenda(Venda venda, List<ItemVenda> itens) throws SQLException {
        if (venda.getFuncionarioId() <= 0) {
            throw new IllegalArgumentException("Venda deve ter um funcionário associado.");
        }
        if (itens.isEmpty()) {
            throw new IllegalArgumentException("A venda precisa conter ao menos um item.");
        }
        vendaDAO.registrarVenda(venda, itens);
    }

    public void registrarVenda(Integer integer, int funcionarioId, List<ItemVenda> itens) {
    }
}
