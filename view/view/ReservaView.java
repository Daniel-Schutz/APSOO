package view;
import java.util.Scanner;

public class ReservaView {
    private Scanner scanner;

    public ReservaView() {
        scanner = new Scanner(System.in);
    }

    public void escolhaConfirmarOuCancelar() {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Confirmar reserva");
        System.out.println("2. Cancelar reserva");
        int opcao = scanner.nextInt();
        scanner.nextLine();  // Limpa o buffer

        if (opcao == 1) {
            confirma();
        } else if (opcao == 2) {
            cancela();
        } else {
            System.out.println("Opção inválida");
        }
    }

    public void pagamento(char tipoPagamento) {
        System.out.println("Forma de pagamento selecionada: " + tipoPagamento);
        // Implemente a lógica de pagamento aqui
    }

    public void selecaoQuartos() {
        System.out.println("Selecione os quartos desejados:");
        // Implemente a lógica de seleção de quartos aqui
    }

    public void quantQuartos() {
        System.out.println("Digite a quantidade de quartos:");
        int quantidade = scanner.nextInt();
        scanner.nextLine();  // Limpa o buffer
        System.out.println("Quantidade de quartos selecionada: " + quantidade);
    }

    public void quantPessoas() {
        System.out.println("Digite a quantidade de pessoas:");
        int quantidade = scanner.nextInt();
        scanner.nextLine();  // Limpa o buffer
        System.out.println("Quantidade de pessoas selecionada: " + quantidade);
    }

    public void dataSaida() {
        System.out.println("Digite a data de saída:");
        String data = scanner.nextLine();
        System.out.println("Data de saída selecionada: " + data);
    }

    public void dataEntrada() {
        System.out.println("Digite a data de entrada:");
        String data = scanner.nextLine();
        System.out.println("Data de entrada selecionada: " + data);
    }

    public void CPF() {
        System.out.println("Digite o CPF:");
        String cpf = scanner.nextLine();
        System.out.println("CPF digitado: " + cpf);
    }

    public void cancela() {
        System.out.println("Reserva cancelada");
        // Implemente a lógica de cancelamento da reserva aqui
    }

    public void confirma() {
        System.out.println("Reserva confirmada");
        // Implemente a lógica de confirmação da reserva aqui
    }
}
