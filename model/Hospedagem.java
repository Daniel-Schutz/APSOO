package model;

import java.sql.Date;
import java.util.Calendar;
import java.util.*;

public class Hospedagem {
    private Date horaCheckOut;
    private Date horaCheckIn;
    private boolean confirmarCheckIn;
    private boolean confirmarCheckout;

    // Construtor da classe
    public Hospedagem() {
        confirmarCheckIn = false;
        confirmarCheckout = false;
    }

    public String realizarCheckIn(int diasEstadia){
        this.confirmarCheckIn = true;
        long tempoAtual = System.currentTimeMillis();
        this.horaCheckIn = new Date(tempoAtual);
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.getHoraCheckIn()); 
        cal.add(Calendar.DAY_OF_MONTH, diasEstadia);
        this.setHoraCheckOut(cal.getTime()); // Obtém a hora do checkout como objeto Date

        return "Checkin realizado!";
    }

    public String realizarCheckOut(){
        this.confirmarCheckout = true;
        return "Checkout realizado";
    }

    // Métodos getters e setters para os atributos
    public Date getHoraCheckOut() {
        return horaCheckOut;
    }

    public void setHoraCheckOut(Date horaCheckOut) {
        this.horaCheckOut = horaCheckOut;
    }

    public Date getHoraCheckIn() {
        return horaCheckIn;
    }

    public void setHoraCheckIn(Date horaCheckIn) {
        this.horaCheckIn = horaCheckIn;
    }

}
