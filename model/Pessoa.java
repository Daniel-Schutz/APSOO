package model;

import java.sql.Date;

public abstract class Pessoa {

    protected static final String CLIENTE = "CLIENTE";
    protected static final String FUNCIONARIO = "FUNCIONARIO";
    protected static final String ADMIN = "ADMINISTRADOR";

    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String endereco;
    protected String tipo;

    public Pessoa(String nome, String cpf, String email, String senha, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Métodos abstratos que podem ser implementados pelas subclasses
    public abstract void realizarAcao();
    // ...

    public String getTelefone() {
        return null;
    }

    public String getSituacao() {
        return null;
    }

    public java.util.Date getDataContratacao() {
        return null;
    }

    public double getSalario() {
        return 0;
    }

    // Getter para o atributo "tipo"
    public String getTipo() {
        return tipo;
    }

    // Setter para o atributo "tipo"
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTelefone(String string) {
    }

    public void setDataContratacao(Date date) {
    }

    public void setSalario(double double1) {
    }

    public void setSituacao(String string) {
    }
}
