import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import widgets.BotaoIgual;
import widgets.BotaoLimpar;
import widgets.BotaoNumero;
import widgets.BotaoOperacao;


public class Calculadora extends JFrame implements ActionListener {

    JLabel lblTelaVisor;
    
    /**
     * Buffer contendo um valor armazenado
     */
    double buffer = 0;
    
    /**
     * Último botão de operação clicado
     */
    String lastOperation = "";
    
    public Calculadora() {

        super( "Calculadora" );
        
        Container container = getContentPane();
        container.setLayout( new BorderLayout() );
        
        container.add( getPainelCalculadora(), BorderLayout.CENTER );

        setSize( 412, 360);
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
    }

    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        calculadora.setVisible( true );
    }
    
    private JComponent getPainelCalculadora() {
        JPanel painel = new JPanel();
        painel.setLayout( null );
        
        /*
         * Adiciona o visor
         */
        lblTelaVisor = new JLabel( "0.0");
        lblTelaVisor.setBounds( 2, 6, 408, 60 );
        lblTelaVisor.setBackground( Color.GREEN );
        lblTelaVisor.setForeground( Color.BLUE );
        lblTelaVisor.setFont( new Font("Monospace", Font.BOLD, 50) );
        lblTelaVisor.setHorizontalAlignment(JLabel.RIGHT);
        lblTelaVisor.setBorder( BorderFactory.createEtchedBorder() );   
        painel.add( lblTelaVisor );
        
        /*
         * Adiciona os botões de números,
         * Note que apenas o primeiro botão tem uma posuição fixa, 
         * todos os demais fica relativos à um anterior.
         */
        BotaoNumero botao7 = new BotaoNumero(7, this);
        botao7.setBounds(2, 78, botao7.getWidth(), botao7.getHeight());
        painel.add( botao7 );

        BotaoNumero botao8 = new BotaoNumero(8, this);
        botao8.placeIn(botao7, "right");
        painel.add( botao8 );

        BotaoNumero botao9 = new BotaoNumero(9, this);
        botao9.placeIn(botao8, "right");
        painel.add( botao9 );

        BotaoNumero botao4 = new BotaoNumero(4, this);
        botao4.placeIn(botao7, "bottom");
        painel.add( botao4 );

        BotaoNumero botao5 = new BotaoNumero(5, this);
        botao5.placeIn(botao4, "right");
        painel.add( botao5 );

        BotaoNumero botao6 = new BotaoNumero(6, this);
        botao6.placeIn(botao5, "right");
        painel.add( botao6 );

        BotaoNumero botao1 = new BotaoNumero(1, this);
        botao1.placeIn(botao4, "bottom");
        painel.add( botao1 );

        BotaoNumero botao2 = new BotaoNumero(2, this);
        botao2.placeIn(botao1, "right");
        painel.add( botao2 );

        BotaoNumero botao3 = new BotaoNumero(3, this);
        botao3.placeIn(botao2, "right");
        painel.add( botao3 );

        BotaoNumero botao0 = new BotaoNumero(0, this);
        botao0.setWidth( botao0.getWidth() *3 + botao0.getPadding() *2 );   //usa o espaço de três botões
        botao0.placeIn(botao1, "bottom");
        painel.add( botao0 );
        
        /*
         * Adiciona os botões de operações.
         * O posicionamento se dá da mesma forma do que com os de números.
         */
        BotaoOperacao botaoDiv = new BotaoOperacao("/", this, "div");
        botaoDiv.placeIn(botao9, "right");
        painel.add( botaoDiv );

        BotaoOperacao botaoMult = new BotaoOperacao("*", this, "mult");
        botaoMult.placeIn(botaoDiv, "bottom");
        painel.add( botaoMult );

        BotaoOperacao botaoSum = new BotaoOperacao("+", this, "sum");
        botaoSum.placeIn(botaoMult, "bottom");
        painel.add( botaoSum );

        BotaoOperacao botaoSub = new BotaoOperacao("-", this, "sub");
        botaoSub.placeIn(botaoSum, "bottom");
        painel.add( botaoSub );
        
        /*
         * Adiciona os botões de limpar e igual
         */
        BotaoLimpar botaoClear = new BotaoLimpar("C", this);
        botaoClear.placeIn(botaoDiv, "right");
        painel.add( botaoClear );
        
        BotaoIgual botaoEqual = new BotaoIgual("=", this);
        botaoEqual.setHeight( botaoEqual.getHeight() * 3 + botaoEqual.getPadding() *2 );
        botaoEqual.placeIn(botaoClear, "bottom");
        painel.add( botaoEqual );

        return painel;
    }

    /**
     * Executa a ação, dependendo do botão pressionado
     */
    public void actionPerformed(ActionEvent ev) {
        
    	/**
    	 * Valor atualmente exibido no visor
    	 */
    	double valor = Double.parseDouble( lblTelaVisor.getText() );
    	
        switch ( ev.getActionCommand() ) {
        case "botaonumero":
            /*
             * Quando um botão de número é pressionado, só precisamos
             * atualizar o visor com este.
             */
            BotaoNumero botaoNum = (BotaoNumero) ev.getSource();
            valor = valor * 10 + botaoNum.getNumber(); //*10 para "saltar" uma casa à esquerda
            
            lblTelaVisor.setText( String.valueOf(valor) );
            break;

        case "botaooperacao":
            BotaoOperacao botaoOpe = (BotaoOperacao) ev.getSource();

        	calcula();
        	
        	//tenho que garantir que o valor esteja atualizado
        	valor = Double.parseDouble( lblTelaVisor.getText() );
        	
        	//joga o valor do visor para o buffer e zera o vizor
        	buffer = valor;
        	lblTelaVisor.setText( String.valueOf(0.0) );
            
            //guarda a operação escolhida
            lastOperation = botaoOpe.getOperation();
            
            break;

        case "botaoigual":
    		calcula();
        	break;

        case "botaolimpar":
    		//limpa tudo
        	buffer = 0;
        	lastOperation = "";
        	lblTelaVisor.setText( String.valueOf(0.0) );
        	break;

        default:
            //do nothing
            break;
        }
    }
    
    /**
     * Executa o cálculo pendente,
     * com base nos dados disponíveis no buffer, lastOperation e lblTelaVisor
     */
    private void calcula() {
    	/**
    	 * Valor atualmente exibido no visor
    	 */
    	double valor = Double.parseDouble( lblTelaVisor.getText() );
    	double resultado = 0;

    	if ( buffer != 0 && lastOperation != "" ) {
    		switch (lastOperation) {
			case "sum":
				resultado = buffer + valor;
				break;
			case "sub":
				resultado = buffer - valor;
				break;
			case "mult":
				resultado = buffer * valor;
				break;
			case "div":
				resultado = buffer / valor;
				break;
			}

			lblTelaVisor.setText( String.valueOf(resultado) );

        	//ao final, reseta o buffer e lastOperation
        	buffer = 0;
        	lastOperation = "";    		
    	}
    }

}