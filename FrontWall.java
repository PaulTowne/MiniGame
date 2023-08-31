import java.awt.*;
import javax.swing.*;

import java.util.*;
public class FrontWall extends Block
{
    private int xpos;
    private int ypos;
    Image phase1;
    public FrontWall(int y, int x)
    {
        super(x, y);
        xpos = x;
        ypos = y; 
        Toolkit tool = Toolkit.getDefaultToolkit();
        phase1 = tool.getImage("stoneWall.png");
      
    }
    public void draw(Graphics g)
    {
        //g.drawImage(phase1, xpos, ypos, null);
        
        g.setColor(Color.gray);
        g.fill3DRect(xpos, ypos, 10, 10,true);
        g.setColor(Color.DARK_GRAY);
        g.fill3DRect(xpos,ypos,10,10,true);
        
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
