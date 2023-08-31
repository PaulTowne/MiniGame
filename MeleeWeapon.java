import java.awt.*;
import javax.swing.*;
import java.util.*;
public abstract class MeleeWeapon {
    
    // this is the parent class for all weapons
    private int damage; 
    private int distance; 
    private int attSpeed;

    public  MeleeWeapon(int dmg, int dis, int att)
    {
        damage = dmg;
        distance = dis;
        attSpeed = att; 
    }
    public abstract int getDmg();
    public abstract int getDistance();
    public abstract int getAttSpeed();
    public abstract void draw(Graphics g, int phase,int direction, int plx, int ply);
    public abstract void drawInactive(Graphics g, Point pos,boolean left);
    public abstract WeaponDraw[] getList(double dir);
    public abstract void drawUI(Graphics g);

}
