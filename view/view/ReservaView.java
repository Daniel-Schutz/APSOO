package view;

import javax.swing.*;

import Persistence.QuartoDAO;
import Persistence.ReservaDAO;
import Persistence.ReservaQuartoDAO;

import java.sql.Connection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; // Import the ArrayList class
import java.util.Calendar;

import controller.*;
import model.Quarto;
import model.Reserva;
import model.ReservaQuarto;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.text.ParseException;
import java.util.List;
import java.util.Iterator;

public class ReservaView extends JFrame {
    private JTextField Field;
    private JTextField cpfField;
    private JTextField dataEntradaField;
    private JTextField diasEstadiaField;
    private JTextField quantidadePessoasField;
    private JTextField quantidadeQuartosField;
    private JComboBox<String> tipoPagamentoField;

    // private SisHotel sisHotel;

    public ReservaView(SisHotel sisHotel) {

        // this.sisHotel = new SisHotel(conexao);

        setTitle("Reserva de Quartos");
        setSize(300, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel cpfLabel = new JLabel("CPF:");
        JLabel entradaLabel = new JLabel("Data entrada:");
        JLabel saidaLabel = new JLabel("Dias de estadia:");
        JLabel quantidadePessoasLabel = new JLabel("Quantidade de Pessoas:");
        JLabel quantidadeQuartosLabel = new JLabel("Quantidade de Quartos:");
        JLabel tipoPagamentoLabel = new JLabel("Tipo de pagamento:");

        JTextField cpfField = new JTextField(20);
        JTextField dataEntradaField = new JTextField(20);
        JTextField diasEstadiaField = new JTextField(20);
        JTextField quantidadePessoasField = new JTextField(20);
        JTextField quantidadeQuartosField = new JTextField(20);
        String[] tipoPagamentoOptions = { "Dinheiro", "Pix", "Crédito", "Débito" };
        tipoPagamentoField = new JComboBox<>(tipoPagamentoOptions);

        JButton reservarButton = new JButton("Realizar Reserva");
        JButton voltarButton = new JButton("Cancelar Reserva");

        JPanel panel = new JPanel(new GridLayout(10, 2));
        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(entradaLabel);
        panel.add(dataEntradaField);
        panel.add(saidaLabel);
        panel.add(diasEstadiaField);
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

                // Validate CPF field
                if (cpf.length() != 11 || !cpf.matches("\\d+")) {
                    JOptionPane.showMessageDialog(ReservaView.this, "CPF inválido. Deve conter 11 dígitos numéricos.");
                    return;
                }

                String entradaText = dataEntradaField.getText(); // Correção aqui
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String saidaTexto = diasEstadiaField.getText();

                String diasEstadia = diasEstadiaField.getText();
                try {
                    int dias = Integer.parseInt(diasEstadia);
                    if (dias < 1 || dias > 15) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ReservaView.this,
                            "Quantidade de dias invalido. Deve ser um número entre 1 e 14.");
                    return;
                }

                // Validate diasEstadiaField

                int tempoEstadia = Integer.parseInt(diasEstadia);

                String quantidadePessoas = quantidadePessoasField.getText();
                String quantidadeQuartos = quantidadeQuartosField.getText();
                String situacao = "RESERVADO";
                String tipoPagamento = (String) tipoPagamentoField.getSelectedItem();

                // Validate quantidadePessoasField
                try {
                    int pessoas = Integer.parseInt(quantidadePessoas);
                    if (pessoas < 1 || pessoas > 10) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ReservaView.this,
                            "Quantidade de pessoas inválida. Deve ser um número entre 1 e 10.");
                    return;
                }

                // Validate quantidadeQuartosField
                try {
                    int quartos = Integer.parseInt(quantidadeQuartos);
                    if (quartos < 1 || quartos > 4) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ReservaView.this,
                            "Quantidade de quartos inválida. Deve ser um número entre 1 e 3.");
                    return;
                }
                System.out.println("NAO1");
                try {
                    Date entrada = sdf.parse(entradaText);
                    List<Quarto> quartosDisponiveis = QuartoDAO.listarQuartos();
                    List<ReservaQuarto> listaReservaQuartos = ReservaQuartoDAO.listarReservaQuarto();

                    if (listaReservaQuartos != null) {
                        for (ReservaQuarto reservaQuarto : listaReservaQuartos) {
                            Reserva reserva = ReservaDAO.buscarReserva(reservaQuarto.getcodigoReserva());
                            Date Rdata = reserva.getData();
                            int RdiasEstadia = reserva.getDiasEstadia();
                            if (verificarPeriodosCoincidem(Rdata, RdiasEstadia, entrada, tempoEstadia) == true) {
                                Iterator<Quarto> iterator = quartosDisponiveis.iterator();
                                while (iterator.hasNext()) {
                                    Quarto quarto = iterator.next();
                                    // Itera pelas reservas de quartos
                                    if (quarto.getIdQuarto() == reservaQuarto.getIdQuarto()) {
                                        iterator.remove(); // Remove o quarto da lista
                                        break; // Sai do loop de reservas
                                    }

                                }
                            }
                        }

                    }

                    for (Quarto quarto : quartosDisponiveis) {
                        System.out.println(quarto.getIdQuarto());
                    }
                    try {
                        /*
                         * AQUI, antes de efetuar a reserva, quero que apareça os quartosDisponiveis
                         * para o usuario
                         */

                        // Crie um JTextArea para exibir os quartos disponíveis

                        java.sql.Date sqlEntrada = new java.sql.Date(entrada.getTime());
                        sisHotel.registrarReserva(sqlEntrada, tempoEstadia, tipoPagamento, situacao, cpf);

                        // JOptionPane.showMessageDialog(ReservaView.this, message);

                        cpfField.setText("");
                        dataEntradaField.setText("");
                        diasEstadiaField.setText("");
                        quantidadePessoasField.setText("");
                        quantidadeQuartosField.setText("");

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ReservaView.this,
                                "Data entrada inválida. Utilize o formato dd/MM/yyyy.");
                    }

                    QuartosDisponiveisView quartosView = new QuartosDisponiveisView(quartosDisponiveis,
                            Integer.parseInt(quantidadeQuartos));
                    quartosView.setVisible(true);
                    dispose();

                } catch (Exception e1) {
                    e1.printStackTrace();
                    // Lógica para tratamento de exceção
                }

            }
        });

        // Adiciona o listener para o botão de voltar
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Fecha a tela de cadastro de funcionário e abre a tela principal
                JOptionPane.showMessageDialog(ReservaView.this,
                        "Reserva Cancelada!");
                PesquisarCliente pesquisarCliente = new PesquisarCliente(sisHotel);
                pesquisarCliente.setVisible(true);
                dispose();
            }
        });

        // Adiciona o painel à janela
        add(panel);

        // Exibe a janela
        setVisible(true);

    }

    public static boolean verificarPeriodosCoincidem(Date data1, int diasEstadia1, Date data2, int diasEstadia2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(data1);
        calendar1.add(Calendar.DAY_OF_YEAR, diasEstadia1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(data2);
        calendar2.add(Calendar.DAY_OF_YEAR, diasEstadia2);

        // Verifica se há interseção entre os períodos
        if (calendar1.getTime().after(data2) && calendar2.getTime().after(data1)) {
            return true; // Há coincidência entre os períodos
        }

        return false; // Não há coincidência entre os períodos
    }

}
