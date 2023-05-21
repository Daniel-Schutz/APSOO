import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Pessoa;


public class PessoaDAO {
    private Connection conexao;

    public PessoaDAO(Connection conexao){
        this.conexao = conexao;

    }

    public void criarPessoa(Pessoa pessoa) {
    String sql = "INSERT INTO pessoa (cpf, nome, email, senha, endereco, telefone, situacao, dataContratacao, salario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
       
        stmt.setString(1, pessoa.getCpf());
        stmt.setString(2, pessoa.getNome());
        stmt.setString(3, pessoa.getEmail());
        stmt.setString(4, pessoa.getSenha());
        stmt.setString(5, pessoa.getEndereco());
        stmt.setString(6, pessoa.getTelefone());
        stmt.setString(7, pessoa.getSituacao());
        stmt.setDate(8, pessoa.getDataContratacao());
        stmt.setDouble(9, pessoa.getSalario());

        stmt.executeUpdate();
        System.out.println("Pessoa inserida com sucesso!");
        
        }
    }

    public List<Pessoa> listarPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setSenha(rs.getString("senha"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setSituacao(rs.getString("situacao"));
                pessoa.setDataContratacao(rs.getDate("dataContratacao"));
                pessoa.setSalario(rs.getDouble("salario"));
                pessoas.add(pessoa);
            }
        } 

        return pessoas;
    }

    public void atualizarPessoa(Pessoa pessoa) {
    String sql = "UPDATE pessoa SET nome = ?, email = ?, senha = ?, endereco = ?, telefone = ?, situacao = ?, dataContratacao = ?, salario = ? WHERE cpf = ?";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
        
        stmt.setString(1, pessoa.getNome());
        stmt.setString(2, pessoa.getEmail());
        stmt.setString(3, pessoa.getSenha());
        stmt.setString(4, pessoa.getEndereco());
        stmt.setString(5, pessoa.getTelefone());
        stmt.setString(6, pessoa.getSituacao());
        stmt.setDate(7, pessoa.getDataContratacao());
        stmt.setDouble(8, pessoa.getSalario());
        stmt.setString(9, pessoa.getCpf());

        stmt.executeUpdate();
        System.out.println("Pessoa atualizada com sucesso!");
       
        }
    }


    public void excluirPessoa(String cpf) {
    String sql = "DELETE FROM pessoa WHERE cpf = ?";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
       
        stmt.setString(1, cpf);
        stmt.executeUpdate();
        System.out.println("Pessoa excluída com sucesso!");
        
    }
    }


    public Pessoa buscarPessoa(String cpf) {
    String sql = "SELECT * FROM pessoa WHERE cpf = ?";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
        
        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setCpf(rs.getString("cpf"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setEmail(rs.getString("email"));
            pessoa.setSenha(rs.getString("senha"));
            pessoa.setEndereco(rs.getString("endereco"));
            pessoa.setTelefone(rs.getString("telefone"));
            pessoa.setSituacao(rs.getString("situacao"));
            pessoa.setDataContratacao(rs.getDate("dataContratacao"));
            pessoa.setSalario(rs.getDouble("salario"));
            return pessoa;
        }
    } 
    return null;
    }




}