package Tanks;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Panel extends JPanel implements ActionListener {

    public static boolean presskeyUp1;
    public static boolean presskeyDown1;
    public static boolean presskeyLeft1;
    public static boolean presskeyRight1;
    public static boolean presskeyFire1;

    public static boolean presskeyUp2;
    public static boolean presskeyDown2;
    public static boolean presskeyLeft2;
    public static boolean presskeyRight2;
    public static boolean presskeyFire2;

    public static boolean enter;


    public static int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static double winner;

    //System.out.println("Timer START");
    public static int limitLeft  = 150;
    public static int limitRight = 150;

    public static enum STATES{MENUE,PLAY,FINISH}

   // public static STATES state = STATES.PLAY;
    public static STATES state = STATES.MENUE;

    private BufferedImage image;
    private Graphics2D g;

    public static ArrayList<Bullet> bullets;

    Timer mainTimer = new Timer(30,this);

    Back back = new Back();
    public static PlayerTank tank1 = new PlayerTank();
    public static PlayerTank tank2 = new PlayerTank();

    //Временно
//    PlayerTank tank3 = new PlayerTank();
//    PlayerTank tank4 = new PlayerTank();
//    PlayerTank tank5 = new PlayerTank();
//    PlayerTank tank6 = new PlayerTank();
//    PlayerTank tank7 = new PlayerTank();


    public Panel() {
        super();
        setFocusable(true);
        requestFocus();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        addKeyListener(new Listener());

        mainTimer.start();
        System.out.println("Timer START");

        tank1.setImg(new ImageIcon("image/pl2.png").getImage());
        tank1.setXYAngle(800,250,1.60);
        tank1.setColorBullets(Color.YELLOW);
        tank1.setNumberTank(1);
        //tank1.setLimitX(150);

        tank2.setImg(new ImageIcon("image/pl1.png").getImage());
        tank2.setXYAngle(100,50,4.75);
        tank2.setColorBullets(Color.GREEN);
        tank2.setNumberTank(2);
        //tank2.setLimitX(150);

        bullets = new ArrayList<Bullet>();

    }

    public void actionPerformed(ActionEvent e) {

        if(state.equals(STATES.PLAY)){
            gameUpdate();
            gameRender();
            gameDraw();
        }

        if(state.equals(STATES.MENUE)){
            g.setColor(Color.BLACK);
            Image imge = new ImageIcon("image/Control.png").getImage();
            g.drawImage(imge,1,1,null);
            gameDraw();

            if (enter) state = STATES.PLAY;
        }

        if(state.equals(STATES.FINISH)) {

            JFrame jFrame = new JFrame("Танки");
            int result = JOptionPane.showConfirmDialog(jFrame, "Победил танк номер " + (int)winner +". Возобновить игру?");

            if (result == 0){
                state = STATES.PLAY;
//                tank1.setHealth(10);
//                tank2.setHealth(10);

            }
            else if (result == 1)
                System.exit(1);
            else {
                state = STATES.MENUE;
//                tank1.resetControrl();
//                tank2.resetControrl();
            }
        }

    }

    private void gameUpdate() {

        WIDTH   = getBounds().width;
        HEIGHT  = getBounds().height;

        tank1.setControl(presskeyUp1,presskeyDown1,presskeyLeft1,presskeyRight1,presskeyFire1);
        tank1.update();

        tank2.setControl(presskeyUp2,presskeyDown2,presskeyLeft2,presskeyRight2,presskeyFire2);
        tank2.update();

        for (int i=0; i<bullets.size();i++){

            //Движение пули
            bullets.get(i).update();

            //if (i>2) {bullets.remove(i);}
            System.out.println(presskeyFire1);

            //Удаление пилу
            if (bullets.get(i).remove()) {
                bullets.remove(i);
                i--;
            }
        }

        //Столкновение с пулями
        for (int i=0;i<bullets.size();i++){
            if (collision(i,tank1)) break;//i--;//
            if (collision(i,tank2)) break;//i--;//
        }


        //Столкновение танков
        double t1x = tank1.getXcenter();
        double t1y = tank1.getYcenter();

        double t2x = tank2.getXcenter();
        double t2y = tank2.getYcenter();

        double dx = t2x - t1x;
        double dy = t2y - t1y;

        double dist = Math.sqrt(dx*dx + dy*dy);
        //System.out.println(dist);

        if (dist < tank2.getR() + tank1.getR()){

            tank2.collision(tank1.getX(),tank1.getY());
            tank1.collision(tank2.getX(),tank2.getY());

        }

        int countLastWinner = 1;

        if (tank1.getHealth() < countLastWinner){
            winner = tank2.getNumberTank();
            state = STATES.FINISH;
        }
        if (tank2.getHealth() < countLastWinner){
            winner = tank1.getNumberTank();
            state = STATES.FINISH;
        }
    }


    private boolean collision(int i, PlayerTank tank) {

        Bullet  b = bullets.get(i);

        double bx = b.getX();
        double by = b.getY();

        double tx = tank.getXcenter();
        double ty = tank.getYcenter();

        double dx = bx - tx;
        double dy = by - ty;

        double dist = Math.sqrt(dx*dx + dy*dy);
        //System.out.println(dist);

        //попадание пули в танк
        if (dist < b.getR() + tank.getR()){
            if (b.getNumberTank() != tank.getNumberTank()){
                tank.hit();
                bullets.remove(i);
                i--;
                return true;
            }
        }
        return false;

    }

    private void gameRender() {
        back.draw(g,tank1,tank2);

        for (int i=0; i<bullets.size();i++){
            bullets.get(i).draw(g);
             // System.out.println(bullets.size());
        }

        tank1.setPanelPositionRight(true);
        tank1.draw(g);

        tank1.setPanelPositionRight(false);
        tank2.draw(g);


        Image imge = new ImageIcon("image/krisha.jpg").getImage();
        g.drawImage(imge,(int)back.getX()+50,HEIGHT-400,null);

       // Image imge2 = new ImageIcon("image/krisha.jpg").getImage();
        g.drawImage(imge, (int)back.getX()+1800,20,null);

    }

    private void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image ,0,0,null);
        g2.dispose();
    }
}
