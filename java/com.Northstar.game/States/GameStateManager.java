package com.Northstar.game.States;


import com.Northstar.game.GamePanel;
import com.Northstar.game.Graphics.FontTTF;
import com.Northstar.game.Util.KeyHandler;
import com.Northstar.game.Util.Vector2f;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager {

    private GameState states[];

    public static Vector2f map;

    public static final int PLAY = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;
    public static final int MENU = 4;

    public int onTopState = 0;

    public static FontTTF fontTTF;

    public GameStateManager(){
        map = new Vector2f(GamePanel.width, GamePanel.height);

        states = new GameState[4];

        fontTTF = new FontTTF("WuXia.ttf",10f);

        states[PLAY]= new PlayState(this);
    }

    public boolean getstate(int state){
        return states[state]!=null;
    }

    public void pop(int state) {
        states[state] = null;
    }

    public void add(int state) {

        if(states[state]!= null){return;}
            if (state == PLAY) {
                states[PLAY] = new PlayState(this);
            }else if(state == MENU){
                states[MENU] = new MenuState(this);
            }
            else if (state == PAUSE) {
                states[PAUSE] = new PauseState(this);
            }
            else if (state == GAMEOVER) {
                states[GAMEOVER] = new GameOverState(this);
            }
    }

    public void addAndpop(int state) {
        addAndpop(state,0);
    }

    public void addAndpop(int state, int remove){
        add(state);
        pop(remove);
    }

    public void update() {
        for (int i = 0; i < states.length; i++) {
            if(states[i]!=null){
                states[i].update();
            }
        }
    }

    public void input(KeyHandler key) {
        for (int i = 0; i < states.length; i++) {
            if(states[i]!=null) {
                states[i].input(key);
            }
        }
    }

    public void render(Graphics2D g) {
        for (int i = 0; i < states.length; i++) {
            if(states[i]!=null) {
                states[i].render(g);
            }
        }
    }
}
