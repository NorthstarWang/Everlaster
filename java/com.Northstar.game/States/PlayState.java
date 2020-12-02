package com.Northstar.game.States;

import com.Northstar.game.Entity.Attack;
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
    private Attack attack;

    Rectangle rec = new Rectangle(960,960);

    public PlayState(GameStateManager gsm){
        super(gsm);
        attack = new Attack(new Sprite("./Resources/Entity/Attack.png"), new Vector2f(300,300),128);
        player = new Player(new Sprite("./Resources/Entity/Movement.png"), new Vector2f(300,300),128);
    }

    public void update(){
        player.update();
    }
    public void input(MouseHandler mouse, KeyHandler key){
        player.input(mouse,key);
    }
    public void render(Graphics2D g) {
        new FontTTF(g,"Arena",rec,"WuXia.ttf",7,100f);
        player.render(g);
    }


}
