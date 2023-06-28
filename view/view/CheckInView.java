package view;

import javax.swing.*;

import Persistence.HospedagemDAO;
import Persistence.ReservaDAO;
import controller.SisHotel;
import model.Hospedagem;
import model.Reserva;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CheckInView extends JFrame {
    private JComboBox<String> cpfComboBox;
    private JPanel reservasPanel;
    private String cpf;

    public CheckInView(String cpf) {
        setTitle("Check-In");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.cpf = cpf;

        reservasPanel = new JPanel();
        reservasPanel.setLayout(new BoxLayout(reservasPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(reservasPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        displayReservas(getReservasByCpf(cpf));

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SisHotel sisHotel = new SisHotel();
                AtualizarReservaInterface atualizarReservaInterface = new AtualizarReservaInterface(sisHotel);
                atualizarReservaInterface.setVisible(true);
                dispose(); // Fecha a janela de check-in
            }
        });
        getContentPane().add(backButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private List<Reserva> getReservasByCpf(String cpf) {
        List<Reserva> reservas = new ArrayList<>();

        // Call the buscarReservaPorCpf function to retrieve Reservas by CPF
        List<Reserva> resultado = ReservaDAO.buscarReservaPorCpf(cpf);
        reservas.addAll(resultado);

        return reservas;
    }

    private void displayReservas(List<Reserva> reservas) {
        for (Reserva reserva : reservas) {
            JPanel reservaPanel = createReservaPanel(reserva);
            reservasPanel.add(reservaPanel);
        }
    }

    private JPanel createReservaPanel(Reserva reserva) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel reservaLabel = new JLabel(
                "Código " + reserva.getCodigo() + " | Data Entrada: " + reserva.getDiasEstadia());
        reservaLabel.setFont(reservaLabel.getFont().deriveFont(Font.BOLD));

        panel.add(reservaLabel);

        JButton detailsButton = new JButton("Detalhes");
        detailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showReservaDetails(reserva);
            }
        });
        panel.add(detailsButton);

        return panel;
    }

    private void showReservaDetails(Reserva reserva) {
        JFrame detailsFrame = new JFrame("Detalhes da Reserva");
        detailsFrame.setSize(300, 200);
        detailsFrame.setLocationRelativeTo(null);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel detailsLabel = new JLabel("Detalhes da Reserva:");
        detailsLabel.setFont(detailsLabel.getFont().deriveFont(Font.BOLD));
        detailsPanel.add(detailsLabel);

        JLabel codeLabel = new JLabel("Código: " + reserva.getCodigo());
        detailsPanel.add(codeLabel);

        JLabel entryDateLabel = new JLabel("Data Entrada: " + reserva.getDiasEstadia());
        detailsPanel.add(entryDateLabel);

        JButton checkInButton = new JButton("Fazer Check-In");
        checkInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Integer> codigosReserva = HospedagemDAO.buscarCodigosReserva();

                    boolean numeroPresente = codigosReserva.contains(reserva.getCodigo());

                    if (numeroPresente) {
                        JOptionPane.showMessageDialog(CheckInView.this, "Check-in já realizado.");
                        detailsFrame.dispose();
                    } else {
                        String mensagem = Hospedagem.realizarCheckIn(reserva);
                        JOptionPane.showMessageDialog(CheckInView.this, "Check-in realizado com sucesso.");
                        detailsFrame.dispose();
                        SisHotel sisHotel = new SisHotel();
                        PrincipalInterface principalInterface = new PrincipalInterface(sisHotel);
                        principalInterface.setVisible(true);
                        dispose(); // Fecha a janela de check-in
                        System.out.println(mensagem);
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        detailsPanel.add(checkInButton);

        detailsFrame.getContentPane().add(detailsPanel);
        detailsFrame.setVisible(true);
    }
}
