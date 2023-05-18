import java.util.HashMap;
import java.util.Map;

public class PessoaDAO {
    private Map<String, Pessoa> bancoPessoas;

    public PessoaDAO() {
        bancoPessoas = new HashMap<>();
    }

    public void adicionarPessoa(Pessoa pessoa) {
        bancoPessoas.put(pessoa.getCpf(), pessoa);
    }

    public Administrador tornarAdmin(String cpf) {
        Pessoa pessoa = bancoPessoas.get(cpf);
        if (pessoa instanceof Cliente) {
            Cliente cliente = (Cliente) pessoa;
            Administrador administrador = new Administrador(cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getSenha(), cliente.getEndereco(), true);
            bancoPessoas.put(cpf, administrador);
            return administrador;
        }
        return null;
    }

    public Cliente buscarCliente(String cpf) {
        Pessoa pessoa = bancoPessoas.get(cpf);
        if (pessoa instanceof Cliente) {
            return (Cliente) pessoa;
        }
        return null;
    }

    // Outros métodos de CRUD: atualizarPessoa, deletarPessoa, etc.
}
