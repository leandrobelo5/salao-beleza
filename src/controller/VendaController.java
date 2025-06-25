package controller;

import model.dao.VendaDAO;
import model.dao.ProdutoDAO;
import model.entities.Venda;
import model.entities.ItemVenda;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class VendaController {
    private VendaDAO vendaDAO = new VendaDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public static void listarVendas() {
        // implementação existente
    }

    public void registrarVenda(Integer clienteId, int funcionarioId, List<ItemVenda> itens) throws SQLException {
        if (funcionarioId <= 0) {
            throw new IllegalArgumentException("Venda deve ter um funcionário associado.");
        }
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("A venda precisa conter ao menos um item.");
        }

        // Calcular o valor total da venda buscando o preço do produto no banco
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ItemVenda item : itens) {
            // Obter o preço do produto do banco de dados
            BigDecimal precoProduto = produtoDAO.obterPrecoPorId(item.getProdutoId());

            // Definir o valor no item
            item.setValor(precoProduto);

            // Calcular o valor do item
            BigDecimal valorItem = precoProduto.multiply(new BigDecimal(item.getQuantidade()));
            valorTotal = valorTotal.add(valorItem);
        }

        // Criar objeto de venda
        Venda venda = new Venda();
        venda.setClienteId(clienteId);
        venda.setFuncionarioId(funcionarioId);
        venda.setDataHora(LocalDateTime.now());
        venda.setValorTotal(valorTotal);

        // Chamar o método que registra a venda no banco de dados
        vendaDAO.registrarVenda(venda, itens);
    }
}