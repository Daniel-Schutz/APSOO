public class ReservaQuarto {
    private int reserva;
    private int quarto;

    public ReservaQuarto(int reserva, int quarto) {
        this.reserva = reserva;
        this.quarto = quarto;
    }

    // Métodos getters e setters
    public int getReserva() {
        return reserva;
    }

    public void setReserva(int reserva) {
        this.reserva = reserva;
    }

    public int getQuarto() {
        return quarto;
    }

    public void setQuarto(int quarto) {
        this.quarto = quarto;
    }
}
