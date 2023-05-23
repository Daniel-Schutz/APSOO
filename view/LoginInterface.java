import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginInterface extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginInterface() {
        // Configurações da janela
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criação dos componentes
        JLabel usernameLabel = new JLabel("Usuário:");
        JLabel passwordLabel = new JLabel("Senha:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Entrar");

        // Layout
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // espaço em branco para alinhar corretamente
        panel.add(loginButton);

        // Adiciona o listener para o botão de login
        loginButton.addActionListener(this);

        // Adiciona o painel à janela
        add(panel);

        // Exibe a janela
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Verifica se o usuário e senha estão corretos (exemplo: usuário=admin,
        // senha=123)
        if (username.equals("admin") && password.equals("123")) {
            JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");

            // Redireciona para a tela principal
            PrincipalInterface principalInterface = new PrincipalInterface();
            principalInterface.setVisible(true);
            dispose(); // Fecha a janela de login
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginInterface());
    }
}
