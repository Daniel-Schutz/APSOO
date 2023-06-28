package model;

import Persistence.*;
import java.sql.Date;
import java.util.Calendar;

import java.util.*;
import java.time.LocalDateTime;

// ESSA CLASSE SO DEVE SER UTILIZADA QUANDO O CLIENTE ENTRAR NO HOTEL, OU SEJA, SÓ SERÁ CRIADO HOSPEDAGEM NO BANCO QUANDO O CLIENTE ESTIVER HOSPEDADO.
// ASSIM EVITAMOS TER Q DELETAR HOSPEDAGEM NO BANCO EM CASO DE CANCELAMENTO DE RESERVA
public class Hospedagem {
    private LocalDateTime horaCheckOut;
    private LocalDateTime horaCheckIn;
    private Reserva reserva;
    private boolean statusCheckIn;
    private boolean statusCheckOut;

    // Construtor da classe
    public Hospedagem(Reserva reserva, LocalDateTime horaCheckIn, LocalDateTime horaCheckOut) {
        this.reserva = reserva;
        this.horaCheckOut = horaCheckOut;
        this.horaCheckIn = horaCheckIn;
        this.statusCheckIn = false;
        this.statusCheckOut = false;
    }

    // public static String realizarCheckOut(int codigoReserva, HospedagemDAO
    // hospedagemDAO, ReservaQuartoDAO reservaQuartoDAO, QuartoDAO quartoDAO){
    // //codigo deverá ser alterado para cpf

    // String mensagem = "Checkout não Realizado";
    // try{
    // Hospedagem hospedagem = hospedagemDAO.buscarHospedagem(codigoReserva);
    // hospedagem.statusCheckOut = true; //temos q buscar uma hospedagem antes para
    // depois realizar a atualização dessa, temos que corrigir BuscarHospedagem no
    // hospedagem dao
    // hospedagem.setHoraCheckOut(LocalDateTime.now());
    // if (hospedagem.getHoraCheckOut().getHour() > 12){

    // List<ReservaQuarto> reservaQuarto =
    // reservaQuartoDAO.buscarReservaQuarto(codigoReserva);
    // int i = 0;
    // float soma = 0;
    // while (i < reservaQuarto.size()){
    // int idQuarto = reservaQuarto.get(i).getIdQuarto();
    // Quarto quarto = quartoDAO.buscarQuarto(idQuarto);
    // soma = soma + quarto.getValor();
    // i++;
    // }
    // Float novaDiaria = soma;
    // return novaDiaria.toString();
    // }

    // mensagem = hospedagemDAO.atualizarHospedagem(hospedagem);
    // mensagem = "checkout realizado";

    // } catch (Exception e){
    // return "Error";
    // }

    // return mensagem;
    // }

    public static String realizarCheckIn(Reserva reserva) { // Mudar para cpf no futuro Precisarrá mudar a função
                                                            // buscarporCPF no dao
        try {
            Hospedagem newHospedagem = new Hospedagem(reserva, LocalDateTime.now(), null);
            if (LocalDateTime.now().getHour() < 16) {
                return "Não é permitido realizar Check in antes das 16 horas";
            }
            newHospedagem.setStatusCheckIn(true);
            return HospedagemDAO.criarHospedagem(newHospedagem);

        } catch (Exception e) {
            return "Não foi possível realizar checkin";
        }
    }

    // Métodos getters e setters para os atributos
    public LocalDateTime getHoraCheckOut() {
        return horaCheckOut;
    }

    public void setHoraCheckOut(LocalDateTime horaCheckOut) {
        this.horaCheckOut = horaCheckOut;
    }

    public String getHoraCheckIn() {
        return horaCheckIn.toString();
    }

    public void setHoraCheckIn(LocalDateTime horaCheckIn) {
        this.horaCheckIn = horaCheckIn;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Reserva getReserva() {
        return this.reserva;
    }

    public int getCodigoReserva() {
        return this.reserva.getCodigo();
    }

    public boolean getStatusCheckIn() {
        return this.statusCheckIn;
    }

    public void setStatusCheckIn(boolean checker) {
        this.statusCheckIn = checker;
    }
}