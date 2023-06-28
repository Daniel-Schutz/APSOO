package view;

import javax.swing.*;
import controller.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.*;

public class PrincipalInterface extends JFrame {
    private ArrayList<model.Funcionario> funcionarios;

    public PrincipalInterface(SisHotel sishotel) {
        // Configurações da janela
        setTitle("Tela Principal");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicialização da lista de clientes

        // Criação dos componentes
        JButton cadastrarFuncionarioButton = new JButton("Cadastrar Funcionário");
        JButton cadastrarClienteButton = new JButton("Cadastrar Cliente");
        JButton editarClienteButton = new JButton("Editar Cliente");
        JButton editarFuncionarioButton = new JButton("Editar Funcionário");
        JButton criarReservaButton = new JButton("Criar Reserva");
        JButton atualizarReservaButton = new JButton("Fazer Check-In");

        // Layout
        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.add(cadastrarFuncionarioButton);
        panel.add(cadastrarClienteButton);
        panel.add(editarClienteButton);
        panel.add(editarFuncionarioButton);
        panel.add(criarReservaButton);
        panel.add(atualizarReservaButton);

        cadastrarFuncionarioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de cadastro de funcionário, passando a referência do ArrayList
                CadastroFuncionarioInterface cadastroFuncionarioInterface = new CadastroFuncionarioInterface(
                        funcionarios, sishotel);
                cadastroFuncionarioInterface.setVisible(true);
                dispose();
            }
        });

        cadastrarClienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botao apertado");
                // Abre a tela de cadastro de cliente, passando a referência do ArrayList
                CadastroClienteInterface cadastroClienteInterface = new CadastroClienteInterface(sishotel);
                cadastroClienteInterface.setVisible(true);
                dispose();
            }
        });

        editarClienteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre a lista de funcionários para edição
                // ListaClientesInterface listaClientesInterface = new
                // ListaClientesInterface(sishotel);
                // listaClientesInterface.setVisible(true);
                dispose();
            }
        });

        criarReservaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PesquisarCliente pesquisarCliente = new PesquisarCliente(sishotel);
                pesquisarCliente.setVisible(true);
                dispose();
                // JOptionPane.showMessageDialog(PrincipalInterface.this, "Reserva criada!");
            }
        });

        editarFuncionarioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre a lista de funcionários para edição
                ListaFuncionariosInterface listaFuncionariosInterface = new ListaFuncionariosInterface(sishotel);
                listaFuncionariosInterface.setVisible(true);
                dispose();
            }
        });

        atualizarReservaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de cadastro de funcionário, passando a referência do ArrayList
                AtualizarReservaInterface atualizarReservaInterface = new AtualizarReservaInterface(sishotel);
                atualizarReservaInterface.setVisible(true);
                dispose();
            }
        });

        // Adiciona o painel à janela
        add(panel);

        // Exibe a janela
        setVisible(true);
    }

    private class ListaFuncionariosInterface extends JFrame {
        private JList<String> funcionariosList;

        public ListaFuncionariosInterface(SisHotel sisHotel) {
            // Configurações da janela
            setTitle("Lista de Funcionários");
            setSize(300, 150);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Criação dos componentes
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (model.Funcionario funcionario : funcionarios) {
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
                    InfoFuncionarioInterface infoFuncionarioInterface = new InfoFuncionarioInterface(index, sisHotel);
                    infoFuncionarioInterface.setVisible(true);
                    dispose();
                }
            });

            voltarButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Volta para a tela principal
                    PrincipalInterface principalInterface = new PrincipalInterface(sisHotel);
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

        public InfoFuncionarioInterface(int index, SisHotel sisHotel) {
            // Configurações da janela
            setTitle("Informações do Funcionário");
            setSize(300, 250);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Obtém o funcionário selecionado pelo índice
            model.Funcionario funcionario = funcionarios.get(index);

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
                    ListaFuncionariosInterface listaFuncionariosInterface = new ListaFuncionariosInterface(sisHotel);
                    listaFuncionariosInterface.setVisible(true);
                    dispose();
                }
            });

            apagarButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Remove o funcionário da lista
                    funcionarios.remove(funcionarioIndex);

                    // Volta para a lista de funcionários
                    ListaFuncionariosInterface listaFuncionariosInterface = new ListaFuncionariosInterface(sisHotel);
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
                    model.Funcionario funcionario = funcionarios.get(funcionarioIndex);
                    funcionario.setNome(nome);
                    funcionario.setCpf(cpf);
                    funcionario.setEmail(email);
                    funcionario.setTelefone(telefone);
                    funcionario.setEndereco(endereco);

                    // Exibe uma mensagem de sucesso
                    JOptionPane.showMessageDialog(InfoFuncionarioInterface.this, "Edições salvas com sucesso!");

                    // Volta para a lista de funcionários
                    ListaFuncionariosInterface listaFuncionariosInterface = new ListaFuncionariosInterface(sisHotel);
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

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(() -> new PrincipalInterface());
    // }

    /*
     * private class ListaClientesInterface extends JFrame {
     * private JList<String> clientesList;
     * 
     * public ListaClientesInterface(SisHotel sisHotel) {
     * // Configurações da janela
     * setTitle("Lista de Clientes");
     * setSize(300, 150);
     * setDefaultCloseOperation(DISPOSE_ON_CLOSE);
     * setLocationRelativeTo(null);
     * 
     * // Criação dos componentes
     * DefaultListModel<String> listModel = new DefaultListModel<>();
     * for (Cliente cliente : clientes) {
     * listModel.addElement(cliente.getNome());
     * }
     * clientesList = new JList<>(listModel);
     * JScrollPane scrollPane = new JScrollPane(clientesList);
     * JButton voltarButton = new JButton("Voltar");
     * 
     * // Layout
     * JPanel panel = new JPanel(new BorderLayout());
     * panel.add(scrollPane, BorderLayout.CENTER);
     * panel.add(voltarButton, BorderLayout.SOUTH);
     * 
     * clientesList.addListSelectionListener(e -> {
     * // Obtém o índice do clientes selecionado na lista
     * int index = clientesList.getSelectedIndex();
     * if (index != -1) {
     * // Abre a página de informações do funcionário
     * InfoClienteInterface infoClienteInterface = new InfoClienteInterface(index,
     * sisHotel);
     * infoClienteInterface.setVisible(true);
     * dispose();
     * }
     * });
     * 
     * voltarButton.addActionListener(new ActionListener() {
     * public void actionPerformed(ActionEvent e) {
     * // Volta para a tela principal
     * PrincipalInterface principalInterface = new PrincipalInterface(sisHotel);
     * principalInterface.setVisible(true);
     * dispose();
     * }
     * });
     * 
     * // Adiciona o painel à janela
     * add(panel);
     * 
     * // Exibe a janela
     * setVisible(true);
     * }
     * }
     */

    /*
     * private class InfoClienteInterface extends JFrame {
     * private JTextField nomeField;
     * private JTextField cpfField;
     * private JTextField emailField;
     * private JTextField telefoneField;
     * private JTextField enderecoField;
     * private int clienteIndex;
     * 
     * public InfoClienteInterface(int index, SisHotel sisHotel) {
     * // Configurações da janela
     * setTitle("Informações do cliente");
     * setSize(300, 250);
     * setDefaultCloseOperation(DISPOSE_ON_CLOSE);
     * setLocationRelativeTo(null);
     * 
     * // Obtém o funcionário selecionado pelo índice
     * Cliente cliente = clientes.get(index);
     * 
     * // Salva o índice do funcionário para atualizar as informações posteriormente
     * clienteIndex = index;
     * 
     * // Criação dos componentes
     * JLabel nomeLabel = new JLabel("Nome:");
     * JLabel cpfLabel = new JLabel("CPF:");
     * JLabel emailLabel = new JLabel("Email:");
     * JLabel telefoneLabel = new JLabel("Telefone:");
     * JLabel enderecoLabel = new JLabel("Endereço:");
     * nomeField = new JTextField(cliente.getNome(), 20);
     * cpfField = new JTextField(cliente.getCpf(), 20);
     * emailField = new JTextField(cliente.getEmail(), 20);
     * telefoneField = new JTextField(cliente.getTelefone(), 20);
     * enderecoField = new JTextField(cliente.getEndereco(), 20);
     * JButton voltarButton = new JButton("Voltar");
     * JButton apagarButton = new JButton("Apagar Cliente");
     * JButton salvarButton = new JButton("Salvar");
     * 
     * // Layout
     * JPanel panel = new JPanel(new GridLayout(8, 2));
     * panel.add(nomeLabel);
     * panel.add(nomeField);
     * panel.add(cpfLabel);
     * panel.add(cpfField);
     * panel.add(emailLabel);
     * panel.add(emailField);
     * panel.add(telefoneLabel);
     * panel.add(telefoneField);
     * panel.add(enderecoLabel);
     * panel.add(enderecoField);
     * panel.add(new JLabel()); // espaço em branco para alinhar corretamente
     * panel.add(voltarButton);
     * panel.add(new JLabel()); // espaço em branco para alinhar corretamente
     * panel.add(apagarButton);
     * panel.add(new JLabel()); // espaço em branco para alinhar corretamente
     * panel.add(salvarButton);
     * 
     * voltarButton.addActionListener(new ActionListener() {
     * public void actionPerformed(ActionEvent e) {
     * // Volta para a lista de funcionários
     * ListaClientesInterface listaClientesInterface = new
     * ListaClientesInterface(sisHotel);
     * listaClientesInterface.setVisible(true);
     * dispose();
     * }
     * });
     * 
     * apagarButton.addActionListener(new ActionListener() {
     * public void actionPerformed(ActionEvent e) {
     * // Remove o funcionário da lista
     * clientes.remove(clienteIndex);
     * 
     * // Volta para a lista de funcionários
     * ListaClientesInterface listaClientesInterface = new
     * ListaClientesInterface(sisHotel);
     * listaClientesInterface.setVisible(true);
     * dispose();
     * }
     * });
     * 
     * salvarButton.addActionListener(new ActionListener() {
     * public void actionPerformed(ActionEvent e) {
     * // Obtém as informações editadas
     * String nome = nomeField.getText();
     * String cpf = cpfField.getText();
     * String email = emailField.getText();
     * String telefone = telefoneField.getText();
     * String endereco = enderecoField.getText();
     * 
     * // Atualiza as informações do funcionário selecionado
     * Cliente cliente = clientes.get(clienteIndex);
     * cliente.setNome(nome);
     * cliente.setCpf(cpf);
     * cliente.setEmail(email);
     * cliente.setTelefone(telefone);
     * cliente.setEndereco(endereco);
     * 
     * // Exibe uma mensagem de sucesso
     * JOptionPane.showMessageDialog(InfoClienteInterface.this,
     * "Edições salvas com sucesso!");
     * 
     * // Volta para a lista de funcionários
     * ListaClientesInterface listaClientesInterface = new
     * ListaClientesInterface(sisHotel);
     * listaClientesInterface.setVisible(true);
     * dispose();
     * }
     * });
     * 
     * // Adiciona o painel à janela
     * add(panel);
     * 
     * // Exibe a janela
     * setVisible(true);
     * }
     * }
     */
}
