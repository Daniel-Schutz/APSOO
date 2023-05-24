import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Pessoa;
import model.Cliente;
import model.Funcionario;
import model.Administrador;


public class PessoaDAO {
    private Connection conexao;
    public PessoaDAO(Connection conexao){
        this.conexao = conexao;
    }

    public void criarPessoa(Cliente cliente) {
    String sql = "INSERT INTO pessoa (cpf, nome, email, senha, endereco, telefone, situacao, dataContratacao, salario, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
       
        stmt.setString(1, cliente.getCpf());
        stmt.setString(2, cliente.getNome());
        stmt.setString(3, cliente.getEmail());
        stmt.setString(4, cliente.getSenha());
        stmt.setString(5, cliente.getEndereco());
        stmt.setString(6, cliente.getTelefone());
        stmt.setString(7, cliente.getSituacao());
        stmt.setDate(8, cliente.getDataContratacao());
        stmt.setDouble(9, cliente.getSalario());
        stmt.setString(10, cliente.getTipo());

        stmt.executeUpdate();
        System.out.println("Cliente inserido com sucesso!");
        
        }
    }

    
    public void criarPessoa(Funcionario funcionario) {
    String sql = "INSERT INTO pessoa (cpf, nome, email, senha, endereco, telefone, situacao, dataContratacao, salario, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
       
        stmt.setString(1, funcionario.getCpf());
        stmt.setString(2, funcionario.getNome());
        stmt.setString(3, funcionario.getEmail());
        stmt.setString(4, funcionario.getSenha());
        stmt.setString(5, funcionario.getEndereco());
        stmt.setString(6, funcionario.getTelefone());
        stmt.setString(7, funcionario.getSituacao());
        stmt.setDate(8, funcionario.getDataContratacao());
        stmt.setDouble(9, funcionario.getSalario());
        stmt.setString(10, funcionario.getTipo());

        stmt.executeUpdate();
        System.out.println("Funcionario inserido com sucesso!");
        
        }
    }

    public void criarPessoa(Administrador administrador) {
    String sql = "INSERT INTO pessoa (cpf, nome, email, senha, endereco, telefone, situacao, dataContratacao, salario, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
       
        stmt.setString(1, administrador.getCpf());
        stmt.setString(2, administrador.getNome());
        stmt.setString(3, administrador.getEmail());
        stmt.setString(4, administrador.getSenha());
        stmt.setString(5, administrador.getEndereco());
        stmt.setString(6, administrador.getTelefone());
        stmt.setString(7, administrador.getSituacao());
        stmt.setDate(8, administrador.getDataContratacao());
        stmt.setDouble(9, administrador.getSalario());
        stmt.setString(10, administrador.getTipo());

        stmt.executeUpdate();
        System.out.println("Administrador inserido com sucesso!");
        
        }
    }
    
    
    public List<Pessoa> listarPessoas() {
        List<Pessoa> clientes = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setSituacao(rs.getString("situacao"));
                cliente.setDataContratacao(rs.getDate("dataContratacao"));
                cliente.setSalario(rs.getDouble("salario"));
                clientes.add(cliente);
            }
        } 

        return clientes;
    }

    public List<Pessoa> listarPessoas() {
        List<Pessoa> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setSituacao(rs.getString("situacao"));
                funcionario.setDataContratacao(rs.getDate("dataContratacao"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionarios.add(funcionario);
            }
        } 

        return funcionarios;
    }


    public List<Pessoa> listarPessoas() {
        List<Pessoa> administradores = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Administrador administrador = new Administrador();
                administrador.setCpf(rs.getString("cpf"));
                administrador.setNome(rs.getString("nome"));
                administrador.setEmail(rs.getString("email"));
                administrador.setSenha(rs.getString("senha"));
                administrador.setEndereco(rs.getString("endereco"));
                administrador.setTelefone(rs.getString("telefone"));
                administrador.setSituacao(rs.getString("situacao"));
                administrador.setDataContratacao(rs.getDate("dataContratacao"));
                administrador.setSalario(rs.getDouble("salario"));
                administradores.add(administrador);
            }
        } 

        return administradores;
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
        //verificar se o front já recebe a mensagem
        
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

    //Fiz essa função baseada na decima mas não sei se está certa
    public boolean existePessoa(String cpf) {
        String sql = "SELECT * FROM pessoa WHERE cpf = ?";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } 
        return false;
        }




}