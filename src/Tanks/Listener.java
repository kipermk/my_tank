package Tanks;

import java.awt.event.*;

public class Listener implements MouseListener, KeyListener,MouseMotionListener {


   // private boolean isFiring_on1;
  //  private boolean isFiring_on2;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key==KeyEvent.VK_ENTER) Panel.enter          = true;

        if (key==KeyEvent.VK_UP)    Panel.presskeyUp1    = true;
        if (key==KeyEvent.VK_DOWN)  Panel.presskeyDown1  = true;
        if (key==KeyEvent.VK_LEFT)  Panel.presskeyLeft1  = true;
        if (key==KeyEvent.VK_RIGHT) Panel.presskeyRight1 = true;

        if (key==KeyEvent.VK_CONTROL) {
           // if (isFiring_on1)
            Panel.presskeyFire1 = true;
            //Panel.presskeyFire1 = false;
           // isFiring_on1 = false;
        }

        if (key==KeyEvent.VK_W)     Panel.presskeyUp2    = true;
        if (key==KeyEvent.VK_S)     Panel.presskeyDown2  = true;
        if (key==KeyEvent.VK_A)     Panel.presskeyLeft2  = true;
        if (key==KeyEvent.VK_D)     Panel.presskeyRight2 = true;

        if (key==KeyEvent.VK_SPACE) {
           // if (isFiring_on2) {
            Panel.presskeyFire2 = true;
          //  isFiring_on2 = false;}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key==KeyEvent.VK_ENTER) Panel.enter          = false;

        if (key==KeyEvent.VK_UP)    Panel.presskeyUp1    = false;
        if (key==KeyEvent.VK_DOWN)  Panel.presskeyDown1  = false;
        if (key==KeyEvent.VK_LEFT)  Panel.presskeyLeft1  = false;
        if (key==KeyEvent.VK_RIGHT) Panel.presskeyRight1 = false;

        if (key==KeyEvent.VK_CONTROL) {
            Panel.presskeyFire1 = false;
          //  isFiring_on1 = true;
        }



        if (key==KeyEvent.VK_W)     Panel.presskeyUp2    = false;
        if (key==KeyEvent.VK_S)     Panel.presskeyDown2  = false;
        if (key==KeyEvent.VK_A)     Panel.presskeyLeft2  = false;
        if (key==KeyEvent.VK_D)     Panel.presskeyRight2 = false;

        if (key==KeyEvent.VK_SPACE) {
            Panel.presskeyFire2 = false;
          //  isFiring_on2 = true;
        }

    }


    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }
    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
