package com.Northstar.game.States;

import com.Northstar.game.Entity.Enemy;
import com.Northstar.game.Entity.Player;
import com.Northstar.game.GamePanel;
import com.Northstar.game.Graphics.FontTTF;
import com.Northstar.game.Graphics.Sprite;
import com.Northstar.game.Util.KeyHandler;
import com.Northstar.game.Util.Vector2f;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class PlayState extends GameState {

    public List<Enemy> enemyList;
    public int EnemyNumber = 1;
    public int score = 0;
    public int life = 5;
    File fileName = new File("./Resources/score.txt");
    Rectangle rec = new Rectangle(960, 960);
    private Player player;

    public PlayState(GameStateManager gsm) throws FileNotFoundException {
        //Load sprite sheet in game
        super(gsm);
        player = new Player(new Sprite("./Resources/Entity/Movement.png"), new Vector2f(416, 416), 128);
        enemyList = new Vector<Enemy>();
        for (int i = 0; i < EnemyNumber; i++) {
            enemyList.add(new Enemy(new Sprite("./Resources/Entity/Enemy.png"), new Vector2f(randowX(), randowY()), 128));
        }
    }

    public void removeEnemy(Enemy enemy) {
        enemyList.remove(enemy);
    }

    public int randowX() {
        Random rand = new Random();
        int randomNum1 = rand.nextInt(101) + 870;
        int randomNum2 = -40 - rand.nextInt(101);
        //Randomize an integer that determine X position to spawn enemy;
        return rand.nextBoolean() ? randomNum1 : randomNum2;
    }

    public int randowY() {
        Random rand = new Random();
        int randomNum1 = rand.nextInt(101) + 830;
        int randomNum2 = -60 - rand.nextInt(101);
        //Randomize an integer that determine Y position to spawn enemy;
        return rand.nextBoolean() ? randomNum1 : randomNum2;
    }

    public void update() throws IOException, URISyntaxException {
        if (life > 0) {
            //if still alive
            if (!gsm.getstate(GameStateManager.PAUSE)) {
                //If the game is not pause
                //Update Dimensions
                player.update();
                if (enemyList.size() > 0) {
                    //if there is still enemy in list(alive)
                    for (int i = enemyList.size() - 1; i >= 0; i--) {
                        Enemy enemy = enemyList.get(i);
                        int n = enemy.update(player);
                        if (n == 1) {
                            //enemy destroy
                            enemyList.remove(enemy);
                            score++;
                        } else if (n == 2) {
                            //player get contact by enemy, enemy self destruct, player life decrease
                            enemyList.remove(enemy);
                            life--;
                        }

                    }
                } else {
                    //if no enemy alive, increase difficulty
                    EnemyNumber++;
                    for (int i = 0; i < EnemyNumber; i++) {
                        enemyList.add(new Enemy(new Sprite("./Resources/Entity/Enemy.png"), new Vector2f(randowX(), randowY()), 128));
                    }
                }
            }
        } else {
            //if dead, proceed to game over state, record score in text file
            FileWriter fileWriter = new FileWriter(fileName, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(score);
            printWriter.close();
            gsm.addAndpop(GameStateManager.GAMEOVER, GameStateManager.PLAY);
        }
    }

    public void input(KeyHandler key) throws FileNotFoundException, URISyntaxException {
        key.escape.tick();
        //Read key input
        player.input(key);
        if (key.escape.clicked) {
            //if esc clicked, proceed to pause state
            if (gsm.getstate(GameStateManager.PAUSE)) {
                gsm.pop(GameStateManager.PAUSE);
            } else {
                gsm.add(GameStateManager.PAUSE);
            }

        }
    }

    public void render(Graphics2D g) {
        //Add font word onto background image
        player.render(g);
        for (Enemy enemy :
                enemyList) {
            enemy.render(g);
        }

        //Load font
        new FontTTF(g, "Everlaster", rec, "WuXia.ttf", 7, 20f);

        //render fps and waves count
        g.setColor(Color.white);

        String fps = GamePanel.frame + " FPS";
        g.drawString(fps, GamePanel.width - 6 * 32, 32);

        String wave = EnemyNumber + " Waves";
        g.drawString(wave, GamePanel.width - 6 * 32, 64);

        String scores = "Score: " + score;
        g.drawString(scores, GamePanel.width - 6 * 32, 96);

        String health = "Life: " + life;
        if (life <= 2) {
            g.setColor(Color.RED);
        }
        g.drawString(health, GamePanel.width - 6 * 32, 128);


    }


}
