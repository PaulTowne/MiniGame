import java.awt.*;
import javax.swing.*;
import java.util.*;
public class WeaponDraw 
{
    double x;
    double y;
    Image g;
    public WeaponDraw(Image g, double x, double y)
    {
        this.x = x;
        this.y = y;
        this.g = g;
    }
    public double getX()
    {
        return x;
    }    
    public double getY()
    {
        return y;
    }
    public Image getImage()
    {
        return g;
    }
}
