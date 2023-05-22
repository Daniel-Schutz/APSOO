package model;
import java.util.Date;
public class Administrador extends Funcionario {
    private boolean isAdministrador;

    // Construtor da classe
    public Administrador(String nome, String cpf, String email, String senha, String endereco, boolean isAdministrador) {
        super(nome, cpf, email, senha, endereco);
        this.isAdministrador = isAdministrador;
    }

    // Método getter para o atributo isAdministrador
    public boolean isAdministrador() {
        return isAdministrador;
    }

    // Método setter para o atributo isAdministrador
    public void setAdministrador(boolean isAdministrador) {
        this.isAdministrador = isAdministrador;
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

