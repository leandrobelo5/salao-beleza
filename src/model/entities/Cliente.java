package model.entities;

public class Cliente {
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private java.time.LocalDate dataCadastro;

    public Cliente() {}

    public Cliente(int id, String nome, String cpf, String telefone, String endereco, java.time.LocalDate dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
    }

    // Getters e setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public java.time.LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(java.time.LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }

    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nome='" + nome + '\'' + ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' + ", endereco='" + endereco + '\'' +
                ", dataCadastro=" + dataCadastro + '}';
    }
}
