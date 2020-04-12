package com.Northstar.game.Graphics;

import com.Northstar.game.Util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Font {

    private BufferedImage FONTSHEET = null;
    private BufferedImage[][] fontArray;
    private final int TILE_SIZE = 32;
    public int w;
    public int h;
    private int wFont;
    private int hFont;

    public Font(String file){
        w = TILE_SIZE;
        h = TILE_SIZE;

        System.out.println("Loading: "+ file + "...");
        FONTSHEET = loadFont(file);

        wFont = FONTSHEET.getWidth()/w;
        hFont = FONTSHEET.getHeight()/h;
        loadFontArray();
    }

    public Font(String file, int w,int h){
        this.w = w;
        this.h = h;

        System.out.println("Loading: "+ file + "...");
        FONTSHEET = loadFont(file);

        wFont = FONTSHEET.getWidth()/w;
        hFont = FONTSHEET.getHeight()/h;
        loadFontArray();
    }

    public void setSize(int width,int height){
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i) {
        w = i;
        wFont = FONTSHEET.getWidth()/w;
    }

    public void setHeight(int i) {
        h = i;
        hFont = FONTSHEET.getHeight()/h;
    }

    public int getWidth() { return w; }
    public int getHeight() { return h; }

    private BufferedImage loadFont(String file){
        BufferedImage sprite = null;
        try{
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        }catch (Exception e){
            System.out.println("Error: could not load file: "+ file);
        }
        return sprite;
    }

    public void loadFontArray(){
        fontArray = new BufferedImage[wFont][hFont];

        for(int x= 0;x< wFont;x++){
            for(int y = 0;y<hFont;y++){
                fontArray[x][y] = getLetter(x,y);
            }
        }
    }

    public BufferedImage getFontSheet(int x, int y) {
        return FONTSHEET;
    }

    public BufferedImage getLetter(int x, int y) {
        return FONTSHEET.getSubimage(x*w,y*h,w,h);
    }

    public BufferedImage getFont(char letter){
        int value = letter - 65;
        int x = value % wFont;
        int y = value / wFont;

        return getLetter(x,y);
    }

}
