package interfaces;

/**
 * Created by Сергей on 14.04.2016.
 */
public enum Direction {
    UP(0), DOWN(1), LEFT(2), RIGHT(3);
    private int id;

    private Direction(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
