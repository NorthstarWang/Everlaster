package com.Northstar.game.States;

import com.Northstar.game.Graphics.FontTTF;
import com.Northstar.game.Util.KeyHandler;

import java.awt.*;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class GameOverState extends GameState {

    Rectangle rec = new Rectangle (960,960);
    boolean retry = true;
    boolean score = false;
    boolean menu = false;
    boolean quit = false;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void update() {


    }

    @Override
    public void input(KeyHandler key) throws FileNotFoundException, URISyntaxException {
        key.attack.tick();
        key.enter.tick();
        if (key.attack.clicked){
            if(retry){
                retry = false;
                menu = true;
            }else if(score){
                score = false;
                quit = true;
            }else if(menu){
                menu = false;
                score = true;
            }else if(quit){
                quit = false;
                retry = true;
            }
        }
        if (key.enter.clicked){
            if(retry){
                //if choose retry
                gsm.addAndpop(GameStateManager.PLAY, GameStateManager.GAMEOVER);
            }else if(menu){
                //go to menu game state
                gsm.addAndpop(GameStateManager.MENU, GameStateManager.GAMEOVER);
            }else if(score){
                //go to score board
                gsm.addAndpop(GameStateManager.SCORE, GameStateManager.GAMEOVER);
            }else if(quit){
                System.exit(0);
            }
        }
    }

    @Override
    public void render(Graphics2D g) {

        new FontTTF(g,"",rec,"WuXia.ttf",7,20f);
        //render button background with white
        g.setColor(Color.white);
        g.fillRoundRect(405,295,150,50,10,10);
        g.fillRoundRect(405,385,150,50,10,10);
        g.fillRoundRect(405,475,150,50,10,10);
        g.fillRoundRect(405,565,150,50,10,10);

        g.setColor(Color.black);
        if(retry){
            g.drawString("Quit",460,595);
            g.drawString("Menu",460,415);
            g.drawString("Score",455,505);
            g.drawRoundRect(405,385,150,50,20,10);
            g.drawRoundRect(405,475,150,50,20,10);
            g.drawRoundRect(405,565,150,50,20,10);

            new FontTTF(g,"",rec,"WuXia.ttf",7,25f);
            g.drawString("Retry",450,325);
        }else if(score){
            g.drawString("Quit",460,595);
            g.drawString("Retry",455,325);
            g.drawString("Menu",460,415);

            g.drawRoundRect(405,295,150,50,20,10);
            g.drawRoundRect(405,385,150,50,20,10);
            g.drawRoundRect(405,565,150,50,20,10);

            new FontTTF(g,"",rec,"WuXia.ttf",7,25f);
            g.drawString("Score",450,505);
        }else if(menu){

            g.drawString("Quit",460,595);
            g.drawString("Retry",455,325);
            g.drawString("Score",455,505);

            g.drawRoundRect(405,295,150,50,20,10);
            g.drawRoundRect(405,475,150,50,20,10);
            g.drawRoundRect(405,565,150,50,20,10);

            new FontTTF(g,"",rec,"WuXia.ttf",7,25f);
            g.drawString("Menu",455,415);
        }else if(quit){
            g.drawString("Retry",455,325);
            g.drawString("Menu",460,415);
            g.drawString("Score",455,505);

            g.drawRoundRect(405,295,150,50,20,10);
            g.drawRoundRect(405,385,150,50,20,10);
            g.drawRoundRect(405,475,150,50,20,10);

            new FontTTF(g,"",rec,"WuXia.ttf",7,25f);
            g.drawString("Quit",455,595);
        }
    }
}
