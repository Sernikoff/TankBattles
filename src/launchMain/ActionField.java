package launchMain;

import barrier.Water;
import interfaces.Direction;
import tanks.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.bind.SchemaOutputResolver;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.awt.Color.BLUE;


public class ActionField extends JPanel {
    JFrame frame;
    Menushka menu;
    GameOver gameOver;
    int speed = 10;
    private BattleField bf;
    private AbstractTank defender;
    private AbstractTank agressorE;
    private AbstractTank agressorD;
    private Bullet bullet;
    private Direction direction;
    private Registrator registrator;
    private Map<String,String> map;

    void runTheGame() throws Exception {
//          defender.fire();
//          defender.fire();
//        defender.fire();
//        restartTigr();
//        defender.fire();
//        defender.fire();

//        agressorBT7.attacEagle();
//


//        defender.setDirection(Direction.LEFT);
//        defender.move();
//        defender.setDirection(Direction.UP);
//        defender.move();defender.move();defender.move();
//        defender.setDirection(Direction.LEFT);
//        defender.move();defender.move();
//        defender.setDirection(Direction.UP);
//        defender.move();
//        defender.setDirection(Direction.LEFT);
//        defender.move();
//        defender.setDirection(Direction.DOWN);
//        defender.move(); defender.move();
      //  agressorD.attacTank(defender);
        agressorE.attacEagle();
        agressorD.attacTank(defender);
    //    System.out.println(Arrays.deepToString(bf.getMapDistances()));

    }

    void replay()throws Exception {

        File file = new File("Registrator.txt");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader in = new InputStreamReader(fis);
        BufferedReader buff = new BufferedReader(in);
        String str;
        bf = new BattleField();
        map = new HashMap<>();
        frame.getContentPane().removeAll();
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
        while ((str = buff.readLine())!=null){

            if (str.indexOf(";")>0) {
                switch (str.substring(str.indexOf("(") + 1, str.indexOf(")"))) {
                    case "defender":
                        map.put(str.substring((str.indexOf(")")+1) , str.indexOf(":")),  "defender");
                        defender.setX(Integer.valueOf(str.substring((str.indexOf(":") + 1), str.indexOf("_"))));
                        defender.setY(Integer.valueOf(str.substring((str.indexOf("_") + 1), str.indexOf(";"))));
                        defender.setDirection(Direction.UP);
                        defender.setBf(bf);
                        break;
                    case "agressorD":
                        map.put(str.substring((str.indexOf(")")+1) , str.indexOf(":")),  "agressorD");
                        agressorD.setX(Integer.valueOf(str.substring((str.indexOf(":") + 1), str.indexOf("_"))));
                        agressorD.setY(Integer.valueOf(str.substring((str.indexOf("_") + 1), str.indexOf(";"))));
                        agressorD.setDirection(Direction.LEFT);
                        agressorD.setBf(bf);
                        break;
                    default:
                        map.put(str.substring((str.indexOf(")")+1) , str.indexOf(":")),  "agressorE");
                        agressorE.setX(Integer.valueOf(str.substring((str.indexOf(":") + 1), str.indexOf("_"))));
                        agressorE.setY(Integer.valueOf(str.substring((str.indexOf("_") + 1), str.indexOf(";"))));
                        agressorE.setDirection(Direction.DOWN);
                        agressorE.setBf(bf);
                        break;
                }
                continue;
            }
            System.out.println(str.substring(0,str.indexOf(":"))+" - "+ str.substring(str.indexOf(":")+1));

replayAction(str.substring(0,str.indexOf(":")),str.substring(str.indexOf(":")+1));
        }
//        defender.setDirection(Direction.LEFT);
//        defender.move();
//        System.out.println("agressorE: "+agressorE.getX()+"_"+agressorE.getY());
//        agressorE.attacEagle();
//        agressorD.attacTank(defender);


    }

