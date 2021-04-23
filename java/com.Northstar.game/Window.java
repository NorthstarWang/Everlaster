package com.Northstar.game;

import javax.swing.*;

public class Window extends JFrame {

    public Window() {
        //Window title
        setTitle("Everlaster");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Window size dimensions
        setContentPane(new GamePanel(960, 960));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
