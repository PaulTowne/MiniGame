import java.awt.*;
import javax.swing.*;
import java.util.*;
public abstract class RangedWeapon // this is basically projectile class but the differences will be the projectile and the weapon drawings
{
    private int damage; 
    private int distance; 
    private int attSpeed; // how many times you can attack
    private int projSpeed; // how fast the projectiles fly

    public RangedWeapon(int dmg, int dis, int attSp, int projS)
    {
        damage = dmg;
        distance = dis;
        attSpeed = attSp;
        projSpeed = projS;
    }
    public abstract int getDmg();
    public abstract int getDistance();
    public abstract int getAttSpeed();
    public abstract void draw(Graphics g, int phase,int direction, int plx, int ply);
    public abstract void drawInactive(Graphics g, Point pos,boolean left);
    public abstract WeaponDraw[] getList(double dir);
    public abstract void drawUI(Graphics g);
}
