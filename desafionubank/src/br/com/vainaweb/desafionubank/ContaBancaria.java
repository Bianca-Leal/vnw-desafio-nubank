package br.com.vainaweb.desafionubank;

import br.com.vainaweb.desafionubank.Conta;
import br.com.vainaweb.desafionubank.OperacoesBancarias;

interface OperacoesBancarias {
    void sacar(double valor);
    void depositar(double valor);
    void transferir(Conta destino, double valor);
}

abstract class Conta implements OperacoesBancarias {
    private String numero;
    private String agencia;
    private String nomeTitular;
    private String cpfTitular;
    private double saldo;

    public Conta(String numero, String agencia, String nomeTitular, String cpfTitular) {
        this.numero = numero;
        this.agencia = agencia;
        this.nomeTitular = nomeTitular;
        this.cpfTitular = cpfTitular;
        this.saldo = 0.0;
    }

    public String getNumero() {
        return numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public abstract void sacar(double valor);
    public abstract void depositar(double valor);
    public abstract void transferir(Conta destino, double valor);
}

class ContaCorrente extends Conta {
    private double limiteCredito;

    public ContaCorrente(String numero, String agencia, String nomeTitular, String cpfTitular, double limiteCredito) {
        super(numero, agencia, nomeTitular, cpfTitular);
        this.limiteCredito = limiteCredito;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= (getSaldo() + limiteCredito)) {
            double novoSaldo = getSaldo() - valor;
            setSaldo(novoSaldo);
            System.out.println("Saque realizado com sucesso. Novo saldo: " + getSaldo());
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public void depositar(double valor) {
        if (valor > 0) {
            double novoSaldo = getSaldo() + valor;
            setSaldo(novoSaldo);
            System.out.println("Depósito realizado com sucesso. Novo saldo: " + getSaldo());
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public void transferir(Conta destino, double valor) {
        if (valor > 0 && valor <= (getSaldo() + limiteCredito)) {
            double novoSaldo = getSaldo() - valor;
            setSaldo(novoSaldo);
            System.out.println("Transferência realizada com sucesso. Novo saldo: " + getSaldo());
        } else {
            System.out.println("Saldo insuficiente para realizar a transferência.");
        }
    }
}

class ContaPoupanca extends Conta {
    private int diaAniversario;

    public ContaPoupanca(String numero, String agencia, String nomeTitular, String cpfTitular, int diaAniversario) {
        super(numero, agencia, nomeTitular, cpfTitular);
        this.diaAniversario = diaAniversario;
    }

    public int getDiaAniversario() {
        return diaAniversario;
    }

    public void sacar(double valor) {
        double taxa = valor * 0.02; 
        double valorComTaxa = valor + taxa;
        if (valorComTaxa <= getSaldo()) {
            double novoSaldo = getSaldo() - valorComTaxa;
            setSaldo(novoSaldo);
            System.out.println("Saque de R$" + String.format("%.2f", valorComTaxa) + " realizado com sucesso, incluindo a taxa de 2%.");
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
    }

    public void depositar(double valor) {
        if (valor > 0) {
            double novoSaldo = getSaldo() + valor;
            setSaldo(novoSaldo);
            System.out.println("Depósito de R$" + String.format("%.2f", valor) + " realizado com sucesso. Novo saldo: " + String.format("%.2f", getSaldo()));
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }


    public void transferir(Conta destino, double valor) {
        if (valor > 0 && valor <= getSaldo()) {
            double novoSaldo = getSaldo() - valor;
            setSaldo(novoSaldo);
            System.out.println("Transferência realizada com sucesso. Saldo atual da conta: " + String.format("%.2f", novoSaldo));
        } else {
            System.out.println("Saldo insuficiente para realizar a transferência.");
        }
    }
}
