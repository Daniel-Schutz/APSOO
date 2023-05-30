import javax.swing.SwingUtilities;

import view.LoginInterface;
import view.PrincipalInterface;

public class Main {
    public static void main(String[] args) {
        Conexao connect = new Conexao("jdbc:mysql://127.0.0.1:3306/hotelariadb", "root", "fernanda123");
        try{
             connect.conectar();
        } catch (Exception e){
            System.out.println(e);
        }
       
        SwingUtilities.invokeLater(() -> new PrincipalInterface(connect.getConexao()));
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
