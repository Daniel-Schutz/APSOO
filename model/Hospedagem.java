package model;

import java.sql.Date;
import java.util.Calendar;
import java.util.*;
import java.time.LocalTime;

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

    public String realizarCheckOut(){
        this.confirmarCheckOut = true;
        setHoraCheckOut(LocalTime.now());
        return "CheckOut realizado";
    }

    public String realizarCheckIn(){
        this.confirmarCheckIn = true;
        setHoraCheckIn(LocalTime.now());
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

}
