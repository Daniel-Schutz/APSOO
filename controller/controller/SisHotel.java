package controller;
import DAO.*;
import model.*;
import view.ReservaView;

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
        reservaView = new ReservaView(conexao);
        reservaQuartoDAO = new ReservaQuartoDAO(conexao);
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

    public List<Pessoa> buscarTodosClientes(String tipo) {
        List<Pessoa> objCliente;
        objCliente = Cliente.buscarTodosCliente(this.pessoaDAO, tipo); //Mudar no dao para receeber parametro tipo
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
        try{
            
            String message;
            cliente = new Cliente(this.pessoaDAO, nome, cpf, email, senha, endereco, situacao);
            message = cliente.atualizarCliente();
            return message;

        } catch (Exception e){
            return "Error!";
        }
       
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

    public String emiteMultaCancelamento(int codigo, String cpf) {
        String message;
        message = Reserva.emiteMultaCancelamentoReserva(this.reservaDAO, this.reservaQuartoDAO, this.quartoDAO, codigo, cpf);
        
        return message;
    }

    public String excluirReserva(int codigo) {
    try{
        String message;
        message = Reserva.excluirReserva(this.reservaDAO, codigo); 
        return message;
    } catch (Exception e){
        return "Error";
    }
    }

    public String realizarCheckIn(){
        return null;
        
    }
    // Outros métodos e funcionalidades da classe SisHotel
    // ...
}
