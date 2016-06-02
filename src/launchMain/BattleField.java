package launchMain;

import barrier.*;
import interfaces.Drowable;
import tanks.AbstractTank;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Сергей on 14.04.2016.
 */
public class BattleField implements Drowable {
    boolean COLORDED_MODE = false;
    private final int BF_WIDTH = 576;
    private final int BF_HEIGHT = 576;
    LinkedList woters;

    public String[][] battleField = {
            {"B", "B", " ", "B", " ", "B", "B", " ", "B"},
            {" ", " ", " ", " ", " ", " ", " ", " ", "B"},
            {"B", "B", "B", "B", "B", "B", "B", "B", "B"},
            {"B", "R", "R", "B", "B", "B", "R", "R", "B"},
            {"B", "B", "B", " ", "W", " ", "B", "B", "B"},
            {"B", "B", " ", "W", "W", "W", " ", "B", "B"},
            {"B", "B", " ", " ", " ", " ", " ", "B", "B"},
            {"B", " ", " ", "B", "B", "B", " ", " ", "B"},
            {"B", " ", " ", "B", "E", "B", " ", " ", "B"}
    };

    private int[][] mapDistances = mapDistances();

    public BattleField(){
    }

    private int[][] mapDistances(){
        int[][] mapDistanc = new int[9][9];
        for (int i = 0; i<9; i++){
            for (int j = 0; j<9; j++){
            mapDistanc[i][j] = 88;
            }
        }

        LinkedList<String> fil = new LinkedList<>();
        int vEagle = 8;
        int hEagle = 4;
        mapDistanc[vEagle][hEagle] = 1;
        fil.add(String.valueOf(vEagle)+"_"+String.valueOf(hEagle));
        String v_h = fil.getFirst();
        int j = 0;

        do{
            int v = Integer.valueOf(v_h.substring(0, v_h.indexOf("_")));
            int h = Integer.valueOf(v_h.substring(v_h.indexOf("_") + 1));

            if(h>0&& barrierWR(v, h-1) && mapDistanc[v][h-1]>(mapDistanc[v][h]+1+k_barrier(v, h-1))){
                mapDistanc[v][h-1]=(mapDistanc[v][h]+1+k_barrier(v, h-1));
                fil.add(String.valueOf(v)+"_"+String.valueOf(h-1));
            }
            if(v>0&&barrierWR(v-1, h) && mapDistanc[v-1][h]>(mapDistanc[v][h]+1+k_barrier(v-1, h))){
                mapDistanc[v-1][h]=(mapDistanc[v][h]+1+k_barrier(v-1, h));
                fil.add(String.valueOf(v-1)+"_"+String.valueOf(h));
            }
            if(h<8&& barrierWR(v, h+1) && mapDistanc[v][h+1]>(mapDistanc[v][h]+1+k_barrier(v, h+1))){
                mapDistanc[v][h+1]=(mapDistanc[v][h]+1+k_barrier(v, h+1));
                fil.add(String.valueOf(v)+"_"+String.valueOf(h+1));
            }
            if(v<8&& barrierWR(v+1, h) && mapDistanc[v+1][h]>(mapDistanc[v][h]+1+k_barrier(v+1, h))){
                mapDistanc[v+1][h]=(mapDistanc[v][h]+1+k_barrier(v+1, h));
                fil.add(String.valueOf(v+1)+"_"+String.valueOf(h));
            }
            j++;
            v_h = fil.get(j);
            } while (j<fil.size()-1);
        return mapDistanc;
    }

    private int k_barrier(int v, int h){
        if (battleField[v][h].equals("B")){
            return 1;
        }
        return 0;
    }

    private boolean barrierWR (int v, int h){
        if (battleField[v][h].equals("W") || battleField[v][h].equals("R") ||  battleField[v][h].equals("E")){return false;}
        return true;
    }

    public BattleField(String[][] battleField){
        this.battleField = battleField;
    }

    public String[][] getBattleField() {
        return battleField;
    }

    public int getBF_WIDTH() {
        return BF_WIDTH;
    }

    public int getBF_HEIGHT() {
        return BF_HEIGHT;
    }

    public String scanQuadrant(int x, int y) {
        return  battleField[x][y];
    }

    public void updateQuadrant(int x, int y, String field){
        battleField[x][y] = field;
    }

    public int getDimentionX(){
        return battleField.length;
    }

    public int getDimentionY(){
        return battleField[0].length;
    }

