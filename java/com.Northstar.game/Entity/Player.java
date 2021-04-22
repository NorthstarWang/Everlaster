package com.Northstar.game.Entity;

import com.Northstar.game.Graphics.Sprite;
import com.Northstar.game.Util.KeyHandler;
import com.Northstar.game.Util.Vector2f;

import java.awt.*;

public class Player extends Entity {
    //Edge of the map
    private final int[] map_edge = {-40,870,-60,830};

    public Player(Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);

        setIdleAnimation(0,10);

        acc = 2f;
        maxSpeed = 3f;
        maxDash = 6f;

        bounds.setWidth(30);
        bounds.setHeight(90);

        bounds.setxOffset(0);
        bounds.setyOffset(0);
    }

    public void update() {
        //Change of position in map due to motion
        if(!shift&&attack){
            super.update_attack();
        }else{
            super.update();
        }

        if(attack&&!shift){
            //if attack button is pressed, no moving allowed and attack are not allowed while dashing
            dy = 0;
            dx = 0;
        }else{
            move();
        }

        System.out.println(pos.x+" "+pos.y);

        //Limit range of movement(can only move within window)
        if(pos.x+dx>map_edge[0]&&pos.x+dx<map_edge[1]){
            pos.x += dx;
        }
        if(pos.y+dy>map_edge[2]&&pos.y+dy<map_edge[3]){
            pos.y += dy;
        }
    }

    private void move() {
        //Movement logic with run and direction motion
        if (up) {
            if (shift) {
                dy -= acc;
                if (dy < -maxDash) {
                    dy = -maxDash;
                }
            } else {
                dy -= acc;
                if (dy < -maxSpeed) {
                    dy = -maxSpeed;
                }
            }

        } else {
            if (dy < 0) {
                dy += deacc;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }
        if (down) {
            if (shift) {
                dy += acc;
                if (dy > -maxDash) {
                    dy = maxDash;
                }
            } else {
                dy += acc;
                if (dy > maxSpeed) {
                    dy = maxSpeed;
                }
            }
        } else {
            if (dy > 0) {
                dy -= deacc;
                if (dy < 0) {
                    dy = 0;
                }
            }
        }
        if (left) {
            if (shift) {
                dx -= acc;
                if (dx < -maxDash) {
                    dx = -maxDash;
                }
            } else {
                dx -= acc;
                if (dx < -maxSpeed) {
                    dx = -maxSpeed;
                }
            }
        } else {
            if (dx < 0) {
                dx += deacc;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }
        if (right) {
            if (shift) {
                dx += acc;
                if (dx > maxDash) {
                    dx = maxDash;
                }
            } else {
                dx += acc;
                if (dx > maxSpeed) {
                    dx = maxSpeed;
                }
            }
        } else {
            if (dx > 0) {
                dx -= deacc;
                if (dx < 0) {
                    dx = 0;
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        //render player sprite sheet
        g.drawImage(ani.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
        //render attack animation, if space button(attack button) pressed, attack
        if(!shift&&attack){
            g.setColor(Color.RED);
            g.drawRect((int)(hitBounds.getPos().x + hitBounds.getxOffset()),(int)(hitBounds.getPos().y + hitBounds.getyOffset()),(int)hitBounds.getWidth(),(int)(hitBounds.getHeight()));
        }
    }

    public void input(KeyHandler key) {
        //Motion controller
        up = key.up.down;
        down = key.down.down;
        left = key.left.down;
        right = key.right.down;
        shift = key.shift.down;

        //Action controller
        attack = key.attack.down;
        pause = key.escape.down;
        enter = key.enter.down;

        idle = !key.isKeypressed;
    }
}
