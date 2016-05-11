package tanks;

import interfaces.Destroyable;
import interfaces.Direction;
import interfaces.Drowable;

public interface Tank extends Drowable, Destroyable {

    public Action setUp();

    public void move() throws Exception;

    public void fire() throws Exception;

    public int getX();

    public int getY();

    public Direction getDirection();

    public void updateX(int x);

    public void updateY(int y);

    public int getSpeed();

    public int getMovePath();

}
