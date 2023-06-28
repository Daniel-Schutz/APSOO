package Persistence;

import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;

import model.Hospedagem;

public class HospedagemDAO {
    private static Connection conexao;

<<<<<<< HEAD
    public static String criarHospedagem(Hospedagem hospedagem) throws SQLException {
        System.out.println(hospedagem.getHoraCheckIn());
        System.out.println("sim");
        System.out.println(Date.valueOf(hospedagem.getHoraCheckIn()));
        String sql = "INSERT INTO hospedagem (horaCheckIn, horaCheckOut, codigoReserva) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(hospedagem.getHoraCheckIn()));
            stmt.setDate(2, null);
            stmt.setInt(3, hospedagem.getCodigoReserva());

=======
    public static String criarHospedagem(Hospedagem hospedagem) throws SQLException{
        
        String sql = "INSERT INTO hospedagem (horaCheckIn, horaCheckOut, codigoReserva) VALUES (?, ?, ?)";
        
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setDate(1, Date.valueOf(hospedagem.getHoraCheckIn()));
            stmt.setDate(2, null);
            stmt.setInt(3, hospedagem.getCodigoReserva());
            
>>>>>>> d002aee3c3f3fca9d49b8c90eb5a01931a7b3198
            stmt.executeUpdate();
            return "checkIn realizado";

        }

    }

    // public static List<Hospedagem> listarHospedagem() throws SQLException {
    // String sql = "SELECT * FROM hospedagem";
    // List<Hospedagem> hospedagens = new ArrayList<>();

<<<<<<< HEAD
    // try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
    // ResultSet rs = stmt.executeQuery();

    // while (rs.next()) {
    // Hospedagem hospedagem = new Hospedagem();
    // hospedagem.setHoraCheckIn(rs.getDate("horaCheckIn"));
    // hospedagem.setHoraCheckOut(rs.getDate("horaCheckOut"));
    // hospedagem.setCodigoReserva(rs.getInt("codigoReserva"));

    // hospedagens.add(hospedagem);
    // }

    // return hospedagens;
    // }
    // }

    // public static String atualizarHospedagem(Hospedagem hospedagem) throws
    // SQLException {
    // String sql = "UPDATE hospedagem SET horaCheckIn = ?, horaCheckOut = ? WHERE
    // idHospedagem = ?";
=======
    // public static List<Hospedagem> listarHospedagem() throws SQLException {
    // String sql = "SELECT * FROM hospedagem";
    // List<Hospedagem> hospedagens = new ArrayList<>();

    // try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
    //     ResultSet rs = stmt.executeQuery();

    //     while (rs.next()) {
    //         Hospedagem hospedagem = new Hospedagem();
    //         hospedagem.setHoraCheckIn(rs.getDate("horaCheckIn"));
    //         hospedagem.setHoraCheckOut(rs.getDate("horaCheckOut"));
    //         hospedagem.setCodigoReserva(rs.getInt("codigoReserva"));
        

    //         hospedagens.add(hospedagem);
    //     }

    //         return hospedagens;
    //     }
    // }
>>>>>>> d002aee3c3f3fca9d49b8c90eb5a01931a7b3198

    // try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

<<<<<<< HEAD
    // stmt.setDate(1, hospedagem.getHoraCheckIn());
    // stmt.setDate(2, hospedagem.getHoraCheckOut());
    // stmt.setInt(3, hospedagem.getIdHospedagem());

    // stmt.executeUpdate();
    // return "Hospedagem Atualizada";

    // }
    // }

    // public static void excluirHospedagem(int idHospedagem) throws SQLException {
    // String sql = "DELETE FROM hospedagem WHERE idHospedagem = ?";

    // try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
    // stmt.setInt(1, idHospedagem);
    // stmt.executeUpdate();
    // System.out.println("Hospedagem excluída com sucesso!");

    // }
=======
    // public static String atualizarHospedagem(Hospedagem hospedagem) throws SQLException {
    // String sql = "UPDATE hospedagem SET horaCheckIn = ?, horaCheckOut = ? WHERE idHospedagem = ?";

    // try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

    //     stmt.setDate(1, hospedagem.getHoraCheckIn());
    //     stmt.setDate(2, hospedagem.getHoraCheckOut());
    //     stmt.setInt(3, hospedagem.getIdHospedagem());

    //     stmt.executeUpdate();
    //     return "Hospedagem Atualizada";
         
    //     }
    // }

    // public static void excluirHospedagem(int idHospedagem) throws SQLException {
    // String sql = "DELETE FROM hospedagem WHERE idHospedagem = ?";

    // try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
    //     stmt.setInt(1, idHospedagem);
    //     stmt.executeUpdate();
    //     System.out.println("Hospedagem excluída com sucesso!");
        
    //     }
>>>>>>> d002aee3c3f3fca9d49b8c90eb5a01931a7b3198
    // }

    // public static Hospedagem buscarHospedagem(int idHospedagem) throws
    // SQLException {
    // String sql = "SELECT * FROM hospedagem WHERE idHospedagem = ?";

<<<<<<< HEAD
    // try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
    // stmt.setInt(1, idHospedagem);
    // ResultSet rs = stmt.executeQuery();

    // if (rs.next()) {
    // Hospedagem hospedagem = new Hospedagem();
    // hospedagem.setIdHospedagem(rs.getInt("idHospedagem"));
    // hospedagem.setHoraCheckIn(rs.getDate("horaCheckIn"));
    // hospedagem.setHoraCheckOut(rs.getDate("horaCheckOut"));
    // hospedagem.setCodigoReserva(rs.getInt("codigoReserva"));

    // return hospedagem;
    // } else {
    // return null;
    // }
    // }
    // }

    public static void setConexao(Connection conexao) {
=======
    // public static Hospedagem buscarHospedagem(int idHospedagem) throws SQLException {
    // String sql = "SELECT * FROM hospedagem WHERE idHospedagem = ?";

    // try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
    //     stmt.setInt(1, idHospedagem);
    //     ResultSet rs = stmt.executeQuery();

    //     if (rs.next()) {
    //         Hospedagem hospedagem = new Hospedagem();
    //         hospedagem.setIdHospedagem(rs.getInt("idHospedagem"));
    //         hospedagem.setHoraCheckIn(rs.getDate("horaCheckIn"));
    //         hospedagem.setHoraCheckOut(rs.getDate("horaCheckOut"));
    //         hospedagem.setCodigoReserva(rs.getInt("codigoReserva"));

    //         return hospedagem;
    //     } else {
    //         return null;
    //     }
    //     }
    // }

    public static void setConexao(Connection conexao){
>>>>>>> d002aee3c3f3fca9d49b8c90eb5a01931a7b3198
        HospedagemDAO.conexao = conexao;
    }

    
}