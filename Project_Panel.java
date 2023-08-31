import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
public class Project_Panel extends JPanel
{
    int row; 
    int column;
    //Block array[][] = new Block[90][180]; 
    ArrayList<Block> array = new ArrayList<Block>();
    ArrayList<Block> wall = new ArrayList<Block>();
    ArrayList<Slime> nest = new ArrayList<Slime>();
    ArrayList<Tank> armory = new ArrayList<Tank>();
    ArrayList<Entity> horde = new ArrayList<Entity>();
    PathFinder path;
    PathFinderREDUCED path2;
    Player player = new Player(200,100,100,1);
    //Spider sp1 = new Spider(350,350,100,1);
    //Spider sp2 = new Spider(500,500,100,1);
    boolean w = false;
    boolean a = false;
    boolean s = false;
    boolean d = false;
    boolean space = false;
    boolean e1 = false; // UI control
    // set up for player facing direction
    boolean left = false;
    boolean right = false;
    // for damage
    boolean hurt = false;
    int dmgBuffer=0;
    int playerBuffer=0; // for player using weapon
    int boost = 0;
    // photos (spider)
    //private BufferedImage image; 
    Image background;
    Image tree;
    Image spiderTest;
    Image test;
    CollideClass classCollide;

    // mouse
    int mousex;
    int mousey;
    public ArrayList<Block> getBlock()
    {
        return wall;
    }
    
