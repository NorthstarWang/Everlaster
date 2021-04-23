package com.Northstar.game.States;

import com.Northstar.game.GamePanel;
import com.Northstar.game.Graphics.FontTTF;
import com.Northstar.game.Util.KeyHandler;
import com.Northstar.game.Util.Vector2f;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameStateManager {

    public static final int PLAY = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;
    public static final int MENU = 4;
    public static final int SCORE = 5;
    public static Vector2f map;
    public static FontTTF fontTTF;
    public int onTopState = 0;
    private GameState states[];

    public GameStateManager() throws FileNotFoundException, URISyntaxException {
        map = new Vector2f(GamePanel.width, GamePanel.height);

        states = new GameState[6];

        fontTTF = new FontTTF("WuXia.ttf", 10f);

        states[MENU] = new MenuState(this);
    }

    public boolean getstate(int state) {
        return states[state] != null;
    }

    public void pop(int state) {
        states[state] = null;
    }

    public void add(int state) throws FileNotFoundException, URISyntaxException {

        if (states[state] != null) {
            return;
        }
        if (state == PLAY) {
            states[PLAY] = new PlayState(this);
        } else if (state == MENU) {
            states[MENU] = new MenuState(this);
        } else if (state == PAUSE) {
            states[PAUSE] = new PauseState(this);
        } else if (state == GAMEOVER) {
            states[GAMEOVER] = new GameOverState(this);
        } else if (state == SCORE) {
            states[SCORE] = new ScoreState(this);
        }
    }

    public void addAndpop(int state, int remove) throws FileNotFoundException, URISyntaxException {
        add(state);
        pop(remove);
    }

    public void update() throws IOException, URISyntaxException {
        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
                states[i].update();
            }
        }
    }

    public void input(KeyHandler key) throws FileNotFoundException, URISyntaxException {
        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
                states[i].input(key);
            }
        }
    }

    public void render(Graphics2D g) {
        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
                states[i].render(g);
            }
        }
    }
}
