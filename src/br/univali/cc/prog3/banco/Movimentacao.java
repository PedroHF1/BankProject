package br.univali.cc.prog3.banco;

/**
 *
 * @author 1978233
 */
public class Movimentacao {
    private final String descricao;
    private final char tipo;
    private final double valor;

    public Movimentacao(String descricao, char tipo, double valor) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
    }
    
    public String getMovimentacao() {
        return this.descricao+" ("+this.tipo+") R$ "+this.valor;
    }
    
    
}
