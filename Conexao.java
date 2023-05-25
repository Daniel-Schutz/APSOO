import java.sql.*;

import javax.swing.SwingUtilities;

import view.*;

public class Conexao {
    private Connection conexao;
    private String url;
    private String usuario;
    private String senha;
    
    public Conexao(String url, String usuario, String senha) {
        this.url = url;
        this.usuario = usuario;
        this.senha = senha;
    }
    
    public void conectar() throws SQLException {
        conexao = DriverManager.getConnection(url, usuario, senha);
        System.out.println("Conexão estabelecida com sucesso!");
    }
    
    public void desconectar() throws SQLException {
        conexao.close();
        System.out.println("Conexão encerrada com sucesso!");
    }

    public static void main(String[] args) {
        Conexao connect = new Conexao("jdbc:mysql://192.168.17.1:3306/meubanco", "root", "fernanda123");
        SwingUtilities.invokeLater(() -> new LoginInterface(connect.conexao));
    }
}
