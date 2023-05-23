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

    // Método getter para o atributo situacao
    public String getSituacao() {
        return situacao;
    }

    // Método setter para o atributo situacao
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    // Método para cadastrar um cliente no banco de dados
    public void cadastrarCliente() {
        this.pessoaDAO.criarPessoa(this);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    public void atualizarCliente(){
        pessoaDAO.atualizarPessoa(this);
        System.out.println("Cliente atualizado com sucesso!");

    }

    // Método para verificar se um cliente existe no banco de dados pelo CPF
    public static boolean existeCliente(PessoaDAO pessoaDAO, String cpf) {
        
        return pessoaDAO.existePessoa(cpf);
    }

    public static Cliente buscarCliente(PessoaDAO pessoaDAO,String cpf) {

        Cliente cliente;
        cliente = pessoaDAO.buscarPessoa(cpf); 
        return cliente;
    }

    public static Cliente[] buscarTodosCliente(PessoaDAO pessoaDAO, String tipo) {
        Cliente[] objClientes;
        objClientes = pessoaDAO.listarPessoas(tipo); //CRIAR FUNÇAO DE BUSCAR TODAS AS PESSOAS DE UM TIPO NO DAO
        return objClientes;
    }

    public static void deletarCliente(PessoaDAO pessoaDAO, String cpf) {
        pessoaDAO.excluirPessoa(cpf);
        System.out.println("Cliente deletado");
    }


    public void realizarAcao() {

    }
}
