package com.Northstar.game.Util;

import com.Northstar.game.Entity.Entity;

public class AABB {

    private Vector2f pos;
    private float yOffset = 0;
    private float xOffset = 0;
    private float w;
    private float h;
    private float r;
    private int size;
    private Entity e;

    public AABB(Vector2f pos,int w, int h){
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w,h);
    }

    public AABB(Vector2f pos, int r, Entity e){
        this.pos = pos;
        this.r = r;
        this.e = e;
        size = r;

    }

    public AABB(Vector2f pos, int r) {
        this.pos = pos;
        this.r = r;

        size = r;
    }

    //Accessor
    public Vector2f getPos(){return pos;}
    public float getWidth(){return w;}
    public float getHeight(){return h;}
    public float getyOffset(){return yOffset;}
    public float getxOffset(){return xOffset;}

    //Mutator
    public void setBox(Vector2f pos,int w,int h){
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w,h);
    }

    public void setWidth(float f){w=f;}
    public void setHeight(float f){h=f;}

    public  void setxOffset(float f){xOffset = f; }
    public  void setyOffset(float f){yOffset = f; }

    public boolean collides(AABB bBox){
        float ax = ((pos.x + (xOffset))+(this.w/2));
        float ay = ((pos.y + (yOffset))+(this.h/2));
        float bx = ((bBox.pos.x+(bBox.xOffset))+(bBox.getWidth()/2));
        float by = ((bBox.pos.y+(bBox.yOffset))+(bBox.getHeight()/2));

        if(Math.abs(ax - bx)<(this.w/2)+(bBox.getWidth()/2)){
            return Math.abs(ay - by) < (this.h / 2) + (bBox.getHeight() / 2);
        }
        return false;
    }
}
