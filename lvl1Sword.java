
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class lvl1Sword extends MeleeWeapon
{
    
     int damage; 
     int distance; 
     int attSpeed;
     Image phase1;
     Image phase2;
     WeaponDraw array[] = new WeaponDraw[10];
    public lvl1Sword(int dmg, int dis, int att)
    {
        super(dmg, dis, att);
        damage = dmg;
        distance = dis;
        attSpeed = att; 
        Toolkit tool = Toolkit.getDefaultToolkit();
        phase1 = tool.getImage("Photos/lvl1Sword.png");
        phase2 = tool.getImage("Photos/lvlSwordUI.png");
            
    }
    public  int getDmg()
    {
        return damage;
    }
    public  int getDistance()
    {
        return distance;
    }
    public  int getAttSpeed()
    {
        return attSpeed; 
    }
    public WeaponDraw[] getList(double degree)
    {
        // do stuff in degrees
        double start = degree-45; 
        double end = degree +45; 
        //System.out.println("imported degree "+degree);
        // we use the radius and angle to get our x and y values. this will be stored in an array
        for(int i=0;i<array.length;i++)
        {
            // I set to 40 because the graphics point outwards.
            double x = 30*Math.cos(Math.toRadians(start));
            double y = -30*Math.sin(Math.toRadians(start));
            //System.out.println("x and y and angle ="+x+" "+y+" "+start);
            array[i] = new WeaponDraw(phase1, x, y);
            start=start+10;
        }

        return array;
    }
    public void drawUI(Graphics g)
    {
        //phase2 = phase2.getScaledInstance(60, 60, 20);
        g.drawImage(phase2,80,30,60,60,null);
        g.drawString("Dmg ="+damage,135, 55);
        g.drawString("Range ="+distance,135, 67);
        g.drawString("AttSp ="+attSpeed,135, 79);

    }
    public void drawInactive(Graphics g,Point pos, boolean left)
    {
            Graphics2D g4d=(Graphics2D)g;
            
            if(!left)
            {
                g4d.translate(pos.getX(),pos.getY()); // Translate the center of our coordinates.
                g4d.rotate(-.785);
            }
            else
            {
                g4d.translate(pos.getX()-10,pos.getY()+15);
                g4d.rotate(-2.35);
            }
            
            g4d.drawImage(phase1, 0, 0, null);
       //g.drawImage(phase1,pos.getX(),pos.getY()-10,null);
    }
    public void draw(Graphics g, int phase,int direction, int plx, int ply)
    {
        
        
    }

}

