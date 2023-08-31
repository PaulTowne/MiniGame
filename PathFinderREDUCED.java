import java.util.*;
import java.awt.*;
import javax.lang.model.type.NullType;
import javax.swing.*;
import javax.swing.plaf.DesktopIconUI;

import org.ietf.jgss.GSSContext;
public class PathFinderREDUCED 
{
    ArrayList<Block> obstacle; 
    // path = [i][k][j] has the shortest  distance
    Point path[] = new Point[2]; 
    Point level1[] = new Point[8];
    Point level2[][] = new Point[8][8];
    Point Origin;
    int objXpos;
    int objYpos; 
    Point destination;
    int range;

    int lvl1;   // stores the i component of the lvl1 array

        int lvl2i=-1;
        int lvl2k=-1;

        int lvl3i=-1;
        int lvl3k=-1;
        int lvl3j=-1;
    // range is how far each path is shooting out
    public PathFinderREDUCED(ArrayList<Block> obst, int objX, int objY, Point destination, int range)
    {
        System.out.println("pathingReduced");
        ArrayList<Block> nearby = new ArrayList<Block>();
        for(int i=0;i<obst.size();i++)
        {
            int dX =Math.abs(obst.get(i).xpos() - objX);
            int dY = Math.abs(obst.get(i).ypos() - objY);
            if(dX <120 && dY<120)
            {
                nearby.add(obst.get(i));
            }
        }
        obstacle = nearby; 
        objXpos = objX;
        objYpos = objY; 
        this.destination = destination; 
        this.range = range;
        Origin = new Point(objX,objY);
        level1 = findpoints(Origin);
        for(int i=0;i<2;i++)
        {
            path[i] = new Point(Origin.getX(),Origin.getY());
        }
        for(int i=0;i<8;i++)
        {
            // basically we need a way to get all the points into the level2 when we are returning an array. 
            Point temp[] = findpoints(level1[i]);
            for(int k=0;k<8;k++)
            {
                level2[i][k] = temp[k]; 
            
            }
            
        }
        
        // we need to check what collided and set to null. then we can check distance

        for(int i=0;i<8;i++)
        {
            if(pathCheck(Origin,level1[i]))
                //if collide then
                level1[i] = null; 

                for(int k=0;k<8;k++)
                {
                    if(level1[i]!= null)
                    {
                        if(pathCheck(level1[i],level2[i][k]))
                        {
                            level2[i][k] = null;
                        }
                    }
                }
        }
       
        // we have all the collided paths as null. Now time to check distances from each path to the destination. 
        
        double max =1000000000;
        for(int i=0;i<8;i++)
        {
            if(level1[i] !=null){
                int x1 = destination.getX();
                int y1 = destination.getY();

                int x2 = level1[i].getX();
                int y2 = level1[i].getY();

                double distance = Math.pow(x1-x2,2) + Math.pow(y1-y2,2);
                distance = Math.sqrt(distance);
                if(max==-1)
                {
                    max = distance;
                    lvl1 = i;
                }
                else if(distance<max)
                {
                    max = distance;
                    lvl1 = i;
                }
            }
            for(int k=0;k<8;k++)
            {
                if(level2[i][k] != null && level1[i] !=null){
                    int x1 = destination.getX();
                    int y1 = destination.getY();

                    int x2 = level2[i][k].getX();
                    int y2 = level2[i][k].getY();

                    double distance = Math.pow(x1-x2,2) + Math.pow(y1-y2,2);
                    distance = Math.sqrt(distance);
                    if(max==-1)
                    {
                        max = distance;
                        lvl2i = i;
                        lvl2k = k;
                    }
                    else if(distance<max)
                    {
                        max = distance;
                        lvl2i = i;
                        lvl2k = k;
                    }
                }
            }
        }
        
        // this variable is whether level 1,2, or 3 has the smallest distance
        int small;
        double dis1=10000;
        double dis2=10000;
        // this should be the distance between the destination and the current point.
         
        if(lvl1!=-1)
            dis1 = Math.sqrt(Math.pow(destination.getX()-level1[lvl1].getX(),2) + Math.pow(destination.getY()-level1[lvl1].getY(),2));
        if(lvl2i!=-1)
            dis2 = Math.sqrt(Math.pow(destination.getX()-level2[lvl2i][lvl2k].getX(),2)+Math.pow(destination.getY()-level2[lvl2i][lvl2k].getY(),2));
        
        if(dis1 < dis2)
        {
            small = 1;
           
        }
        else
        {
            small = 2; 
           
        }
        if(small==1)
        {
            path[0] = level1[lvl1];
        }
        else if(small==2)
        {
            path[0] = level1[lvl2i];
            path[1] = level2[lvl2i][lvl2k];
        }



    }
    public Point[] getBest()
    {
        return path;
    }
    // this method takes in a point and finds the 16 points around it
    public Point[] findpoints(Point point)
    {
        //local array
        //System.out.println("in findpoint");
        Point local[] = new Point[16];
        // for cardinal points (x and y values)
        int xdia = (int) Math.sqrt(((int) Math.pow(range,2)) / 2);
        // cardinal points 
        Point a = new Point(point.getX()-range,point.getY());
        Point b = new Point(point.getX()+range,point.getY());
        Point c = new Point(point.getX(),point.getY()-range);
        Point d = new Point(point.getX(),point.getY()+range);

        local[0] = a;
        local[1] = b;
        local[2] = c;
        local[3] = d;

        // diagonals
        Point e = new Point(point.getX()+xdia,point.getY()+xdia);
        Point f = new Point(point.getX()+xdia,point.getY()-xdia);
        Point g = new Point(point.getX()-xdia,point.getY()+xdia);
        Point h = new Point(point.getX()-xdia,point.getY()-xdia);

        local[4] = e;
        local[5] = f;
        local[6] = g;
        local[7] = h;
        return local;
    }    
    // this will check each path individually. the parameters are the starting position to player position.
    public boolean pathCheck(Point start, Point end)
    {
        // range 
            //System.out.println("Spider test");
           // while(tempX!=end.getX() && )
           boolean col= false;
           int tempX = start.getX();
           int tempY = start.getY();
           int finalX = end.getX();
           int finalY = end.getY();
           while(!(tempX == finalX && tempY == finalY))
           {
                for(int i=0;i<obstacle.size();i++)
                {
                    int Xpos = obstacle.get(i).xpos();
                    int Ypos = obstacle.get(i).ypos();
                    //System.out.println("wall("+i+") "+Xpos+" "+Ypos);
                    // player collides
                    int top = tempY;
                    int bot = tempY+10;
                    int left = tempX;
                    int right = tempX+10;
                    int Otop = Ypos;
                    int Obot = Ypos+10;
                    int Oleft = Xpos;
                    int Oright = Xpos+10;
                    if(obstacle.get(i) instanceof FenceV){
                        Oleft = Xpos+3;
                        Oright = Xpos +7;
                    }
                    else if(obstacle.get(i) instanceof Tree){
                        Otop = Ypos+34;
                        Oright = Xpos+15;
                        Oleft = Xpos+5;
                        Obot = Ypos+36;
                    }
                    else if(obstacle.get(i) instanceof Tree2){
                        Otop =Ypos+20;
                        Oright = Xpos+28;
                        Oleft = Xpos+13;
                        Obot = Ypos+30;
                    }
                    else if(obstacle.get(i) instanceof upBot){
                        Obot = Ypos+50;
                        Oright = Xpos+20;
                    }
                    else if(obstacle.get(i) instanceof upTop)
                    {
                        Otop = Ypos+12;
                        Obot = Ypos+13;
                    }
                    else if(obstacle.get(i) instanceof upRight)
                    {
                        Oleft = Xpos+20;
                        Oright = Xpos +24;
                    }
                    else if(obstacle.get(i) instanceof downTRcorner)
                    {
                        Oright = Xpos+20;
                        Otop = Ypos+8;
                        Obot = Ypos+50;
                    }
                    else if(obstacle.get(i) instanceof downTLcorner)
                    {
                        Oright = Xpos +20;
                        Obot = Ypos+50;
                    }
                    else if(obstacle.get(i) instanceof downLeft)
                    {
                        Oright = Xpos+20;
                        Oleft = Xpos+10;
                    }
                    else if(obstacle.get(i) instanceof Stairs)
                    {
                        Oright = 0;
                        
                    }
                    if(!(bot<Otop || top>Obot || left>Oright || right<Oleft))
                    {
                    // you have collided
                    //System.out.println("collided");
                        col = true;
                    }
                    
                    //System.out.println("didnt collide");
                }
                if(tempX<finalX)
                {
                    tempX = tempX+2; 
                }
                else
                {
                    tempX = tempX -2; 
                }
                if(tempY<finalY)
                {
                    tempY = tempY +2;
                }
                else
                {
                    tempY = tempY-2; 
                }
            }
        return col;
    }
    public void draw(Graphics g)
    {
        // use this to draw the lines for each path.
        g.setColor(Color.RED);
        for(int i=0;i<8;i++)
        {
            if(level1[i]!=null)
            g.drawLine(Origin.getX(),Origin.getY(), level1[i].getX(), level1[i].getY());
        }
        for(int i=0;i<8;i++)
        {
            for(int k=0;k<8;k++)
            {
                if(level2[i][k]!=null && level1[i]!= null)
                g.drawLine(level1[i].getX(),level1[i].getY(),level2[i][k].getX(),level2[i][k].getY());
            }
        }
        //System.out.println("drawing best path");
        g.setColor(Color.BLUE);
        g.drawLine(Origin.getX(), Origin.getY(), path[0].getX(), path[0].getY());
        g.drawLine(path[0].getX(), path[0].getY(), path[1].getX(), path[1].getY());   
    }
}

