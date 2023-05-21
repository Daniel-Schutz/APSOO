package model;
import java.util.HashMap;
import java.util.Map;

public class Cliente extends Pessoa {
    private String situacao;

    // Construtor da classe
    public Cliente(String nome, String cpf, String email, String senha, String endereco, String situacao) {
        super(nome, cpf, email, senha, endereco);
        this.situacao = situacao;
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
    public static void cadastrarCliente(String cpf, String nome, String email, String situacao, String senha, String endereco) {
        // Implemente a lógica de cadastro do cliente no banco de dados aqui
        System.out.println("Cliente cadastrado com sucesso!");
    }

    // Método para verificar se um cliente existe no banco de dados pelo CPF
    public static boolean existeCliente(String cpf) {
        // Implemente a lógica de busca do cliente no banco de dados pelo CPF aqui
        // Neste exemplo, utilizaremos um mapa simples para simular o banco de dados
        Map<String, Cliente> bancoClientes = new HashMap<>();
        // Supondo que o CPF seja a chave do mapa
        return bancoClientes.containsKey(cpf);
    }

    public void realizarAcao(){

    }
}
