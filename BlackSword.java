import java.awt.*;
import javax.swing.*;
import java.util.*;

public class BlackSword extends MeleeWeapon
{
    
     int damage; 
     int distance; 
     int attSpeed;
     Image phase1;
     Image phase2;
     WeaponDraw [] photo = new WeaponDraw[24];
    public BlackSword(int dmg, int dis, int att)
    {
        super(dmg, dis, att);
        damage = dmg;
        distance = dis;
        attSpeed = att; 
        Toolkit tool = Toolkit.getDefaultToolkit();
        phase2 = tool.getImage("Photos/DefaultSword/defaultflip.png");
        phase1 = tool.getImage("Photos/DefaultSword/45.png");
        
        photo[0] = new WeaponDraw(tool.getImage("Photos/DefaultSword/0.png"),20,-15);
        photo[1] = new WeaponDraw(tool.getImage("Photos/DefaultSword/15.png"),20,-25);
        photo[2] = new WeaponDraw(tool.getImage("Photos/DefaultSword/30.png"),20,-35);
        photo[3] = new WeaponDraw(tool.getImage("Photos/DefaultSword/45.png"),18,-42);
        photo[4] = new WeaponDraw(tool.getImage("Photos/DefaultSword/60.png"),10,-50);
        photo[5] = new WeaponDraw(tool.getImage("Photos/DefaultSword/75.png"),-5,-60);
        photo[6] = new WeaponDraw(tool.getImage("Photos/DefaultSword/90.png"),-10,-60);

        photo[13] = new WeaponDraw(tool.getImage("Photos/DefaultSword/195.png"),-42,-10);
        photo[12] = new WeaponDraw(tool.getImage("Photos/DefaultSword/180.png"),-42,-18);
        photo[11] = new WeaponDraw(tool.getImage("Photos/DefaultSword/165.png"),-45,-30);
        photo[10] = new WeaponDraw(tool.getImage("Photos/DefaultSword/150.png"),-45,-42);
        photo[9] = new WeaponDraw(tool.getImage("Photos/DefaultSword/135.png"),-42,-50);
        photo[8] = new WeaponDraw(tool.getImage("Photos/DefaultSword/120.png"),-32,-58);
        photo[7] = new WeaponDraw(tool.getImage("Photos/DefaultSword/105.png"),-19,-60);

        photo[14] = new WeaponDraw(tool.getImage("Photos/DefaultSword/210.png"),-42,0);
        photo[15] = new WeaponDraw(tool.getImage("Photos/DefaultSword/225.png"),-36,5);
        photo[16] = new WeaponDraw(tool.getImage("Photos/DefaultSword/240.png"),-30,9);
        photo[17] = new WeaponDraw(tool.getImage("Photos/DefaultSword/255.png"),-23,12);
        photo[18] = new WeaponDraw(tool.getImage("Photos/DefaultSword/270.png"),-12,16);
        photo[19] = new WeaponDraw(tool.getImage("Photos/DefaultSword/285.png"),-5,13);
        photo[20] = new WeaponDraw(tool.getImage("Photos/DefaultSword/300.png"),2,8);

        photo[21] = new WeaponDraw(tool.getImage("Photos/DefaultSword/315.png"),8,5);
        photo[22] = new WeaponDraw(tool.getImage("Photos/DefaultSword/330.png"),12,-2);
        photo[23] = new WeaponDraw(tool.getImage("Photos/DefaultSword/345.png"),18,-8);

            // instead of 24 photos lets have like 4 large "photos" they have their own set of photos ot make an animation
            
    }
    public  int getDmg()
    {
        return 20;
    }
    public  int getDistance()
    {
        return 50;
    }
    public  int getAttSpeed()
    {
        return 10; 
    }
    public WeaponDraw[] getList(double dir)
    {
        if(dir ==0)
        {
            WeaponDraw w [] = new WeaponDraw[7]; 
            w[0] = photo[21];
            w[1] = photo[22];
            w[2] = photo[23];
            w[3] = photo[0];
            w[4] = photo[1];
            w[5] = photo[2];
            w[6] = photo[3];

            return w;
        }
        else if(dir==1)
        {
            return Arrays.copyOfRange(photo,0,12);
        }
        else if(dir ==2)
        {
            return Arrays.copyOfRange(photo,12,23);
        }
        else if(dir ==3)
        {
            return Arrays.copyOfRange(photo,9,17);
        }
        return null;
       // return Arrays.copyOfRange(photo,1,5);
    }
    public void drawInactive(Graphics g,Point pos, boolean left)
    {
        if(!left)
        g.drawImage(phase1, pos.getX()+5, pos.getY()-10, null);
        else
        g.drawImage(phase2, pos.getX()-15, pos.getY()-10, null);
    }
    public void draw(Graphics g, int phase,int direction, int plx, int ply)
    {
        // make like 20 photos. get the angles and order them.then get the degree the mouse makes with the player and choose those photos. 
        //g.drawRect()
      //  System.out.println("in draw default sword");
       // g.drawImage(photo[phase].getImage(),plx+photo[phase].getX(),ply+photo[phase].getY(),null);
       // g.setColor(Color.blue);
       // g.drawRect(plx-50, ply-50, 100, 100);
        
    }

}
