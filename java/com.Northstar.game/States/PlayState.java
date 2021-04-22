package com.Northstar.game.States;

import com.Northstar.game.Entity.Enemy;
import com.Northstar.game.Entity.Player;
import com.Northstar.game.Graphics.Sprite;
import com.Northstar.game.Util.KeyHandler;
import com.Northstar.game.Util.Vector2f;

import com.Northstar.game.Graphics.FontTTF;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class PlayState extends GameState {

    private Player player;
    private List<Enemy> enemyList;
    public int EnemyNumber = 1;

    Rectangle rec = new Rectangle (960,960);

    public PlayState(GameStateManager gsm){
        //Load sprite sheet in game
        super(gsm);
        player = new Player(new Sprite("./Resources/Entity/Movement.png"), new Vector2f(300,300),128);
        enemyList = new Vector<Enemy>();
        for (int i = 0; i < EnemyNumber; i++) {
            enemyList.add(new Enemy(new Sprite("./Resources/Entity/Enemy.png"), new Vector2f(randowX(),randowY()),128));
        }
    }

    public int randowX(){
        Random rand = new Random();
        int randomNum1 = rand.nextInt(101) + 870;
        int randomNum2 = -40-rand.nextInt(101);
        //Randomize an integer that determine X position to spawn enemy;
        return  rand.nextBoolean() ?randomNum1:randomNum2;
    }

    public int randowY(){
        Random rand = new Random();
        int randomNum1 = rand.nextInt(101) + 830;
        int randomNum2 = -60-rand.nextInt(101);
        //Randomize an integer that determine Y position to spawn enemy;
        return  rand.nextBoolean() ?randomNum1:randomNum2;
    }

    public void update(){
        //Update Dimensions
        player.update();
        for (Enemy enemy :
                enemyList) {
            enemy.update(player);
        }
    }
    public void input(KeyHandler key){
        //Read key input
        player.input(key);
    }
    public void render(Graphics2D g) {
        //Add font word onto background image
        new FontTTF(g,"Everlaster",rec,"WuXia.ttf",7,80f);
        player.render(g);
        for (Enemy enemy :
                enemyList) {
            enemy.render(g);
        }
    }


}
