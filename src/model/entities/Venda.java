package model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Venda {
    private int id;
    private Integer clienteId; // pode ser null
    private int funcionarioId;
    private LocalDateTime dataHora;
    private List<ItemVenda> itens;
    private BigDecimal valorTotal;

    public Venda() {}

    public Venda(int id, Integer clienteId, int funcionarioId, LocalDateTime dataHora, List<ItemVenda> itens, BigDecimal valorTotal) {
        this.id = id;
        this.clienteId = clienteId;
        this.funcionarioId = funcionarioId;
        this.dataHora = dataHora;
        this.itens = itens;
        this.valorTotal = valorTotal;
    }

    // Getters e setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Integer getClienteId() { return clienteId; }
    public void setClienteId(Integer clienteId) { this.clienteId = clienteId; }

    public int getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(int funcionarioId) { this.funcionarioId = funcionarioId; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public List<ItemVenda> getItens() { return itens; }
    public void setItens(List<ItemVenda> itens) { this.itens = itens; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    @Override
    public String toString() {
        return "Venda{id=" + id + ", clienteId=" + clienteId + ", funcionarioId=" + funcionarioId +
                ", dataHora=" + dataHora + ", itens=" + itens + ", valorTotal=" + valorTotal + '}';
    }
}