    public Project_Panel(int x)
    {

    }
    // Constructor 
    public Project_Panel()
    {
        System.out.println("testing");
        // size of 2000 by 1000
        addKeyListener(new KeyEventDemo());
        addMouseListener(new MouseClass());
        Timer t = new Timer(1, new TimeListener());
        Timer t2 = new Timer(5000,new TimeListener2());
        Timer t3 = new Timer(10,new TimeListener3());

        t.start();
       // t2.start();
        //t3.start();
        // setting up the collide class
        classCollide = new CollideClass(wall);
        setFocusable(true);
         row = 90;
         column = 180;
         for(int i=0;i<5;i++)
         {
            horde.add(new Slime(250,350,100,1,wall));
            //nest.get(i).getBlock(wall);
         }
         for(int i=0;i<0;i++)
         {
            horde.add(new Tank(250,350,100,1,wall));
         }
         for( int i=0;i<1;i++)
         {
            horde.add(new Dummy(900, 600, 1000, 1, wall));
         }
         horde.add(new Dummy(920, 600, 1000, 1, wall));
         horde.add(new Dummy(1000, 700, 1000, 1, wall));
        try
        {
            Toolkit tool = Toolkit.getDefaultToolkit();
            background = tool.getImage("Photos/grassFull.png");
            test = tool.getImage("Photos/PlayerFiles/PlayerDraft.png");
            //tree = tool.getImage("tree.png");
            Scanner scan = new Scanner(new File("map2.txt"));
            // "-" is null
            // 0 is stone wall
            // 1 os fenceV
            // 2 is fenceH
            for(int i=0;i<row;i++)
            {
                for(int k=0;k<column;k++)
                {
                    String temp = scan.next();
                    if(temp.equals("1"))
                    {
                        // long grass one
                        wall.add(new upBot(i*10+10, k*10+10));
                    }
                    else if(temp.equals("0"))
                    {
                        wall.add(new FrontWall(i*10+10,k*10+10));
                    }
                    else if(temp.equals("2"))
                    {
                        wall.add(new FenceV(i*10+10,k*10+10));
                    }
                    else if(temp.equals("3"))
                    {
                        array.add(new Path(i*10+10,k*10+10));
                    }
                    else if(temp.equals("4"))
                    {
                        wall.add(new Tree(i*10+10,k*10+10));
                    }
                    else if(temp.equals("5"))
                    {
                        wall.add(new Tree2(i*10+10,k*10+10));
                    }
                    else if(temp.equals("6"))
                    {
                        // make this exception no collides
                        wall.add(new Stairs(i*10+10,k*10+10));
                    }
                    else if(temp.equals("7"))
                    {
                        wall.add(new upTop(i*10+10,k*10+10));
                    }
                    else if(temp.equals("8"))
                    {
                        wall.add(new upLeft(i*10+10,k*10+10));
                    }
                    else if (temp.equals("9"))
                    {
                        wall.add(new upRight(i*10+10,k*10+10));
                    }
                    else if(temp.equals("10"))
                    {
                        wall.add(new downTRcorner(i*10+10,k*10+10));
                    }
                    else if(temp.equals("11"))
                    {
                        // maybe we  can extend the walls near it collision and make this one no collision. 
                        wall.add(new downBLcorner(i*10+10,k*10+10));
                    }
                    else if(temp.equals("12"))
                    {
                        wall.add(new downTLcorner(i*10+10,k*10+10));
                    }
                    else if(temp.equals("13"))
                    {
                        wall.add(new downLeft(i*10+10,k*10+10));
                    }
                    else if(temp.equals("14"))
                    {
                        wall.add(new downRight(i*10+10,k*10+10));
                    }
                    else if(temp.equals("15"))
                    {
                        wall.add(new downBRcorner(i*10+10,k*10+10));
                    }
                    else if(temp.equals("16"))
                    {
                        //wall.add(new darkerGrass(i*10+10,k*10+10));
                    }
                }
            }
            player.getWall(wall);
            //System.out.println("size of wall in projectpanel = "+wall.size());
            // gives the nest objects the array data ( should change )
            /* 
            for(int i=0;i<nest.size();i++)
            {
                nest.get(i).getBlock(wall);
            }
            */
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not Found");
        }
    }
    public void paintComponent(Graphics g)
    {
            super.paintComponent(g);
           
            for(int i=0;i<array.size();i++){
                    if(array.get(i)!=null)
                    array.get(i).draw(g);
                
            }
            g.drawImage(background,0,0,this);
            //g.drawImage(tree,100,100,this);
            player.draw(g);
            for(int i=0;i<wall.size();i++)
            {
                wall.get(i).draw(g);
            }
            for(int i=0;i<horde.size();i++)
            {
                horde.get(i).draw(g);
            }
            
            //pathfinder
            if(path!= null)
            {
                //path.draw(g);
            }
            if(path2!= null)
            {
                //path2.draw(g);
            }
            if(classCollide.collide(player,10,10))
            {
                player.draw(g);
            }
            if(e1)
                player.drawPlayerUI(g);
            // draws player weapons
            player.drawWeapon(g);
            //path.draw(g);
            //path2.draw(g);
            // testing rotate
            //Graphics2D g2d=(Graphics2D)g;
            //g2d.translate(250, 200); // Translate the center of our coordinates.
            //g2d.rotate(.1);
           
            //g2d.drawImage(test, 0, 0,this);
            
            
    }
    public class KeyEventDemo implements KeyListener
    {
        public void keyTyped(KeyEvent e){}
        public void keyReleased(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_W)
            {
                w = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_A)
            {
                a = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_S)
            {
                s = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_D)
            {
                d = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_SPACE)
            {
                space = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_E)
            {
                //e1= false;
            }
        }
        public void keyPressed(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_W)
            {
                w = true;
            }
            if(e.getKeyCode() == KeyEvent.VK_A)
            {
                a = true;
                left = true;
                right = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_S)
            {
                s = true;
            }
            if(e.getKeyCode() == KeyEvent.VK_D)
            {
                d = true;
                right = true;
                left = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_SPACE)
            {
                space = true;
                
            }
            if(e.getKeyCode() == KeyEvent.VK_E)
            {
                if(e1==false)
                    e1 = true;
                else
                    e1 = false;
                
            }
        }
    }
    public class MouseClass extends MouseInputAdapter
    {
        public void mousePressed(MouseEvent me)
        {
            int pointx = me.getX();
            int pointy = me.getY();
            //System.out.println("Mouse = "+pointx+" "+pointy);
           // System.out.println("Spider pos ="+sp1.getX()+" "+sp1.getY());
            mousex = me.getX();
            mousey = me.getY();
            if(playerBuffer==0){
            player.attack1(horde,mousex,mousey);
            //System.out.println("attack buffer");
            playerBuffer = player.w1.getAttSpeed();
            }
            if(!player.midAnimated)
            player.setActive(true);
            
        }
        public void mouseMoved(MouseEvent me)
        {
           // mousex = me.getX();
           // mousey = me.getY();
           // System.out.println("mosuemove");
        }
        public void mouseDragged(MouseEvent me)
        {

        }
    }
    public class TimeListener3 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            for(int i=0;i<nest.size();i++)
            {
                path = nest.get(i).testRun();
            }
            for(int i=0;i<armory.size();i++)
            {
                path2 = armory.get(i).testRun();
            }
        }
    }
    public class TimeListener2 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           // System.out.println("setDes");
            
           for(int i=0;i<nest.size();i++)
           {
                //nest.get(i).Destination();
           }
           
        }
    }
    public class TimeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //System.out.println("boost: "+boost);
            if(space == true)
            {
                if(boost>0){
                    boost = boost-30;
                    player.setSpeed(2);
                }
                else{
                    player.setSpeed(1);
                }
            }
            else{
                if(boost<1000)
                {
                    boost = boost+4; 
                    player.setSpeed(1);
                }
                
            }
                if(w==true)
                {
                    player.move(0, -2, 0);
                    if(classCollide.collide(player))
                    player.move(0, 2, 0);
                }
                if(a==true)
                {
                    player.move(-2, 0, 0);
                    if(classCollide.collide(player))
                    player.move(2,0,0);
                }
                if(s==true)
                {
                    player.move(0, 2, 0);
                    if(classCollide.collide(player))
                    player.move(0, -2, 0);
                }
                if(d==true)
                {
                    player.move(2, 0, 0);
                    if(classCollide.collide(player))
                    player.move(-2, 0, 0);
                }
                // horde 
                for(int i=0;i<horde.size();i++)
                {
                    horde.get(i).move(0,0,0);
                    horde.get(i).detect(player.getX(),player.getY());
                }
                

                // send hte mouse info to the player
                player.getMouse(left,mousex,mousey);
                player.getattackDirection(mousex, mousey);
                //if(dmgBuffer ==50)
                    if(hurt==false)
                    {
                        hurt = player.damage(horde);
                        if(hurt)
                            hurt = true;

                    }
                    else{
                        dmgBuffer++;
                    }
                //}
                    if(dmgBuffer>200){
                        dmgBuffer=0;
                        hurt = false;
                    }
                
                // this iterates for playerBuffer so the player cant spam weapon
                if(playerBuffer>0)
                {
                    playerBuffer--;
                }
                else{

                }

                // if somethings health goes to 0 remove it
                for(int i=0;i<horde.size();i++)
                {
                    if(horde.get(i).getHp()<=0)
                    {
                        horde.remove(i);
                    }
                }
            repaint();
            //repaint(player.getX()-20, player.getY()-20, 60, 60);
            //repaint(each entity)
            
        }
    }
    
}
