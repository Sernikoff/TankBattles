package barrier;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Сергей on 14.04.2016.
 */
public class Water extends AbstractBarrier{
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
}
