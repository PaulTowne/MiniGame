import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.tree.DefaultTreeSelectionModel;

import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
public class Dummy extends Entity 
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
    PathFinder path;
    Point setPath[];
    Image phase1;
    Image phase2;
    Image phase3;
    Image phase4;
    Image phase5;
    

    // collides class
    CollideClass classCollide;
    public Dummy(int x, int y, int health, int speed,ArrayList<Block> arr)
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
        phase1 = tool.getImage("Photos/dummy.png");

        

        // collide
       // System.out.println("size of wall in spider ="+wall.size());
        classCollide = new CollideClass(wall);   
        
    }
    public void draw(Graphics g)
    {
       g.drawImage(phase1,xpos,ypos,null);
       //g.setColor(Color.RED);
       //g.drawRect(xpos,ypos, health,10);
       g.drawString("Hp ="+health, xpos, ypos);
       g.drawRect(xpos,ypos,20,20);
    }
    
    public void getBlock(ArrayList<Block> b)
    {
        wall = b;
    }
    // set all to 0 for the spider set up. 
    public boolean move(int xDes, int yDes, int sped)
    {    
        // does not move
        return false;
    }
    public PathFinder testRun()
    {
        //Point p = new Point(playerX,playerY);
       // PathFinder path2 = new PathFinder(wall, xpos, ypos, p ,30);
        return path;
        
    }
    public void hurt(int x)
    {
        health = health - x;
    }
    public int getHp()
    {
        return health;
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



