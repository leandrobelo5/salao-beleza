package model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario {
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String cargo;
    private BigDecimal salario;
    private LocalDate dataContratacao;

    public Funcionario() {}

    public Funcionario(int id, String nome, String cpf, String telefone, String cargo, BigDecimal salario, LocalDate dataContratacao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cargo = cargo;
        this.salario = salario;
        this.dataContratacao = dataContratacao;
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

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public BigDecimal getSalario() { return salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }

    public LocalDate getDataContratacao() { return dataContratacao; }
    public void setDataContratacao(LocalDate dataContratacao) { this.dataContratacao = dataContratacao; }

    @Override
    public String toString() {
        return "Funcionario{id=" + id + ", nome='" + nome + '\'' + ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' + ", cargo='" + cargo + '\'' +
                ", salario=" + salario + ", dataContratacao=" + dataContratacao + '}';
    }
}
