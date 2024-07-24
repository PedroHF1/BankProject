package br.univali.cc.prog3.visao;

import br.univali.cc.prog3.exceptions.SaldoInsuficienteException;
import br.univali.cc.prog3.exceptions.ContasMesmoNumeroException;
import br.univali.cc.prog3.exceptions.ContasInexistentesException;
import br.univali.cc.prog3.banco.Banco;
import java.util.Scanner;

/**
 *
 * @author 1978233
 */
public class CaixaEletronico {
    Banco banco;

    public CaixaEletronico(Banco banco) {
        this.banco = banco;
    }
    
    protected String lerValor(String rotulo) {
        Scanner leitor = new Scanner(System.in);
        System.out.print(rotulo+": ");
        return leitor.nextLine();
    }
    
    protected void escrever(String valor) {
        System.out.println(valor);
    }
    
    public void menu() {
        System.out.println("Caixa Eletronico >>>>"+this.banco.getNome()+"<<<<");
        char opcao;
        do {
            System.out.println("1. Criar Conta Corrente");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Tranferir");
            System.out.println("5. Emitir Extrato");
            System.out.println("6. Sair");
            opcao = this.lerValor("Digite a opção").charAt(0);
            
            switch (opcao) {
                case '1': this.criarConta(); break;
                case '2': this.depositar(); break;
                case '3': this.sacar(); break;
                case '4': this.transferir(); break;
                case '5': this.emitirExtrato(); break;
                case '6': this.escrever("Hasta la vista baby!"); break;
            }
            
        } while (opcao != '6');
        
    }

    protected void criarConta() {
        int numeroConta = Integer.parseInt(this.lerValor("Digite o numero da conta"));
        double saldo = Double.parseDouble(this.lerValor("Digite o saldo inicial"));
        boolean especial = this.lerValor("1 - Especial / 2 - Normal").charAt(0) == '1';
        if (especial) {
            double limite = Double.parseDouble(this.lerValor("Digite o limite"));
            try {
                this.banco.criarConta(numeroConta, saldo, limite);
            } catch (ContasMesmoNumeroException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            try {
                this.banco.criarConta(numeroConta, saldo);
            } catch (ContasMesmoNumeroException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    protected void depositar() {
        int numero = Integer.parseInt(this.lerValor("Digite o numero da conta"));
        double valor = Double.parseDouble(this.lerValor("Digite o valor"));
        this.banco.depositar(numero, valor);
    }

    protected void sacar() {
        int numero = Integer.parseInt(this.lerValor("Digite o numero da conta"));
        double valor = Double.parseDouble(this.lerValor("Digite o valor"));
        try {
             this.banco.sacar(numero, valor);
        } catch (SaldoInsuficienteException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

    protected void transferir() {
        int contaOrigem = Integer.parseInt(this.lerValor("Digite o numero da conta de origem"));
        int contaDestino = Integer.parseInt(this.lerValor("Digite o numero da conta de destino"));
        double valor = Double.parseDouble(this.lerValor("Digite o valor"));
        try {
            this.banco.transferir(contaOrigem, contaDestino, valor);
        } catch (SaldoInsuficienteException | ContasInexistentesException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    protected void emitirExtrato() {
        int numero = Integer.parseInt(this.lerValor("Digite o numero da conta"));
        this.escrever(banco.emitirExtrato(numero));
    }
}
