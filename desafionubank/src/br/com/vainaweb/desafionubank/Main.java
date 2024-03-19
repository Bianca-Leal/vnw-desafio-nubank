package br.com.vainaweb.desafionubank;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        ArrayList<Conta> contas = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Criar conta corrente");
            System.out.println("2. Criar conta poupança");
            System.out.println("3. Realizar operações bancárias");
            System.out.println("4. Visualizar todas as contas");
            System.out.println("5. Buscar uma conta pelo número");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Digite o número da conta: ");
                    String numeroContaCC = scanner.nextLine();
                    System.out.print("Digite a agência da conta: ");
                    String agenciaCC = scanner.nextLine();
                    System.out.print("Digite o nome do titular da conta: ");
                    String nomeTitularCC = scanner.nextLine();
                    System.out.print("Digite o CPF do titular da conta: ");
                    String cpfTitularCC = scanner.nextLine();
                    System.out.print("Digite o limite de crédito da conta: ");
                    double limiteCreditoCC = scanner.nextDouble();
                    contas.add(new ContaCorrente(numeroContaCC, agenciaCC, nomeTitularCC, cpfTitularCC, limiteCreditoCC));
                    System.out.println("Conta corrente criada com sucesso.");
                    break;
                case 2:
                    System.out.print("Digite o número da conta: ");
                    String numeroContaCP = scanner.nextLine();
                    System.out.print("Digite a agência da conta: ");
                    String agenciaCP = scanner.nextLine();
                    System.out.print("Digite o nome do titular da conta: ");
                    String nomeTitularCP = scanner.nextLine();
                    System.out.print("Digite o CPF do titular da conta: ");
                    String cpfTitularCP = scanner.nextLine();
                    System.out.print("Digite o dia de aniversário da conta: ");
                    int diaAniversario = scanner.nextInt();
                    scanner.nextLine();
                    contas.add(new ContaPoupanca(numeroContaCP, agenciaCP, nomeTitularCP, cpfTitularCP, diaAniversario));
                    System.out.println("Conta poupança criada com sucesso.");
                    break;

                case 3:
                    System.out.println("Operações Bancárias:");
                    System.out.print("Digite o número da conta: ");
                    String numeroContaOperacoes = scanner.nextLine();
                    Conta contaOperacoes = null;
                   
                    for (Conta conta : contas) {
                        if (conta.getNumero().equals(numeroContaOperacoes)) {
                            contaOperacoes = conta;
                            break;
                        }
                    }
                    if (contaOperacoes != null) {
                        System.out.println("Conta encontrada: " + contaOperacoes.getNumero() + " - " + contaOperacoes.getNomeTitular());
                        while (true) {
                            System.out.println("\n1. Sacar");
                            System.out.println("2. Depositar");
                            System.out.println("3. Transferir");
                            System.out.println("4. Voltar");
                            System.out.print("Escolha uma opção: ");
                            int opcaoOperacoes = scanner.nextInt();
                            scanner.nextLine(); 
                            switch (opcaoOperacoes) {
                                case 1:
                                    System.out.print("Digite o valor que deseja sacar: ");
                                    double valorSaque = scanner.nextDouble();
                                    contaOperacoes.sacar(valorSaque);
                                    break;
                                case 2:
                                    System.out.print("Digite o valor que deseja depositar: ");
                                    double valorDeposito = scanner.nextDouble();
                                    contaOperacoes.depositar(valorDeposito);
                                    break;
                                case 3:
                                    System.out.print("Digite o número da conta de destino: ");
                                    String numeroContaDestino = scanner.nextLine();
                                    Conta contaDestino = null;
                                   
                                    for (Conta conta : contas) {
                                        if (conta.getNumero().equals(numeroContaDestino)) {
                                            contaDestino = conta;
                                            break;
                                        }
                                    }
                                    if (contaDestino != null) {
                                        System.out.print("Digite o valor que deseja transferir: ");
                                        double valorTransferencia = scanner.nextDouble();
                                        contaOperacoes.transferir(contaDestino, valorTransferencia);
                                    } else {
                                        System.out.println("Conta de destino não encontrada.");
                                    }
                                    break;
                                case 4:
                                    System.out.println("Voltando ao menu principal.");
                                    break;
                                default:
                                    System.out.println("Opção inválida. Escolha uma opção de 1 a 4.");
                            }
                            if (opcaoOperacoes == 4) {
                                break; 
                            }
                        }
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;

                case 4:
                    System.out.println("Visualizar todas as contas:");
                    for (Conta conta : contas) {
                        System.out.println(conta.getNumero() + " - " + conta.getNomeTitular());
                    }
                    break;
                case 5:
                    System.out.print("Digite o número da conta que deseja buscar: ");
                    String numeroContaBusca = scanner.nextLine();
                    boolean encontrada = false;
                    for (Conta conta : contas) {
                        if (conta.getNumero().equals(numeroContaBusca)) {
                            System.out.println("Conta encontrada: " + conta.getNumero() + " - " + conta.getNomeTitular());
                            encontrada = true;
                            break;
                        }
                    }
                    if (!encontrada) {
                        System.out.println("Conta não encontrada.");
                    }
                    break;
                case 6:
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Escolha uma opção de 1 a 6.");
            }
        }
    }
}