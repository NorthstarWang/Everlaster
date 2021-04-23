package com.Northstar.game.States;

import com.Northstar.game.Util.KeyHandler;

import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class GameState {

    protected GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void update() throws IOException, URISyntaxException;
    public abstract void input(KeyHandler key) throws FileNotFoundException, URISyntaxException;
    public abstract void render(Graphics2D g);

}
