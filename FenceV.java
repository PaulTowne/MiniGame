import java.awt.*;
import javax.swing.*;

import java.util.*;
public class FenceV extends Block
{
    private int xpos;
    private int ypos;
    Color c = new Color(95,57,57);
    public FenceV(int y, int x)
    {
        super(x, y);
        xpos = x;
        ypos = y; 
      
    }
    public void draw(Graphics g)
    {
        g.setColor(c);
        g.fillRect(xpos+3, ypos, 4, 10);

        
    }
    public int xpos()
    {
        return xpos;
    }
    public int ypos()
    {
        return ypos; 
    }
}

