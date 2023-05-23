package model;
import java.util.Date;
public class Administrador extends Funcionario {

    // Construtor da classe
    public Administrador(String nome, String cpf, String email, String senha, String endereco, double salario, Date dataContratacao, String telefone) {
        super(nome, cpf, email, senha, endereco, salario, dataContratacao, telefone);
        this.tipo = ADMIN;
    }

    // Método para cadastrar um funcionário
    public void cadastrarFuncionario(String nome, String cpf, String email, String senha, String endereco, double salario, Date dataContratacao, String telefone) {
        // Implemente a lógica de cadastro do funcionário aqui
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    // Outros métodos e funcionalidades da classe Administrador
    // ...

     public void realizarAcao(){

     }
}

