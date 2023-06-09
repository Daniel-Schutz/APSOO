package Persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.Reserva;
import model.Quarto;

public class ReservaDAO{
    private static Connection conexao;

    public ReservaDAO(Connection conexao){
        ReservaDAO.conexao = conexao;
    }

    
    public static String criarReserva(Reserva reserva) throws SQLException {
        System.out.println("ReservaDAO: criarReserva");
        String sql = "INSERT INTO reserva (data, diasEstadia, tipoPagamento, situacao, pessoaCPF) VALUES (?, ?, ?, ?, ?, ?)";
        System.out.println("PESSOA CPF" + reserva.getpessoaCpf());
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDate(1, (Date) reserva.getData());
            stmt.setInt(2, reserva.getDiasEstadia());
            stmt.setString(3, reserva.getTipoPagamento());
            stmt.setString(4, reserva.getSituacao());
            stmt.setString(6, reserva.getpessoaCpf()); 
            
            stmt.executeUpdate();
            return "Reserva inserida com sucesso!";
        }
    }

        

    public static List<Reserva> listarReservas() throws SQLException {
    String sql = "SELECT * FROM reserva";
    List<Reserva> reservas = new ArrayList<>();

    try (PreparedStatement stmt = conexao.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Reserva reserva = new Reserva(0, null, 0, sql, sql, sql);
            reserva.setCodigo(rs.getInt("codigo"));
            reserva.setData(rs.getDate("data"));
            reserva.setDiasEstadia(rs.getInt("diasEstadia"));
            reserva.setTipoPagamento(rs.getString("tipoPagamento"));
            reserva.setSituacao(rs.getString("situacao"));
            reserva.setpessoaCpf(rs.getString("pessoaCPF"));

            reservas.add(reserva);
            }
        }

        return reservas;
    }

    public static String atualizarReserva(Reserva reserva) throws SQLException {
            String sql = "UPDATE reserva SET data = ?, diasEstadia = ?, tipoPagamento = ?, situacao = ? WHERE codigo = ?";

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setDate(1, (Date) reserva.getData());
                stmt.setInt(2, reserva.getDiasEstadia());
                stmt.setString(3, reserva.getTipoPagamento());
                stmt.setString(4, reserva.getSituacao());
                stmt.setInt(5, reserva.getCodigo());

                stmt.executeUpdate();
                return "Reserva atualizada com sucesso!";
            }
        }


    public static String excluirReserva(int codigo) throws SQLException {
    String sql = "DELETE FROM reserva WHERE codigo = ?";
    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, codigo);
        stmt.executeUpdate();
        return "Reserva excluída com sucesso!";
         
        }
    }


    public static Reserva buscarReserva(int codigo) throws SQLException {
    String sql = "SELECT * FROM reserva WHERE codigo = ?";

    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, codigo);
        ResultSet rs = stmt.executeQuery();
    
        if (rs.next()) {
            Reserva reserva = new Reserva(codigo, null, 0, sql, sql, sql);
            reserva.setCodigo(rs.getInt("id"));
            reserva.setData(rs.getDate("data"));
            reserva.setDiasEstadia(rs.getInt("diasEstadia"));
            reserva.setTipoPagamento(rs.getString("tipoPagamento"));
            reserva.setSituacao(rs.getString("situacao"));
            reserva.setpessoaCpf(rs.getString("pessoaCPF"));
            return reserva;
            }
        
            return null;
            
        }

        
    }

    
    public static List<Reserva> buscarReservaPorCpf(String cpf) {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva WHERE pessoaCPF = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);

            ResultSet rs = stmt.executeQuery();
            
             while(rs.next()) {
                Reserva resultado = new Reserva(0, null, 0, sql, sql, cpf);
                resultado.setCodigo(rs.getInt("codigo"));
                resultado.setData(rs.getDate("data"));
                resultado.setDiasEstadia(rs.getInt("diasEstadia"));
                resultado.setTipoPagamento(rs.getString("tipoPagamento"));
                resultado.setSituacao(rs.getString("situacao"));
                reservas.add(resultado);
            }

        
            
            return reservas;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

        
    }

    public static void setConexao(Connection conexao){
        ReservaDAO.conexao = conexao;
    }
}




    
