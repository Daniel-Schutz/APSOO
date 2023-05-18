import java.util.ArrayList;
import java.util.List;

public class ReservaQuartoDAO {
    private List<ReservaQuarto> listaReservaQuarto;

    public ReservaQuartoDAO() {
        listaReservaQuarto = new ArrayList<>();
    }

    public void salvar(ReservaQuarto resqua) {
        listaReservaQuarto.add(resqua);
    }

    // Outros métodos de CRUD: buscar, atualizar, deletar, etc.
}
