package br.univali.cc.prog3.exceptions;

/**
 *
 * @author 1978233
 */
public class ContasMesmoNumeroException extends Exception{

    public ContasMesmoNumeroException() {
        super("Nao pode haver contas com o mesmo numero");
    }
    
}
