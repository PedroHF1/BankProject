package br.univali.cc.prog3.banco;
import br.univali.cc.prog3.exceptions.ContasInexistentesException;
import br.univali.cc.prog3.exceptions.ContasMesmoNumeroException;
import br.univali.cc.prog3.exceptions.SaldoInsuficienteException;
import java.util.*;
/**
 *
 * @author 1978233
 */
public class Banco {
    private String nome;
    private int numero;
    private Collection<ContaCorrente> contas = new ArrayList<ContaCorrente>();

    public Banco(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
    }
    
    public void criarConta(int numeroConta, double saldoInicial) throws ContasMesmoNumeroException {
        ContaCorrente conta = new ContaCorrente(numeroConta, saldoInicial);
        for(ContaCorrente contaCorrente:contas) {
            if(contaCorrente.getNumeroConta() == numeroConta) {
                throw new ContasMesmoNumeroException();
            }
        }
            contas.add(conta);

    }
    
    public void criarConta(int numeroConta, double saldoInicial, double limite) throws ContasMesmoNumeroException  {
        ContaCorrente conta = new ContaCorrente(numeroConta, saldoInicial, limite);
        for(ContaCorrente contaCorrente:contas) {
            if(contaCorrente.getNumeroConta() == numeroConta) {
                throw new ContasMesmoNumeroException();
            }
        }
            contas.add(conta);
    }    
    
    public void depositar(int numeroConta, double valor) {
        ContaCorrente conta = this.localizarConta(numeroConta);
        if (conta != null) {
            conta.depositar(valor);
        }
    }
    
    public void sacar(int numeroConta, double valor) throws SaldoInsuficienteException {
        ContaCorrente conta = this.localizarConta(numeroConta);
        if (conta != null) {
            conta.sacar(valor);
        }
    }
    
    public void transferir(int numeroContaOrigem, int numeroContaDestino, double valor) throws SaldoInsuficienteException, ContasInexistentesException {
        if (this.localizarConta(numeroContaOrigem) == null || this.localizarConta(numeroContaDestino) == null){
            throw new ContasInexistentesException();
        }
        ContaCorrente contaOrigem = this.localizarConta(numeroContaOrigem);
        ContaCorrente contaDestino = this.localizarConta(numeroContaDestino);
        if (contaOrigem != null && contaDestino != null) {
            if (contaOrigem.sacar(valor)) {
                contaDestino.depositar(valor);
            }
        }
    }
    
    public String emitirExtrato(int numeroConta) {
        ContaCorrente conta = this.localizarConta(numeroConta);
        if (conta != null) {
            return conta.emitirExtrato();
        }
        return "Conta não encontrada";
    }

    public String getNome() {
        return nome;
    }
    
    
    
    private ContaCorrente localizarConta(int numeroConta) {
        for(ContaCorrente conta:contas) {
            if (conta != null && conta.getNumeroConta()==numeroConta) {
                return conta;
            }
        }
        return null;
    }
    
}
