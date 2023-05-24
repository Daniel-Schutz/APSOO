package model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import dao.*;

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
        this.reservaDAO.criarReserva(this); //argumentos error
        System.out.println("Registrada a reserva!!");
    }

    public static Reserva buscarReserva(ReservaDAO reservaDAO,int codigo){
        
        //a função buscarReserva está declarando que pode lançar uma exceção do tipo SQLException
        //por isso precisa de um try catch aqui
        try {
            Reserva reserva;
            reserva = reservaDAO.buscarReserva(codigo);
            return reserva;
            
        } catch (Exception e) {
            return null;
        }
        
    }

    public static Collection<String> buscarReservaPorCpf(String cpf, ReservaDAO reservaDAO){

        if (reservaDAO.buscarReservaPorCpf(cpf) != null){
            return reservaDAO.buscarReservaPorCpf(cpf);
        }
        return null;
    }
    public static List<Reserva> buscarTodasReservas(ReservaDAO reservaDAO){
        List<Reserva> reservas = reservaDAO.listarReservas();
        return reservas;
    }

    public String atualizarReserva(){
        this.reservaDAO.atualizarReserva(this);
        return "Reserva Atualizada";
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