    public void replayAction(String tank, String action) throws Exception {
        System.out.println("map.get(tank) - "+map.get(tank));

        switch(map.get(tank)) {

            case "defender":
                if (action.contains("move")) {
                    defender.move();
                } else if (action.contains("Fire")) {
                    defender.fire();
                } else if (action.contains("LEFT")) {
                    defender.turn(Direction.LEFT);
                } else if (action.contains("DOWN")){
                    defender.turn(Direction.DOWN);
                } else if (action.contains("UP")){
                    defender.turn(Direction.UP);
                } else if (action.contains("RIGHT")){
                    defender.turn(Direction.RIGHT);
                } else {
                        System.out.println(action + " - no identification, case:defender, replayAction");
                } break;
            case "agressorD":
                if (action.contains("move")) {
                    agressorD.move();
                } else if (action.contains("Fire")) {
                    agressorD.fire();
                } else if (action.contains("LEFT")) {
                    agressorD.turn(Direction.LEFT);
                } else if (action.contains("DOWN")){
                    agressorD.turn(Direction.DOWN);
                } else if (action.contains("UP")){
                    agressorD.turn(Direction.UP);
                } else if (action.contains("RIGHT")){
                    agressorD.turn(Direction.RIGHT);
                } else {
                    System.out.println(action + " - no identification, case:defender, replayAction");
                }  break;
            default :
                if (action.contains("move")) {
                    agressorE.move();
                } else if (action.contains("Fire")) {
                    agressorE.fire();
                } else if (action.contains("LEFT")) {
                    agressorE.turn(Direction.LEFT);
                } else if (action.contains("DOWN")){
                    agressorE.turn(Direction.DOWN);
                } else if (action.contains("UP")){
                    agressorE.turn(Direction.UP);
                } else if (action.contains("RIGHT")){
                    agressorE.turn(Direction.RIGHT);
                } else {
                    System.out.println(action + " - no identification, case:agressorE, replayAction");
                } break;

        }
        Thread.sleep(100);
    }



    private boolean processInterception() throws Exception {
        String str = getQuadrant(bullet.getBulletX(), bullet.getBulletY());
        int x = Integer.valueOf(str.substring(0, str.indexOf("_")));
        int y = Integer.valueOf(str.substring(str.indexOf("_") + 1));
        if (x>=0 && y>=0 && x<9 && y<9 && bf.scanQuadrant(x,y) != " ") {
            bf.updateQuadrant(x,y," ");
            return true;
        }
        //check aggressor
//        if (checkInterception(getQuadrant(agressor.getX(), agressor.getY()), str)){
//            bullet.destroy();
//            agressor.destroy();
//            repaint();
//            return true;
//        }

//        check defender
		if (checkInterception(getQuadrant(defender.getX(), defender.getY()), str)){
		            bullet.destroy();
		            defender.destroy();
            gameOver();


		  //          restartTigr();
		            return true;
		}
        return false;
    }

    private boolean checkInterception(String object, String quadrant){
        int oy = Integer.parseInt(object.split("_")[1]);
        int ox = Integer.parseInt(object.split("_")[0]);

        int qx = Integer.parseInt(quadrant.split("_")[0]);
        int qy = Integer.parseInt(quadrant.split("_")[1]);

        if (oy>=0 && oy<9 && ox>=0 && ox<9){
            if(oy==qy && ox==qx){
                return true;
            }
        }
        return false;
    }

    public String getQuadrant(int x, int y) {
        String str = y / 64 + "_" + x / 64;
        return str;
    }

    public int getQuadrant(int vh) {
        return (vh - 1) * 64;
    }

    public static String getQuadrantXY(int v, int h) {
        return (v - 1) * 64 + "_" + (h - 1) * 64;
    }

    public void processTurn(AbstractTank tank) throws Exception{
        registrator.addList(tank.getName()+":"+tank.getDirection());
        repaint();
    }

    public void processMove(AbstractTank tank) throws Exception{
        int step = 1;
        int covered = 0;
        direction = tank.getDirection();
        int tankY = tank.getY();
        int tankX = tank.getX();
        int speed = tank.getSpeed();



        // check limits x: 0, 513; y: 0, 513
        if ((direction == Direction.UP && tankY == 0) || (direction == Direction.DOWN && tankY >= 512)
                || (direction == Direction.LEFT && tankX == 0) || (direction == Direction.RIGHT && tankX >= 512)) {
            System.out.println("[illegal move] direction: " + direction + " tankX: " + tankX + ", tankY: " + tankY);
            return;
        }

        tank.turn(direction);

        while (covered < 64) {
            if (direction == Direction.UP) {
                tank.updateY (-step);
            } else if (direction == Direction.DOWN) {
                tank.updateY (step);
            } else if (direction == Direction.LEFT) {
                tank.updateX (-step);
            } else {
                tank.updateX (step);
            }
            covered += step;


            Thread.sleep(speed);
        }

        registrator.addList(tank.getName()+":move");
    }


