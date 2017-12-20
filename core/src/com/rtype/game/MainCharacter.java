package com.rtype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by egaona on 12/18/2017.
 */

public class MainCharacter extends Actor {
    Sprite spr_player;
    float speed = 2;

    public MainCharacter(){
        spr_player = new Sprite(new Texture("nave.png"));
        spr_player.setSize(Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/5);
        spr_player.setPosition(0, Gdx.graphics.getHeight()/2-spr_player.getHeight()/2);
    }

    public void goUp(){
        if(spr_player.getY() + spr_player.getHeight() <= Gdx.graphics.getHeight()) spr_player.setY(spr_player.getY() + speed);
    }

    public void goDown(){
        if(spr_player.getY() >= 0) spr_player.setY(spr_player.getY() - speed);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
