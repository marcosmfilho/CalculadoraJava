package widgets;

import java.awt.Rectangle;

public interface IBotao {

    public void placeIn(IBotao botao, String position);

    public Rectangle getBounds();
}
