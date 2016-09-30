package widgets;

import java.awt.event.ActionListener;

public class BotaoLimpar extends Botao implements IBotao {

    public BotaoLimpar(String text, ActionListener act) {
        super(text);
        
        this.setActionCommand("botaolimpar");
        this.addActionListener(act);        
    }

}
