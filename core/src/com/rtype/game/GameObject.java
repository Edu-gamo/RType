package com.rtype.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Eduard on 15/01/2018.
 */

public abstract class GameObject extends Actor {

    protected Sprite sprite;

    protected boolean alive = true;

    public boolean isAlive(){
        return alive;
    }

    public void setAlive(boolean _alive){
        alive = _alive;
    }

    public float getX(){
        return sprite.getX();
    }

    public float getY(){
        return sprite.getY();
    }

    public void setX(float _x){
        sprite.setX(_x);
    }

    public void setY(float _y){
        sprite.setY(_y);
    }

    public float getWidth(){
        return sprite.getWidth();
    }

    public float getHeight(){
        return sprite.getHeight();
    }

    public void setPosition(float _x, float _y){
        sprite.setPosition(_x, _y);
    }

    public void setScale(float _scale){
        sprite.setScale(_scale);
    }

    public Rectangle getBoundingBox(){
        return sprite.getBoundingRectangle();
    }

    @Override
    public abstract void act(float delta);

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);
    }
}
