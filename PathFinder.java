import java.util.*;
import java.awt.*;
import javax.lang.model.type.NullType;
import javax.swing.*;
import javax.swing.plaf.DesktopIconUI;

import org.ietf.jgss.GSSContext;
public class PathFinder 
{
    ArrayList<Block> obstacle; 
    // path = [i][k][j] has the shortest  distance
    Point path[] = new Point[3]; 
    Point level1[] = new Point[16];
    Point level2[][] = new Point[16][16];
    Point level3[][][] = new Point[16][16][16];
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
    public PathFinder(ArrayList<Block> obst, int objX, int objY, Point destination, int range)
    {
        //System.out.println("pathing");
        ArrayList<Block> nearby = new ArrayList<Block>();
        for(int i=0;i<obst.size();i++)
        {
            int dX =Math.abs(obst.get(i).xpos() - objX);
            int dY = Math.abs(obst.get(i).ypos() - objY);
            if(dX <(range*3) && dY<(range*3))
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
        for(int i=0;i<3;i++)
        {
            path[i] = new Point(Origin.getX(),Origin.getY());
        }
        for(int i=0;i<16;i++)
        {
            // basically we need a way to get all the points into the level2 when we are returning an array. 
            Point temp[] = findpoints(level1[i]);
            for(int k=0;k<16;k++)
            {
                level2[i][k] = temp[k]; 
                // adding here
            
                Point temp2[] = findpoints(level2[i][k]);
                for(int j=0;j<16;j++)
                {
                    level3[i][k][j] = temp2[j]; 
                }
            
            }
            
        }
        //for(int i=0;i<16;i++)
       // {
            /* 
            for(int k=0;k<16;k++)
            {
                Point temp[] = findpoints(level2[i][k]);
                for(int j=0;j<16;j++)
                {
                    level3[i][k][j] = temp[j]; 
                }
            }
            */
        //}
        // we need to check what collided and set to null. then we can check distance

        for(int i=0;i<16;i++)
        {
            if(pathCheck(Origin,level1[i]))
                //if collide then
                level1[i] = null; 

                for(int k=0;k<16;k++)
                {
                    if(level1[i]!= null)
                    {
                        if(pathCheck(level1[i],level2[i][k]))
                        {
                            level2[i][k] = null;
                        }
                    }
                    for(int j=0;j<16;j++)
                    {
                        if(level2[i][k] != null)
                        {
                            if(pathCheck(level2[i][k], level3[i][k][j]))
                            {
                                level3[i][k][j] = null;
                            }
                        }
                    }
                }
        }
       // for(int i=0;i<16;i++)
       // {
            
       // }
        //for(int i=0;i<16;i++)
       // {
           // for(int k=0;k<16;k++)
           // {
                
          //  }
      //  }
        // we have all the collided paths as null. Now time to check distances from each path to the destination. 
        
        double max =1000000000;
        for(int i=0;i<16;i++)
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
            for(int k=0;k<16;k++)
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
                for(int j=0;j<16;j++)
                {
                    if(level3[i][k][j] != null && level2[i][k] !=null && level1[i] !=null){
                        int x1 = destination.getX();
                        int y1 = destination.getY();

                        int x2 = level3[i][k][j].getX();
                        int y2 = level3[i][k][j].getY();

                        double distance = Math.pow(x1-x2,2) + Math.pow(y1-y2,2);
                        distance = Math.sqrt(distance);
                        if(max==-1)
                        {
                            max = distance;
                            lvl3i = i;
                            lvl3k = k;
                            lvl3j = j;
                        }
                        else if(distance<max)
                        {
                            max = distance;
                            lvl3i = i;
                            lvl3k = k;
                            lvl3j = j;
                        }
                    }
                }

            }
        }
        //max = -1;
        /* 
        for(int i=0;i<16;i++)
        {
            
        }
        max = -1;
        for(int i=0;i<16;i++)
        {
            for(int k=0;k<16;k++)
            {
                
            }
        }
        */
        // gets the best path and stores it
        /*
         * int lvl1;   // stores the i component of the lvl1 array

        int lvl2i=-1;
        int lvl2k=-1;

        int lvl3i=-1;
        int lvl3k=-1;
        int lvl3j=-1;
         */
        // this variable is whether level 1,2, or 3 has the smallest distance
        int small;
        double sm;
        double dis1=10000;
        double dis2=10000;
        double dis3=10000; 
        // this should be the distance between the destination and the current point.
         
