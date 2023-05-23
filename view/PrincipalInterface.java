import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalInterface extends JFrame {
    public PrincipalInterface() {
        // Configurações da janela
        setTitle("Tela Principal");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criação dos componentes
        JButton cadastrarFuncionarioButton = new JButton("Cadastrar Funcionário");
        JButton criarReservaButton = new JButton("Criar Reserva");

        // Layout
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(cadastrarFuncionarioButton);
        panel.add(criarReservaButton);

        // Adiciona os listeners para os botões
        cadastrarFuncionarioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de cadastro de funcionário
                CadastroFuncionarioInterface cadastroFuncionarioInterface = new CadastroFuncionarioInterface();
                cadastroFuncionarioInterface.setVisible(true);
                dispose();
            }
        });

        criarReservaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código para tratar o evento de criar reserva
                JOptionPane.showMessageDialog(PrincipalInterface.this, "Reserva criada!");
            }
        });

        // Adiciona o painel à janela
        add(panel);

        // Exibe a janela
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PrincipalInterface());
    }
}
