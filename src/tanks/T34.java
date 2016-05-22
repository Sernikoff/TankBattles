package tanks;

import interfaces.Direction;
import launchMain.ActionField;
import launchMain.BattleField;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;


public class T34 extends AbstractTank{
    public T34(ActionField af, BattleField bf){
        this(af, bf, 448, 512, Direction.UP);
    }

    public T34(ActionField af, BattleField bf, int x, int y, Direction direction){
        super(af, bf, x, y, direction);
        tankColor = new Color(0, 255, 0);
        towerColor = new Color(255, 0, 0);
        speed=5;

        try {
            img[0] = ImageIO.read(new File("T34_Up.jpeg"));
            img[1] = ImageIO.read(new File("T34_Down.jpeg"));
            img[2] = ImageIO.read(new File("T34_Left.jpeg"));
            img[3] = ImageIO.read(new File("T34_Right.jpeg"));
        } catch (IOException e) {
            System.out.println("There is no file");
        }
    }

    public void draw(Graphics g){

        Graphics2D g2d = (Graphics2D) g.create();
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        g2d.setComposite(alphaComposite);
        System.out.println("t34 x= "+x +" y = "+y);
        g.drawImage(img[getDirection().getId()], x, y, new ImageObserver(){

            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height){
                return false;
            }
        });
    }

    public void destroy() throws Exception{
        updateX(-1000);
        updateY(-1000);
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
