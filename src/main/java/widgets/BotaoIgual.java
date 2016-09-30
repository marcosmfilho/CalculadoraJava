package widgets;

import java.awt.event.ActionListener;

public class BotaoIgual extends Botao implements IBotao {

	public BotaoIgual(String text, ActionListener act) {
        super(text);
        
        this.setActionCommand("botaoigual");
        this.addActionListener(act);
    }

}