    public void processFire(Bullet bullet) throws Exception {
        this.bullet = bullet;

            if (bullet.getDirection() == Direction.UP && bullet.getBulletY() > 0) {
            while (bullet.getBulletY() > -14) {
                bullet.updateY(-1);
                if (processInterception()) {
                    bullet.destroy(); return;
                }
                Thread.sleep(3l);
            }
        } else if (bullet.getDirection() == Direction.DOWN &&  bullet.getBulletY()< 590) {
            while (bullet.getBulletY() < 590) {
                bullet.updateY(1);
             //   System.out.println("processInterception = "+processInterception());
                if (processInterception()) {
                    bullet.destroy(); return;
                }
                Thread.sleep(3l);
            }
        } else if (bullet.getDirection() == Direction.LEFT && bullet.getBulletX() > 0) {
            while (bullet.getBulletX() > -14) {
                bullet.updateX(-1);
                if (processInterception()) {
                    bullet.destroy(); return;
                }
                Thread.sleep(3l);
            }
        } else if (bullet.getDirection() == Direction.RIGHT && bullet.getBulletX() < 590) {
            while (bullet.getBulletX() < 590) {
                bullet.updateX(1);
                if (processInterception()) {
                    bullet.destroy(); return;
                }
                Thread.sleep(3l);
            }
        } else {
            return;
        }
    }

    public AbstractTank restartTigr(){
        String location = bf.getAgressorLocation();
        return new Tiger(this, bf, Integer.parseInt(location.split("_")[0]), Integer.parseInt(location.split("_")[1]), Direction.LEFT);
    }

    public AbstractTank restartBT7(){
        String location = bf.getAgressorLocation();
        return new BT7(this, bf, Integer.parseInt(location.split("_")[0]), Integer.parseInt(location.split("_")[1])-64, Direction.DOWN);
    }

    public AbstractTank restartT34(){
        String location = bf.getAgressorLocation();
        return new T34(this, bf, Integer.parseInt(location.split("_")[0]), Integer.parseInt(location.split("_")[1])-64, Direction.DOWN);
    }


    //КОНСТРУКТОР  ТУТ!!!!!!!!!!!!!!!!!!!!!!!!!!
    public ActionField() throws Exception {

        registrator = new Registrator();

        bf = new BattleField();

        frame = new JFrame("BATTLE FIELD, DAY 2");
        frame.setLocation(750, 150);
        frame.setMinimumSize(new Dimension(bf.getBF_WIDTH(), bf.getBF_HEIGHT() + 38));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    menuGame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        while(true){
            repaint();
            Thread.sleep(1000/60);
        }
    }


    void init(ActionEvent e) throws Exception {
       if(e.getActionCommand().equals("T34")){
           bf = new BattleField();
        defender = new T34(this, bf);
        agressorD = restartTigr();
        agressorE = restartBT7();

           registrator.addList("(defender)"+defender.getName()+":"+String.valueOf(defender.getX())+"_"+String.valueOf(defender.getY())+";"+String.valueOf(defender.getDirection()));
           registrator.addList("(agressorD)"+agressorD.getName()+":"+String.valueOf(agressorD.getX())+"_"+String.valueOf(agressorD.getY())+";"+String.valueOf(agressorD.getDirection()));
           registrator.addList("(agressorE)"+agressorE.getName()+":"+String.valueOf(agressorE.getX())+"_"+String.valueOf(agressorE.getY())+";"+String.valueOf(agressorE.getDirection()));

           frame.getContentPane().removeAll();
           frame.getContentPane().add(this);
           frame.pack();
           frame.setVisible(true);
           runTheGame();
        }

        if(e.getActionCommand().equals("BT7")){
            defender = new BT7(this, bf);
            agressorD = restartTigr();
            agressorE = restartT34();
        }
    }
    void menuGame() throws Exception {
        menu = new Menushka();
        menu.setBackground(Color.CYAN);
        frame.getContentPane().add(menu);
        frame.pack();
        frame.setVisible(true);
        while(menu.getE()==null){}
        init(menu.getE());
    }

    void gameOver() throws Exception {
        registrator.pushToFile();
        frame.getContentPane().removeAll();
        gameOver = new GameOver();
        frame.getContentPane().add(gameOver);
        while(gameOver.getE()==null){}
        if(gameOver.getE().getActionCommand().equals("EXIT")){
            System.exit(0);
        };
        if(gameOver.getE().getActionCommand().equals("REPLAY")){
            replay();
            gameOver();
        };
        if(gameOver.getE().getActionCommand().equals("RESTART")){
            init(menu.getE());
        };

    }

    public AbstractTank getAgressorD() {
        return agressorD;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        bf.draw(g);
        defender.draw(g);
        agressorE.draw(g);
        agressorD.draw(g);
        for (Object elem: bf.woters){
            if(elem instanceof Water){
                ((Water) elem).draw(g);
        }}
        if (bullet!=null){
        bullet.draw(g);}


        }

    public Registrator getRegistrator() {
        return registrator;
    }
}



