package model;

public class ReservaQuarto {
    private int codigoReserva;
    private int idQuarto;

    public ReservaQuarto(int codigoReserva, int codigoQuarto) {
        this.codigoReserva = codigoReserva;
        this.idQuarto = idQuarto;
    }

    // Métodos getters e setters
    public int getcodigoReserva() {
        return codigoReserva;
    }

    public void setcodigoReserva(int codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public int getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }
}
