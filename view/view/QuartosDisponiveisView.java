package view;

import javax.swing.*;

import Persistence.ReservaDAO;
import Persistence.ReservaQuartoDAO;
import controller.SisHotel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Quarto;
import model.ReservaQuarto;

public class QuartosDisponiveisView extends JFrame {
    private JTextArea quartosDisponiveisArea;
    private JComboBox<Integer> quartoComboBox;
    private JButton selecionarButton;
    private JButton voltarButton;
    private List<Integer> quartosSelecionados;

    public QuartosDisponiveisView(List<Quarto> quartosDisponiveis, int quantidadeQuartos) {

        setTitle("Quartos Disponíveis");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        quartosDisponiveisArea = new JTextArea();
        quartosDisponiveisArea.setEditable(false);
        quartosDisponiveisArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(quartosDisponiveisArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        exibirQuartosDisponiveis(quartosDisponiveis);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        quartoComboBox = new JComboBox<>();
        for (Quarto quarto : quartosDisponiveis) {
            quartoComboBox.addItem(quarto.getIdQuarto());
        }
        bottomPanel.add(quartoComboBox);

        selecionarButton = new JButton("Selecionar Quarto");
        selecionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int numeroSelecionado = (int) quartoComboBox.getSelectedItem();

                if (quartosSelecionados.contains(numeroSelecionado)) {
                    JOptionPane.showMessageDialog(QuartosDisponiveisView.this, "Quarto já selecionado!");
                    return;
                }

                if (quartosSelecionados.size() >= quantidadeQuartos) {
                    JOptionPane.showMessageDialog(QuartosDisponiveisView.this,
                            "Limite de quartos selecionados atingido!");
                    return;
                }

                quartosSelecionados.add(numeroSelecionado);
                quartoComboBox.removeItem(numeroSelecionado);

                System.out.println("Quarto selecionado: " + numeroSelecionado);
            }
        });
        bottomPanel.add(selecionarButton);

        voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int ultimo = ReservaDAO.buscarCodigo();
                    ReservaDAO.excluirReserva(ultimo);

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                SisHotel sisHotel = new SisHotel();
                ReservaView reservaView = new ReservaView(sisHotel); // Replace with your ReservaView instantiation code
                reservaView.setVisible(true);
                dispose();
            }
        });
        bottomPanel.add(voltarButton);

        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Quartos selecionados: " + quartosSelecionados);
                try {
                    int codigoReserva = ReservaDAO.buscarCodigo();
                    for (int quartoSelecionado : quartosSelecionados) {
                        ReservaQuarto reservaQuarto = new ReservaQuarto(codigoReserva, quartoSelecionado);
                        ReservaQuartoDAO.criarReservaQuarto(reservaQuarto);
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                // Go back to the PrincipalInterface
                SisHotel sisHotel = new SisHotel();
                PrincipalInterface principalInterface = new PrincipalInterface(sisHotel);
                principalInterface.setVisible(true);
                dispose();
            }

        });
        bottomPanel.add(confirmarButton);

        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        SisHotel sisHotel = new SisHotel();
        PrincipalInterface principalInterface = new PrincipalInterface(sisHotel);
        principalInterface.setVisible(true);
        dispose();
        quartosSelecionados = new ArrayList<>();

        setVisible(true);
    }

    private void exibirQuartosDisponiveis(List<Quarto> quartosDisponiveis) {
        quartosDisponiveisArea.setText("");

        for (Quarto quarto : quartosDisponiveis) {
            quartosDisponiveisArea.append("ID: " + quarto.getIdQuarto() + "\n");
            quartosDisponiveisArea.append("Tipo: " + quarto.getDescricao() + "\n");
            quartosDisponiveisArea.append("Preço: " + quarto.getValor() + "\n");
            quartosDisponiveisArea.append("-------------------------\n");
        }
    }

}