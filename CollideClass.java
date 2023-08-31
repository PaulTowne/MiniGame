import java.util.ArrayList;

public class CollideClass 
{
    int objX;
    int objY;
    ArrayList<Block> wall;
    public CollideClass()
    {

    }
    public CollideClass(ArrayList<Block> list)
    {
        // so we will have a class that takes in the arrays of data. so small wall, medium wall, large wall. it will take in the data 
        // the constructor will take in the data
        // the methods  will take in which object we are testing for collision
        // other entities with collision properties will run through this
        // any collistion based interactables and debuffs will run by this and certain methods will return certain scenarios. 
        wall = list; 

    }
    public void setWalls()
    {

    }
    // this method is the attack method called to see if a melee weapon collides with the objects
    public boolean melee(ArrayList<Entity> en, int range, Point player, int mousePos, MeleeWeapon w)
    {
        //direction
       // System.out.println("melee mousePos ="+mousePos);
        int d = mousePos;
        // 1,2,3,4 each quadrant 
        int objX;
        int objY;
        int plaX;
        int plaY;
        ArrayList<Point> data = new ArrayList<Point>();
        //boolean hit = false;
        // make a circle equality. We use mouse position to limit which part of the circle we check (x >0 or y>0), we check points on object tosee if they are in circle.
        // this will also be for 20x20 entities.
        for(int i=0;i<en.size();i++)
        {
           // System.out.println("CollideClass loop (i)="+i);
            plaX = player.getX();
            plaY = player.getY();
            objX = en.get(i).getX() - plaX;
            //objY = plaY - en.get(i).getY();
            objY = en.get(i).getY() - plaY; 
            data.add(new Point(objX,objY));
            data.add(new Point(objX+10,objY));
            data.add(new Point(objX+20,objY));
            data.add(new Point(objX,objY+10));
            data.add(new Point(objX+20,objY+10));
            data.add(new Point(objX,objY+20));
            data.add(new Point(objX+10,objY+20));
            data.add(new Point(objX+20,objY+20));
            //System.out.println("Entity (i), x and y pos "+i+" "+objX+" "+objY);
            objX +=10;
            objY +=10;// added the plus 10 to make it the middle of the entity we are checking
            if(d==1)
            {
                if(objX >=0 && objY<=0)
                {
                    // in the right coordinate 
                    for(int k=0;k<data.size();k++)
                    {
                        if(data.get(k).getX() >=0 && data.get(k).getY()<=0)
                        {
                            int p  = (int) Math.sqrt((int) Math.pow(data.get(k).getX(),2) + (int) Math.pow(data.get(k).getY(),2));
                            if(p<range)
                            {
                                // in range in the right quadrant 
                                en.get(i).hurt(w.getDmg());
                               // System.out.println("hurt 1(i)="+i);
                                break;
                            }
                        }
                    }

                }
               
            }
            else if(d==2)
            {
                if(objX <=0 && objY<=0)
                {
                    // in the right coordinate 
                    for(int k=0;k<data.size();k++)
                    {
                        if(data.get(k).getX() <=0 && data.get(k).getY()<=0)
                        {
                            int p  = (int) Math.sqrt((int) Math.pow(data.get(k).getX(),2) + (int) Math.pow(data.get(k).getY(),2));
                            if(p<range)
                            {
                                // in range in the right quadrant 
                                en.get(i).hurt(w.getDmg());
                                //System.out.println("hurt 2(i)="+i);
                                break;
                            }
                        }
                    }
                }
                
            }
            else if(d==3)
            {
                if(objX <=0 && objY>=0)
                {
                    // in the right coordinate 
                    for(int k=0;k<data.size();k++)
                    {
                        if(data.get(k).getX() <=0 && data.get(k).getY()>=0)
                        {
                            int p  = (int) Math.sqrt((int) Math.pow(data.get(k).getX(),2) + (int) Math.pow(data.get(k).getY(),2));
                            if(p<range)
                            {
                                // in range in the right quadrant 
                                en.get(i).hurt(w.getDmg());
                                //System.out.println("hurt 3(i)="+i);
                                break;
                            }
                        }
                    }
                }
                
            }
            else if(d==4)
            {
                if(objX >=0 && objY>=0)
                {
                    // in the right coordinate 
                    for(int k=0;k<data.size();k++)
                    {
                        if(data.get(k).getX() >=0 && data.get(k).getY()>=0)
                        {
                            int p  = (int) Math.sqrt((int) Math.pow(data.get(k).getX(),2) + (int) Math.pow(data.get(k).getY(),2));
                            //System.out.println(" data x and y = p = "+data.get(k).getX()+" "+data.get(k).getY()+" "+p);
                            if(p<range)
                            {
                                // in range in the right quadrant 
                                en.get(i).hurt(w.getDmg());
                                //System.out.println("hurt 4(i)="+i);
                                break;
                            }
                        }
                       // System.out.println("1");
                    }
                   // System.out.println("2");
                }
                
            }
            //System.out.println("3");
            data.clear();
            objX -=10;
            objY -=10;

        }
        return false;
    }
    // this method will return the dmg done by collision depending on the instance
    public int collide(int x, int y, Entity entity)
    {
                int Xpos = entity.getX();
                int Ypos = entity.getY();
                //System.out.println("wall("+i+") "+Xpos+" "+Ypos);
                // player collidesz
                int top = y-5;
                int bot = y+10;
                int left = x-5;
                int right = x+15;
                int Otop = Ypos;
                int Obot = Ypos+10;
                int Oleft = Xpos;
                int Oright = Xpos+10;
        if(entity instanceof Dummy)
        {
            Oright = Xpos+20;
            Obot = Ypos+20; 
            if(!(bot<Otop || top>Obot || left>Oright || right<Oleft))
                {
                   return 2;
                }
        }
        else if(entity instanceof Slime)
        {
            if(!(bot<Otop || top>Obot || left>Oright || right<Oleft))
                {
                   return 1;
                }
        }
        return 0;
    }
    // this checks if there is any drawn image above the player.
    public boolean collide(Player player, int xrange, int yrange)
    {

        for(int i=0;i<wall.size();i++)
            {
                int Xpos = wall.get(i).xpos();
                int Ypos = wall.get(i).ypos();
                //System.out.println("wall("+i+") "+Xpos+" "+Ypos);
                // player collides
                int top = player.getY()-40;
                int bot = player.getY()+10;
                int left = player.getX()-5;
                int right = player.getX()+15;
                int Otop = Ypos;
                int Obot = Ypos+10;
                int Oleft = Xpos;
                int Oright = Xpos+10;
                if(wall.get(i) instanceof FenceV){
                    Oleft = Xpos+3;
                    Oright = Xpos +7;
                }
                else if(wall.get(i) instanceof Tree){
                    Otop = Ypos+34;
                    Oright = Xpos+15;
                    Oleft = Xpos+5;
                    Obot = Ypos+36;
                }
                else if(wall.get(i) instanceof Tree2){
                    Otop =Ypos+20;
                    Oright = Xpos+28;
                    Oleft = Xpos+13;
                    Obot = Ypos+30;
                }
                else if(wall.get(i) instanceof upBot){
                    Obot = Ypos+50;
                    Oright = Xpos+20;
                }
                else if(wall.get(i) instanceof upTop)
                {
                    Otop = Ypos+12;
                    Obot = Ypos+13;
                }
                else if(wall.get(i) instanceof upRight)
                {
                    Oleft = Xpos+20;
                    Oright = Xpos +24;
                }
                else if(wall.get(i) instanceof downTRcorner)
                {
                    Oright = Xpos+20;
                    Otop = Ypos+8;
                    Obot = Ypos+50;
                }
                else if(wall.get(i) instanceof downTLcorner)
                {
                    Oright = Xpos +20;
                    Obot = Ypos+50;
                }
                else if(wall.get(i) instanceof downLeft)
                {
                    Oright = Xpos+20;
                    Oleft = Xpos+10;
                }
                else if(wall.get(i) instanceof Stairs)
                {
                    Obot = Ypos+40;
                    
                }
                
                if(!(bot<Otop || top>Obot || left>Oright || right<Oleft))
                {
                   // you have collided
                   //System.out.println("collided player");
                   return true;
                }
            }
            return false;
    }
    // for entities 
    // for slime
    public boolean collide(Slime s, ArrayList<Block> w)
    {
        for(int i=0;i<wall.size();i++)
            {
                int Xpos = wall.get(i).xpos();
                int Ypos = wall.get(i).ypos();
                //System.out.println("wall("+i+") "+Xpos+" "+Ypos);
                // player collides
                int top = s.getY();
                int bot = s.getY()+10;
                int left = s.getX();
                int right = s.getX()+10;
                int Otop = Ypos;
                int Obot = Ypos+10;
                int Oleft = Xpos;
                int Oright = Xpos+10;
                if(wall.get(i) instanceof FenceV){
                    Oleft = Xpos+3;
                    Oright = Xpos +7;
                }
                else if(wall.get(i) instanceof Tree){
                    Otop = Ypos+34;
                    Oright = Xpos+15;
                    Oleft = Xpos+5;
                    Obot = Ypos+36;
                }
                else if(wall.get(i) instanceof Tree2){
                    Otop =Ypos+20;
                    Oright = Xpos+28;
                    Oleft = Xpos+13;
                    Obot = Ypos+30;
                }
                else if(wall.get(i) instanceof upBot){
                    Obot = Ypos+50;
                    Oright = Xpos+20;
                }
                else if(wall.get(i) instanceof upTop)
                {
                    Otop = Ypos+12;
                    Obot = Ypos+13;
                }
                else if(wall.get(i) instanceof upRight)
                {
                    Oleft = Xpos+20;
                    Oright = Xpos +24;
                }
                else if(wall.get(i) instanceof downTRcorner)
                {
                    Oright = Xpos+20;
                    Otop = Ypos+8;
                    Obot = Ypos+50;
                }
                else if(wall.get(i) instanceof downTLcorner)
                {
                    Oright = Xpos +20;
                    Obot = Ypos+50;
                }
                else if(wall.get(i) instanceof downLeft)
                {
                    Oright = Xpos+20;
                    Oleft = Xpos+10;
                }
                else if(wall.get(i) instanceof Stairs)
                {
                    Oright = 0;
                    
                }

                if(!(bot<Otop || top>Obot || left>Oright || right<Oleft))
                {
                   // you have collided
                   //System.out.println("collided player");
                   return true;
                }
            }
            return false;
    }
    public boolean collide(int x, int y, ArrayList<Block> w)
    {
        wall =w;
        //System.out.println("collide class");
        for(int i=0;i<wall.size();i++)
        {
               // System.out.println("in for loop");
                int Xpos = wall.get(i).xpos();
                int Ypos = wall.get(i).ypos();
                //System.out.println("wall("+i+") "+Xpos+" "+Ypos);
                // player collides
                int top = y;
                int bot = y+10;
                int left = x;
                int right = x+10;
                int Otop = Ypos;
                int Obot = Ypos+10;
                int Oleft = Xpos;
                int Oright = Xpos+10;
                if(wall.get(i) instanceof FenceV){
                    Oleft = Xpos+3;
                    Oright = Xpos +7;
                }
                else if(wall.get(i) instanceof Tree){
                    Otop = Ypos+34;
                    Oright = Xpos+15;
                    Oleft = Xpos+5;
                    Obot = Ypos+36;
                }
                else if(wall.get(i) instanceof Tree2){
                    Otop =Ypos+20;
                    Oright = Xpos+28;
                    Oleft = Xpos+13;
                    Obot = Ypos+30;
                }
                else if(wall.get(i) instanceof upBot){
                    Obot = Ypos+50;
                    Oright = Xpos+20;
                }
                else if(wall.get(i) instanceof upTop)
                {
                    Otop = Ypos+12;
                    Obot = Ypos+13;
                }
                else if(wall.get(i) instanceof upRight)
                {
                    Oleft = Xpos+20;
                    Oright = Xpos +24;
                }
                else if(wall.get(i) instanceof downTRcorner)
                {
                    Oright = Xpos+20;
                    Otop = Ypos+8;
                    Obot = Ypos+50;
                }
                else if(wall.get(i) instanceof downTLcorner)
                {
                    Oright = Xpos +20;
                    Obot = Ypos+50;
                }
                else if(wall.get(i) instanceof downLeft)
                {
                    Oright = Xpos+20;
                    Oleft = Xpos+10;
                }
                else if(wall.get(i) instanceof Stairs)
                {
                    Oright = 0;
                    
                }
                if(!(bot<Otop || top>Obot || left>Oright || right<Oleft))
                {
                   // you have collided
                   //System.out.println("collided in spider");
                   return true;
                }
        }
        return false;
    }
    // for player
    public boolean collide(Player player)
    {
        for(int i=0;i<wall.size();i++)
            {
                int Xpos = wall.get(i).xpos();
                int Ypos = wall.get(i).ypos();
                //System.out.println("wall("+i+") "+Xpos+" "+Ypos);
                // player collides
                int top = player.getY();
                int bot = player.getY()+10;
                int left = player.getX();
                int right = player.getX()+10;
                int Otop = Ypos;
                int Obot = Ypos+10;
                int Oleft = Xpos;
                int Oright = Xpos+10;
                if(wall.get(i) instanceof FenceV){
                    Oleft = Xpos+3;
                    Oright = Xpos +7;
                }
                else if(wall.get(i) instanceof Tree){
                    Otop = Ypos+34;
                    Oright = Xpos+15;
                    Oleft = Xpos+5;
                    Obot = Ypos+36;
                }
                else if(wall.get(i) instanceof Tree2){
                    Otop =Ypos+20;
                    Oright = Xpos+28;
                    Oleft = Xpos+13;
                    Obot = Ypos+30;
                }
                else if(wall.get(i) instanceof upBot){
                    Obot = Ypos+50;
                    Oright = Xpos+20;
                }
                else if(wall.get(i) instanceof upTop)
                {
                    Otop = Ypos+12;
                    Obot = Ypos+13;
                }
                else if(wall.get(i) instanceof upRight)
                {
                    Oleft = Xpos+20;
                    Oright = Xpos +24;
                }
                else if(wall.get(i) instanceof downTRcorner)
                {
                    Oright = Xpos+20;
                    Otop = Ypos+8;
                    Obot = Ypos+50;
                }
                else if(wall.get(i) instanceof downTLcorner)
                {
                    Oright = Xpos +20;
                    Obot = Ypos+50;
                }
                else if(wall.get(i) instanceof downLeft)
                {
                    Oright = Xpos+20;
                    Oleft = Xpos+10;
                }
                else if(wall.get(i) instanceof Stairs)
                {
                    Oright = 0;
                    
                }

                if(!(bot<Otop || top>Obot || left>Oright || right<Oleft))
                {
                   // you have collided
                   //System.out.println("collided player");
                   return true;
                }
            }
            return false;
    }
}