    public String getAgressorLocation(){
        String str = null;
        Random r = new Random();
        while (true){
            if (r.nextInt(4) > 0) {
                if (r.nextInt(4)==1) {if (str!= "128_64"){str = "128_64"; return str;}}
                if (r.nextInt(4)==2) {if (str!= "256_64"){str = "256_64"; return str;}}
                if (r.nextInt(4)==3) {if (str!= "448_64"){str = "448_64"; return str;}}
            }
        }
    }

    public void draw(Graphics g){
        woters = new LinkedList();
        int i = 0;
        Color cc;
        for (int v = 0; v < 9; v++) {
            for (int h = 0; h < 9; h++) {
                if (COLORDED_MODE) {
                    if (i % 2 == 0) {
                        cc = new Color(252, 241, 177);
                    } else {
                        cc = new Color(233, 243, 255);
                    }
                } else {
                    cc = new Color(180, 180, 180);
                }
                i++;
                g.setColor(cc);
                g.fillRect(h * 64, v * 64, 64, 64);
            }
        }

        for (int j = 0; j < getDimentionX(); j++) {
            for (int k = 0; k < getDimentionY(); k++) {

                String coordinates = ActionField.getQuadrantXY(j + 1, k + 1);
                int separator = coordinates.indexOf("_");
                int y = Integer.parseInt(coordinates.substring(0, separator));
                int x = Integer.parseInt(coordinates.substring(separator + 1));

                if (scanQuadrant(j, k).equals("B")) {
                    Brick brick =new Brick(x, y);
                    brick.draw(g);
                }
                if (scanQuadrant(j, k).equals("W")) {
                    Water water =new Water(x, y);
                    woters.add(water);
                    water.draw(g);
                }
                if (scanQuadrant(j, k).equals("R")) {
                    Rock rock =new Rock(x, y);
                    rock.draw(g);
                }
                if (scanQuadrant(j, k).equals("E")) {
                    Eagle eagle =new Eagle(x, y);
                    eagle.draw(g);
                }
                if (scanQuadrant(j, k).equals(" ")) {
                    Trava trava =new Trava(x, y);
                    trava.draw(g);
                }
            }
        }
    }

    public int[][] getMapDistances() {
        return mapDistances;
    }

    public int[][] distancesTank(AbstractTank tank){
        int[][] distancTank = new int[9][9];
        for (int i = 0; i<9; i++){
            for (int j = 0; j<9; j++){
                distancTank[i][j] = 88;
            }
        }

        LinkedList<String> fil = new LinkedList<>();
        int vTank = (int)tank.getY()/64;
        int hTank = (int)tank.getX()/64;
        distancTank[vTank][hTank] = 1;
        fil.add(String.valueOf(vTank)+"_"+String.valueOf(hTank));
        String v_h = fil.getFirst();
        int j = 0;

        do{
            int v = Integer.valueOf(v_h.substring(0, v_h.indexOf("_")));
            int h = Integer.valueOf(v_h.substring(v_h.indexOf("_") + 1));

            if(h>0&& barrierWR(v, h-1) && distancTank[v][h-1]>(distancTank[v][h]+1+k_barrier(v, h-1))){
                distancTank[v][h-1]=(distancTank[v][h]+1+k_barrier(v, h-1));
                fil.add(String.valueOf(v)+"_"+String.valueOf(h-1));
            }
            if(v>0&&barrierWR(v-1, h) && distancTank[v-1][h]>(distancTank[v][h]+1+k_barrier(v-1, h))){
                distancTank[v-1][h]=(distancTank[v][h]+1+k_barrier(v-1, h));
                fil.add(String.valueOf(v-1)+"_"+String.valueOf(h));
            }
            if(h<8&& barrierWR(v, h+1) && distancTank[v][h+1]>(distancTank[v][h]+1+k_barrier(v, h+1))){
                distancTank[v][h+1]=(distancTank[v][h]+1+k_barrier(v, h+1));
                fil.add(String.valueOf(v)+"_"+String.valueOf(h+1));
            }
            if(v<8&& barrierWR(v+1, h) && distancTank[v+1][h]>(distancTank[v][h]+1+k_barrier(v+1, h))){
                distancTank[v+1][h]=(distancTank[v][h]+1+k_barrier(v+1, h));
                fil.add(String.valueOf(v+1)+"_"+String.valueOf(h));
            }
            j++;
            v_h = fil.get(j);
        } while (j<fil.size()-1);
        return distancTank;
    }
}
