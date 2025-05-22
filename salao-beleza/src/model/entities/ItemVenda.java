package model.entities;

import java.math.BigDecimal;

public class ItemVenda {
    private int id;
    private int produtoId;
    private int quantidade;
    private BigDecimal valor;

    public ItemVenda() {}

    public ItemVenda(int id, int produtoId, int quantidade, BigDecimal valor) {
        this.id = id;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    // Getters e setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProdutoId() { return produtoId; }
    public void setProdutoId(int produtoId) { this.produtoId = produtoId; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    @Override
    public String toString() {
        return "ItemVenda{id=" + id + ", produtoId=" + produtoId + ", quantidade=" + quantidade + ", valor=" + valor + '}';
    }
}
