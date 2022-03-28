package Tanks;

import javax.swing.*;
import java.awt.*;

public class Back{

    private int x;
    private int y;

    //Image img = new ImageIcon("C:\\Tests\\fon.jpg").getImage();
    Image img = new ImageIcon("image/fon.jpg").getImage();

    public void draw(Graphics2D g, PlayerTank Tank1, PlayerTank Tank2){

        double xTank1 = Tank1.getX();
        double xTank2 = Tank2.getX();
        boolean move = true;

        //ограниечение игровой зоны по иксу
        if (x<-1200 ) {
            Panel.limitRight = 70;
            move = false;
        }
        if (x>1) {
            Panel.limitLeft = 20;
            move = false;
        }


        if (Panel.WIDTH - xTank1 > 250 && Panel.WIDTH - xTank2 > 250 && Panel.limitLeft != 20) {
            Panel.limitRight = 150;
            move = true;
        }
        if (xTank1 > 250 && xTank2 > 250 && Panel.limitRight != 70) {
            Panel.limitLeft = 150;
            move = true;
        }

        //вычисляем растояние между танками по ширине, по иксу
        int difference = (int) (Math.max(xTank1,xTank2)-Math.min(xTank1,xTank2));
        //вычисляем область в которой не происходит движение заднего фона
        int obl = Panel.WIDTH-Panel.limitLeft-Panel.limitRight-120;

        if (difference>obl) move = false;

        //System.out.println(x +" "+ move);
        //System.out.println("ТАНК1 "+ xTank1 +"   ТАНК2  " + xTank2);

        //движение фона
        if (move) {
            tankMoveGround(Panel.presskeyUp1, Tank1, Tank2);
            tankMoveGround(Panel.presskeyUp2, Tank2, Tank1);
        }

//        if (difference>obl){
//            //не двигам граунд
//        } else {
//            //движение фона
//            if (move) {
//                tankMoveGround(Panel.presskeyUp1, Tank1, Tank2);
//                tankMoveGround(Panel.presskeyUp2, Tank2, Tank1);
//            }
//        }

        g.drawImage(img,x,0,null);
//        g.setBackground(Color.BLUE);
//        if (y<0) y = 0;
//        if (y>Panel.HEIGHT-80) y = Panel.HEIGHT-80;
//        System.out.println("xTank = "+xTank1);
//        System.out.println("X = "+Tank1.up);
    }

    private void tankMoveGround(boolean presskeyUp, PlayerTank Tank1, PlayerTank Tank2) {

        double xTank1 = Tank1.getX();
        double xTank2 = Tank2.getX();
        int speed = Tank1.getSpeed();

        //System.out.println(Tank1.getLimitX());
        if (presskeyUp) {

            if (xTank1<Panel.limitLeft+1) {
                x = x +speed;
                Tank2.setXYAngle(xTank2 + 3);
            }
            if (xTank1>Panel.WIDTH-Panel.limitRight-1) {
                x = x -speed;
                Tank2.setXYAngle(xTank2 - 3);
            }
        }
    }

    //ГЕТЕРЫ
    public double getX() {return x; }
    public double getY() {return y; }
}
