package com.Northstar.game.States;

import com.Northstar.game.Graphics.FontTTF;
import com.Northstar.game.Util.KeyHandler;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.*;

public class ScoreState extends GameState{

    Rectangle rec = new Rectangle (960,960);
    Vector<Integer> scoreList = new Vector<>();

    public ScoreState(GameStateManager gsm) throws FileNotFoundException, URISyntaxException {
        super(gsm);

        //process score in score board
        Scanner scanner = new Scanner(new File("./Resources/score.txt"));
        while(scanner.hasNextInt()){
            scoreList.add(scanner.nextInt());
        }

        scoreList.sort(Collections.reverseOrder());
    }

    @Override
    public void update() {

    }

    @Override
    public void input(KeyHandler key) throws FileNotFoundException, URISyntaxException {
        key.escape.tick();
        if(key.escape.clicked){
            gsm.addAndpop(GameStateManager.MENU,GameStateManager.SCORE);
        }
    }

    @Override
    public void render(Graphics2D g) {
        new FontTTF(g,"",rec,"WuXia.ttf",7,80f);
        g.setColor(Color.white);
        g.drawString("One above all",230,140);
        //only display top 10 scores
        g.setColor(Color.white);
        for (int i = 0; i < scoreList.size(); i++) {
            new FontTTF(g,"",rec,"WuXia.ttf",7,50f);
            if(i==10){
                break;
            }else if(i==0){
                //Gold
                new FontTTF(g,"",rec,"WuXia.ttf",7,75f);
                g.setColor(new Color(255,215,0));
                g.drawString(String.valueOf(1),240,250);
                g.drawString(String.valueOf(scoreList.get(0)),440,250);
            }else if(i==1){
                //Silver
                new FontTTF(g,"",rec,"WuXia.ttf",7,65f);
                g.setColor(new Color(192,192,192));
                g.drawString(String.valueOf(2),240,330);
                g.drawString(String.valueOf(scoreList.get(1)),440,330);
            }else if(i==2){
                //Bronze
                new FontTTF(g,"",rec,"WuXia.ttf",7,55f);
                g.setColor(new Color( 205, 127, 50));
                g.drawString(String.valueOf(3),240,400);
                g.drawString(String.valueOf(scoreList.get(2)),440,400);
            }else{
                //4-10
                g.setColor(Color.WHITE);
                g.drawString(String.valueOf(i+1),240,400+(i-2)*60);
                g.drawString(String.valueOf(scoreList.get(i)),440,400+(i-2)*60);
            }

        }
    }
}