        if(lvl1!=-1)
            dis1 = Math.sqrt(Math.pow(destination.getX()-level1[lvl1].getX(),2) + Math.pow(destination.getY()-level1[lvl1].getY(),2));
        if(lvl2i!=-1)
            dis2 = Math.sqrt(Math.pow(destination.getX()-level2[lvl2i][lvl2k].getX(),2)+Math.pow(destination.getY()-level2[lvl2i][lvl2k].getY(),2));
        if(lvl3i!=-1)
            dis3 = Math.sqrt(Math.pow(destination.getX()-level3[lvl3i][lvl3k][lvl3j].getX(),2)+Math.pow(destination.getY()-level3[lvl3i][lvl3k][lvl3j].getY(),2));
            
        if(dis1 < dis2)
        {
            small = 1;
            sm = dis1;
        }
        else
        {
            small = 2; 
            sm = dis2; 
        }
        if(sm>dis3)
        {

            small = 3;
            sm = dis3;  
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
        else if(small==3)
        {
            path[0] = level1[lvl3i];
            path[1] = level2[lvl3i][lvl3k];
            path[2] = level3[lvl3i][lvl3k][lvl3j];
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

        // for diagonals near x axis. 
        int xdai2 = (int) Math.cos(22) * range;
        int ydia2 = (int) Math.sin(22) * range; 

        // diagonals (close to x axis)
        Point i = new Point(point.getX()-xdai2,point.getY()-ydia2);
        Point j = new Point(point.getX()-xdai2,point.getY()+ydia2);
        Point k = new Point(point.getX()+xdai2,point.getY()-ydia2);
        Point l = new Point(point.getX()+xdai2,point.getY()+ydia2);

        local[8] = i;
        local[9] = j;
        local[10] = k;
        local[11] = l;

        // for diagonals (close to y axis)
        int xdai3 = (int) Math.sin(22) * range;
        int ydia3 = (int) Math.cos(22) * range; 

        //diagonals (close to y axis)
        Point m = new Point(point.getX()-xdai3,point.getY()-ydia3);
        Point n = new Point(point.getX()-xdai3,point.getY()+ydia3);
        Point o = new Point(point.getX()+xdai3,point.getY()-ydia3);
        Point p = new Point(point.getX()+xdai3,point.getY()+ydia3);

        local[12] = m;
        local[13] = n;
        local[14] = o;
        local[15] = p;
        
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
                        return true;
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
        for(int i=0;i<16;i++)
        {
            if(level1[i]!=null)
            g.drawLine(Origin.getX(),Origin.getY(), level1[i].getX(), level1[i].getY());
        }
        for(int i=0;i<16;i++)
        {
            for(int k=0;k<16;k++)
            {
                if(level2[i][k]!=null && level1[i]!= null)
                g.drawLine(level1[i].getX(),level1[i].getY(),level2[i][k].getX(),level2[i][k].getY());
            }
        }
        for(int i=0;i<16;i++)
        {
            for(int k=0;k<16;k++)
            {
                for(int j=0;j<16;j++)
                {
                    if(level2[i][k]!=null && level1[i]!= null && level3[i][k][j]!= null)
                    g.drawLine(level2[i][k].getX(),level2[i][k].getY(),level3[i][k][j].getX(),level3[i][k][j].getY());
                }
            }
        }
        //System.out.println("drawing best path");
        g.setColor(Color.BLUE);
        g.drawLine(Origin.getX(), Origin.getY(), path[0].getX(), path[0].getY());
        g.drawLine(path[0].getX(), path[0].getY(), path[1].getX(), path[1].getY());
        g.drawLine(path[1].getX(), path[1].getY(), path[2].getX(), path[2].getY());
        

        
    }
}
