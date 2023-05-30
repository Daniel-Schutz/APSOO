package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Pessoa;
import model.Cliente;
import model.Funcionario;
import model.Administrador;


public class PessoaDAO {
    private static Connection conexao;
    public PessoaDAO(Connection conexao){
        PessoaDAO.conexao = conexao;
    }

  
    public static String criarPessoa(Cliente cliente) {
    String sql = "INSERT INTO pessoa (cpf, nome, email, senha, endereco, telefone, situacao, dataContratacao, salario, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
       
        stmt.setString(1, cliente.getCpf());
        stmt.setString(2, cliente.getNome());
        stmt.setString(3, cliente.getEmail());
        stmt.setString(4, cliente.getSenha());
        stmt.setString(5, cliente.getEndereco());
        stmt.setString(6, cliente.getTelefone());
        stmt.setString(7, cliente.getSituacao());
        stmt.setDate(8, (Date) cliente.getDataContratacao());
        stmt.setDouble(9, cliente.getSalario());
        stmt.setString(10, cliente.getTipo());

        stmt.executeUpdate();
        return "Cliente inserido com sucesso!";
        
        }catch (SQLException e) {
            System.err.println("Erro ao executar a consulta SQL: " + e.getMessage());
        }

        return "Erro!";
    }

    
    public static String criarPessoa(Funcionario funcionario) {
    String sql = "INSERT INTO pessoa (cpf, nome, email, senha, endereco, telefone, situacao, dataContratacao, salario, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
       
        stmt.setString(1, funcionario.getCpf());
        stmt.setString(2, funcionario.getNome());
        stmt.setString(3, funcionario.getEmail());
        stmt.setString(4, funcionario.getSenha());
        stmt.setString(5, funcionario.getEndereco());
        stmt.setString(6, funcionario.getTelefone());
        stmt.setString(7, funcionario.getSituacao());
        stmt.setDate(8, (Date) funcionario.getDataContratacao());
        stmt.setDouble(9, funcionario.getSalario());
        stmt.setString(10, funcionario.getTipo());

        stmt.executeUpdate();
        return "Funcionario inserido com sucesso!";
        
        }catch (SQLException e) {
            System.err.println("Erro ao executar a consulta SQL: " + e.getMessage());
        }
        return "Erro!";
    }

    public static String criarPessoa(Administrador administrador) {
    String sql = "INSERT INTO pessoa (cpf, nome, email, senha, endereco, telefone, situacao, dataContratacao, salario, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
       
        stmt.setString(1, administrador.getCpf());
        stmt.setString(2, administrador.getNome());
        stmt.setString(3, administrador.getEmail());
        stmt.setString(4, administrador.getSenha());
        stmt.setString(5, administrador.getEndereco());
        stmt.setString(6, administrador.getTelefone());
        stmt.setString(7, administrador.getSituacao());
        stmt.setDate(8, (Date) administrador.getDataContratacao());
        stmt.setDouble(9, administrador.getSalario());
        stmt.setString(10, administrador.getTipo());

        stmt.executeUpdate();
        return "Administrador inserido com sucesso!";
        
        }catch (SQLException e) {
            System.err.println("Erro ao executar a consulta SQL: " + e.getMessage());
        }

        return "Erro!";
    }
    
    
    public List<Pessoa> listarPessoas(String tipo) {
        if(tipo.equals("CLIENTE")){
        List<Pessoa> clientes = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(sql, sql, sql, sql, tipo, sql);
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setSalario(rs.getDouble("salario"));
                cliente.setSituacao(rs.getString("situacao"));
                clientes.add(cliente);
            }
        }catch (SQLException e) {
            System.err.println("Erro ao executar a consulta SQL: " + e.getMessage());
        }
        return clientes;
        }else if(tipo.equals("FUNCIONARIO")){
        List<Pessoa> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario(sql, sql, sql, sql, tipo, 0, null, sql);
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
        }catch (SQLException e) {
            System.err.println("Erro ao executar a consulta SQL: " + e.getMessage());
        } 

        return funcionarios;

        }else if(tipo.equals("ADMINISTRADOR")){
        List<Pessoa> administradores = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Administrador administrador = new Administrador(sql, sql, sql, sql, tipo, 0, null, sql);
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
        }catch (SQLException e) {
            System.err.println("Erro ao executar a consulta SQL: " + e.getMessage());
        } 

        return administradores;      

        }
        return null;
       
        
    }

    public static String atualizarPessoa(Cliente cliente) throws SQLException {
    String sql = "UPDATE pessoa SET nome = ?, email = ?, senha = ?, endereco = ?, telefone = ?, situacao = ?, dataContratacao = ?, salario = ? WHERE cpf = ?";
        
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {

        
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getEmail());
        stmt.setString(3, cliente.getSenha());
        stmt.setString(4, cliente.getEndereco());
        stmt.setString(6, cliente.getSituacao());
        stmt.setString(9, cliente.getCpf());

        return "Cliente Atualizado!!";
    
        }
    }

    public static String atualizarPessoa(Funcionario funcionario) throws SQLException {
    String sql = "UPDATE pessoa SET nome = ?, email = ?, senha = ?, endereco = ?, telefone = ?, situacao = ?, dataContratacao = ?, salario = ? WHERE cpf = ?";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setString(1, funcionario.getNome());
        stmt.setString(2, funcionario.getEmail());
        stmt.setString(3, funcionario.getSenha());
        stmt.setString(4, funcionario.getEndereco());
        stmt.setString(5, funcionario.getTelefone());
        stmt.setDate(7, (Date) funcionario.getDataContratacao());
        stmt.setDouble(8, funcionario.getSalario());
        stmt.setString(9, funcionario.getCpf());

        return "Funcionario atualizado";
        }
    }
    
    public static String atualizarPessoa(Administrador administrador) throws SQLException {
    String sql = "UPDATE pessoa SET nome = ?, email = ?, senha = ?, endereco = ?, telefone = ?, situacao = ?, dataContratacao = ?, salario = ? WHERE cpf = ?";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setString(1, administrador.getNome());
        stmt.setString(2, administrador.getEmail());
        stmt.setString(3, administrador.getSenha());
        stmt.setString(4, administrador.getEndereco());
        stmt.setString(5, administrador.getTelefone());
        stmt.setDate(7, (Date) administrador.getDataContratacao());
        stmt.setDouble(8, administrador.getSalario());
        stmt.setString(9, administrador.getCpf());

        return "Administrador Atualizado";
        }
    }


    public String excluirPessoa(String cpf) {
    String sql = "DELETE FROM pessoa WHERE cpf = ?";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
       
        stmt.setString(1, cpf);
        stmt.executeUpdate();
        return "Pessoa excluída com sucesso!";
        
    }catch (SQLException e) {
        System.err.println("Erro ao executar a consulta SQL: " + e.getMessage());
    }
    return "Erro ao excluir pessoa";
    }

    public Pessoa buscarPessoa(Cliente cliente, String cpf) { 
    String sql = "SELECT * FROM pessoa WHERE cpf = ?";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
        
        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {                                        
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setSenha(rs.getString("senha"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setSituacao(rs.getString("situacao"));
            return cliente;
        }
    }catch (SQLException e) {
        System.err.println("Erro ao executar a consulta SQL: " + e.getMessage());
    } 
    return null;
    }

    public Pessoa buscarPessoa(Funcionario funcionario, String cpf) { 
    String sql = "SELECT * FROM pessoa WHERE cpf = ?";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
        
        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
      
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
    }catch (SQLException e) {
        System.err.println("Erro ao executar a consulta SQL: " + e.getMessage());
    } 
    return null;
    }

    public Pessoa buscarPessoa(Administrador administrador, String cpf) {
        String sql = "SELECT * FROM pessoa WHERE cpf = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
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
        } catch (SQLException e) {
            System.err.println("Erro ao executar a consulta SQL: " + e.getMessage());
        }
        return null;
    }

    //Fiz essa função baseada na decima mas não sei se está certa
    public static boolean existePessoa(String cpf) {
        String sql = "SELECT * FROM pessoa WHERE cpf = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar a consulta SQL: " + e.getMessage());
        }
        return false;
    }

}
