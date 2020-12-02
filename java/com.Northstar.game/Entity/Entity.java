package com.Northstar.game.Entity;

import com.Northstar.game.Graphics.Animation;
import com.Northstar.game.Graphics.Sprite;
import com.Northstar.game.Util.AABB;
import com.Northstar.game.Util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    private final int UP = 4;
    private final int DOWN = 1;
    private final int RIGHT = 3;
    private final int LEFT = 2;
    private final int IDLE = 0;
    private final int SHIFT_DOWN = 5;
    private final int SHIFT_LEFT = 6;
    private final int SHIFT_RIGHT = 7;
    private final int SHIFT_UP = 8;

    protected Animation ani;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;
    protected int currentAnimation;

    protected boolean up = false;
    protected boolean down = false;
    protected boolean right = false;
    protected boolean left = false;
    protected boolean shift = false;
    protected boolean attack = false;
    protected boolean idle = true;
    protected int attackSpeed;
    protected int attackDuration;

    protected float dx;
    protected float dy;

    protected float maxSpeed = 3f;
    protected float maxDash = 6f;
    protected float acc =2.5f;
    protected float deacc = 1f;

    protected AABB hitBounds;
    protected AABB bounds;

    public Entity(Sprite sprite, Vector2f origin, int size) {
        this.sprite = sprite;
        pos = origin;
        this.size  = size;

        bounds = new AABB(origin,size,size);
        hitBounds = new AABB(new Vector2f(origin.x + (size / 2),origin.y),size,size);

        ani = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
    }

    public void setAnimation(int i, BufferedImage[] frames, int delay) {
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    public void setIdleAnimation(int i, BufferedImage[] frames, int delay){
        currentAnimation = i;
        ani.setIdleFrames(frames);
        ani.setDelay(delay);
    }

    public void animate() {

        if (up && !shift) {
            if ((currentAnimation != UP || ani.getDelay() == -1)) {
                setAnimation(UP, sprite.getSpriteArray(UP), 5);
            }
        }else if(up && shift){
            if ((currentAnimation != SHIFT_UP || ani.getDelay() == -1)) {
                setIdleAnimation(SHIFT_UP, sprite.getSpriteArray(SHIFT_UP), 5);
            }
        }else if (down && !shift) {
            if ((currentAnimation != DOWN || ani.getDelay() == -1)) {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
            }
        }else if(down && shift){
            if ((currentAnimation != SHIFT_DOWN || ani.getDelay() == -1)) {
                setIdleAnimation(SHIFT_DOWN, sprite.getSpriteArray(SHIFT_DOWN), 5);
            }
        }else if (left && !shift) {
            if ((currentAnimation != LEFT || ani.getDelay() == -1)) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        }else if(left && shift){
            if ((currentAnimation != SHIFT_LEFT || ani.getDelay() == -1)) {
                setIdleAnimation(SHIFT_LEFT, sprite.getSpriteArray(SHIFT_LEFT), 5);
            }
        }else if (right && !shift) {
            if ((currentAnimation != RIGHT || ani.getDelay() == -1)) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        }else if(right && shift){
            if ((currentAnimation != SHIFT_RIGHT || ani.getDelay() == -1)) {
                setIdleAnimation(SHIFT_RIGHT, sprite.getSpriteArray(SHIFT_RIGHT), 5);
            }
        }else if(idle){
            if ((currentAnimation != IDLE || ani.getDelay() == -1)) {
                setIdleAnimation(IDLE, sprite.getSpriteArray(IDLE), 10);
            }
        }

    }

    private void setHitBoxDirection(){
        if (up) {
            hitBounds.setxOffset(-size/2);
            hitBounds.setyOffset(-size/2);
        } else if (down) {
            hitBounds.setyOffset(size/2);
            hitBounds.setxOffset(-size/2);
        } else if (left) {
            hitBounds.setxOffset(-size);
            hitBounds.setyOffset(0);
        } else if (right) {
            hitBounds.setxOffset(0);
            hitBounds.setyOffset(0);
        }
    }

    public void update() {
        animate();
        setHitBoxDirection();
        ani.update();
    }

    public abstract void render(Graphics2D g);

    public int getSize(){return size;}
    public Animation getAnimation(){return ani;}
    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }
    public void setSize(int i){
        size = i;
    }
    public void setMaxSpeed(float f){maxSpeed = f;}
    public void setAcc(float f){acc = f;}
    public void setDeacc(float f){deacc = f;}
    public AABB getBounds(float f){return bounds;}

}
