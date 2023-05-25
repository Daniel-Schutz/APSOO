package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Hospedagem;


public class HospedagemDAO{
    private Connection conexao;


    public HospedagemDAO(Connection conexao){
        this.conexao = conexao;
    }


    public void criarHospedagem(Hospedagem hospedagem){
        String sql = "INSERT INTO hospedagem (horaCheckIn, horaCheckOut, codigoReserva) VALUES (?, ?, ?)";
        
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setDate(1, hospedagem.getHoraCheckIn());
            stmt.setDate(2, hospedagem.getHoraCheckOut());
            stmt.setCodigoReserva(3, hospedagem.getcodigoReserva());
            
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
            hospedagem.setHoraCheckIn(rs.getDate("horaCheckIn"));
            hospedagem.setHoraCheckOut(rs.getDate("horaCheckOut"));
            hospedagem.setCodigoReserva(rs.getInt("codigoReserva"));
        

            hospedagens.add(hospedagem);
        }

            return hospedagens;
        }
    }


    public String atualizarHospedagem(Hospedagem hospedagem) throws SQLException {
    String sql = "UPDATE hospedagem SET horaCheckIn = ?, horaCheckOut = ? WHERE idHospedagem = ?";

    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setDate(1, hospedagem.getHoraCheckIn());
        stmt.setDate(2, hospedagem.getHoraCheckOut());
        stmt.setInt(3, hospedagem.getIdHospedagem());

        stmt.executeUpdate();
        return "Hospedagem Atualizada";
         
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
            hospedagem.setHoraCheckIn(rs.getDate("horaCheckIn"));
            hospedagem.setHoraCheckOut(rs.getDate("horaCheckOut"));
            hospedagem.setCodigoReserva(rs.getInt("codigoReserva"));

            return hospedagem;
        } else {
            return null;
        }
        }
    }


}