package  com.Northstar.game.Util;

import com.Northstar.game.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements KeyListener{
    //Call corresponding function according to key pressed
    public static List<Key> keys = new ArrayList<>();
    public boolean isKeypressed = false;

    public class Key{
        public int presses;
        public boolean down;
        public Key() {
            keys.add(this);
        }

        public void toggle(boolean pressed) {
            //If pressed
            if(pressed != down) {
                down = pressed;
            }
            if(pressed) {
                presses++;
            }
        }
    }

    //Define control keys
    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key attack = new Key();
    public Key enter = new Key();
    public Key escape = new Key();
    public Key shift = new Key();

    public KeyHandler(GamePanel game) {
        game.addKeyListener(this);
    }

    public void toggle(KeyEvent e, boolean pressed) {

        //Define key presses action
        if(e.getKeyCode() == KeyEvent.VK_SPACE) attack.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_S) down.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_ENTER) enter.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) escape.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_SHIFT) shift.toggle(pressed);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Activate toggle when any key press
        isKeypressed = true;
        toggle(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Deactivate toggle when any key release
        isKeypressed = false;
        toggle(e, false);
    }
}
