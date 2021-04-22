package com.Northstar.game.Graphics;

import com.Northstar.game.Util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Sprite {

    private BufferedImage SPRITESHEET = null;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 64;
    public int w;
    public int h;
    private int wSprite;
    private int hSprite;

    public Sprite(String file){
        //Separate tiles in sprite sheet
        w = TILE_SIZE;
        h = TILE_SIZE;

        SPRITESHEET = loadSprite(file);

        wSprite = SPRITESHEET.getWidth()/w;
        hSprite = SPRITESHEET.getHeight()/h;
        loadSpriteArray();
    }

    public BufferedImage loadSprite(String file){
        BufferedImage sprite = null;
        try{
            sprite = ImageIO.read(new File(file));
        }catch (Exception e){
            System.out.println("Error: could not load file: "+ file);
        }
        return sprite;
    }

    public void loadSpriteArray(){
        //Load specific tile in sprite sheet
        spriteArray = new BufferedImage[hSprite][wSprite];

        for(int x= 0;x< wSprite;x++){
            for(int y = 0;y<hSprite;y++){
                spriteArray[y][x] = getSprite(x,y);
            }
        }
    }

    public BufferedImage getSprite(int x, int y) {
        //Get tile in sprite sheet by cutting dimension
        return SPRITESHEET.getSubimage(x*w,y*h,w,h);
    }

    public BufferedImage[] getSpriteArray(int i){
        return spriteArray[i];
    }

}
