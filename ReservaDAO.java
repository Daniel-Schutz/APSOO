import java.util.HashMap;
import java.util.Map;

public class ReservaDAO {
    private Map<Character, Reserva> bancoReservas;

    public ReservaDAO() {
        bancoReservas = new HashMap<>();
    }

    public Reserva criarReserva(char codigo, char data, char diasEstadia, char tipoPagamento, char situacao) {
        Reserva reserva = new Reserva(codigo, data, diasEstadia, tipoPagamento, situacao);
        bancoReservas.put(codigo, reserva);
        return reserva;
    }

    public Reserva buscarReserva(char codigo) {
        return bancoReservas.get(codigo);
    }

    // Outros métodos de CRUD: atualizarReserva, deletarReserva, etc.
}
