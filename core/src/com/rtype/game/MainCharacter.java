package com.rtype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by egaona on 12/18/2017.
 */

public class MainCharacter extends Actor {
    private Sprite spr_player;
    float speed = 200.0f;
    Rectangle boundingBox;
    float maxShootDelay = 0.25f;
    float shootDelay = 0.0f;

    public MainCharacter(Texture texture){
        spr_player = new Sprite(texture);
        spr_player.setSize(Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/5);
        spr_player.setPosition(0, Gdx.graphics.getHeight()/2-spr_player.getHeight()/2);
        boundingBox = new Rectangle(spr_player.getX(), spr_player.getY(), spr_player.getWidth(), spr_player.getHeight());
    }

    public void goUp(float delta){
        if(spr_player.getY() + spr_player.getHeight() <= Gdx.graphics.getHeight()) spr_player.setY(spr_player.getY() + speed*delta);
    }

    public void goDown(float delta){
        if(spr_player.getY() >= 0) spr_player.setY(spr_player.getY() - speed*delta);
    }

    public void shoot(){
        BulletManager.addPlayerBullet(spr_player.getX()+spr_player.getWidth()/1.25f, spr_player.getY()+spr_player.getHeight()/2);
    }

    @Override
    public void act(float delta) {
        shootDelay += delta;
        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) goUp(delta);
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) goDown(delta);
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && shootDelay >= maxShootDelay){
            shootDelay = 0.0f;
            shoot();
        }
        boundingBox.setPosition(spr_player.getX(), spr_player.getY());

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        spr_player.draw(batch);
    }
}
