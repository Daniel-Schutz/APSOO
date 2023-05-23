package model;

import DAO.*;
import java.util.Date;

public class Reserva {
    private int codigo;
    private Date data;
    private int diasEstadia;
    private String tipoPagamento;
    private String situacao;
    private ReservaDAO reservaDAO;

    // Construtor da classe
    public Reserva(int codigo, Date data, int diasEstadia, String tipoPagamento, String situacao, ReservaDAO reservaDAO) {
        this.codigo = codigo;
        this.data = data;
        this.diasEstadia = diasEstadia;
        this.tipoPagamento = tipoPagamento;
        this.situacao = situacao;
        this.reservaDAO = reservaDAO;
    }

    // Métodos getters e setters para os atributos

    public void registrarReserva(){
        this.reservaDAO.criarReserva(this);
        System.out.println("Registrada a reserva!!");
    }

    public Reserva buscarReserva(int codigo){
        Reserva reserva;
        reserva = reserva.reservaDAO.buscarReserva(codigo);
        return reserva;
    }

    public buscarTodasReservas(){

    }

    public atualizarReserva(){

    }

    public deletarReserva(){

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getDiasEstadia() {
        return diasEstadia;
    }

    public void setDiasEstadia(int diasEstadia) {
        this.diasEstadia = diasEstadia;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
