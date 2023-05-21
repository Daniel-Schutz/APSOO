import model.Cliente;

public class SisHotel {
    private PessoaDAO pessoaDAO;
    private QuartoDAO quartoDAO;
    private ReservaDAO reservaDAO;
    private ReservaView reservaView;

    public SisHotel() {
        pessoaDAO = new PessoaDAO();
        quartoDAO = new QuartoDAO();
        reservaDAO = new ReservaDAO();
        reservaView = new ReservaView();
    }

    public void confirmarReserva(int cpf, int dataEntrada, int dataSaida, int quantPessoas, int quantQuartos, int[] quartosSelecionados) {
        if (existeCliente(cpf)) {
            Cliente cliente = pessoaDAO.buscarCliente(cpf);
            // Realizar a lógica de confirmação da reserva utilizando os dados fornecidos
            // e interagir com as classes de DAO e View conforme necessário
            reservaView.escolhaConfirmarOuCancelar();
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void confirmaPagamento(int pagamento, int tipoPagamento) {
        // Realizar a lógica de confirmação do pagamento utilizando os dados fornecidos
        // e interagir com as classes de DAO e View conforme necessário
        reservaView.pagamento((char) tipoPagamento);
    }

    public void adicionarQuartos() {
        // Realizar a lógica para adicionar quartos utilizando os dados fornecidos
        // e interagir com a classe de DAO conforme necessário
        quartoDAO.criarQuartos(...);
    }

    public void exibirOpcoesQuartos() {
        // Exibir as opções de quartos disponíveis utilizando os dados da classe de DAO
        // e interagir com a classe de View conforme necessário
        reservaView.selecaoQuartos();
    }

    public boolean existeCliente(int cpf) {
        // Verificar se o cliente com o CPF fornecido existe na base de dados
        // utilizando a classe de DAO e retornar true ou false
        return pessoaDAO.buscarCliente(cpf) != null;
    }

    public void cancelarReserva() {
        // Realizar a lógica de cancelamento de reserva utilizando a classe de DAO e View
        reservaView.escolhaConfirmarOuCancelar();
    }

    // Outros métodos e funcionalidades da classe SisHotel
    // ...
}
