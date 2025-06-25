package model.entities;

public class Fornecedor {
    private int id;
    private String nomeEmpresa;
    private String cnpj;
    private String telefone;
    private String tipoProduto;

    public Fornecedor() {}

    public Fornecedor(int id, String nomeEmpresa, String cnpj, String telefone, String tipoProduto) {
        this.id = id;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.tipoProduto = tipoProduto;
    }

    // Getters e setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getTipoProduto() { return tipoProduto; }
    public void setTipoProduto(String tipoProduto) { this.tipoProduto = tipoProduto; }

    @Override
    public String toString() {
        return "Fornecedor{id=" + id + ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", cnpj='" + cnpj + '\'' + ", telefone='" + telefone + '\'' +
                ", tipoProduto='" + tipoProduto + '\'' + '}';
    }
}
