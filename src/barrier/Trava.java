package barrier;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Trava extends AbstractBarrier{
    public Trava(int x, int y){
        this.x = x;
        this.y = y;

        imgName = "Trava.jpg";
        try {
            img = ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println("There is no file");
        }
    }
}
