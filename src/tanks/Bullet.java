package tanks;

import interfaces.Destroyable;
import interfaces.Direction;
import interfaces.Drowable;

import java.awt.*;


public class Bullet implements Drowable, Destroyable {
    private  int x;
    private  int y;
    private  int speed=5;
    private Direction direction;

    Bullet(int x, int y, Direction direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }


    public int getBulletX() {
        return x;
    }

    public int getBulletY() {
        return y;
    }

    public int getBulletSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void updateX(int x){
        this.x += x;
    }

    public void updateY(int y){
        this.y += y;
    }

    public void destroy(){
        x = -100;
        y = -100;
    }

    public void draw(Graphics g){
        g.setColor(new Color(255, 255, 0));
        g.fillRect(x, y, 14, 14);
    }
}
