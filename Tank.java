import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.tree.DefaultTreeSelectionModel;

import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
public class Tank extends Entity 
{
    int xpos;
    int ypos;
    int playerX;
    int playerY;
    int health;
    int speed;
    int phase;
    int counter=-1;
    int xDestination;
    int yDestination; 
    int mostRecentX;
    int mostRecentY;
    boolean inRange = false;
    boolean pathing = false;
    ArrayList<Block> wall;
    PathFinderREDUCED path;
    Point setPath[];
    Image phase1;
    Image phase2;
    Image phase3;

    // collides class
    CollideClass classCollide;
    public Tank(int x, int y, int health, int speed,ArrayList<Block> arr)
    {
        super(x, y, health, speed,arr);
        xpos = x;
        ypos = y;
        this.health = health;
        this.speed = speed; 
        phase = 0;
        xDestination = xpos; 
        yDestination = ypos; 
        wall = arr;
        Toolkit tool = Toolkit.getDefaultToolkit();
        phase1 = tool.getImage("Photos/tank.png");
        

        // collide
       // System.out.println("size of wall in spider ="+wall.size());
        classCollide = new CollideClass(wall);   
        
    }
    public int getHp()
    {
        return health;
    }
    public void hurt(int x)
    {
        
    }
    public void draw(Graphics g)
    {
        g.drawImage(phase1, xpos, ypos, null);
        //path.draw(g);
        
    }
    
    public void Destination()
    {
        if(!inRange){
        Random rand = new Random();
        xDestination = rand.nextInt(xpos-50,xpos+50);
        yDestination = rand.nextInt(ypos-50,ypos+50);
        }
        else{
            xDestination = playerX;
            yDestination = playerY;
        }
        
    }
    public void Destination2()
    {
        if(counter==-1)
        {
            System.out.println("pathingReduced");
            Point p = new Point(playerX,playerY);
            path = new PathFinderREDUCED(wall, xpos, ypos, p ,40);
            setPath = path.getBest();
            counter++; 
        }
        xDestination = setPath[counter].getX();
        yDestination = setPath[counter].getY();
        counter++;
        if(counter==2)
        {
            pathing = false;
            counter=-1; 
        }
    }
    public void getBlock(ArrayList<Block> b)
    {
        wall = b;
    }
    // set all to 0 for the spider set up. 
    public boolean move(int xDes, int yDes, int sped)
    {    
        //System.out.println("move");
        if(xpos<xDestination)
        {
           // System.out.println("x++");
            xpos++; 
            mostRecentX = 1;
        }
        else if(xpos>xDestination)
        {
           // System.out.println("x--");
            xpos--; 
            mostRecentX = -1;
        }
        if(ypos<yDestination)
        {
            //System.out.println("y++");
            ypos++;
            mostRecentY = 1;
        }
        else if(ypos>yDestination)
        {
           // System.out.println("y--");
            ypos--; 
            mostRecentY = -1;
        }
        
            boolean test = classCollide.collide(xpos,ypos,wall);
            //System.out.println("collide in spider by class ="+test);
            //detect(playerX,playerY);
            if(test)
            {
                collide();
                if(!inRange)
                {
                    Destination();
                }
                else{
                    pathing = true;
                    Destination2();
                }
            }
            if(xpos== xDestination && ypos == yDestination)
            {
               if(!pathing)
                Destination();
                else
                Destination2();
            }
            return false;
    }
    
    public void collide()
    {
        
        xpos = xpos-mostRecentX; 
        ypos = ypos-mostRecentY;
        //System.out.println("collideSpider");
        
        
        
    }
    public PathFinderREDUCED testRun()
    {
        //Point p = new Point(playerX,playerY);
       // PathFinder path2 = new PathFinder(wall, xpos, ypos, p ,30);
        return path;
        
    }
    public void detect(int x, int y)
    {
        playerX = x;
        playerY = y;
        int ex = xpos - playerX;
        int ey = ypos - playerY; 
        double distance = Math.pow(ex, 2) + Math.pow(ey,2);
        distance = Math.sqrt(distance);
        //System.out.println(x+" "+y+" obj ="+xpos+" "+ypos+" dis ="+distance);
        if(distance<400){
            //System.out.println("inRange "+distance);
        inRange = true;
        //Destination();
        }
        else
        inRange = false;
    }
    public void hP(int dmg)
    {

    }
    public boolean getDirX()
    {
        if(xpos<xDestination)
            return true; 
        else
            return false; 
    }
    public boolean getDirY()
    {
        if(ypos<yDestination)
            return true; 
        else
            return false;
        
    }
    public int getX()
    {
        return xpos;
    }
    public int getY()
    {
        return ypos; 
    }
    public void setSpeed(int x)
    {
        speed = x; 
    }
}



