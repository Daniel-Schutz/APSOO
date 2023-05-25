package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.ReservaQuarto;


public class ReservaQuartoDAO{
    private Connection conexao;

    public ReservaQuartoDAO(Connection conexao){
        this.conexao = conexao;
    }

    public void criarReservaQuarto(ReservaQuarto reservaQuarto){
        String sql = "INSERT INTO reservaQuarto (codigoReserva, idQuarto) VALUES (?, ?)";

        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(1, reservaQuarto.getcodigoReserva());
            stmt.setInt(2, reservaQuarto.getIdQuarto());

            stmt.executeUpdate();
            System.out.println("ReservaQuarto inserida com sucesso!");
        }catch (SQLException e) {
            System.err.println("Erro ao executar a consulta SQL: " + e.getMessage());
        }
    }


    public List<ReservaQuarto> listarReservaQuarto() throws SQLException {
    String sql = "SELECT * FROM reservaQuarto";
    List<ReservaQuarto> reservasQuartos = new ArrayList<>();

    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            ReservaQuarto reservaQuarto = new ReservaQuarto(0, 0);
            reservaQuarto.setcodigoReserva(rs.getInt("codigoReserva"));
            reservaQuarto.setIdQuarto(rs.getInt("idQuarto"));
        

            reservasQuartos.add(reservaQuarto);
        }

            return reservasQuartos;
        }
    }


    public void atualizarReservaQuarto(int idReservaQuarto, int novoIdQuarto) throws SQLException {
    String sql = "UPDATE reservaQuarto SET idQuarto = ? WHERE idReservaQuarto = ?";
    
    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, novoIdQuarto);
        stmt.setInt(2, idReservaQuarto);
        stmt.executeUpdate();

        System.out.println("ReservaQuarto atualizada com sucesso!");
        
        }
    }


    public void excluirReservaQuarto(int idReservaQuarto) throws SQLException {
    String sql = "DELETE FROM reservaQuarto WHERE idReservaQuarto = ?";
    
    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, idReservaQuarto);
        stmt.executeUpdate();
        System.out.println("ReservaQuarto excluída com sucesso!");
        
        }
    }


    public List<ReservaQuarto> buscarReservaQuarto(int codigoDaReserva) throws SQLException { //Cliente não saberá informar ID para buscar, logo, terá que buscar pelo codigo da reserva, mantem essa função e cria outra so pra buscar todos os reservaquarto atrelados ao codigo da reserva
    String sql = "SELECT * FROM reservaQuarto WHERE codigoReserva = ?";
    
    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, codigoDaReserva);
        ResultSet rs = stmt.executeQuery();
        List<ReservaQuarto> resultado =  new ArrayList<>();
        int i =0;
        while (rs.next()) {
            ReservaQuarto reservaQuarto = new ReservaQuarto(codigoDaReserva, rs.getInt("idQuarto"));
            resultado.add(i, reservaQuarto);
            i++;
        } 
        return resultado;
    }
    }
}