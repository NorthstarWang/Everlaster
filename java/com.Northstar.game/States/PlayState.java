package com.Northstar.game.States;

import com.Northstar.game.Entity.Player;
import com.Northstar.game.Graphics.Sprite;
import com.Northstar.game.Util.KeyHandler;
import com.Northstar.game.Util.MouseHandler;
import com.Northstar.game.Util.Vector2f;

import com.Northstar.game.Graphics.Font;
import com.Northstar.game.Graphics.FontTTF;

import java.awt.*;

public class PlayState extends GameState {

    private Font font;
    private Player player;

    Rectangle rec = new Rectangle(1280,1280);

    public PlayState(GameStateManager gsm){
        super(gsm);
        font = new Font("Font/ZeldaFont.png",16,16);
        player = new Player(new Sprite("Entity/Player.png"), new Vector2f(300,300),128);
    }

    public void update(){
        player.update();
    }
    public void input(MouseHandler mouse, KeyHandler key){
        player.input(mouse,key);
    }
    public void render(Graphics2D g) {
        new FontTTF(g,"Arena",rec,"WuXia.ttf");
        player.render(g);
    }


}
