package model;

import java.sql.Date;
import java.util.Calendar;

import DAO.HospedagemDAO;
import DAO.QuartoDAO;
import DAO.ReservaQuartoDAO;

import java.util.*;
import java.time.LocalTime;
// ESSA CLASSE SO DEVE SER UTILIZADA QUANDO O CLIENTE ENTRAR NO HOTEL, OU SEJA, SÓ SERÁ CRIADO HOSPEDAGEM NO BANCO QUANDO O CLIENTE ESTIVER HOSPEDADO.
// ASSIM EVITAMOS TER Q DELETAR HOSPEDAGEM NO BANCO EM CASO DE CANCELAMENTO DE RESERVA
public class Hospedagem {
    private LocalTime horaCheckOut;
    private LocalTime horaCheckIn;
    private int codigoReserva;
    private boolean confirmarCheckIn;
    private boolean confirmarCheckOut;

    // Construtor da classe
    public Hospedagem(int codigo, LocalTime horaCheckIn, LocalTime horaCheckOut) {
        this.codigoReserva = codigo;
        this.horaCheckOut = horaCheckOut;
        this.horaCheckIn = horaCheckIn;
        this.confirmarCheckIn = false;
        this.confirmarCheckOut = false;
    }

    // public String realizarCheckIn(int diasEstadia){
    //     // this.confirmarCheckIn = true;
    //     // long tempoAtual = System.currentTimeMillis();
    //     // this.horaCheckIn = new Date(tempoAtual);
    //     // Calendar cal = Calendar.getInstance();
    //     // cal.setTime(this.getHoraCheckIn()); 
    //     // cal.add(Calendar.DAY_OF_MONTH, diasEstadia);
    //     // this.setHoraCheckOut(cal.getTime()); // Obtém a hora do checkout como objeto Date
        
    //     return "Checkin realizado!";
    // }

    public String realizarCheckOut(int codigoReserva, HospedagemDAO hospedagemDAO, ReservaQuartoDAO reservaQuartoDAO, QuartoDAO quartoDAO){
        this.confirmarCheckOut = true;
        this.setHoraCheckOut(LocalTime.now());
        this.setCodigoReserva(codigoReserva);
        String mensagem = "Checkout não Realizado";
        if (this.getHoraCheckOut().getHour() > 12){
            try{
                List<ReservaQuarto> reservaQuarto = reservaQuartoDAO.buscarReservaQuarto(codigoReserva);
                int i = 0;
                float soma = 0;
                while (i < reservaQuarto.size()){
                    int idQuarto = reservaQuarto.get(i).getIdQuarto();
                    Quarto quarto = quartoDAO.buscarQuarto(idQuarto);
                    soma = soma + quarto.getValor();
                    i++;
                }
                Float novaDiaria = soma;
                return novaDiaria.toString();
            } catch (Exception e){
                return "Error";
            }
        }
        try{
            mensagem = hospedagemDAO.atualizarHospedagem(this);
            mensagem = "checkout realizado";
        } catch (Exception e){
            return mensagem;
        }
        
        return mensagem;
    }

    public String realizarCheckIn(HospedagemDAO hospedagemDAO){
        this.confirmarCheckIn = true;
        this.setHoraCheckIn(LocalTime.now());
        this.setCodigoReserva(codigoReserva);
        if (this.getHoraCheckIn().getHour() < 16){
            return "Não é permitido realizar Check in antes das 16 horas";
        }
        hospedagemDAO.criarHospedagem(this);
        return "CheckIn realizado";
    }

    // Métodos getters e setters para os atributos
    public LocalTime getHoraCheckOut() {
        return horaCheckOut;
    }

    public void setHoraCheckOut(LocalTime horaCheckOut) {
        this.horaCheckOut = horaCheckOut;
    }

    public LocalTime getHoraCheckIn() {
        return horaCheckIn;
    }

    public void setHoraCheckIn(LocalTime horaCheckIn) {
        this.horaCheckIn = horaCheckIn;
    }
    
    public void setCodigoReserva(int codigo){
        this.codigoReserva = codigo;
    }
    public int getCodigoReserva(){
        return this.codigoReserva;
    }
}
