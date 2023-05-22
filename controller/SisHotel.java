import DAO.*;
import model.*;
import java.sql.*;

public class SisHotel {
    private PessoaDAO pessoaDAO;
    private QuartoDAO quartoDAO;
    private ReservaDAO reservaDAO;
    private ReservaView reservaView;
    private Cliente cliente;

    public SisHotel(Connection conexao) {
        pessoaDAO = new PessoaDAO(conexao);
        quartoDAO = new QuartoDAO(conexao);
        reservaDAO = new ReservaDAO(conexao);
        reservaView = new ReservaView();
    }

    public String cadastrarCliente(String nome, String cpf, String email, String senha, String endereco,
            String situacao) {
        cliente = new Cliente(this.pessoaDAO, nome, cpf, email, senha, endereco, situacao);
        cliente.cadastrarCliente();
        return "Cliente cadastrado com sucesso!";
    }

    public Cliente buscarCliente(string cpf) {
        Cliente objCliente;
        objCliente = cliente.buscarCliente(cpf);
        return objCliente;
    }

    public Cliente[] buscarTodosClientes() {
        Cliente[] objCliente;
        //esperar desenvolver função no DAO
        return objCliente;
    }

    public String deletarCliente(String cpf) {

        String mensagem;
        mensagem = cliente.deletarCliente(cpf);

        return mensagem;
    }

    public String atualizarCliente(String nome, String cpf, String email, String senha, String endereco,
    String situacao) {
        cliente = new Cliente(this.pessoaDAO, nome, cpf, email, senha, endereco, situacao);
        cliente.atualizarCliente();
        return "Cliente atualizado com sucesso";
    }

    public boolean existeCliente(int cpf) {
        // Verificar se o cliente com o CPF fornecido existe na base de dados
        // utilizando a classe de DAO e retornar true ou false
        return pessoaDAO.buscarPessoa(cpf) != null;
    }

    public void confirmarReserva(String cpf, int dataEntrada, int dataSaida, int quantPessoas, int quantQuartos,
        int[] quartosSelecionados) {
        if (existeCliente(cpf)) {
            Cliente cliente = pessoaDAO.buscarPessoa(cpf);
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

    public void registrarReserva(string codigo, Date dataEntrada, int dataSaida, String tipoPagamento, String situacao){
        Reserva newReserva = new Reserva(codigo, dataEntrada,dataSaida,tipoPagamento, situacao);
        newReserva.registrarReserva();
        System.out.println("SisHotel: Reserva criada com sucesso!!");
    }

    public void cancelarReserva() {
        // Realizar a lógica de cancelamento de reserva utilizando a classe de DAO e
        // View
        reservaView.escolhaConfirmarOuCancelar();
    }

    // Outros métodos e funcionalidades da classe SisHotel
    // ...
}
