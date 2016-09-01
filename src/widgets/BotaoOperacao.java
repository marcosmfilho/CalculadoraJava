package widgets;

import java.awt.event.ActionListener;

/**
 * Botões de operações básicas,
 * soma, subtração, multiplicação, divisão e igualdade
 * @author diovani
 *
 */
public class BotaoOperacao extends Botao implements IBotao {
    
    private String operation;

	public BotaoOperacao(String text, ActionListener act, String operation) {
        super(text);
        this.operation = operation;
        
        this.setActionCommand("botaooperacao");
        this.addActionListener(act);
    }

    public String getOperation() {
		return operation;
	}

}
