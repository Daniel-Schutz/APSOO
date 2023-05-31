package Persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Quarto;


public class QuartoDAO{
    private static Connection conexao;

    public static void criarQuarto(Quarto quarto) throws SQLException {
    String sql = "INSERT INTO quarto (valor, tipoQuarto, local, descricao, situacao) VALUES (?, ?, ?, ?, ?)";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
        
        stmt.setDouble(1, quarto.getValor());
        stmt.setString(2, quarto.getTipoQuarto());
        stmt.setString(3, quarto.getLocal());
        stmt.setString(4, quarto.getDescricao());
        stmt.setString(5, quarto.getSituacao());

        stmt.executeUpdate();
        System.out.println("Quarto inserido com sucesso!");
        
        } 
    }

    public static List<Quarto> listarQuartos() throws SQLException {
    List<Quarto> quartos = new ArrayList<>();
    String sql = "SELECT * FROM quarto";
    
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            float valor = rs.getFloat("valor");
            String tipoQuarto = rs.getString("tipoQuarto");
            String local = rs.getString("local");
            String descricao = rs.getString("descricao");
            String situacao = rs.getString("situacao");
            int idQuarto = rs.getInt("idQuarto");

            Quarto quarto = new Quarto(situacao, descricao, local, tipoQuarto, valor, idQuarto);
            quartos.add(quarto);
        }

        return quartos;
        }
    }


    public static void atualizarQuarto(Quarto quarto) throws SQLException {
    String sql = "UPDATE quarto SET valor = ?, tipoQuarto = ?, local = ?, descricao = ?, situacao = ? WHERE idQuarto = ?";
    try(PreparedStatement stmt = conexao.prepareStatement(sql)) {
        
        stmt.setDouble(1, quarto.getValor());
        stmt.setString(2, quarto.getTipoQuarto());
        stmt.setString(3, quarto.getLocal());
        stmt.setString(4, quarto.getDescricao());
        stmt.setString(5, quarto.getSituacao());
        stmt.setInt(6, quarto.getIdQuarto());

        stmt.executeUpdate();
        System.out.println("Quarto atualizado com sucesso!");
        
        } 
    }

    public static void excluirQuarto(int idQuarto) throws SQLException {
    String sql = "DELETE FROM quarto WHERE idQuarto = ?";
    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, idQuarto);
        stmt.executeUpdate();
        System.out.println("Quarto excluído com sucesso!");
        }
    }


    public static Quarto buscarQuarto(int idQuarto) throws SQLException {
    String sql = "SELECT * FROM quarto WHERE idQuarto = ?";
    
    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {        
        stmt.setInt(1, idQuarto);
        
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            float valor = rs.getFloat("valor");
            String tipoQuarto = rs.getString("tipoQuarto");
            String local = rs.getString("local");
            String descricao = rs.getString("descricao");
            String situacao = rs.getString("situacao");

            Quarto quarto = new Quarto(situacao, descricao, local, tipoQuarto, valor, idQuarto);
            
            return quarto;
        } else {
            return null;
        }
    }
}

public static void setConexao(Connection conexao){
    QuartoDAO.conexao = conexao;
}
   
}