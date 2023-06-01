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

public class ReservaView extends JFrame {
    private JTextField Field;
    private JTextField cpfField;
    private JTextField dataEntradaField;
    private JTextField dataSaidaField;
    private JTextField quantidadePessoasField;
    private JTextField quantidadeQuartosField;
    private JTextField tipoPagamentoField;

    // private SisHotel sisHotel;

    public ReservaView(SisHotel sisHotel) {

        // this.sisHotel = new SisHotel(conexao);

        setTitle("Reserva de Quartos");
        setSize(300, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel cpfLabel = new JLabel("CPF:");
        JLabel entradaLabel = new JLabel("Data entrada:");
        JLabel saidaLabel = new JLabel("Tempo de estadia:");
        JLabel quantidadePessoasLabel = new JLabel("Quantidade de Pessoas:");
        JLabel quantidadeQuartosLabel = new JLabel("Quantidade de Quartos:");
        JLabel tipoPagamentoLabel = new JLabel("Tipo de pagamento:");

        JTextField cpfField = new JTextField(20);
        JTextField dataEntradaField = new JTextField(20);
        JTextField dataSaidaField = new JTextField(20);
        JTextField quantidadePessoasField = new JTextField(20);
        JTextField quantidadeQuartosField = new JTextField(20);
        JTextField tipoPagamentoField = new JTextField(20);

        JButton reservarButton = new JButton("Realizar Reserva");
        JButton voltarButton = new JButton("Voltar");

        JPanel panel = new JPanel(new GridLayout(10, 2));
        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(entradaLabel);
        panel.add(dataEntradaField);
        panel.add(saidaLabel);
        panel.add(dataSaidaField);
        panel.add(quantidadePessoasLabel);
        panel.add(quantidadePessoasField);
        panel.add(quantidadeQuartosLabel);
        panel.add(quantidadeQuartosField);
        panel.add(tipoPagamentoLabel);
        panel.add(tipoPagamentoField);

        panel.add(new JLabel()); // espaço em branco para alinhar corretamente
        panel.add(reservarButton);
        panel.add(new JLabel()); // espaço em branco para alinhar corretamente
        panel.add(voltarButton);

        getContentPane().add(panel, BorderLayout.CENTER);

        reservarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obter as informações inseridas pelo usuário
                String cpf = cpfField.getText();
                String entradaText = dataEntradaField.getText(); // Correção aqui
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String saidaTexto = dataSaidaField.getText();
                saidaTexto = saidaTexto.replaceAll("[\\D]", "");
                int tempoEstadia = Integer.parseInt(saidaTexto);

                String quantidadePessoas = quantidadePessoasField.getText();
                String quantidadeQuartos = quantidadeQuartosField.getText();
                String situacao = "RESERVADO";
                String tipoPagamento = tipoPagamentoField.getText();

                try {
                    String message;
                    Date entrada = sdf.parse(entradaText);
                    java.sql.Date sqlEntrada = new java.sql.Date(entrada.getTime());
                    message = sisHotel.registrarReserva(sqlEntrada, tempoEstadia, tipoPagamento, situacao, cpf);

                    JOptionPane.showMessageDialog(ReservaView.this, message);

                    cpfField.setText("");
                    dataEntradaField.setText("");
                    dataSaidaField.setText("");
                    quantidadePessoasField.setText("");
                    quantidadeQuartosField.setText("");
                    tipoPagamentoField.setText("");

                    PrincipalInterface principalInterface = new PrincipalInterface(sisHotel);
                    principalInterface.setVisible(true);

                    dispose();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(ReservaView.this, "Data entrada inválida");
                }
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

}

