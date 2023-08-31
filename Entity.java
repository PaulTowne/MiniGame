import java.awt.*;
import javax.swing.*;
import java.util.*;
public abstract class Entity 
{
    private int xpos; 
    private int ypos; 
    private int health; 
    private int speed; 
    private ArrayList<Block> array;

    public Entity(int x,int y,int health,int speed, ArrayList<Block> array)
    {
        this.array = array;
        xpos = x;
        ypos = y; 
        this.health = health;
        this.speed = speed; 
    }
    public abstract void draw(Graphics g);
    public abstract boolean move(int dx, int dy, int dSpeed); 
    public abstract void detect(int x, int y); 
    public abstract void hP(int dmg);
    public abstract int getX();
    public abstract int getY();
    public abstract void hurt(int x);
    public abstract int getHp();
}
