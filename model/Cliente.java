package model;

import DAO.*;
import java.util.HashMap;
import java.util.Map;

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
    public static boolean existeCliente(PessoaDAO pessoaDAO, String cpf) {
    
        return pessoaDAO.existePessoa(cpf);
    }

    // Método para cadastrar um cliente no banco de dados
    public String cadastrarCliente() {
        String message = this.pessoaDAO.criarPessoa(this);
        return message;
    }

    public String atualizarCliente(){
        return this.pessoaDAO.atualizarPessoa(this); //verificar se poderá passar tipo cliente ou pessoa e retornar string no dao
    }

    public static Cliente buscarCliente(PessoaDAO pessoaDAO,String cpf) {

        Cliente cliente;
        if(pessoaDAO.buscarPessoa(cpf) != null){
            cliente = (Cliente) pessoaDAO.buscarPessoa(cpf);
            return cliente;
        }
        return null;
    }

    public static Cliente[] buscarTodosCliente(PessoaDAO pessoaDAO, String tipo) {
        Cliente[] objClientes;
        objClientes = pessoaDAO.listarPessoas(tipo); //CRIAR FUNÇAO DE BUSCAR TODAS AS PESSOAS DE UM TIPO NO DAO
        return objClientes;
    }

    public static String deletarCliente(PessoaDAO pessoaDAO, String cpf) {
        return pessoaDAO.excluirPessoa(cpf); //não esquecer de no DAO retornar a string mensagem;
        
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
