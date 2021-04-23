package com.Northstar.game.States;

import com.Northstar.game.Graphics.FontTTF;
import com.Northstar.game.Util.KeyHandler;

import java.awt.*;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class MenuState extends GameState {

    boolean start = true;
    boolean score = false;
    boolean quit = false;
    Rectangle rec = new Rectangle(960, 960);

    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void update() {

    }

    @Override
    public void input(KeyHandler key) throws FileNotFoundException, URISyntaxException {
        key.attack.tick();
        key.enter.tick();
        if (key.attack.clicked) {
            if (start) {
                start = false;
                score = true;
            } else if (score) {
                score = false;
                quit = true;
            } else if (quit) {
                quit = false;
                start = true;
            }
        }

        if (key.enter.clicked) {
            if (start) {
                gsm.addAndpop(GameStateManager.PLAY, GameStateManager.MENU);
            } else if (quit) {
                System.exit(0);
            } else if (score) {
                gsm.addAndpop(GameStateManager.SCORE, GameStateManager.MENU);
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        new FontTTF(g, "", rec, "WuXia.ttf", 7, 20f);

        //render button background with white
        g.setColor(Color.white);
        g.fillRoundRect(405, 475, 150, 50, 10, 10);
        g.fillRoundRect(405, 385, 150, 50, 10, 10);
        g.fillRoundRect(405, 565, 150, 50, 10, 10);

        g.setColor(Color.black);

        if (start) {
            //enlarge selected button label
            g.drawString("Quit", 460, 595);
            g.drawString("Score", 455, 505);
            //render enlarged text
            new FontTTF(g, "", rec, "WuXia.ttf", 7, 25f);
            g.drawRoundRect(405, 475, 150, 50, 20, 10);
            g.drawRoundRect(405, 565, 150, 50, 20, 10);
            g.drawString("Start", 450, 415);

        } else if (score) {

            g.drawString("Quit", 460, 595);
            g.drawString("Start", 455, 415);
            //render enlarged text
            new FontTTF(g, "", rec, "WuXia.ttf", 7, 25f);
            g.drawRoundRect(405, 385, 150, 50, 20, 10);
            g.drawRoundRect(405, 565, 150, 50, 20, 10);
            //enlarge selected button label
            g.drawString("Score", 445, 505);
        } else if (quit) {

            g.drawString("Score", 455, 505);
            g.drawString("Start", 455, 415);
            //render enlarged text
            new FontTTF(g, "", rec, "WuXia.ttf", 7, 25f);
            g.drawRoundRect(405, 385, 150, 50, 20, 10);
            g.drawRoundRect(405, 475, 150, 50, 20, 10);
            //enlarge selected button label
            g.drawString("Quit", 455, 595);
        }


        new FontTTF(g, "", rec, "WuXia.ttf", 7, 100f);
        g.setColor(Color.white);
        g.drawString("Ever Laster", 200, 200);
    }
}
