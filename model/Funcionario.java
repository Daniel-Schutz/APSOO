package model;
import java.util.Date;

public class Funcionario extends Pessoa {
    private double salario;
    private Date dataContratacao;
    private String telefone;

    // Construtor da classe
    public Funcionario(String nome, String cpf, String email, String senha, String endereco, double salario, Date dataContratacao, String telefone) {
        super(nome, cpf, email, senha, endereco);
        this.salario = salario;
        this.dataContratacao = dataContratacao;
        this.telefone = telefone;
        this.tipo = FUNCIONARIO;
    }

    // Métodos getters e setters para os atributos adicionais
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Método para confirmar uma reserva
    public void confirmarReserva(String cpf, String dataEntrada, String dataSaida, int quantPessoas, int quantQuartos, Quarto[] quartosSelecionados) {
        // Implemente a lógica de confirmação de reserva aqui
        System.out.println("Reserva confirmada!");
    }

    // Método para confirmar um pagamento
    public void confirmarPagamento(String tipoPagamento, int pagamento) {
        // Implemente a lógica de confirmação de pagamento aqui
        System.out.println("Pagamento confirmado!");
    }

    // Método para realizar um pagamento
    public char pagamento(String tipoPagamento) {
        // Implemente a lógica de realização do pagamento aqui
        char statusPagamento = 'A';  // Exemplo: 'A' para "Aprovado"
        return statusPagamento;
    }

    public void realizarAcao(){

    }
}
