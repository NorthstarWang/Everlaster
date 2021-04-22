package com.Northstar.game.States;

import com.Northstar.game.Util.KeyHandler;

import java.awt.Graphics2D;

public abstract class GameState {

    protected GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void update();
    public abstract void input(KeyHandler key);
    public abstract void render(Graphics2D g);

}
