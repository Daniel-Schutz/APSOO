package model;
public class Quarto {
    private String situacao;
    private String descricao;
    private String local;
    private String tipoQuarto;
    private double valor;
    private int idQuarto;

    // Construtor da classe
    public Quarto(String situacao, String descricao, String local, String tipoQuarto, double valor, int idQuarto) {
        this.situacao = situacao;
        this.descricao = descricao;
        this.local = local;
        this.tipoQuarto = tipoQuarto;
        this.valor = valor;
        this.idQuarto = idQuarto;
    }

    // Métodos getters e setters para os atributos
    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }
}
