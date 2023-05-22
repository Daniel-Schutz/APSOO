import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Reserva;


public class ReservaDAO(){
    private Connection conexao;

    public ReservaDAO(Connection conexao){
        this.conexao = conexao;
    }

    
    public void criarReserva(Date data, int diasEstadia, String tipoPagamento, String situacao, String pessoaCPF) throws SQLException {
        String sql = "INSERT INTO reserva (data, diasEstadia, tipoPagamento, situacao, pessoaCPF) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDate(1, data);
            stmt.setInt(2, diasEstadia);
            stmt.setString(3, tipoPagamento);
            stmt.setString(4, situacao);
            stmt.setString(5, pessoaCPF);

            stmt.executeUpdate();
            System.out.println("Reserva inserida com sucesso!");
        }
    }


    public List<Reserva> listarReservas() throws SQLException {
    String sql = "SELECT * FROM reserva";
    List<Reserva> reservas = new ArrayList<>();

    try (PreparedStatement stmt = conexao.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Reserva reserva = new Reserva();
            reserva.setId(rs.getInt("id"));
            reserva.setData(rs.getDate("data"));
            reserva.setDiasEstadia(rs.getInt("diasEstadia"));
            reserva.setTipoPagamento(rs.getString("tipoPagamento"));
            reserva.setSituacao(rs.getString("situacao"));
            reserva.setPessoaCPF(rs.getString("pessoaCPF"));

            reservas.add(reserva);
            }
        }

        return reservas;
    }

    public void atualizarReserva(Date data, int diasEstadia, String tipoPagamento, String situacao, int codigo) throws SQLException {
            String sql = "UPDATE reserva SET data = ?, diasEstadia = ?, tipoPagamento = ?, situacao = ? WHERE codigo = ?";

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setDate(1, data);
                stmt.setInt(2, diasEstadia);
                stmt.setString(3, tipoPagamento);
                stmt.setString(4, situacao);
                stmt.setInt(5, codigo);

                stmt.executeUpdate();
                System.out.println("Reserva atualizada com sucesso!");
            }
        }


    public void excluirReserva(int codigo) throws SQLException {
    String sql = "DELETE FROM reserva WHERE codigo = ?";
    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, codigo);
        stmt.executeUpdate();
        System.out.println("Reserva excluída com sucesso!");
         
        }
    }


    public Reserva buscarReserva(int codigo) throws SQLException {
    String sql = "SELECT * FROM reserva WHERE codigo = ?";

    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, codigo);
        ResultSet rs = stmt.executeQuery();
    
        if (rs.next()) {
            reserva = new Reserva();
            reserva.setId(rs.getInt("id"));
            reserva.setData(rs.getDate("data"));
            reserva.setDiasEstadia(rs.getInt("diasEstadia"));
            reserva.setTipoPagamento(rs.getString("tipoPagamento"));
            reserva.setSituacao(rs.getString("situacao"));
            reserva.setPessoaCPF(rs.getString("pessoaCPF"));
            return reserva;
            }
        
        }

        return null;
    }

}