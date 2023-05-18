public class Administrador extends Pessoa {
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

    // Métodos adicionais da classe Administrador
    // ...
}
