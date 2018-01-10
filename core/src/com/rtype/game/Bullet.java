package com.rtype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bullet extends Actor {
    private Sprite spr_bullet;
    float speed = 200.0f;
    Rectangle boundingBox;

    public Bullet(Texture texture, float posX, float posY){
        spr_bullet = new Sprite(texture);
        //spr_player.setSize(Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/5);
        spr_bullet.setPosition(posX, posY);
        boundingBox = new Rectangle(spr_bullet.getX(), spr_bullet.getY(), spr_bullet.getWidth(), spr_bullet.getHeight());
    }

    @Override
    public void act(float delta) {

        spr_bullet.setX(spr_bullet.getX()+speed*delta);
        boundingBox.setPosition(spr_bullet.getX(), spr_bullet.getY());

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        spr_bullet.draw(batch);
    }

}
