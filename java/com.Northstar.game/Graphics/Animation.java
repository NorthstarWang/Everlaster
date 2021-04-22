package com.Northstar.game.Graphics;

import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] frames;
    private int currentFrame;
    private int numFrames;

    private int count;
    private int delay;
    private int row = 0;
    private int attack_row;
    public boolean isAttack;

    private boolean attack;

    private int timesPlayed;

    private final Sprite attack_sprite = new Sprite("./Resources/Entity/Attack.png");
    private final Sprite sprite = new Sprite("./Resources/Entity/Movement.png");
    private final Sprite enemy = new Sprite("./Resources/Entity/Enemy.png");

    public Animation() {
        timesPlayed = 0;
        attack = false;
        currentFrame = 0;
        count = 0;

    }

    public void setFrames(int i) {
        attack = false;
        //Load specific frames in Entity
        this.frames = sprite.getSpriteArray(i);
        currentFrame = 0;
        count = 0;
        timesPlayed = 0;
        delay = 2;
        numFrames = frames.length;
    }

    public void setEnemyFrames(int i) {
        attack = false;
        //Load specific frames in Entity
        this.frames = enemy.getSpriteArray(i);
        currentFrame = 0;
        count = 0;
        timesPlayed = 0;
        delay = 2;
        numFrames = frames.length;
    }

    public void setOneFrames(int i) {
        attack = false;
        //Load specific frames in Entity
        this.frames = sprite.getSpriteArray(i);
        currentFrame = 7;
        count = 0;
        timesPlayed = 0;
        delay = -1;
        numFrames = 7;
    }

    public void setIdleFrames(int i) {
        attack = false;
        //Load character frames when idle(stand still)
        this.frames = sprite.getSpriteArray(i);
        currentFrame = 0;
        count = 0;
        timesPlayed = 0;
        delay = 2;
        numFrames = 4;
    }

    public void setAttackFrames(int i) {
        attack = true;
        attack_row = i ;

        this.frames = attack_sprite.getSpriteArray(i);
        currentFrame = 0;
        count = 0;
        timesPlayed = 0;
        delay = 2;
        numFrames = 4;
    }

    //Mutator methods
    public void setDelay(int i) { delay = i; }
    public void setFrame(int i) { currentFrame = i; }
    public void setNumFrames(int i) { numFrames = i;}

    public void update() {
        isAttack=false;
        //Infinitely replay the sprite sheet so that the character animation play smoothly if delay if not set to -1
        if(attack){

            count++;
            isAttack = true;
            if(count == delay) {
                currentFrame++;
                count = 0;
            }
            if(currentFrame == numFrames) {
                if(!hasPlayedOnce()) {
                    timesPlayed++;
                    currentFrame = 0;
                    //Switch row if row is done
                    row = (row == 0) ? 1 : 0;
                    this.frames = attack_sprite.getSpriteArray(1 + attack_row);
                }else{
                    isAttack = false;
                    attack = false;
                    setIdleFrames(0);
                    setDelay(10);
                }
            }

        }else{
            if(delay == -1) return;

            count++;

            if(count == delay) {
                currentFrame++;
                count = 0;
            }
            if(currentFrame == numFrames) {
                currentFrame = 0;
                timesPlayed++;
            }
        }

    }

    //Accessor methods
    public int getDelay() { return delay; }
    public int getRow(){return row;}
    public int getFrame() { return currentFrame; }
    public int getCount() { return count; }
    public BufferedImage getImage() { return frames[currentFrame]; }
    public boolean hasPlayedOnce() { return timesPlayed > 0; }
    public boolean hasPlayed(int i) { return timesPlayed == i; }


}
