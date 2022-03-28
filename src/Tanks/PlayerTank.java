package Tanks;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

import static java.lang.Math.*;

public class PlayerTank {

    private int speed;
    private int speedBack;

    private int health;
    private int r;

    private double x;
    private double y;
    private double angl;
    private double anglSin;
    private double anglCos;

    //Панель игрока позиця
    private static boolean rightPosition;


    // public static boolean up;
    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;

    public static boolean fire;

    //Image img = new ImageIcon("C:\\Tests\\fon.jpg").getImage();
    Image img; //= new ImageIcon("image/pl1.png").getImage();
    private Color color;
    private int numberTank;

    private double sdvigX = 24;
    private double sdvigY = 60;

    public PlayerTank(){
        up    = false;
        down  = false;
        left  = false;
        right = false;

        fire = false;
        speed = 3;
        angl = 0;
        health = 10;
        r = 30;
    }

    public void update() {

        anglSin = Math.sin(angl);
        anglCos = Math.cos(angl);

        if (up){
            x = x-speed * anglSin;
            y = y+speed * anglCos;
        }

        if (down){
            speedBack = (speed*-1)+1;
             x = x-speedBack * anglSin;
             y = y+speedBack * anglCos;
        }

        //Развороты
        if (left){
            angl = angl - 0.05;//*speed/10;
        }
        if (right){
            angl = angl + 0.05;//*speed/10;
        }

        //Стрельба
        if (fire) {

            //ограничение на стрельбу по одному выстрелу на танк
            boolean FireYes = true;
            for (int i=0; i< Panel.bullets.size();i++) {
                int NTank = Panel.bullets.get(i).getNumberTank();
                if (NTank == numberTank) FireYes = false;
            }
            if (FireYes && Panel.bullets.size()<2) {
            ////////////////////////////////////////////////////

                Bullet bullet = new Bullet();

                double xb = x-80 * anglSin + sdvigX;
                double yb = y+80 * anglCos + sdvigY;

                bullet.setXY(xb, yb);
                //bullet.setXY(x + sdvigX, y + sdvigY);
                bullet.setSinCos(anglSin, anglCos);

                bullet.setColor(color);
                bullet.setNumberTank(numberTank);

                Panel.bullets.add(bullet);
            }
            //fire = false;
        }

        //Запрет выезда за пределы
        if (x<Panel.limitLeft) x = Panel.limitLeft;
        if (x>Panel.WIDTH-Panel.limitRight) {
            x = Panel.WIDTH-Panel.limitRight;
        }
//        if (x<Panel.limitLeft+20) x = Panel.limitLeft+20;
//        if (x>Panel.WIDTH-Panel.limitRight-70) {
//            x = Panel.WIDTH-Panel.limitRight-70;
//        }

        if (y<-10) y = -10;
        if (y>Panel.HEIGHT-105) y = Panel.HEIGHT-105;
    }

    public void draw(Graphics2D g){

        //СЕРЫЕ НАДПИСИ В ОБРАМЛЕНИИ
        int xPos=50;
        if (rightPosition) {
            xPos = Panel.WIDTH - 250;
        }
//        g.setColor(Color.DARK_GRAY);
//        g.fillRect(xPos ,1,xPos+100,5);

        g.setColor(Color.ORANGE);
        g.setFont(new Font("Arial", Font.BOLD,14));
//        ((Graphics2D) g).drawString("X = "+x,xPos + 15,25);
//        ((Graphics2D) g).drawString("Y = "+y,xPos + 15,50);
//        ((Graphics2D) g).drawString("Angle = "+angl,xPos + 15,75);

        ((Graphics2D) g).drawString("Танк № "+numberTank+"  Жизни: "+health,xPos + 15,20);


        //Вращение танка
        AffineTransform origXform;
        origXform = g.getTransform();
        AffineTransform newXform = (AffineTransform)(origXform.clone());
        newXform.rotate(angl,x+sdvigX,y+sdvigY);
        g.setTransform(newXform);
        g.drawImage(img,(int)x,(int)y,null); //paint
        g.setTransform(origXform);
        //g.setBackground(Color.BLUE);
    }

    //СЕТЕРЫ
    public static void setControl(boolean up,boolean down, boolean left, boolean right, boolean fire) {
        PlayerTank.up       = up;
        PlayerTank.down     = down;
        PlayerTank.left     = left;
        PlayerTank.right    = right;

        PlayerTank.fire     = fire;
    }

    public void resetControrl(){
        up    = false;
        down  = false;
        left  = false;
        right = false;

        fire = false;
        //speed = 3;
        //angl = 0;
        health = 10;
        //r = 30;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setXYAngle(double x, double y, double angl ){
        this.x = x;
        this.y = y;
        this.angl = angl;
    }

    public void setXYAngle(double x,double y){
        this.x = x;
        this.y = y;
    }

    public void setXYAngle(double x){
        this.x = x;
    }

    public void setPanelPositionRight(boolean right){
        this.rightPosition = right;

    }

    public void setColorBullets(Color color) {
        this.color = color;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setNumberTank(int i) {
        this.numberTank = i;
    }


    //ГЕТЕРЫ
    public double getX() {return x; }
    public double getY() {return y; }
    public int getR()    {return r; }

    public double getXcenter() { return x+sdvigX;}

    public double getYcenter() { return y+sdvigY;}

    public double getHealth() { return health;}

    public void hit() {
        health--;
    }

    public double getNumberTank() { return numberTank;}

    public int getSpeed() {
        return speed;
    }

    //Столкновение и отброс танка
    public void collision(double qx, double qy) {
        x = x+(x-qx)/4;
        y = y+(y-qy)/4;
    }
}
