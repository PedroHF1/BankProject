package br.univali.cc.prog3.visao;

import br.univali.cc.prog3.banco.Banco;
import javax.swing.JOptionPane;

/**
 *
 * @author 1978233
 */
public class CaixaEletronicoGUI extends CaixaEletronico{

    public CaixaEletronicoGUI(Banco banco) {
        super(banco);
    }

    
    @Override
    protected String lerValor(String rotulo) {
        return JOptionPane.showInputDialog(null, rotulo);
    }
    
    @Override
    protected void escrever(String valor) {
        JOptionPane.showMessageDialog(null, valor);
    }
    
    @Override
    public void menu() {
        
        String[] opcoes = {
            "1. Criar conta corrente",
            "2. Depositar",
            "3. Sacar",
            "4. Tranferir",
            "5. Emitir Extrato",
            "6. Sair"
        };
        
        char opcao;
        do {
            Object resposta = JOptionPane.showInputDialog(null, 
                "Escolha uma opção",
                "Menu do banco "+this.banco.getNome(),
                JOptionPane.QUESTION_MESSAGE, null, opcoes, "");
            opcao = resposta != null ? ((String)resposta).charAt(0) : '6'; 
            
            switch (opcao) {
                case '1': this.criarConta(); break;
                case '2': this.depositar(); break;
                case '3': this.sacar(); break;
                case '4': this.transferir(); break;
                case '5': this.emitirExtrato(); break;
                case '6': this.escrever("Hasta la vista baby"); break;
            }
            
        } while (opcao != '6');
        
    }

    
}
