package tanks;

import interfaces.Direction;
import launchMain.ActionField;
import launchMain.BattleField;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class T34 extends AbstractTank{
    public T34(ActionField af, BattleField bf){
        this(af, bf, 0, 64, Direction.RIGHT);
    }

    public T34(ActionField af, BattleField bf, int x, int y, Direction direction){
        super(af, bf, x, y, direction);
        tankColor = new Color(0, 255, 0);
        towerColor = new Color(255, 0, 0);
        speed=20;

        imgName = "GreenTank.png";
        try {
            img = ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println("There is no file");
        }
    }

    public void destroy() throws Exception{
        updateX(-100);
        updateY(-100);
        af.repaint();
    }

    @Override
    public Action setUp() {
        return Action.MOVE;
    }

    @Override
    public int getMovePath() {
        return 0;
    }
}
