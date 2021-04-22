package com.Northstar.game.Entity;

import com.Northstar.game.Graphics.Sprite;
import com.Northstar.game.States.PlayState;
import com.Northstar.game.Util.AABB;
import com.Northstar.game.Util.Vector2f;

import java.awt.*;

public class Enemy extends Entity{

    private final int UP = 4;
    private final int DOWN = 1;
    private final int RIGHT = 3;
    private final int LEFT = 2;
    private final int IDLE = 0;

    public Enemy(Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
        setEnemyAnimation(0,10);

        acc = 1f;
        maxSpeed = 2f;

        bounds.setWidth(30);
        bounds.setHeight(90);
        bounds.setxOffset(0);
        bounds.setyOffset(0);
    }

    private boolean checkContact(Player player){
        //If the tile of enemy and player contact, return true, else false
        return getBounds().collides(player.getBounds());
    }

    private void move(Player player) {
        //Movement logic
        //The enemy will follow the player position

        if (pos.y>player.pos.y) {
                dy -= acc;
                if (dy < -maxSpeed) {
                    dy = -maxSpeed;
                }
        } else {
            if (dy < 0) {
                dy += deacc;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }
        if (pos.y<player.pos.y) {
                dy += acc;
                if (dy > maxSpeed) {
                    dy = maxSpeed;
                }
        } else {
            if (dy > 0) {
                dy -= deacc;
                if (dy < 0) {
                    dy = 0;
                }
            }
        }
        if (pos.x>player.pos.x) {
                dx -= acc;
                if (dx < -maxSpeed) {
                    dx = -maxSpeed;
                }
        } else {
            if (dx < 0) {
                dx += deacc;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }
        if (pos.x<player.pos.x) {
                dx += acc;
                if (dx > maxSpeed) {
                    dx = maxSpeed;
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

    public boolean checkKilled(Player player){
        //if player's attack hit bound hit enemy, enemy dead, return true
        return getBounds().collides(player.hitBounds)&&player.getAnimation().isAttack;
    }

    public void checkDirection(Player player){
        //Check which way the enemy is going and thus find out the sprite direction that should display
        down = false;
        up = false;
        right = false;
        left = false;

        //Find distance between player and enemy

        float x_distance = player.getBounds().getPos().x - getBounds().getPos().x;
        float y_distance = player.getBounds().getPos().y - getBounds().getPos().y;

        //Player is under enemy in map
        if(Math.abs(x_distance)<Math.abs(y_distance)){
            if(y_distance>0){
                down = true;
            }else{
                up = true;
            }
        }else{
            if(x_distance<0){
                left = true;
            }else{
                right = true;
            }
        }
    }

    @Override
    public void animate() {
        //Play animations according to motion
        if(up){
            if ((currentAnimation != UP || ani.getDelay() == -1)) {
                setEnemyAnimation(UP, 5);
            }
        }else if(down){
            if ((currentAnimation != DOWN || ani.getDelay() == -1)) {
                setEnemyAnimation(DOWN, 5);
            }
        }else if(left){
            if ((currentAnimation != LEFT || ani.getDelay() == -1)) {
                setEnemyAnimation(LEFT, 5);
            }
        }else if(right){
            if ((currentAnimation != RIGHT || ani.getDelay() == -1)) {
                setEnemyAnimation(RIGHT, 5);
            }
        }else if(idle){
            if ((currentAnimation != IDLE || ani.getDelay() == -1)) {
                setEnemyAnimation(IDLE, 10);
            }
        }

    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(ani.getImage(),(int)(pos.x),(int)(pos.y),size,size,null);
    }

    public int update(Player player) {
        checkDirection(player);
        animate();
        ani.update();
        move(player);

        //if not contact yet, continue moving until contact player
        if(!checkContact(player)){
            if(checkKilled(player)){
                //if enemy dead, return 1
                return 1;
            }
            getBounds().getPos().x+=dx;
            getBounds().getPos().y+=dy;
            return 0;
        } else{
            //if enemy should kill player, return 2
            return 2;
        }
    }
}
