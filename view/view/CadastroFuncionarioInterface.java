package view;
import javax.swing.*;
import java.sql.Connection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; // Import the ArrayList class

public class CadastroFuncionarioInterface extends JFrame {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField emailField;
    private JTextField telefoneField;
    private JTextField enderecoField;

    public CadastroFuncionarioInterface(ArrayList<Funcionario> funcionarios, Connection conexao) {
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

                // Criar um novo objeto Funcionario com base nas informações inseridas
                Funcionario novoFuncionario = new Funcionario(nome, cpf, email, telefone, endereco);

                // Adicionar o novo funcionário ao ArrayList de funcionários
                funcionarios.add(novoFuncionario);

                // Exibir uma mensagem de confirmação
                JOptionPane.showMessageDialog(CadastroFuncionarioInterface.this, "Novo funcionário cadastrado!");

                // Limpar os campos de entrada após o cadastro
                nomeField.setText("");
                cpfField.setText("");
                emailField.setText("");
                telefoneField.setText("");
                enderecoField.setText("");

                // Voltar para a tela principal
                PrincipalInterface principalInterface = new PrincipalInterface(conexao);
                principalInterface.setVisible(true);

                // Fechar a tela de cadastro de funcionário
                dispose();
            }
        });

        // Adiciona o listener para o botão de voltar
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Fecha a tela de cadastro de funcionário e abre a tela principal
                dispose();
                PrincipalInterface principalInterface = new PrincipalInterface(conexao);
                principalInterface.setVisible(true);
            }
        });

        // Adiciona o painel à janela
        add(panel);

        // Exibe a janela
        setVisible(true);
    }
}
