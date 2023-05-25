import javax.swing.SwingUtilities;

import view.LoginInterface;

public class Main {
    public static void main(String[] args) {
        Conexao connect = new Conexao("jdbc:mysql://localhost:3306/banco_de_dados", "root", "fernanda123");
        SwingUtilities.invokeLater(() -> new LoginInterface(connect.getConexao()));
    }
}


//codigo para testar a conexao com o banco de dados

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;

// public class Main {
//     public static void main(String[] args) {
//         Connection connection = null;
//         try {
//             Class.forName("com.mysql.jdbc.Driver");
//             String url = "jdbc:mysql://192.168.17.1:3306/meubanco";
//             String usuario = "root";
//             String senha = "fernanda123";
//             connection = DriverManager.getConnection(url, usuario, senha);
//             if (connection != null) {
//                 System.out.println("Conexão estabelecida!");
//             }
//         } catch (ClassNotFoundException | SQLException ex) {
//             System.out.println("Erro ao conectar ao banco de dados: " + ex.getMessage());
//         } finally {
//             try {
//                 if (connection != null) {
//                     connection.close();
//                 }
//             } catch (SQLException ex) {
//                 System.out.println("Erro ao fechar conexão com o banco de dados: " + ex.getMessage());
//             }
//         }
//     }
// }
