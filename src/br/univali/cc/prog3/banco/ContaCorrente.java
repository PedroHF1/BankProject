package br.univali.cc.prog3.banco;
import br.univali.cc.prog3.exceptions.SaldoInsuficienteException;
import java.util.*;
/**
 *
 * @author 1978233
 */
public class ContaCorrente {
    private boolean especial;
    private double limite;
    private int numero;
    private double saldo;
    private Collection<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();

    public ContaCorrente( int numero, double saldo) {
        this.especial = false;
        this.limite = 0;
        this.numero = numero;
        this.saldo = saldo;
        this.criarMovimentacao("Deposito inicial", 'C', saldo);
    }

    public ContaCorrente( int numero, double saldo, double limite) {
        this.especial = true;
        this.limite = limite;
        this.saldo = saldo;
        this.numero = numero;
        this.criarMovimentacao("Deposito inicial", 'C', saldo);
    } 

    public int getNumeroConta() {
        return this.numero;
    }

    public double getSaldo() {
        return this.saldo;
    }
    
    protected boolean depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            this.criarMovimentacao("deposito", 'C', valor);
            return true;
        }
        return false;
    }
    
    protected boolean sacar(double valor) throws SaldoInsuficienteException {
        double saldoDisponivel = this.especial ? 
                this.saldo+this.limite : 
                this.saldo;
        
        if (valor > 0 && valor <= saldoDisponivel) {
            this.saldo -= valor;
            this.criarMovimentacao("saque", 'D', valor);
            return true;
        }
        throw new SaldoInsuficienteException();
    }
    
    protected String emitirExtrato() {
        String extrato = "Extrato bancário da conta "+this.numero;
        for (Movimentacao movimentacao:movimentacoes) {
            if (movimentacao == null) {
                break;
            }
            extrato += "\n "+movimentacao.getMovimentacao();
        }
        extrato += "\n Saldo final: "+this.getSaldo();
        return extrato;
    }
    
    private void criarMovimentacao(String descricao, char tipo, double valor) {
        Movimentacao movimentacao = new Movimentacao(descricao, tipo, valor);
        movimentacoes.add(movimentacao);
    }
    
    
    
    
}
