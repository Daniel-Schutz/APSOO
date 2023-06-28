package Persistence;

import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Execute the query to save the current date and time to the database
// ...

import model.Hospedagem;

public class HospedagemDAO {
    private static Connection conexao;
    static LocalDateTime currentDateTime = LocalDateTime.now();

    public static String criarHospedagem(Hospedagem hospedagem) throws SQLException {

        String sql = "INSERT INTO hospedagem (horaCheckIn, horaCheckOut, codigoReserva) VALUES (?, ?, ?)";
        LocalDateTime currentDateTime = LocalDateTime.now();

        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(currentDateTime);
        Time time = new Time(timestamp.getTime());
        System.out.println(time);

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setTime(1, time);
            stmt.setTime(2, null);
            stmt.setInt(3, hospedagem.getCodigoReserva());

            stmt.executeUpdate();
            return "checkIn realizado";

        }

    }

    public static List<Integer> buscarCodigosReserva() throws SQLException {
        String sql = "SELECT DISTINCT codigoReserva FROM hospedagem";
        List<Integer> codigosReserva = new ArrayList<>();

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int codigoReserva = rs.getInt("codigoReserva");
                codigosReserva.add(codigoReserva);
            }

        }

        return codigosReserva;
    }

    // public static List<Hospedagem> listarHospedagem() throws SQLException {
    // String sql = "SELECT * FROM hospedagem";
    // List<Hospedagem> hospedagens = new ArrayList<>();

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

    // try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

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
    // }

    // public static Hospedagem buscarHospedagem(int idHospedagem) throws
    // SQLException {
    // String sql = "SELECT * FROM hospedagem WHERE idHospedagem = ?";

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
        HospedagemDAO.conexao = conexao;
    }

}