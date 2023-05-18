import java.util.HashMap;
import java.util.Map;

public class QuartoDAO {
    private Map<Character, Quarto> bancoQuartos;

    public QuartoDAO() {
        bancoQuartos = new HashMap<>();
    }

    public Quarto criarQuartos(int valor, char tipoQuarto, char local, char descricao, char situacao, char idQuarto) {
        Quarto quarto = new Quarto(valor, tipoQuarto, local, descricao, situacao, idQuarto);
        bancoQuartos.put(idQuarto, quarto);
        return quarto;
    }

    public Quarto buscarQuartos(char idQuarto) {
        return bancoQuartos.get(idQuarto);
    }

    // Outros métodos de CRUD: atualizarQuarto, deletarQuarto, etc.
}
