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

public class PesquisarCliente extends JFrame {
    private JTextField Field;
    private JTextField cpfField;

    public PesquisarCliente(SisHotel sisHotel) {

        // this.sisHotel = new SisHotel(conexao);

        setTitle("Pesquisar cliente");
        setSize(300, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

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
                ReservaView reservaInterface = new ReservaView(sisHotel);
                reservaInterface.setVisible(true);
                dispose();

                // Obter as informações inseridas pelo usuário
                String cpf = cpfField.getText();
                // Abre a lista de funcionários para edição
                // Validate CPF field
                if (cpf.length() != 11 || !cpf.matches("\\d+")) {
                    JOptionPane.showMessageDialog(PesquisarCliente.this,
                            "CPF inválido. Deve conter 11 dígitos numéricos.");
                    return;
                }
                model.Cliente cliente = sisHotel.buscarCliente(cpf);
                if (cliente == null) {
                    JOptionPane.showMessageDialog(PesquisarCliente.this, "CPF não registrado!");
                } else {
                    JOptionPane.showMessageDialog(PesquisarCliente.this, cliente.getNome());
                    ReservaView reservaInterface2 = new ReservaView(sisHotel);
                    reservaInterface.setVisible(true);
                    dispose();
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

}