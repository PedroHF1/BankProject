
import br.univali.cc.prog3.banco.Banco;
import br.univali.cc.prog3.visao.CaixaEletronico;
import br.univali.cc.prog3.visao.CaixaEletronicoGUI;

/**
 *
 * @author 1978233
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Banco bb = new Banco("Banco do Brasil", 1);
        
        String tipo = args.length > 0 ? args[0] : "console";
        CaixaEletronico caixa;
        if (tipo.equalsIgnoreCase("console")){
            caixa = new CaixaEletronico(bb);
        } else {
            caixa = new CaixaEletronicoGUI(bb);
        }
        caixa.menu();
    }
    
}
