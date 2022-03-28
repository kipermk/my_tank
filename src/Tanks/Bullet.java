package Tanks;

import java.awt.*;


public class Bullet {

    private double currentX;
    private double currentY;

    private double x;
    private double y;
    private int r;

    private double anglSin;
    private double anglCos;

    private int speed;

    private Color color;
    private int numberTank;


    public Bullet(){

        x = 0;// Panel.tank2.getX();
        y = 0;//Panel.tank2.getY();
        r = 5;

        speed = 10;
        color = Color.YELLOW;
    }

    public void update(){
        x = x-speed * anglSin;
        y = y+speed * anglCos;
    }

    //условия для удаления пули
    public boolean remove(){
       // if(x>1000) return true;
        if (Math.abs(currentX-x) > 400) return true;
        if (Math.abs(currentY-y) > 400) return true;

        return false;
    }


    public void draw(Graphics2D g){

       // System.out.println("ПУЛЯ");
        g.setColor(color);
        g.fillOval((int)x,(int)y,r,r*2);
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;

        this.currentX = x;
        this.currentY = y;
    }

    public void setSinCos(double sin, double cos) {
        this.anglSin = sin;
        this.anglCos = cos;
    }

    public void setNumberTank(int numberTank) {
        this.numberTank = numberTank;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getX(){return x;}
    public double getY(){return y;}
    public int    getR(){return r;}
    public int getNumberTank() {return numberTank;}
}
