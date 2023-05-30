package model;

import java.sql.Date;
import java.util.Calendar;

import DAO.HospedagemDAO;
import DAO.QuartoDAO;
import DAO.ReservaDAO;
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

    public static String realizarCheckOut(int codigoReserva, HospedagemDAO hospedagemDAO, ReservaQuartoDAO reservaQuartoDAO, QuartoDAO quartoDAO){ //codigo deverá ser alterado para cpf
       
        String mensagem = "Checkout não Realizado";
        try{
            Hospedagem hospedagem = hospedagemDAO.buscarHospedagem(codigoReserva);
            hospedagem.confirmarCheckOut = true; //temos q buscar uma hospedagem antes para depois realizar a atualização dessa, temos que corrigir BuscarHospedagem no hospedagem dao
            hospedagem.setHoraCheckOut(LocalTime.now());
            if (hospedagem.getHoraCheckOut().getHour() > 12){
            
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
            }
        
            mensagem = hospedagemDAO.atualizarHospedagem(hospedagem);
            mensagem = "checkout realizado";
            
        } catch (Exception e){
            return "Error";
            }
       
        return mensagem;
    }

    public String realizarCheckIn(HospedagemDAO hospedagemDAO, ReservaDAO reservaDAO, int codigo){ //Mudar para cpf no futuro Precisarrá mudar a função buscarporCPF no dao
       try{
        Reserva reserva = reservaDAO.buscarReserva(codigo);
        this.confirmarCheckIn = true;
        this.setHoraCheckIn(LocalTime.now());
        this.setCodigoReserva(reserva.getCodigo());
        if (this.getHoraCheckIn().getHour() < 16){
            return "Não é permitido realizar Check in antes das 16 horas";
        }
        hospedagemDAO.criarHospedagem(this);
        return "CheckIn realizado";
       } catch (Exception e){
        return "Não foi possível realizar checkin";
       }
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
