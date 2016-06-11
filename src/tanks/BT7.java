package tanks;

import interfaces.Direction;
import launchMain.ActionField;
import launchMain.BattleField;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
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
        speed = 3;

        try {
            img[0] = ImageIO.read(new File("BT7_Up.jpeg"));
            img[1] = ImageIO.read(new File("BT7_Down.jpeg"));
            img[2] = ImageIO.read(new File("BT7_Left.jpeg"));
            img[3] = ImageIO.read(new File("BT7_Right.jpeg"));
        } catch (IOException e) {
            System.out.println("There is no file");
        }
    }

//    public void draw(Graphics g){
//
//        Graphics2D g2d = (Graphics2D) g.create();
//        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
//        g2d.setComposite(alphaComposite);
//        g2d.drawImage(img[getDirection().getId()], x, y, new ImageObserver(){
//
//            @Override
//            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height){
//                return false;
//            }
//        });
//    }

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

