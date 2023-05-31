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
    private ArrayList<Reserva> Reservas;

    // private SisHotel sisHotel;

    public AtualizarReservaInterface(SisHotel sisHotel) {

        // this.sisHotel = new SisHotel(conexao);

        setTitle("Pesquisar cliente");
        setSize(300, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicialização da lista de funcionários
        Reservas = new ArrayList<>();
        Reservas.add(new Reserva("26/05/2023", "123456789", "5", "1", "Dinheiro"));
        Reservas.add(new Reserva("26/06/2023", "123456789", "7", "1", "Dinheiro"));

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
                ListaReservasInterface listaReservasInterface = new ListaReservasInterface(sisHotel);
                listaReservasInterface.setVisible(true);
                dispose();

            }
        });

        // Adiciona o listener para o botão de voltar
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Fecha a tela de cadastro de funcionário e abre a tela principal
                dispose();
                PrincipalInterface principalInterface = new PrincipalInterface(sisHotel);
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

    private class ListaReservasInterface extends JFrame {
        private JList<String> ReservasList;

        public ListaReservasInterface(SisHotel sisHotel) {
            // Configurações da janela
            setTitle("Lista de reservas deste usuário");
            setSize(300, 150);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Criação dos componentes
            DefaultListModel<String> listModel = new DefaultListModel<>();
            for (Reserva Reserva : Reservas) {
                listModel.addElement(Reserva.getdataEntrada());
            }
            ReservasList = new JList<>(listModel);
            JScrollPane scrollPane = new JScrollPane(ReservasList);
            JButton voltarButton = new JButton("Voltar");

            // Layout
            JPanel panel = new JPanel(new BorderLayout());

            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(voltarButton, BorderLayout.SOUTH);

            ReservasList.addListSelectionListener(e -> {
                // Obtém o índice do funcionário selecionado na lista
                int index = ReservasList.getSelectedIndex();
                if (index != -1) {
                    // Abre a página de informações do funcionário
                    InfoReservaInterface infoReservaInterface = new InfoReservaInterface(index, sisHotel);
                    infoReservaInterface.setVisible(true);
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

    private class InfoReservaInterface extends JFrame {
        private JTextField dataEntradaField;
        private JTextField cpfField;
        private JTextField tempoEstadiaField;
        private JTextField quantQuartosField;
        private JTextField formaPagamentoField;
        private int ReservaIndex;

        public InfoReservaInterface(int index, SisHotel sisHotel) {
            // Configurações da janela
            setTitle("Informações do Funcionário");
            setSize(300, 250);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Obtém o funcionário selecionado pelo índice
            Reserva Reserva = Reservas.get(index);

            // Salva o índice do funcionário para atualizar as informações posteriormente
            ReservaIndex = index;

            // Criação dos componentes
            JLabel dataEntradaLabel = new JLabel("dataEntrada:");
            JLabel cpfLabel = new JLabel("CPF:");
            JLabel tempoEstadiaLabel = new JLabel("tempoEstadia:");
            JLabel quantQuartosLabel = new JLabel("quantQuartos:");
            JLabel formaPagamentoLabel = new JLabel("Endereço:");
            dataEntradaField = new JTextField(Reserva.getdataEntrada(), 20);
            cpfField = new JTextField(Reserva.getCpf(), 20);
            tempoEstadiaField = new JTextField(Reserva.gettempoEstadia(), 20);
            quantQuartosField = new JTextField(Reserva.getquantQuartos(), 20);
            formaPagamentoField = new JTextField(Reserva.getformaPagamento(), 20);
            JButton voltarButton = new JButton("Voltar");
            JButton apagarButton = new JButton("Cancelar Reserva");
            JButton checkInButton = new JButton("Realizar Check-In");
            JButton checkOutButton = new JButton("Realizar Check-Out");

            // Layout
            JPanel panel = new JPanel(new GridLayout(9, 2));
            panel.add(dataEntradaLabel);
            panel.add(dataEntradaField);
            panel.add(cpfLabel);
            panel.add(cpfField);
            panel.add(tempoEstadiaLabel);
            panel.add(tempoEstadiaField);
            panel.add(quantQuartosLabel);
            panel.add(quantQuartosField);
            panel.add(formaPagamentoLabel);
            panel.add(formaPagamentoField);
            panel.add(new JLabel()); // espaço em branco para alinhar corretamente
            panel.add(voltarButton);
            panel.add(new JLabel()); // espaço em branco para alinhar corretamente
            panel.add(apagarButton);
            panel.add(new JLabel()); // espaço em branco para alinhar corretamente
            panel.add(checkInButton);
            panel.add(new JLabel()); // espaço em branco para alinhar corretamente
            panel.add(checkOutButton);

            voltarButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Volta para a lista de funcionários
                    ListaReservasInterface listaReservasInterface = new ListaReservasInterface(sisHotel);
                    listaReservasInterface.setVisible(true);
                    dispose();
                }
            });

            apagarButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Remove o funcionário da lista
                    Reservas.remove(ReservaIndex);

                    // Exibe uma mensagem de sucesso
                    JOptionPane.showMessageDialog(InfoReservaInterface.this, "Reserva cancelada com sucesso!");

                    // Volta para a lista de funcionários
                    ListaReservasInterface listaReservasInterface = new ListaReservasInterface(sisHotel);
                    listaReservasInterface.setVisible(true);
                    dispose();
                }
            });

            checkInButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Obtém as informações editadas
                    String dataEntrada = dataEntradaField.getText();
                    String cpf = cpfField.getText();
                    String tempoEstadia = tempoEstadiaField.getText();
                    String quantQuartos = quantQuartosField.getText();
                    String formaPagamento = formaPagamentoField.getText();

                    // Atualiza as informações do funcionário selecionado
                    Reserva Reserva = Reservas.get(ReservaIndex);
                    Reserva.setdataEntrada(dataEntrada);
                    Reserva.setCpf(cpf);
                    Reserva.settempoEstadia(tempoEstadia);
                    Reserva.setquantQuartos(quantQuartos);
                    Reserva.setformaPagamento(formaPagamento);

                    // Exibe uma mensagem de sucesso
                    JOptionPane.showMessageDialog(InfoReservaInterface.this, "Check-In realizado com sucesso!");

                    // Volta para a lista de funcionários
                    ListaReservasInterface listaReservasInterface = new ListaReservasInterface(sisHotel);
                    listaReservasInterface.setVisible(true);
                    dispose();
                }
            });

            checkOutButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Obtém as informações editadas
                    String dataEntrada = dataEntradaField.getText();
                    String cpf = cpfField.getText();
                    String tempoEstadia = tempoEstadiaField.getText();
                    String quantQuartos = quantQuartosField.getText();
                    String formaPagamento = formaPagamentoField.getText();

                    // Atualiza as informações do funcionário selecionado
                    Reserva Reserva = Reservas.get(ReservaIndex);
                    Reserva.setdataEntrada(dataEntrada);
                    Reserva.setCpf(cpf);
                    Reserva.settempoEstadia(tempoEstadia);
                    Reserva.setquantQuartos(quantQuartos);
                    Reserva.setformaPagamento(formaPagamento);

                    // Remove o funcionário da lista
                    Reservas.remove(ReservaIndex);

                    // Exibe uma mensagem de sucesso
                    JOptionPane.showMessageDialog(InfoReservaInterface.this, "Check-Out realizado com sucesso!");

                    // Volta para a lista de funcionários
                    ListaReservasInterface listaReservasInterface = new ListaReservasInterface(sisHotel);
                    listaReservasInterface.setVisible(true);
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
