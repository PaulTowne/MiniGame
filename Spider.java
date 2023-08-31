import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
public class Spider extends Entity 
{
    int xpos;
    int ypos;
    int playerX;
    int playerY;
    int health;
    int speed;
    int phase;
    int counter=0;
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

    // collides class
    CollideClass classCollide;
    public Spider(int x, int y, int health, int speed,ArrayList<Block> arr)
    {
        super(x, y, health, speed,arr);
        xpos = x;
        ypos = y;
        this.health = health;
        this.speed = speed; 
        phase = 0;
        xDestination = xpos; 
        yDestination = ypos; 
        Toolkit tool = Toolkit.getDefaultToolkit();
        phase1 = tool.getImage("Photos/SpiderFiles/SpiderP1.png");
        phase2 = tool.getImage("Photos/SpiderFiles/SpiderP2.png");
        phase3 = tool.getImage("Photos/SpiderFiles/SpiderP3.png");

        // collide
        Project_Panel pro = new Project_Panel(1);
        wall =pro.getBlock();
       // System.out.println("size of wall in spider ="+wall.size());
        classCollide = new CollideClass(wall);
        
    }
    public void draw(Graphics g)
    {
        if(phase<30)
        phase++;
        else
        phase =0; 

        if(phase<10)
        g.drawImage(phase1,xpos,ypos,null);
        else if(phase>9 && phase <20)
        {
            g.drawImage(phase2,xpos,ypos,null);
        }
        else if(phase>19)
            g.drawImage(phase3,xpos,ypos,null);
        
    }
    // spider move gives the direction it wants to travel. not dx and dy but rather a position to head to. 
    // if it collides with something give it a new random direction. 
    // should patrol in a general area. so they stay local when detect is false; 
    // if detect, then chase the players last position. 
    public void Destination()
    {

        //System.out.println("destination");
        if(!pathing){
            if(inRange==false){
            Random rand = new Random();
            xDestination = rand.nextInt(xpos-50,xpos+50);
            yDestination = rand.nextInt(ypos-50,ypos+50);
            }
            else
            {
                xDestination = playerX;
                yDestination = playerY;
            }
        }   
        else 
        Destination2();
        
        
        // we can return an array of size 3 with the coordiantes for the 3 points
        
        
    }
    public void Destination2()
    {
        //System.out.println("destination2");
        //for(int i=0;i<3;i++)
       // {
          //  if(setPath[i]!=null)
            //System.out.println(setPath[i].getX()+" "+setPath[i].getY());
       // }
        //if(xpos == xDestination && ypos == yDestination){
            
            xDestination = setPath[counter].getX();
            yDestination = setPath[counter].getY();
            counter++;
           // System.out.println("current path ="+xDestination+" "+yDestination+" "+counter);
            if(counter ==3)
            {
                pathing = false;
                counter =0;
            }
        //}
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
        // adding a collide possibility
            //System.out.println("Spider test");
            //System.out.println("2nd");
            boolean test = classCollide.collide(xpos,ypos,wall);
            //System.out.println("collide in spider by class ="+test);
            if(test)
            {
                // you have collided
                   //System.out.println("COLLIDED");
                   collide();
                   // its run into a wall. If detect, run the pathfinder
                   //if(false)
                    if(inRange)
                    {
                        //System.out.println("inRangecollide");
                        if(pathing ==false)
                        {
                            Point p = new Point(playerX,playerY);
                            path = new PathFinder(wall, xpos, ypos, p ,40);
                            setPath = path.getBest();
                        }
                        pathing = true; 
                        
                        Destination2();
                    } 
                    else{
                        Destination();
                    }
            }
            /* 
            for(int i=0;i<wall.size();i++)
            {
                int Xpos = wall.get(i).xpos();
                int Ypos = wall.get(i).ypos();
                //System.out.println("wall("+i+") "+Xpos+" "+Ypos);
                // player collides
                int top = ypos;
                int bot = ypos+10;
                int left = xpos;
                int right = xpos+10;
                int Otop = Ypos;
                int Obot = Ypos+10;
                int Oleft = Xpos;
                int Oright = Xpos+10;
                if(wall.get(i) instanceof FenceV){
                    Oleft = Xpos+3;
                    Oright = Xpos +7;
                }
                if(!(bot<Otop || top>Obot || left>Oright || right<Oleft))
                {
                   // you have collided
                   //System.out.println("collided");
                   collide();
                   // its run into a wall. If detect, run the pathfinder
                    if(inRange)
                    {
                        //System.out.println("inRangecollide");
                        pathing = true; 
                        Point p = new Point(playerX,playerY);
                        path = new PathFinder(wall, xpos, ypos, p ,40);
                        setPath = path.getBest();
                        Destination2();
                    } 
                    else{
                        Destination();
                    }
                   return true;
                }
                
                //System.out.println("didnt collide");
            }
            */
            //System.out.println("3rd");
           // System.out.println(xpos+" "+ypos+" destination ="+xDestination+" "+yDestination);
            if(xpos== xDestination && ypos == yDestination)
            {
               // System.out.println("4th");
                Destination();
            }
            return false;
    }
    
    public void collide()
    {
        
        xpos = xpos-mostRecentX; 
        ypos = ypos-mostRecentY;
        //System.out.println("collideSpider");
        
        
        
    }
    public PathFinder testRun()
    {
        Point p = new Point(playerX,playerY);
        PathFinder path2 = new PathFinder(wall, xpos, ypos, p ,30);
        return path2;
        
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
        if(distance<200){
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

