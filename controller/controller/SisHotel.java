package controller;
import model.*;
import utils.NovaExcecao;

import java.sql.*;
import java.util.Collection;
import java.util.List;

public class SisHotel {

    public SisHotel() {
        
    }

    public String cadastrarCliente(String nome, String cpf, String email, String senha, String endereco,
            String situacao) {
        try{
            System.out.println("SisHotel Cadastrar cliente");
            Cliente cliente = new Cliente(nome, cpf, email, senha, endereco, situacao);
            cliente.cadastrarCliente();
            
        } catch (NovaExcecao e){
            System.out.println(e);
        }
        return "Cliente cadastrado com sucesso!";
        
    }

    public Cliente buscarCliente(String cpf) {

        if(Cliente.buscarCliente(cpf) != null){
            return Cliente.buscarCliente(cpf);
        }
        return null;
    }

    public List<Pessoa> buscarTodosClientes(String tipo) {
        List<Pessoa> objCliente;
        objCliente = Cliente.buscarTodosCliente(tipo); //Mudar no dao para receeber parametro tipo
        return objCliente;
    }

    public String deletarCliente(String cpf) {
        if(!this.existeCliente(cpf)){
            return "Cliente não encontrado";
        }
        String message = Cliente.deletarCliente(cpf);
        return message;
    }

    public String atualizarCliente(String nome, String cpf, String email, String senha, String endereco,
    String situacao) {
        try{

            String message;
            Cliente cliente = new Cliente(nome, cpf, email, senha, endereco, situacao);
            message = cliente.atualizarCliente();
            return message;

        } catch (Exception e){
            return "Error!";
        }
       
    }

    public boolean existeCliente(String cpf) {
        return Cliente.existeCliente(cpf);
    }

    public String registrarReserva(Date dataEntrada, int diasEstadia, String tipoPagamento, String situacao, String pessoaCPF){
        System.out.println("SisHotel: registrar reserva");
        int codigo = 0; //definir logica para gerar codigo
        Reserva newReserva = new Reserva(codigo, dataEntrada, diasEstadia,tipoPagamento, situacao, pessoaCPF);
        //vai tentar criar reserva se não trata exceção correspondente
        try{
            String message = newReserva.registrarReserva();
            return message;
        } catch (Exception e){
            //return NovaExcecao.getNewMessage();
            return "Erro ao registrar reserva";
        }        
        
        //return "Reserva registrada com sucesso";
    }

    public Reserva buscarReserva(int codigo){
        Reserva reserva;
        reserva = Reserva.buscarReserva(codigo);
        if (reserva != null){
            return reserva;
        }
        return null;
    }

    public List<Reserva> buscarTodasReservas(){
        List<Reserva> reservas = Reserva.buscarTodasReservas();
        if (reservas != null){
            return reservas;
        }
        return null;
    }

    public List<Reserva> buscarReservaPorCpf(String cpf){
        List<Reserva> resultado = Reserva.buscarReservaPorCpf(cpf);
        return resultado;
       
    }

    public String atualizarReserva(int codigo, Date dataEntrada, int dataSaida, String tipoPagamento, String situacao, String pessoaCPF){
        Reserva updatedReserva = new Reserva(codigo, dataEntrada,dataSaida,tipoPagamento, situacao, pessoaCPF);
        String message = Reserva.atualizarReserva(updatedReserva);
        return message;
    }

    public String emiteMultaCancelamento(int codigo, String cpf) {
        String message;
        message = Reserva.emiteMultaCancelamentoReserva(codigo, cpf);
        
        return message;
    }

    public String excluirReserva(int codigo) {
    try{
        String message;
        message = Reserva.excluirReserva(codigo); 
        return message;
    } catch (Exception e){
        return "Error";
    }
    }

    public String realizarCheckIn(Reserva reserva){ // vai mudar pra cpf ainda,
        
        String mensagem = Hospedagem.realizarCheckIn(reserva);
        return mensagem;

    }

    // public String realizarCheckOut(int codigo){

    //     String mensagem = Hospedagem.realizarCheckOut(codigo, this.hospedagemDAO, this.reservaQuartoDAO, this.quartoDAO);
    //     return mensagem;
        
    // }
    // Outros métodos e funcionalidades da classe SisHotel
    // ...
}
