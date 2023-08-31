import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
public class Project extends JFrame
{
    public static void main(String [] args)
    {
        JFrame frame = new JFrame("MiniGame");
        frame.setSize(1860,1000);
        frame.setContentPane(new Project_Panel());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    }
}