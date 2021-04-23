package com.Northstar.game.States;

import com.Northstar.game.GamePanel;
import com.Northstar.game.Graphics.FontTTF;
import com.Northstar.game.Util.KeyHandler;
import com.Northstar.game.Util.Vector2f;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class PauseState extends GameState {

    boolean resume = true;
    boolean menu = false;
    Rectangle rec = new Rectangle (960,960);

    public PauseState(GameStateManager gsm) {
        super(gsm);


    }

    @Override
    public void update() {

    }

    @Override
    public void input(KeyHandler key) {
        key.attack.tick();
        key.enter.tick();
        if (key.attack.clicked){
            resume = !resume;
            menu = !menu;
        }
        if (key.enter.clicked){
            if(resume){
                //if choose resume
                gsm.pop(GameStateManager.PAUSE);
            }else if(menu){
                //go to menu game state
                gsm.addAndpop(GameStateManager.MENU,GameStateManager.PLAY);
                gsm.pop(GameStateManager.PAUSE);
            }
        }
    }

    @Override
    public void render(Graphics2D g) {

        new FontTTF(g,"",rec,"WuXia.ttf",7,20f);

        //render button background with white
        g.setColor(Color.white);
        g.fillRoundRect(405,475,150,50,10,10);
        g.fillRoundRect(405,385,150,50,10,10);

        g.setColor(Color.black);

        if(resume){
            //enlarge selected button label
            g.drawString("Menu",455,505);

            //render enlarged text
            new FontTTF(g,"",rec,"WuXia.ttf",7,25f);
            g.drawRoundRect(405,475,150,50,20,10);
            g.drawString("Resume",440,415);

        }else if(menu){
            g.drawString("Resume",445,415);
            //render enlarged text
            new FontTTF(g,"",rec,"WuXia.ttf",7,25f);
            g.drawRoundRect(405,385,150,50,20,10);
            //enlarge selected button label
            g.drawString("Menu",450,505);
        }


    }
}
