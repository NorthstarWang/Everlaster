package com.Northstar.game.Entity;

import com.Northstar.game.Graphics.Sprite;
import com.Northstar.game.Util.KeyHandler;
import com.Northstar.game.Util.MouseHandler;
import com.Northstar.game.Util.Vector2f;

import java.awt.*;

public class Player extends Entity{
    public Player(Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
    }

    public void update(){
        super.update();
        move();
        pos.x += dx;
        pos.y += dy;
    }

    private void move() {
        if(up){
            dy -= acc;
            if(dy < -maxSpeed){
                dy = -maxSpeed;
            }
        }else {
            if(dy < 0){
                dy+= deacc;
                if(dy>0){
                    dy=0;
                }
            }
        }
        if(down){
            dy += acc;
            if(dy > maxSpeed){
                dy = maxSpeed;
            }
        }else {
            if(dy >0){
                dy-= deacc;
                if(dy<0){
                    dy=0;
                }
            }
        }
        if(left){
            dx -= acc;
            if(dx < -maxSpeed){
                dx = -maxSpeed;
            }
        }else {
            if(dx <0){
                dx+= deacc;
                if(dx>0){
                    dx=0;
                }
            }
        }
        if(right){
            dx += acc;
            if(dx > maxSpeed){
                dx = maxSpeed;
            }
        }else {
            if(dx > 0){
                dx-= deacc;
                if(dx<0){
                    dx=0;
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(ani.getImage(),(int)(pos.x),(int)(pos.y),size,size,null);
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        if(mouse.getButton()==1){
            System.out.println("Play: " + pos.x + " , "+ pos.y);
        }


            if(key.up.down) {
                up = true;
            } else {
                up = false;
            }
            if(key.down.down) {
                down = true;
            } else {
                down = false;
            }
            if(key.left.down) {
                left = true;
            } else {
                left = false;
            }
            if(key.right.down) {
                right = true;
            } else {
                right = false;
            }

            if(key.attack.down) {
                attack = true;
            } else {
                    attack = false;
            }

            if(!key.isKeypressed){
                idle = true;
            }else{
                idle = false;
            }
    }
}
