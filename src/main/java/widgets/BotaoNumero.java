package widgets;

import java.awt.event.ActionListener;

/**
 * Classe para criação de um botão de número (0-9).
 * @author diovani
 *
 */
public class BotaoNumero extends Botao implements IBotao {
        
    /**
     * O número/valor do botão
     */
    private int number;

    /**
     * 
     * @param number Número que representa/imprime o botão
     * @param act Objeto que interpreta as ações deste botão
     */
    public BotaoNumero(int number, ActionListener act) {
        super( String.valueOf(number) );
        this.number = number;
        
        this.setActionCommand("botaonumero");
        this.addActionListener(act);
    }
    
    public int getNumber() {
        return this.number;
    }    
}
