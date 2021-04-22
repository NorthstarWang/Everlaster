package com.Northstar.game.States;

import com.Northstar.game.Entity.Attack;
import com.Northstar.game.Entity.Enemy;
import com.Northstar.game.Entity.Player;
import com.Northstar.game.Graphics.Sprite;
import com.Northstar.game.Util.KeyHandler;
import com.Northstar.game.Util.Vector2f;

import com.Northstar.game.Graphics.FontTTF;

import java.awt.*;

public class PlayState extends GameState {

    private Player player;
    private Attack attack;
    private Enemy enemy;

    Rectangle rec = new Rectangle (960,960);

    public PlayState(GameStateManager gsm){
        //Load sprite sheet in game
        super(gsm);
        attack = new Attack(new Sprite("./Resources/Entity/Attack.png"), new Vector2f(300,300),128);
        player = new Player(new Sprite("./Resources/Entity/Movement.png"), new Vector2f(300,300),128);
        enemy = new Enemy(new Sprite("./Resources/Entity/Enemy.png"), new Vector2f(300,300),128);
    }

    public void update(){
        //Update Dimensions
        player.update(enemy);
        enemy.update(player);
    }
    public void input(KeyHandler key){
        //Read key input
        player.input(key);
    }
    public void render(Graphics2D g) {
        //Add font word onto background image
        new FontTTF(g,"Everlaster",rec,"WuXia.ttf",7,80f);
        player.render(g);
        enemy.render(g);
    }


}
