package view;

import javax.swing.*;

import Persistence.HospedagemDAO;
import Persistence.ReservaDAO;
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
    private JPanel ReservasPanel;

    public CheckInView(String cpf) {
        setTitle("Check-In");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ReservasPanel = new JPanel();
        ReservasPanel.setLayout(new BoxLayout(ReservasPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(ReservasPanel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        displayReservas(getReservasByCpf(cpf));

        setVisible(true);
    }

    private List<Reserva> getReservasByCpf(String cpf) {
        List<Reserva> Reservas = new ArrayList<>();

        // Call the buscarReservaPorCpf function to retrieve Reservas by CPF
        List<Reserva> resultado;
        resultado = ReservaDAO.buscarReservaPorCpf(cpf);
        Reservas.addAll(resultado);

        return Reservas;
    }

    private void displayReservas(List<Reserva> Reservas) {
        for (Reserva Reserva : Reservas) {
            JPanel ReservaPanel = createReservaPanel(Reserva);
            ReservasPanel.add(ReservaPanel);
        }
    }

    private JPanel createReservaPanel(Reserva Reserva) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel ReservaLabel = new JLabel(
                "Código " + Reserva.getCodigo() + " | Data Entrada: " + Reserva.getDiasEstadia());
        ReservaLabel.setFont(ReservaLabel.getFont().deriveFont(Font.BOLD));

        panel.add(ReservaLabel);

        JButton detailsButton = new JButton("Detalhes");
        detailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showReservaDetails(Reserva);
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
                System.out.println(LocalDateTime.now());
                String mensagem = Hospedagem.realizarCheckIn(reserva);

                System.out.println(mensagem);
            }
        });
        detailsPanel.add(checkInButton);

        detailsFrame.getContentPane().add(detailsPanel);
        detailsFrame.setVisible(true);
    }

}
