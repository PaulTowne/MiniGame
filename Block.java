
import java.awt.*;
import javax.swing.*;
import java.util.*;

public abstract class Block 
{
    private int xpos; 
    private int ypos; 
    public Block(int x, int y)
    {
        xpos = x;
        ypos = y; 
    }
    public abstract void draw(Graphics g);
    public int xpos()
    {
        return xpos; 
    }
    public int ypos()
    {
        return ypos; 
    }
    

       
}
