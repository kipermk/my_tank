package Tanks;

import javax.swing.*;
import java.awt.*;

public class Window {

    public static void main(String[] args) {

        JFrame start = new JFrame("Танки начало!");
        start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start.setLocation(200,50);

        Dimension scrinSise = Toolkit.getDefaultToolkit().getScreenSize();
       // start.setSize(scrinSise);

        start.setSize(1000,600);
        //start.getContentPane().getSize().height;
        start.add(new Panel());

        start.setVisible(true);

    }
}
