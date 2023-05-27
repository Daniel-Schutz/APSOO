package model;

import java.sql.SQLException;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import DAO.*;
import utils.NovaExcecao;

public class Reserva {
    private int codigo;
    private Date data;
    private int diasEstadia;
    private String tipoPagamento;
    private String situacao;
    private String pessoaCPF;
    private ReservaDAO reservaDAO;

    // Construtor da classe
    public Reserva(int codigo, Date data, int diasEstadia, String tipoPagamento, String situacao, String pessoaCPF,
            ReservaDAO reservaDAO) {
        this.codigo = codigo;
        this.data = data;
        this.diasEstadia = diasEstadia;
        this.tipoPagamento = tipoPagamento;
        this.situacao = situacao;
        this.pessoaCPF = pessoaCPF;
        this.reservaDAO = reservaDAO;
    }

    // Métodos getters e setters para os atributos

    public String registrarReserva() throws NovaExcecao {
        String message;
        try {
            if (!Cliente.existeCliente(pessoaCPF)){
                message = "Cliente não encontrado";
                throw new NovaExcecao(message);
            }

            if(this.getpessoaCpf().matches("\\d+") && this.getpessoaCpf().length() == 11){
                message = this.reservaDAO.criarReserva(this); // cria cliente caso cpf seja válido
            }
            else{
                message = "CPF Inválido";
                throw new NovaExcecao(message);
            }
        } catch (Exception e) {
            return "SQLError";
        }
        return message;
    }

    public static Reserva buscarReserva(ReservaDAO reservaDAO, int codigo) {

        // a função buscarReserva está declarando que pode lançar uma exceção do tipo
        // SQLException
        // por isso precisa de um try catch aqui
        try {
            Reserva reserva;
            reserva = reservaDAO.buscarReserva(codigo);
            return reserva;

        } catch (Exception e) {
            return null;
        }

    }

    public static Collection<String> buscarReservaPorCpf(ReservaDAO reservaDAO, String cpf) {

        if (reservaDAO.buscarReservaPorCpf(cpf) != null) {
            return reservaDAO.buscarReservaPorCpf(cpf);
        }
        return null;
    }

    public static List<Reserva> buscarTodasReservas(ReservaDAO reservaDAO) {
        try {
            List<Reserva> reservas = reservaDAO.listarReservas();
            return reservas;
        } catch (Exception e) {
            return null;
        }

    }

    public String atualizarReserva() {
        try {
            this.reservaDAO.atualizarReserva(this);
            return "Reserva Atualizada";
        } catch (Exception e) {
            return "Erro ao atualizar reserva";
        }

    }

    public static String emiteMultaCancelamentoReserva(ReservaDAO reservaDAO, ReservaQuartoDAO reservaQuartoDAO,
            QuartoDAO quartoDAO, int codigo, String cpf) {
        try {

            Reserva reservaACancelar;
            reservaACancelar = reservaDAO.buscarReserva(codigo);
            List<ReservaQuarto> reservaQuarto = reservaQuartoDAO.buscarReservaQuarto(codigo); 
            Float valorMulta;

            if (reservaACancelar.getpessoaCpf() != cpf) {
                return "ERROR : CPF não titular da reserva";
            }
            if (reservaACancelar == null) {
                return "ERROR : Reserva não encontrada";
            }

            Date dataAtual = new Date();
            Date dataDaReserva = reservaACancelar.getData();
            int i = 0;
            float soma = 0;
            if (((dataDaReserva.getTime() - dataAtual.getTime()) / (1000 * 60 * 60 * 24)) > 7) { // mais de uma semana
                                                                                                 // antes
                valorMulta = (float) 0.0;
                return valorMulta.toString();
            }

            else if (((dataDaReserva.getTime() - dataAtual.getTime()) / (1000 * 60 * 60 * 24)) > 4) { // 5 a 7 dias
                                                                                                      // antes
                while (i < reservaQuarto.size()) {
                    Quarto quartoAtual = quartoDAO.buscarQuarto(reservaQuarto.get(i).getIdQuarto());
                    soma = soma + quartoAtual.getValor() * reservaACancelar.getDiasEstadia(); // Possibilidade de
                                                                                              // mudança de atributo em
                                                                                              // reserva
                    i++;
                }
                valorMulta = (float) ((20 / 100) * soma);
                return valorMulta.toString();
            } else if (((dataDaReserva.getTime() - dataAtual.getTime()) / (1000 * 60 * 60 * 24)) > 1) { // 2 a 4 dias
                while (i < reservaQuarto.size()) {
                    Quarto quartoAtual = quartoDAO.buscarQuarto(reservaQuarto.get(i).getIdQuarto());
                    soma = soma + quartoAtual.getValor() * reservaACancelar.getDiasEstadia(); // Possibilidade de
                                                                                              // mudança de atributo em
                                                                                              // reserva
                    i++;
                }
                valorMulta = (float) ((30 / 100) * soma);
                return valorMulta.toString();
            } else if (((dataDaReserva.getTime() - dataAtual.getTime()) / (1000 * 60 * 60 * 24)) > 0) { // 24horas antes
                while (i < reservaQuarto.size()) {
                    Quarto quartoAtual = quartoDAO.buscarQuarto(reservaQuarto.get(i).getIdQuarto());
                    soma = soma + quartoAtual.getValor() * reservaACancelar.getDiasEstadia(); // Possibilidade de
                                                                                              // mudança de atributo em
                                                                                              // reserva
                    i++;
                }
                valorMulta = (float) ((50 / 100) * soma);
                return valorMulta.toString();
            } else { // trata tanto para caso na hora quanto para cancelamentos atrasados
                while (i < reservaQuarto.size()) {
                    Quarto quartoAtual = quartoDAO.buscarQuarto(reservaQuarto.get(i).getIdQuarto());
                    soma = soma + quartoAtual.getValor() * reservaACancelar.getDiasEstadia(); // Possibilidade de
                                                                                              // mudança de atributo em
                                                                                              // reserva
                    i++;
                }
                valorMulta = (float) (soma);
                return valorMulta.toString();
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String excluirReserva(ReservaDAO reservaDAO, int codigo) throws SQLException {
        String message = reservaDAO.excluirReserva(codigo);
        return message;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getDiasEstadia() {
        return diasEstadia;
    }

    public void setDiasEstadia(int diasEstadia) {
        this.diasEstadia = diasEstadia;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getpessoaCpf() {
        return pessoaCPF;
    }

    public void setpessoaCpf(String pessoaCPF) {
        this.pessoaCPF = pessoaCPF;
    }

}
