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
    private String cpf;

    public QuartosDisponiveisView(List<Quarto> quartosDisponiveis, int quantidadeQuartos, String cpf) {
        this.cpf = cpf;
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

        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (quartosSelecionados.size() < quantidadeQuartos) {
                    JOptionPane.showMessageDialog(QuartosDisponiveisView.this,
                            "Selecione a quantidade certa de quartos");

                } else {
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

            }

        });
        bottomPanel.add(confirmarButton);

        voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int ultimo = ReservaDAO.buscarCodigo();
                    ReservaDAO.excluirReserva(ultimo);
                    SisHotel sisHotel = new SisHotel();
                    ReservaView reservaView = new ReservaView(sisHotel, cpf);
                    reservaView.setVisible(true);
                    dispose(); // Fecha a janela de login

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        bottomPanel.add(voltarButton);

        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        quartosSelecionados = new ArrayList<>();

        setVisible(true);
    }

    private void exibirQuartosDisponiveis(List<Quarto> quartosDisponiveis) {
        quartosDisponiveisArea.setText("");

        for (Quarto quarto : quartosDisponiveis) {
            quartosDisponiveisArea.append("ID: " + quarto.getIdQuarto() + "\n");
            quartosDisponiveisArea.append("Descrição: " + quarto.getDescricao() + "\n");
            quartosDisponiveisArea.append("Preço: " + quarto.getValor() + "\n");
            quartosDisponiveisArea.append("Local: " + quarto.getLocal() + "\n");
            quartosDisponiveisArea.append("Tipo: " + quarto.getTipoQuarto() + "\n");
            quartosDisponiveisArea.append("Local: " + quarto.getLocal() + "\n");
            quartosDisponiveisArea.append("Situação: " + quarto.getSituacao() + "\n");
            quartosDisponiveisArea.append("-------------------------\n");
        }
    }

}
