package barrier;


import interfaces.Drowable;

import java.awt.*;
import java.awt.image.ImageObserver;

public class AbstractBarrier implements Drowable {
    protected int x;
    protected int y;
    protected Color barrierColor;
    protected Image img;
    protected String imgName;

    AbstractBarrier(){

    }

    public void draw(Graphics g){
//        g.setColor(barrierColor);
//        g.fillRect(x, y, 64, 64);

        g.drawImage(img, x, y, new ImageObserver(){

            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height){
                return false;
            }
        });
    }
}
