package view;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;

public class CadastroClienteInterface extends JFrame {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField emailField;
    private JTextField enderecoField;

    public CadastroClienteInterface(SisHotel sishotel) {
  
        // Configurações da janela
        setTitle("Cadastro de Cliente");
        setSize(300, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criação dos componentes
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel cpfLabel = new JLabel("CPF:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel enderecoLabel = new JLabel("Endereço:");
        nomeField = new JTextField(20);
        cpfField = new JTextField(20);
        emailField = new JTextField(20);
        enderecoField = new JTextField(20);
        JButton cadastrarButton = new JButton("Cadastrar");
        JButton voltarButton = new JButton("Voltar");

        // Layout
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(emailLabel);
        panel.add(emailField);
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
                String endereco = enderecoField.getText();
                String senha = "RETIRAR SENHA DPS";
                String situacao = "VAZIO"; //QUAIS TIPOS DE SITUAÇÃO??

                // Criar um novo objeto Funcionario com base nas informações inseridas
                sishotel.cadastrarCliente(nome, cpf, email, senha, endereco, situacao);
                //Cliente novoCliente = new Cliente(nome, cpf, email, telefone, endereco);

                // Adicionar o novo funcionário ao ArrayList de funcionários
                //clientes.add(novoCliente);

                // Exibir uma mensagem de confirmação
                JOptionPane.showMessageDialog(CadastroClienteInterface.this, "Novo Cliente cadastrado!");

                // Limpar os campos de entrada após o cadastro
                nomeField.setText("");
                cpfField.setText("");
                emailField.setText("");
                enderecoField.setText("");

                // Voltar para a tela principal
                PrincipalInterface principalInterface = new PrincipalInterface(sishotel);
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
                PrincipalInterface principalInterface = new PrincipalInterface(sishotel);
                principalInterface.setVisible(true);
            }
        });

        // Adiciona o painel à janela
        add(panel);

        // Exibe a janela
        setVisible(true);
    }
}
