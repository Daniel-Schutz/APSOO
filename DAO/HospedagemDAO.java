package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Hospedagem;


public class HospedagemDAO(){
    private Connection conexao;


    public HospedagemDAO(){
        this.conexao = conexao;
    }


    public void criarHospedagem(String horaCheckIn, String getHoraCheckOut, int codigoReserva){
        String sql = "INSERT INTO hospedagem (horaCheckIn, horaCheckOut, codigoReserva) VALUES (?, ?, ?)";
        
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setHoraCheckIn(1, horaCheckIn);
            stmt.setHoraCheckOut(2, horaCheckOut);
            stmt.setCodigoReserva(3, codigoReserva);
            
            stmt.executeUpdate();
            System.out.println("Hospedagem inserida com sucesso!");

        }

    }


    public List<Hospedagem> listarHospedagem() throws SQLException {
    String sql = "SELECT * FROM hospedagem";
    List<Hospedagem> hospedagens = new ArrayList<>();

    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Hospedagem hospedagem = new Hospedagem();
            hospedagem.setCodigoReserva(rs.getString("horaCheckIn"));
            hospedagem.setIdQuarto(rs.getString("horaCheckOut"));
            hospedagem.setCodigoReserva(rs.getInt("codigoReserva"));
        

            hospedagens.add(hospedagem);
        }

            return hospedagens;
        }
    }


    public void atualizarHospedagem(String horaCheckIn, String horaCheckOut, int idHospedagem) throws SQLException {
    String sql = "UPDATE hospedagem SET horaCheckIn = ?, horaCheckOut = ? WHERE idHospedagem = ?";

    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setString(1, horaCheckIn);
        stmt.setString(2, horaCheckOut);
        stmt.setInt(3, idHospedagem);

        stmt.executeUpdate();
        System.out.println("Hospedagem atualizada com sucesso!");
         
        }
    }

    public void excluirHospedagem(int idHospedagem) throws SQLException {
    String sql = "DELETE FROM hospedagem WHERE idHospedagem = ?";

    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, idHospedagem);
        stmt.executeUpdate();
        System.out.println("Hospedagem excluída com sucesso!");
        
        }
    }


    public Hospedagem buscarHospedagem(int idHospedagem) throws SQLException {
    String sql = "SELECT * FROM hospedagem WHERE idHospedagem = ?";

    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, idHospedagem);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Hospedagem hospedagem = new Hospedagem();
            hospedagem.setIdHospedagem(rs.getInt("idHospedagem"));
            hospedagem.setHoraCheckIn(rs.getString("horaCheckIn"));
            hospedagem.setHoraCheckOut(rs.getString("horaCheckOut"));
            hospedagem.setCodigoReserva(rs.getInt("codigoReserva"));

            return hospedagem;
        } else {
            return null;
        }
        }
    }


}