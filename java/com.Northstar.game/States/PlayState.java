package com.Northstar.game.States;

import com.Northstar.game.Util.KeyHandler;
import com.Northstar.game.Util.MouseHandler;
import com.Northstar.game.States.GameState;
import com.Northstar.game.States.GameStateManager;

import java.awt.*;

public class PlayState extends GameState {

    private Font font;

    public PlayState(GameStateManager gsm){
        super(gsm);
        font = new Font("/Resources/Font");
    }

    public void update(){};
    public void input(MouseHandler mouse, KeyHandler key){
        key.escape.tick();
        key.f1.tick();
        key.enter.tick();
/*
        if(!gsm.isStateActive(GameStateManager.PAUSE)) {
            if(cam.getTarget() == player) {
                player.input(mouse, key);
            }
            cam.input(mouse, key);

            if(key.f1.clicked) {
                if(gsm.isStateActive(GameStateManager.EDIT)) {
                    gsm.pop(GameStateManager.EDIT);
                    cam.target(player);
                } else {
                    gsm.add(GameStateManager.EDIT);
                    cam.target(null);
                }
            }

            if(key.enter.clicked) {
                System.out.println(aabbTree.toString());
                System.out.println(gameObject.toString());
            }

            pui.input(mouse, key);
        } else if(gsm.isStateActive(GameStateManager.EDIT)) {
            gsm.pop(GameStateManager.EDIT);
            cam.target(player);
        }

        if (key.escape.clicked) {
            if(gsm.isStateActive(GameStateManager.PAUSE)) {
                gsm.pop(GameStateManager.PAUSE);
            } else {
                gsm.add(GameStateManager.PAUSE);
            }
        }*/
    };
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect(100,100,100,100);
    }
}
