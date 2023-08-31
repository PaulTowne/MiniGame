import java.awt.*;

import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import java.util.*;
public class Player
{
    int xpos;
    int ypos;
    int health;
    int speed;
    Image phase1;
    Image phase2;
    Image phase3;
    int phase =0;
    int mostRecentX;
    int mostRecentY;
    boolean left;
    int mousex;
    int MsX;
    int MsY;
    double rad;
    ArrayList<Block> array;
    CollideClass collide;
    

    // weaponns 
    MeleeWeapon w1;
    boolean w1active = false;
    // weapon phase test. 
    int phas =0;
    WeaponDraw list [];
    // to allow animation to finish
    boolean midAnimated = false;
    // player no longer inherits from entity
    public Player(int x, int y, int health, int speed)
    {
        //super(x, y, health, speed);
        w1 = new lvl1Sword(10, 50, 10);
        xpos = x;
        ypos = y;
        this.health = health;
        this.speed = speed; 
        collide = new CollideClass();
        Toolkit tool = Toolkit.getDefaultToolkit();
        //phase1 = tool.getImage("Photos/PlayerFiles/PlayerDraft.png");
        //phase2 = tool.getImage("Photos/PlayerFiles/Playerp2.png");
        phase1 = tool.getImage("Photos/PlayerFiles/TechnoCat.png");
        phase2 = tool.getImage("Photos/PlayerFiles/TechnoCatLeft.png");
        phase3 = tool.getImage("Photos/heart.png");
        
    }
    public void getWall(ArrayList<Block> arr)
    {
        array = arr;
    }
    // called when the player attacks (left mouse click) ( melee)
    //returns true if hit enemy 
    public void getattackDirection(int x, int y)
    {
        MsX =x;
        MsY =y;
    }
    // draws information for player such as health and weapon.
    public void drawPlayerUI(Graphics gU)
    {
        gU.drawImage(phase3,20,20,null);
        gU.drawString(health+"", 40, 60);
        w1.drawUI(gU);
    }
    // attacking entities
    public boolean attack1(ArrayList<Entity> en, int mx, int my)
    {
        
        MsX = mx;
        MsY = my; 
        int range  = w1.getDistance();
        int attSpeed = w1.getAttSpeed();
        int dmg  = w1.getDmg();
        int direction=-1;
        Point player = new Point(xpos,ypos);
        // calculating direction to give 
            int calcX = MsX-player.getX();
            int calcY = MsY-player.getY();
            if(calcX>=0 && calcY<=0)
            {
                direction =1;
            }
            else if(calcX<=0 && calcY<=0)
            {
                direction = 2; 
            }
            else if(calcX<=0 && calcY>=0)
            {
                direction =3; 
            }
            else if(calcX>=0 && calcY>=0)
            {
                direction = 4;
            }
            
            boolean hit = collide.melee(en, range,player,direction, w1);
            //System.out.println("Hit? = "+hit);
        return hit;
    }
    // collision with entity
    public boolean damage(ArrayList<Entity> en)
    {
        int hurt=0;
        //System.out.println("size of entity in damage ="+en.size());
        for(int i=0;i<en.size();i++)
        {
            if(en.get(i)!=null)
            hurt = collide.collide(xpos, ypos, en.get(i));
            health = health - hurt;
            if(health<0)
            {
                health =0;
                break;
            }
        }
        if(hurt !=0)
        {
            //took damage 
            hurt =0;
            return true;
        }
        else
        {
            hurt =0;
            return false;
        }
        
    }
    public void setActive(boolean t)
    {
        w1active = t;
    }   
    public boolean midAnimate()
    {
        return midAnimated;
    }
    // constantly called. 
    public void drawWeapon(Graphics g)
    {

        int x = MsX-xpos;
        int y = ypos-MsY;
       // System.out.println("x and y in drawWeapon ="+x+" "+y);
        double angle = Math.toDegrees(Math.atan2(y, x));
        //double degree = Math.toDegrees(Math.atan((y/x)));
        
        //System.out.println("degree from mouse="+angle);
        if(w1active)
        {
            if(phas==0){
                list = w1.getList(angle);
                midAnimated=true;
            }
            rad =(Math.atan2(list[phas].getY(),list[phas].getX()));
            Graphics2D g3d=(Graphics2D)g;
            g3d.translate(list[phas].getX()+xpos,list[phas].getY()+ypos); // Translate the center of our coordinates.
            g3d.rotate(rad);
            g3d.drawImage(list[phas].getImage(), 0, 0, null);
            phas++;
            if(phas==list.length)
            {
                phas=0;
                midAnimated = false;
                w1active = false;
            }
        }
        else{
             // inactive
             Point p = new Point(xpos,ypos);
             w1.drawInactive(g, p,left);
        }



        // using MsX and MsY.
        // we need to know the mouse position in regards to player and the phase. 
        // we will always be drawing weapons. but whether they are active or passive needs to be checked
        // USE GETTERS TO GET ARRAYS OF SPECFIC SWORD POSITIONS DEPENDING ON WHICH DIRECTION WE HAVE
        /* 
        if(w1active){
            int direction=0;
            if(phase==0 && midAnimated==false){
                if(MsX>=xpos && MsY>=(ypos-20) && MsY<=(ypos+15))
                {
                    // give me an array from defaultsword with a list of swords.
                    // then a loop after this runs the array inputed. 
                    direction =0;
                }
                else if(MsY<=(ypos-20))
                {
                    direction =1;
                }
                else if(MsY>=(ypos+15))
                {
                    direction = 2;
                }
                else if(MsX<=xpos && MsY>=(ypos-20) && MsY<=(ypos+15))
                {
                    direction = 3;
                }   
                list = w1.getList(direction);
                midAnimated = true;
            }

           System.out.println("size of weaponlist = "+list.length+" phas = "+phas);
           //g.drawImage(list[0].getImage(),xpos,ypos,null);
           if(phas>list.length)
                phas =0;
           g.drawImage(list[phas].getImage(),xpos+list[phas].getX(),ypos+list[phas].getY(),null);
            phas++;
            if(phas>list.length-1)
            {
                phas = 0;
                w1active = false;
                midAnimated = false;
            }
                
            // sets inactive afterwards. 
            //w1active = false;
        }
        else{
            // inactive
            Point p = new Point(xpos,ypos);
            w1.drawInactive(g, p,left);
        }
        */
    }
    public void draw(Graphics g)
    {
        
        g.setColor(Color.black);
        g.fillOval(xpos-5,ypos+8,20,5);

        if(left)
        {
            //if(mousex<xpos)
            g.drawImage(phase2,xpos-10,ypos-20,null);
            
        }
        else
        {
            g.drawImage(phase1,xpos-10,ypos-20,null);
        }
        
        mousex = xpos;
        //g.setColor(Color.red);
        //System.out.println("health ="+health);
        //g.fillRect(50, 900, health*5, 50);
       // g.setColor(Color.BLACK);
        //g.drawRect(50,900,500,50);
        //g.drawRect(xpos,ypos,20,20);
        //g.drawOval(xpos-45, ypos-50, w1.getDistance()*2, w1.getDistance()*2);

          
       // g.drawLine(xpos, ypos-20, 20, ypos-20);
        //g.drawLine(xpos,ypos+15,20,ypos+15);
        
        //g.drawRect(xpos,ypos,20,20);
    }
    public void move(int x, int y, int sp)
    {
        speed = speed+sp; 
        xpos = xpos+(speed*x);
        ypos = ypos+(speed*y);
        if(xpos>1800 || xpos<10)
        {
            xpos = xpos-(speed*x);
        }
        if(ypos>900 || ypos<10)
        {
            ypos = ypos-(speed*y);
        }
    }
    public void getMouse(boolean x, int mx, int my)
    {
        left = x;
        mousex = mx;

    }
    public void detect(int x, int y)
    {

    }
    public void hP(int dmg)
    {

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
