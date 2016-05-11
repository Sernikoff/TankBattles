package tanks;

import interfaces.Destroyable;
import interfaces.Direction;
import interfaces.Drowable;
import launchMain.ActionField;
import launchMain.BattleField;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Random;


public abstract class AbstractTank implements Tank {

    protected int speed;
    protected Direction direction;
    protected int x;
    protected int y;
    protected Color tankColor;
    protected Color towerColor;

    protected ActionField af;
    protected BattleField bf;

    protected Image img;
    protected String imgName;



    protected AbstractTank(ActionField af, BattleField bf, int x, int y, Direction direction){
        this.af = af;
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.direction = direction;

    }

    public int getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void turn(Direction direction) throws Exception{
        af.processTurn(this);
    }

    public void move() throws Exception{
        af.processMove(this);
    }

    public void fire() throws Exception{
        Bullet bullet = new Bullet((x+25), (y+25), direction);
        af.processFire(bullet);
    }

    public void updateX(int x){
        this.x += x;
    }

    public void updateY(int y){
        this.y += y;
    }


    public void moveRandome() throws Exception{
        Random r = new Random();
        int i;
        while (true) {
            i = r.nextInt(5);
            if (i > 0) {
                if (i==1) {direction = Direction.UP;}
                if (i==2) {direction = Direction.DOWN;}
                if (i==3) {direction = Direction.LEFT;}
                if (i==4) {direction = Direction.RIGHT;}
                turn(direction);
                fire();
                move();
            }
        }
    }

    public void attacEagle() throws Exception {
        while (bf.scanQuadrant(8, 4).equals("E")){
            int y = this.y/64;
            int x = this.x/64;
         int v = y;
         int h = x;
            int min = 88;
            if(y<8){
                min = bf.getMapDistances()[y+1][x];
                v = y+1; h = x;
                direction = Direction.DOWN;
            }
            if(x>0 && bf.getMapDistances()[y][x-1]<min){
                min = bf.getMapDistances()[v][h-1];
                v = y; h = x-1;
                direction = Direction.LEFT;
            }
            if(y>0 && bf.getMapDistances()[y-1][x]<min){
                min = bf.getMapDistances()[v-1][h];
                v = y-1; h = x;
                direction = Direction.UP;
            }
            if(x<8 && bf.getMapDistances()[y][x+1]<min){
                min = bf.getMapDistances()[v][h+1];
                v = y; h = x+1;
                direction = Direction.RIGHT;
            }

            if (bf.scanQuadrant(v,h).equals("B") || bf.scanQuadrant(v,h).equals("E")){
                fire();
            }
            move();
        }
    }


    public void moveToQuadrant(int v, int h) throws Exception {
        int x1 = af.getQuadrant(h);
        int y1 = af.getQuadrant(v);
        int hX = (x1 - x) / 64;
        int vY = (y1 - y) / 64;
        System.out.println("hX+vY= "+hX+"+"+vY);
        if (hX > 0) {
            int i = 0;
            while (i < hX) {
                turn(Direction.RIGHT);
                if (bf.battleField[x/64][y/64+1]=="B"){fire();}
                af.processMove(this);
                i++;
            }
        }
        if (hX < 0) {
            int i = 0;
            while (i > hX) {
                turn(Direction.LEFT);
                if (bf.battleField[y/64][x/64-1]=="B"){fire();}
                af.processMove(this);
                i--;
            }
        }
        if (vY > 0) {
            int i = 0;
            while (i < vY) {
                turn(Direction.DOWN);
                if (bf.battleField[y/64+1][x/64]=="B"){fire();}
                af.processMove(this);
                i++;
            }
        }
        if (vY < 0) {
            int i = 0;
            while (i > vY) {
                turn(Direction.UP);
                if (bf.battleField[y/64-1][x/64]=="B"){fire();}
                af.processMove(this);
                i--;
            }
        }
        Thread.sleep(1000l);
    }


    void clean() throws Exception{
        int i;
        moveToQuadrant(1,1);
        for (i = 1; i<10; i++) {
            moveToQuadrant(i,1);
            turn(Direction.RIGHT);
            for (String elem : bf.battleField[i-1]) {
                if (elem == "B") {
                    fire();
                }
            }
        }
    }


    public void draw(Graphics g){
//        g.setColor(tankColor);
//        g.fillRect( this.getX(), this.getY(), 64, 64 );
//
//        g.setColor(towerColor);
//        if (direction == Direction.UP) {
//            g.fillRect(this.getX() + 20, this.getY(), 24, 34);
//        } else if (direction == Direction.DOWN) {
//            g.fillRect(this.getX() + 20, this.getY() + 30, 24, 34);
//        } else if (direction == Direction.LEFT) {
//            g.fillRect(this.getX(), this.getY() + 20, 34, 24);
//        } else {
//            g.fillRect(this.getX() + 30, this.getY() + 20, 34, 24);
//        }

        g.drawImage(img, x, y, new ImageObserver(){

            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height){
                return false;
            }
        });
    }
}
