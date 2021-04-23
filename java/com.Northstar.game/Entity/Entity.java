package com.Northstar.game.Entity;

import com.Northstar.game.Graphics.Animation;
import com.Northstar.game.Graphics.Sprite;
import com.Northstar.game.Util.AABB;
import com.Northstar.game.Util.Vector2f;

import java.awt.*;

public abstract class Entity {

    private final int UP = 4;
    private final int DOWN = 1;
    private final int RIGHT = 3;
    private final int LEFT = 2;
    private final int IDLE = 0;
    private final int SHIFT_LEFT = 6;
    private final int SHIFT_RIGHT = 7;
    private final int SHIFT_UP = 8;

    private final int UP_IDLE = 8;
    private final int RIGHT_IDLE = 7;
    private final int LEFT_IDLE = 6;

    private final int RIGHT_ATTACK = 0;
    private final int LEFT_ATTACK = 2;
    private final int UP_ATTACK = 4;
    private final int DOWN_ATTACK = 6;
    public boolean attack = false;
    public AABB hitBounds;
    public AABB bounds;
    protected Animation ani;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;
    protected int currentAnimation;
    protected int currentAttackAnimation = -1;
    protected boolean up = false;
    protected boolean down = false;
    protected boolean right = false;
    protected boolean left = false;
    protected boolean shift = false;
    protected boolean pause = false;
    protected boolean enter = false;
    protected boolean idle = true;
    protected int attackSpeed;
    protected int attackDuration;
    protected float dx;
    protected float dy;
    protected float maxSpeed = 3f;
    protected float maxDash = 6f;
    protected float acc = 2.5f;
    protected float deacc = 1f;

    public Entity(Sprite sprite, Vector2f origin, int size) {
        //Load constructor
        this.sprite = sprite;
        pos = origin;
        this.size = size;

        bounds = new AABB(origin, size, size);
        hitBounds = new AABB(origin, size, size);
        hitBounds.setyOffset(size / 2);
        hitBounds.setxOffset(0);

        ani = new Animation();
    }

    //Mutator methods
    public void setAnimation(int i, int delay) {
        currentAnimation = i;
        ani.setFrames(i);
        ani.setDelay(delay);
        currentAttackAnimation = -1;
    }

    public void setIdleAnimation(int i, int delay) {
        currentAnimation = i;
        ani.setIdleFrames(i);
        ani.setDelay(delay);
        currentAttackAnimation = -1;
    }

    public void setIdleAnimationWithOneFrame(int i, int delay) {
        currentAnimation = i;
        int n = 0;
        switch (i) {
            case UP: {
                n = UP_IDLE;
                break;
            }
            case RIGHT: {
                n = RIGHT_IDLE;
                break;
            }
            case LEFT: {
                n = LEFT_IDLE;
                break;
            }
        }
        ani.setOneFrames(n);
        ani.setDelay(delay);
        currentAttackAnimation = -1;
    }

    //Since the attack png has separated each animation in two rows, read differently
    public void setAttackAnimation(int i, int delay) {
        currentAttackAnimation = i;
        ani.setAttackFrames(i);
        ani.setDelay(delay);
    }

    public void setEnemyAnimation(int i, int delay) {
        currentAnimation = i;
        ani.setEnemyFrames(i);
        ani.setDelay(delay);
    }

    public void attack_animate() {
        //If there is no animation played yet
        if (currentAttackAnimation == -1) {
            if (currentAnimation == UP) {
                //if face up
                setAttackAnimation(UP_ATTACK, 5);
            } else if (currentAnimation == DOWN || currentAnimation == IDLE) {
                //if face down
                setAttackAnimation(DOWN_ATTACK, 5);
            } else if (currentAnimation == RIGHT) {
                //if face right
                setAttackAnimation(RIGHT_ATTACK, 5);
            } else if (currentAnimation == LEFT) {
                //if face left
                setAttackAnimation(LEFT_ATTACK, 5);
            }
        }
    }

    public void animate() {
        currentAttackAnimation = -1;
        //Play animations according to motion
        //Normal movements are store in Movement.png
        if (up && !shift) {
            if ((currentAnimation != UP || ani.getDelay() == -1)) {
                setAnimation(UP, 5);
            }
        } else if (up) {
            if ((currentAnimation != SHIFT_UP || ani.getDelay() == -1)) {
                setIdleAnimation(SHIFT_UP, 5);
            }
        } else if (down && !shift) {
            if ((currentAnimation != DOWN || ani.getDelay() == -1)) {
                setAnimation(DOWN, 5);
            }
        } else if (down) {
            int SHIFT_DOWN = 5;
            if ((currentAnimation != SHIFT_DOWN || ani.getDelay() == -1)) {
                setIdleAnimation(SHIFT_DOWN, 5);
            }
        } else if (left && !shift) {
            if ((currentAnimation != LEFT || ani.getDelay() == -1)) {
                setAnimation(LEFT, 5);
            }
        } else if (left) {
            if ((currentAnimation != SHIFT_LEFT || ani.getDelay() == -1)) {
                setIdleAnimation(SHIFT_LEFT, 5);
            }
        } else if (right && !shift) {
            if ((currentAnimation != RIGHT || ani.getDelay() == -1)) {
                setAnimation(RIGHT, 5);
            }
        } else if (right) {
            if ((currentAnimation != SHIFT_RIGHT || ani.getDelay() == -1)) {
                setIdleAnimation(SHIFT_RIGHT, 5);
            }
        } else if (idle) {
            if (currentAnimation == UP) {
                setIdleAnimationWithOneFrame(UP, -1);
            } else if (currentAnimation == RIGHT) {
                setIdleAnimationWithOneFrame(RIGHT, -1);
            } else if (currentAnimation == LEFT) {
                setIdleAnimationWithOneFrame(LEFT, -1);
            } else if ((currentAnimation != IDLE || ani.getDelay() == -1)) {
                setIdleAnimation(IDLE, 10);
            }
        }

    }

    public void setHitBoxDirection() {
        //Set facing direction and attacking range, varies due to direction
        if (up) {
            hitBounds.setyOffset(-size / 2);
            hitBounds.setxOffset(0);
        } else if (down) {
            hitBounds.setyOffset(size / 2);
            hitBounds.setxOffset(0);
        } else if (left) {
            hitBounds.setxOffset(-size / 2);
            hitBounds.setyOffset(0);
        } else if (right) {
            hitBounds.setxOffset(size / 2);
            hitBounds.setyOffset(0);
        }
    }

    public void update_attack() {
        attack_animate();
        setHitBoxDirection();
        ani.update();
    }

    public void update() {
        animate();
        setHitBoxDirection();
        ani.update();
    }

    public abstract void render(Graphics2D g);

    //Accessor method
    public int getSize() {
        return size;
    }

    public void setSize(int i) {
        size = i;
    }

    public Animation getAnimation() {
        return ani;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setMaxSpeed(float f) {
        maxSpeed = f;
    }

    public void setAcc(float f) {
        acc = f;
    }

    public void setDeacc(float f) {
        deacc = f;
    }

    public AABB getBounds() {
        return bounds;
    }

}
