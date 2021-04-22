package com.Northstar.game;

import com.Northstar.game.GamePanel;

import javax.swing.JFrame;

public class Window extends JFrame{

    public Window(){
        //Window title
        setTitle("Everlaster");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Window size dimensions
        setContentPane(new GamePanel(960,960));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
