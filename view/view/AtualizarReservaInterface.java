package view;

import javax.swing.*;
import java.sql.Connection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; // Import the ArrayList class
import controller.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.text.ParseException;

public class AtualizarReservaInterface extends JFrame {
    private JTextField Field;
    private JTextField cpfField;
    private ArrayList<Funcionario> funcionarios;

    // private SisHotel sisHotel;

    public AtualizarReservaInterface(Connection conexao) {

        // this.sisHotel = new SisHotel(conexao);

        setTitle("Pesquisar cliente");
        setSize(300, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicialização da lista de funcionários
        funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("João", "123456789", "joao@example.com", "1234567890", "Rua A, 123"));
        funcionarios.add(new Funcionario("Maria", "987654321", "maria@example.com", "0987654321", "Rua B, 456"));

        JLabel cpfLabel = new JLabel("CPF:");

        JTextField cpfField = new JTextField(20);

        JButton pesquisarClienteButton = new JButton("Pesquisar Cliente");
        JButton voltarButton = new JButton("Voltar");

        JPanel panel = new JPanel(new GridLayout(10, 2));
        panel.add(cpfLabel);
        panel.add(cpfField);

        panel.add(new JLabel()); // espaço em branco para alinhar corretamente
        panel.add(pesquisarClienteButton);
        panel.add(new JLabel()); // espaço em branco para alinhar corretamente
        panel.add(voltarButton);

        getContentPane().add(panel, BorderLayout.CENTER);

        pesquisarClienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obter as informações inseridas pelo usuário
                String cpf = cpfField.getText();
                // Abre a lista de funcionários para edição
                ListaFuncionariosInterface listaFuncionariosInterface = new ListaFuncionariosInterface(conexao);
                listaFuncionariosInterface.setVisible(true);
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

    public void pagamento(char tipoPagamento) {
    }

    public void selecaoQuartos() {
    }

    public void escolhaConfirmarOuCancelar() {

    }

    private class ListaFuncionariosInterface extends JFrame {
        private JList<String> funcionariosList;

        public ListaFuncionariosInterface(Connection conexao) {
            // Configurações da janela
            setTitle("Lista de Funcionários");
            setSize(300, 150);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Criação dos componentes
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (Funcionario funcionario : funcionarios) {
                listModel.addElement(funcionario.getNome());
            }
            funcionariosList = new JList<>(listModel);
            JScrollPane scrollPane = new JScrollPane(funcionariosList);
            JButton voltarButton = new JButton("Voltar");

            // Layout
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(voltarButton, BorderLayout.SOUTH);

            funcionariosList.addListSelectionListener(e -> {
                // Obtém o índice do funcionário selecionado na lista
                int index = funcionariosList.getSelectedIndex();
                if (index != -1) {
                    // Abre a página de informações do funcionário
                    InfoFuncionarioInterface infoFuncionarioInterface = new InfoFuncionarioInterface(index, conexao);
                    infoFuncionarioInterface.setVisible(true);
                    dispose();
                }
            });

            voltarButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Volta para a tela principal
                    PrincipalInterface principalInterface = new PrincipalInterface(conexao);
                    principalInterface.setVisible(true);
                    dispose();
                }
            });

            // Adiciona o painel à janela
            add(panel);

            // Exibe a janela
            setVisible(true);
        }
    }

    private class InfoFuncionarioInterface extends JFrame {
        private JTextField nomeField;
        private JTextField cpfField;
        private JTextField emailField;
        private JTextField telefoneField;
        private JTextField enderecoField;
        private int funcionarioIndex;

        public InfoFuncionarioInterface(int index, Connection conexao) {
            // Configurações da janela
            setTitle("Informações do Funcionário");
            setSize(300, 250);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Obtém o funcionário selecionado pelo índice
            Funcionario funcionario = funcionarios.get(index);

            // Salva o índice do funcionário para atualizar as informações posteriormente
            funcionarioIndex = index;

            // Criação dos componentes
            JLabel nomeLabel = new JLabel("Nome:");
            JLabel cpfLabel = new JLabel("CPF:");
            JLabel emailLabel = new JLabel("Email:");
            JLabel telefoneLabel = new JLabel("Telefone:");
            JLabel enderecoLabel = new JLabel("Endereço:");
            nomeField = new JTextField(funcionario.getNome(), 20);
            cpfField = new JTextField(funcionario.getCpf(), 20);
            emailField = new JTextField(funcionario.getEmail(), 20);
            telefoneField = new JTextField(funcionario.getTelefone(), 20);
            enderecoField = new JTextField(funcionario.getEndereco(), 20);
            JButton voltarButton = new JButton("Voltar");
            JButton apagarButton = new JButton("Apagar Funcionário");
            JButton salvarButton = new JButton("Salvar");

            // Layout
            JPanel panel = new JPanel(new GridLayout(8, 2));
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
            panel.add(voltarButton);
            panel.add(new JLabel()); // espaço em branco para alinhar corretamente
            panel.add(apagarButton);
            panel.add(new JLabel()); // espaço em branco para alinhar corretamente
            panel.add(salvarButton);

            voltarButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Volta para a lista de funcionários
                    ListaFuncionariosInterface listaFuncionariosInterface = new ListaFuncionariosInterface(conexao);
                    listaFuncionariosInterface.setVisible(true);
                    dispose();
                }
            });

            apagarButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Remove o funcionário da lista
                    funcionarios.remove(funcionarioIndex);

                    // Volta para a lista de funcionários
                    ListaFuncionariosInterface listaFuncionariosInterface = new ListaFuncionariosInterface(conexao);
                    listaFuncionariosInterface.setVisible(true);
                    dispose();
                }
            });

            salvarButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Obtém as informações editadas
                    String nome = nomeField.getText();
                    String cpf = cpfField.getText();
                    String email = emailField.getText();
                    String telefone = telefoneField.getText();
                    String endereco = enderecoField.getText();

                    // Atualiza as informações do funcionário selecionado
                    Funcionario funcionario = funcionarios.get(funcionarioIndex);
                    funcionario.setNome(nome);
                    funcionario.setCpf(cpf);
                    funcionario.setEmail(email);
                    funcionario.setTelefone(telefone);
                    funcionario.setEndereco(endereco);

                    // Exibe uma mensagem de sucesso
                    JOptionPane.showMessageDialog(InfoFuncionarioInterface.this, "Edições salvas com sucesso!");

                    // Volta para a lista de funcionários
                    ListaFuncionariosInterface listaFuncionariosInterface = new ListaFuncionariosInterface(conexao);
                    listaFuncionariosInterface.setVisible(true);
                    dispose();
                }
            });

            // Adiciona o painel à janela
            add(panel);

            // Exibe a janela
            setVisible(true);
        }
    }

}
