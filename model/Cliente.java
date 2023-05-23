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

    public Cliente buscarCliente(String cpf) {

        Cliente cliente;
        cliente = this.pessoaDAO.buscarPessoa(cpf); // errado pq la no dao ta definido cpf como int ao inves de string
        return cliente;
    }

    public Cliente[] buscarTodosCliente() {
        Cliente[] objClientes;
        objClientes = this.pessoaDAO.buscarPessoas(); //CRIAR FUNÇAO DE BUSCAR TODAS AS PESSOAS DE UM TIPO NO DAO
        return objClientes;
    }

    public void deletarCliente(String cpf) {
        this.pessoaDAO.excluirPessoa(cpf);
        System.out.println("Cliente deletado");
    }


    public void realizarAcao() {

    }
}
