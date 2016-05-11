package barrier;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Rock extends AbstractBarrier{

    public Rock(int x, int y){
        this.x = x;
        this.y = y;
        barrierColor = new Color(0 , 0, 0);

        imgName = "Skala.jpg";
        try {
            img = ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println("There is no file");
        }
    }
}
