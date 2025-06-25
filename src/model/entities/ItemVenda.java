package model.entities;

import java.math.BigDecimal;

public class ItemVenda {
    private int produtoId;
    private int quantidade;
    private BigDecimal valor;

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
