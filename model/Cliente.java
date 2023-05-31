package model;

import utils.NovaExcecao;

import java.sql.SQLException;
import java.util.List;

import Persistence.*;


public class Cliente extends Pessoa {
    
    private String situacao;

    // Construtor da classe
    public Cliente( String nome, String cpf, String email, String senha, String endereco,
            String situacao) {
        super(nome, cpf, email, senha, endereco);
        this.situacao = situacao;
        this.tipo = CLIENTE;

    }

    // Método para verificar se um cliente existe no banco de dados pelo CPF
    public static boolean existeCliente(String cpf) {

        return PessoaDAO.existePessoa(cpf);
    }

    // Método para cadastrar um cliente no banco de dados
    public String cadastrarCliente() throws NovaExcecao {
        String message;
        try{
            if(this.getCpf().matches("\\d+") && this.getCpf().length() == 11){
                message = PessoaDAO.criarPessoa(this); // cria cliente caso cpf seja válido
            }
            else{
                message = "CPF Inválido";
                throw new NovaExcecao(message);
            }
        } catch (Exception e){
            message = e.getMessage();
            System.out.println("VERIFICAR ESSE ERROR HANDLING");
        }
        return message;
    }

    public String atualizarCliente() throws SQLException {
        return PessoaDAO.atualizarPessoa(this); // verificar se poderá passar tipo cliente ou pessoa e retornar
                                                     // string no dao
    }

    public static Cliente buscarCliente(String cpf) {

        Cliente cliente = new Cliente(null, null, null, null, null, null);
        if (PessoaDAO.buscarPessoa(cliente,cpf) != null) {
            cliente = (Cliente) PessoaDAO.buscarPessoa(cliente, cpf);
            return cliente;
        }
        return null;
    }

    public static List<Pessoa> buscarTodosCliente(String tipo) {
        List<Pessoa> objClientes;
        objClientes = PessoaDAO.listarPessoas(tipo);
        return objClientes;
    }

    public static String deletarCliente(String cpf) {
        return PessoaDAO.excluirPessoa(cpf); // não esquecer de no DAO retornar a string mensagem;

    }

    // Método getter para o atributo situacao
    public String getSituacao() {
        return situacao;
    }

    // Método setter para o atributo situacao
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public void realizarAcao() {

    }

}
