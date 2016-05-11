package barrier;

import barrier.AbstractBarrier;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Eagle extends AbstractBarrier {

    public Eagle(int x, int y){
        this.x = x;
        this.y = y;
        barrierColor = new Color(100 , 0, 100);

        imgName = "Eagle.png";
        try {
            img = ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println("There is no file");
        }
    }
}
