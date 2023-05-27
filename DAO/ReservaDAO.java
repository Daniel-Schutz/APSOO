package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.Reserva;

public class ReservaDAO{
    private static Connection conexao;

    public ReservaDAO(Connection conexao){
        this.conexao = conexao;
    }

    
    public static String criarReserva(Reserva reserva) throws SQLException {
        String sql = "INSERT INTO reserva (data, diasEstadia, tipoPagamento, situacao, pessoaCPF) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDate(1, (Date) reserva.getData());
            stmt.setInt(2, reserva.getDiasEstadia());
            stmt.setString(3, reserva.getTipoPagamento());
            stmt.setString(4, reserva.getSituacao());
            stmt.setString(5, reserva.getpessoaCpf());       

            stmt.executeUpdate();
            return "Reserva inserida com sucesso!";
        }
    }

        

    public List<Reserva> listarReservas() throws SQLException {
    String sql = "SELECT * FROM reserva";
    List<Reserva> reservas = new ArrayList<>();

    try (PreparedStatement stmt = conexao.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Reserva reserva = new Reserva(0, null, 0, sql, sql, sql, null);
            reserva.setCodigo(rs.getInt("codigo"));
            reserva.setData(rs.getDate("data"));
            reserva.setDiasEstadia(rs.getInt("diasEstadia"));
            reserva.setTipoPagamento(rs.getString("tipoPagamento"));
            reserva.setSituacao(rs.getString("situacao"));
            reserva.setCpf(rs.getString("pessoaCPF"));

            reservas.add(reserva);
            }
        }

        return reservas;
    }

    public String atualizarReserva(Reserva reserva) throws SQLException {
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


    public String excluirReserva(int codigo) throws SQLException {
    String sql = "DELETE FROM reserva WHERE codigo = ?";
    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, codigo);
        stmt.executeUpdate();
        return "Reserva excluída com sucesso!";
         
        }
    }


    public Reserva buscarReserva(int codigo) throws SQLException {
    String sql = "SELECT * FROM reserva WHERE codigo = ?";

    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, codigo);
        ResultSet rs = stmt.executeQuery();
    
        if (rs.next()) {
            Reserva reserva = new Reserva(codigo, null, codigo, sql, sql, sql, null);
            reserva.setCodigo(rs.getInt("id"));
            reserva.setData(rs.getDate("data"));
            reserva.setDiasEstadia(rs.getInt("diasEstadia"));
            reserva.setTipoPagamento(rs.getString("tipoPagamento"));
            reserva.setSituacao(rs.getString("situacao"));
            reserva.setCpf(rs.getString("pessoaCPF"));
            return reserva;
            }
        
            return null;
            
        }

        
    }

    
    public Collection<String> buscarReservaPorCpf(String cpf) {
        Collection<String> resultado = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco_de_dados", "usuario", "senha");

            String sql = "SELECT * FROM reserva INNER JOIN reservaQuarto ON reserva.codigo = reservaQuarto.codigoReserva INNER JOIN hospedagem ON reserva.codigo = hospedagem.codigoReserva INNER JOIN quarto ON reservaQuarto.idQuarto = quarto.idQuarto INNER JOIN pessoa ON pessoa.cpf = reserva.pessoaCPF WHERE pessoaCPF = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cpf);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String detalhesReserva = resultSet.getString("detalhes");
                resultado.add(detalhesReserva);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

}




    