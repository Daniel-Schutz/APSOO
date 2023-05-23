import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroFuncionarioInterface extends JFrame {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField emailField;
    private JTextField telefoneField;
    private JTextField enderecoField;

    public CadastroFuncionarioInterface() {
        // Configurações da janela
        setTitle("Cadastro de Funcionário");
        setSize(300, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criação dos componentes
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel cpfLabel = new JLabel("CPF:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel telefoneLabel = new JLabel("Telefone:");
        JLabel enderecoLabel = new JLabel("Endereço:");
        nomeField = new JTextField(20);
        cpfField = new JTextField(20);
        emailField = new JTextField(20);
        telefoneField = new JTextField(20);
        enderecoField = new JTextField(20);
        JButton cadastrarButton = new JButton("Cadastrar");
        JButton voltarButton = new JButton("Voltar");

        // Layout
        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(telefoneLabel);
        panel.add(telefoneField);
        panel.add(enderecoLabel);
        panel.add(enderecoField);
        panel.add(new JLabel()); // espaço em branco para alinhar corretamente
        panel.add(cadastrarButton);
        panel.add(new JLabel()); // espaço em branco para alinhar corretamente
        panel.add(voltarButton);

        // Adiciona o listener para o botão de cadastrar
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obter as informações inseridas pelo usuário
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String email = emailField.getText();
                String telefone = telefoneField.getText();
                String endereco = enderecoField.getText();

                // Salvar as informações do funcionário (exemplo: apenas exibir as informações)
                JOptionPane.showMessageDialog(CadastroFuncionarioInterface.this,
                        "Novo funcionário cadastrado:\nNome: " + nome + "\nCPF: " + cpf + "\nEmail: " + email +
                                "\nTelefone: " + telefone + "\nEndereço: " + endereco);

                // Limpar os campos de entrada após o cadastro
                nomeField.setText("");
                cpfField.setText("");
                emailField.setText("");
                telefoneField.setText("");
                enderecoField.setText("");
            }
        });

        // Adiciona o listener para o botão de voltar
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Fecha a tela de cadastro de funcionário e abre a tela principal
                dispose();
                PrincipalInterface principalInterface = new PrincipalInterface();
                principalInterface.setVisible(true);
            }
        });

        // Adiciona o painel à janela
        add(panel);

        // Exibe a janela
        setVisible(true);
    }
}
