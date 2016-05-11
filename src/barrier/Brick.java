package barrier;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Brick extends AbstractBarrier{
    public Brick(int x, int y){
        this.x = x;
        this.y = y;
        barrierColor = new Color(70 , 0, 0);
        imgName = "Kirpich.jpg";
        try {
            img = ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println("There is no file");
        }
    }
}
