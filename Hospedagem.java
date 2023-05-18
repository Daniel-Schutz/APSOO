public class Hospedagem {
    private String horaCheckOut;
    private String horaCheckIn;

    // Construtor da classe
    public Hospedagem(String horaCheckOut, String horaCheckIn) {
        this.horaCheckOut = horaCheckOut;
        this.horaCheckIn = horaCheckIn;
    }

    // Métodos getters e setters para os atributos
    public String getHoraCheckOut() {
        return horaCheckOut;
    }

    public void setHoraCheckOut(String horaCheckOut) {
        this.horaCheckOut = horaCheckOut;
    }

    public String getHoraCheckIn() {
        return horaCheckIn;
    }

    public void setHoraCheckIn(String horaCheckIn) {
        this.horaCheckIn = horaCheckIn;
    }
}
