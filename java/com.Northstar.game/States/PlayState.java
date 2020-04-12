package com.Northstar.game.States;

import com.Northstar.game.Graphics.Sprite;
import com.Northstar.game.Util.KeyHandler;
import com.Northstar.game.Util.MouseHandler;
import com.Northstar.game.Util.Vector2f;

import com.Northstar.game.Graphics.Font;

import java.awt.*;

public class PlayState extends GameState {

    private Font font;

    public PlayState(GameStateManager gsm){
        super(gsm);
        font = new Font("Font/ZeldaFont.png",16,16);
    }

    public void update(){

    };
    public void input(MouseHandler mouse, KeyHandler key){

    };
    public void render(Graphics2D g) {
        Sprite.drawArray(g,font,"Testing",new Vector2f(100,100),32,32,32,0);
    }
}
