package controller;
import DAO.*;
import model.*;
import view.ReservaView;

import java.sql.*;

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

    public Cliente[] buscarTodosClientes(String tipo) {
        Cliente[] objCliente;
        objCliente = cliente.buscarTodosCliente(tipo); //Mudar no dao para receeber parametro tipo
        return objCliente;
    }

    public String deletarCliente(String cpf) {
        if(!this.existeCliente(cpf)){
            return "Cliente não encontrado";
        }
        String message = Cliente.deletarCliente(this.pessoaDAO, cpf);
        return message;
    }

    public String atualizarCliente(String nome, String cpf, String email, String senha, String endereco,
    String situacao) {
        String message;
        cliente = new Cliente(this.pessoaDAO, nome, cpf, email, senha, endereco, situacao);
        message = cliente.atualizarCliente();
        return message;
    }

    public boolean existeCliente(String cpf) {
        return Cliente.existeCliente(this.pessoaDAO, cpf);
    }

    public void registrarReserva(Date dataEntrada, int dataSaida, String tipoPagamento, String situacao, String pessoaCPF){
        int codigo = 0; //definir logica para gerar codigo
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
        Reserva updatedReserva = new Reserva(codigo, dataEntrada,dataSaida,tipoPagamento, situacao, pessoaCPF, this.reservaDAO);
        String message = updatedReserva.atualizarReserva();
        return message;
    }

    public String realizarCheckIn()


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
