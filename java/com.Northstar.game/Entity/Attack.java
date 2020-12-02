package com.Northstar.game.Entity;

import com.Northstar.game.Graphics.Sprite;
import com.Northstar.game.Util.KeyHandler;
import com.Northstar.game.Util.MouseHandler;
import com.Northstar.game.Util.Vector2f;

import java.awt.*;

public class Attack extends Entity{
    public Attack(Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
    }

    public void update(){
        super.update();
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(ani.getImage(),(int)(pos.x),(int)(pos.y),size,size,null);
    }

}
