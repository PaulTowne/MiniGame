import java.awt.*;
import javax.swing.*;

import java.util.*;
public class upBot extends Block
{
    private int xpos;
    private int ypos;
    private Color gre1 = new Color(17,75,21);
    Image phase1;
    // long one no corners
    public upBot(int y, int x)
    {
        super(x, y);
        xpos = x;
        ypos = y; 
        Toolkit tool = Toolkit.getDefaultToolkit();
        phase1 = tool.getImage("Photos/grassWall2.png");
      
    }
    public void draw(Graphics g)
    {
       // g.setColor(Color.BLACK);
        //g.fillRect(xpos, ypos, 10, 10);
       // g.setColor(gre1);
        //g.fillRect(xpos, ypos, 10, 10);
        g.drawImage(phase1, xpos, ypos, null);
    }
}
