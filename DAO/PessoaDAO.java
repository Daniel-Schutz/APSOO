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

    public String criarPessoa(Cliente cliente) {
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
        return "Cliente inserido com sucesso!";
        
        }
    }

    
    public String criarPessoa(Funcionario funcionario) {
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
        return "Funcionario inserido com sucesso!";
        
        }
    }

    public String criarPessoa(Administrador administrador) {
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
        return "Administrador inserido com sucesso!";
        
        }
    }
    
    
    public List<Pessoa> listarPessoas(String tipo) {
        if(tipo.equals("CLIENTE")){
        List<Pessoa> clientes = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente;
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setSalario(rs.getDouble("salario"));
                cliente.setSituacao(rs.getString("situacao"));
                clientes.add(cliente);
            }
        }
        return clientes;
        }else if(tipo.equals("FUNCIONARIO")){
        List<Pessoa> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario funcionario;
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setDataContratacao(rs.getDate("dataContratacao"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionarios.add(funcionario);
            }
        } 

        return funcionarios;

        }else if(tipo.equals("ADMINISTRADOR")){
        List<Pessoa> administradores = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Administrador administrador;
                administrador.setCpf(rs.getString("cpf"));
                administrador.setNome(rs.getString("nome"));
                administrador.setEmail(rs.getString("email"));
                administrador.setSenha(rs.getString("senha"));
                administrador.setEndereco(rs.getString("endereco"));
                administrador.setTelefone(rs.getString("telefone"));
                administrador.setDataContratacao(rs.getDate("dataContratacao"));
                administrador.setSalario(rs.getDouble("salario"));
                administradores.add(administrador);
            }
        } 

        return administradores;      

        }

        
    }

    public List<Pessoa> listarPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pessoa pessoa;
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setSenha(rs.getString("senha"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setDataContratacao(rs.getDate("dataContratacao"));
                pessoa.setSalario(rs.getDouble("salario"));
                pessoa.setSituacao(rs.getString("situacao"));
                pessoas.add(pessoa);
            }
        } 

        return pessoas;
    }


    public String excluirPessoa(String cpf) {
    String sql = "DELETE FROM pessoa WHERE cpf = ?";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
       
        stmt.setString(1, cpf);
        stmt.executeUpdate();
        return "Pessoa excluída com sucesso!";
        
    }
    }

    public Pessoa buscarPessoa(String cpf) { 
    String sql = "SELECT * FROM pessoa WHERE cpf = ?";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
        
        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Cliente cliente;                                            
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setSenha(rs.getString("senha"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setSituacao(rs.getString("situacao"));
            return cliente;
        }
    } 
    return null;
    }

    public Pessoa buscarPessoa(String cpf) { 
    String sql = "SELECT * FROM pessoa WHERE cpf = ?";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
        
        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Funcionario funcionario;  
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setEmail(rs.getString("email"));
            funcionario.setSenha(rs.getString("senha"));
            funcionario.setEndereco(rs.getString("endereco"));
            funcionario.setTelefone(rs.getString("telefone"));
            funcionario.setDataContratacao(rs.getDate("dataContratacao"));
            funcionario.setSalario(rs.getDouble("salario"));
            return funcionario;
        }
    } 
    return null;
    }

    public Pessoa buscarPessoa(String cpf) { 
    String sql = "SELECT * FROM pessoa WHERE cpf = ?";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
        
        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Administrador administrador; 
            administrador.setCpf(rs.getString("cpf"));
            administrador.setNome(rs.getString("nome"));
            administrador.setEmail(rs.getString("email"));
            administrador.setSenha(rs.getString("senha"));
            administrador.setEndereco(rs.getString("endereco"));
            administrador.setTelefone(rs.getString("telefone"));
            administrador.setDataContratacao(rs.getDate("dataContratacao"));
            administrador.setSalario(rs.getDouble("salario"));
            return administrador;
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