package barrier;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * Created by Сергей on 14.04.2016.
 */
public class Water extends AbstractBarrier{

    float alphaValue = 0.8f;
    int compositeRule = AlphaComposite.SRC_OVER;
    AlphaComposite ac;

    public	Water(int x, int y){
        this.x = x;
        this.y = y;
        barrierColor = new Color(0 , 0, 230);

        imgName = "Woda.jpg";
        try {
            img = ImageIO.read(new File(imgName));

        } catch (IOException e) {
            System.out.println("There is no file");
        }
    }

    @Override
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Composite org = g2d.getComposite();
        ac = AlphaComposite.getInstance(compositeRule, alphaValue);
        g2d.setComposite(ac);

        g.drawImage(img, x, y, new ImageObserver(){

            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height){
                return false;
            }
        });
        g2d.setComposite(org);
    }
}
