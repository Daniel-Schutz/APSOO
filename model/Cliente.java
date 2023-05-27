package model;

import DAO.*;
import utils.NovaExcecao;

import java.sql.SQLException;
import java.util.List;


public class Cliente extends Pessoa {
    PessoaDAO pessoaDAO;
    private String situacao;

    // Construtor da classe
    public Cliente(PessoaDAO pessoaDAO, String nome, String cpf, String email, String senha, String endereco,
            String situacao) {
        super(nome, cpf, email, senha, endereco);
        this.situacao = situacao;
        this.pessoaDAO = pessoaDAO;
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
                message = this.pessoaDAO.criarPessoa(this); // cria cliente caso cpf seja válido
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
        return this.pessoaDAO.atualizarPessoa(this); // verificar se poderá passar tipo cliente ou pessoa e retornar
                                                     // string no dao
    }

    public static Cliente buscarCliente(PessoaDAO pessoaDAO, String cpf) {

        Cliente cliente = new Cliente(null, null, null, null, null, null, null);
        if (pessoaDAO.buscarPessoa(cliente,cpf) != null) {
            cliente = (Cliente) pessoaDAO.buscarPessoa(cliente, cpf);
            return cliente;
        }
        return null;
    }

    public static List<Pessoa> buscarTodosCliente(PessoaDAO pessoaDAO, String tipo) {
        List<Pessoa> objClientes;
        objClientes = pessoaDAO.listarPessoas(tipo);
        return objClientes;
    }

    public static String deletarCliente(PessoaDAO pessoaDAO, String cpf) {
        return pessoaDAO.excluirPessoa(cpf); // não esquecer de no DAO retornar a string mensagem;

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
