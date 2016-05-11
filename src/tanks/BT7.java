package tanks;

import interfaces.Direction;
import launchMain.ActionField;
import launchMain.BattleField;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class BT7 extends AbstractTank {

    public BT7(ActionField af, BattleField bf) {
        this(af, bf, 0, 64, Direction.RIGHT);
    }

    public BT7(ActionField af, BattleField bf, int x, int y, Direction direction) {
        super(af, bf, x, y, direction);
        tankColor = new Color(0, 150, 0);
        towerColor = new Color(150, 0, 0);
        speed = 10;

        imgName = "СамТанк.jpg";
        try {
            img = ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println("There is no file");
        }
    }

    public void destroy() throws Exception {
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

