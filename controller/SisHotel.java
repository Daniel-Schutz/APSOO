import DAO.*;
import model.*;
import java.sql.*;
import java.util.Collection;
import java.util.List;

public class SisHotel {
    private PessoaDAO pessoaDAO;
    private QuartoDAO quartoDAO;
    private ReservaDAO reservaDAO;
    private ReservaView reservaView;
    private ReservaQuartoDAO reservaQuartoDAO;
    private Cliente cliente;

    public SisHotel(Connection conexao) {
        pessoaDAO = new PessoaDAO(conexao);
        quartoDAO = new QuartoDAO(conexao);
        reservaDAO = new ReservaDAO(conexao);
        reservaView = new ReservaView();
        reservaQuartoDAO = new ReservaQuartoDAO();
    }

    public String cadastrarCliente(String nome, String cpf, String email, String senha, String endereco,
            String situacao) {
        cliente = new Cliente(this.pessoaDAO, nome, cpf, email, senha, endereco, situacao);
        cliente.cadastrarCliente();
        return "Cliente cadastrado com sucesso!";
    }

    public Cliente buscarCliente(String cpf) {

        if(Cliente.buscarCliente(pessoaDAO, cpf) != null){
            return Cliente.buscarCliente(pessoaDAO, cpf);
        }
        return null;
    }

    public Cliente[] buscarTodosClientes() {
        Cliente[] objCliente;
        objCliente = cliente.buscarTodosCliente()
        return objCliente;
    }

    public String deletarCliente(String cpf) {
        if(!existeCliente(cpf)){
            return "Cliente não encontrado";
        }
        Cliente.deletarCliente(this.pessoaDAO, cpf);
        return "Cliente deletado com sucesso";
    }

    public String atualizarCliente(String nome, String cpf, String email, String senha, String endereco,
    String situacao) {
        cliente = new Cliente(this.pessoaDAO, nome, cpf, email, senha, endereco, situacao);
        cliente.atualizarCliente(cliente);
        return "Cliente atualizado com sucesso";
    }

    public boolean existeCliente(String cpf) {
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

    public void registrarReserva(int codigo, Date dataEntrada, int dataSaida, String tipoPagamento, String situacao, String pessoaCPF){
        Reserva newReserva = new Reserva(codigo, dataEntrada,dataSaida,tipoPagamento, situacao, pessoaCPF, this.reservaDAO);
        newReserva.registrarReserva();
        System.out.println("SisHotel: Reserva criada com sucesso!!");
    }

    public Reserva buscarReserva(int codigo){
        Reserva reserva;
        reserva = Reserva.buscarReserva(this.reservaDAO, codigo);
        if (reserva != null){
            return reserva;
        }
        return null;
    }

    public List<Reserva> buscarTodasReservas(){
        List<Reserva> reservas = Reserva.buscarTodasReservas(this.reservaDAO);
        if (reservas != null){
            return reservas;
        }
        return null;
    }

    public Collection<String> buscarReservaPorCpf(String cpf){
        Collection<String> resultado = Reserva.buscarReservaPorCpf(this.reservaDAO, cpf);
        if (resultado != null){
            return resultado;
        }
        return null;
    }

    public String atualizarReserva(int codigo, Date dataEntrada, int dataSaida, String tipoPagamento, String situacao, String pessoaCPF){
        Reserva updatedReserva = new Reserva(codigo, dataEntrada,dataSaida,tipoPagamento, situacao, this.reservaDAO, pessoaCPF);
        String message = updatedReserva.atualizarReserva();
        return message;
    }


    public String cancelarReserva(int codigo, String cpf) {
        String message;
        message = Reserva.emiteMultaCancelamentoReserva(reservaDAO, this.reservaQuartoDAO, quartoDAO, codigo, cpf);
        if (message.startsWith("ERROR")){
            return message; //exibir ela na view
        }
        // if pagamento { ver a opção na view que vai confirmar ou 
        //                cancelar o pagamento do cliente que nem discutimos com a prof em sala
        // message = Reserva.excluirReserva(this.reservaDao, codigo, cpf)   
        //}
        return message; //exibir ela na view
        reservaView.escolhaConfirmarOuCancelar();
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

    // Outros métodos e funcionalidades da classe SisHotel
    // ...
}
