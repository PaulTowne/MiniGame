import java.awt.*;
import javax.swing.*;

import java.util.*;
public class Path extends Block
{
    private int xpos;
    private int ypos;
    Color bro1 = new Color(80,47,14);
    Color DGreen = new Color(8,68,8);
    public Path(int y, int x)
    {
        super(x, y);
        xpos = x;
        ypos = y; 
      
    }
    public void draw(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(xpos, ypos, 10, 10);
        g.setColor(bro1);
        g.fillRect(xpos+1, ypos+1, 8, 8);
    }
}