import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.Reserva;

public class ReservaDAO{
    private Connection conexao;

    public ReservaDAO(Connection conexao){
        this.conexao = conexao;
    }

    
    public void criarReserva(Reserva reserva) throws SQLException {
        String sql = "INSERT INTO reserva (data, diasEstadia, tipoPagamento, situacao, pessoaCPF) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDate(1, reserva.getData());
            stmt.setInt(2, reserva.getDiasEstadia());
            stmt.setString(3, reserva.getTipoPagamento());
            stmt.setString(4, reserva.getSituacao());
            stmt.setString(5, reserva.getCpf());       // irá receber cpf de pessoa por parametro ou pelo método get?

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

    public void atualizarReserva(Reserva reserva) throws SQLException {
            String sql = "UPDATE reserva SET data = ?, diasEstadia = ?, tipoPagamento = ?, situacao = ? WHERE codigo = ?";

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setDate(1, reserva.getData());
                stmt.setInt(2, reserva.getDiasEstadia());
                stmt.setString(3, reserva.getTipoPagamento());
                stmt.setString(4, reserva.getSituacao());
                stmt.setInt(5, reserva.getCodigo());

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
    
    public Collection<String> buscarReservaPorCpf(String cpf) {
        Collection<String> resultado = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco_de_dados", "usuario", "senha");

            String sql = "SELECT * FROM reserva INNER JOIN reservaQuarto 
            ON reserva.codigo = reservaQuarto.codigoReserva INNER JOIN hospedagem
            ON reserva.codigo = hospedagem.codigoReserva INNER JOIN quarto
            ON reservaQuarto.idQuarto = quarto.idQuarto INNER JOIN pessoa
            ON pessoa.cpf = reserva.pessoaCPF
            WHERE pessoaCPF = ?";

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
