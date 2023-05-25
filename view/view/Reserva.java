package view;

public class Reserva {
    private String dataEntrada;
    private String cpf;
    private String tempoEstadia;
    private String quantQuartos;
    private String formaPagamento;

    public Reserva(String dataEntrada, String cpf, String tempoEstadia, String quantQuartos, String formaPagamento) {
        this.dataEntrada = dataEntrada;
        this.cpf = cpf;
        this.tempoEstadia = tempoEstadia;
        this.quantQuartos = quantQuartos;
        this.formaPagamento = formaPagamento;
    }

    public String getdataEntrada() {
        return dataEntrada;
    }

    public String getCpf() {
        return cpf;
    }

    public String gettempoEstadia() {
        return tempoEstadia;
    }

    public String getquantQuartos() {
        return quantQuartos;
    }

    public String getformaPagamento() {
        return formaPagamento;
    }

    public void setdataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void settempoEstadia(String tempoEstadia) {
        this.tempoEstadia = tempoEstadia;
    }

    public void setquantQuartos(String quantQuartos) {
        this.quantQuartos = quantQuartos;
    }

    public void setformaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

}
