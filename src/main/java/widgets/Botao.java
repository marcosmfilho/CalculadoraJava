package widgets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;

/**
 * Classe para botões genéricos.
 * Reúne as propriedades e métodos comuns aos diferentes tipos de botões.
 * 
 * @author diovani
 *
 */
public class Botao extends JButton {

    private int width   = 80;
    private int height  = 60;
    private int padding = 2;

    public Botao(String text) {
        super(text);
        
        this.setForeground( Color.RED );
        this.setFont( new Font("Arial", Font.BOLD, 50) );
    }

    /**
     * Posiciona o botão, de acordo com a posição de outro botão e uma direçãi
     * 
     * @param botao Botão de referência para posicionar
     * @param position Posição, referente ao outro botão (top, bottom, left, right)
     */
    public void placeIn(IBotao botao, String position) {
        Rectangle bounds = botao.getBounds();
        
        switch (position) {
        case "top":
            this.setBounds(bounds.x, bounds.y - bounds.height - this.padding, this.width, this.height);
            break;
        case "bottom":
            this.setBounds(bounds.x, bounds.y + bounds.height + this.padding, this.width, this.height);
            break;
        case "left":
            this.setBounds(bounds.x - bounds.width - this.padding, bounds.y, this.width, this.height);
            break;
        case "right":
        default:
            this.setBounds(bounds.x + bounds.width + this.padding, bounds.y, this.width, this.height);
            break;
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }    
}
