package com.rtype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by egaona on 1/8/2018.
 */

public class EnemyL extends Enemy {
    float speed = 200.0f;
    int posY;

    public EnemyL(Texture texture){
        spr_enemy = new Sprite(texture);
        spr_enemy.setSize(Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/5);
        spr_enemy.setSize(spr_enemy.getWidth()*-1, spr_enemy.getHeight());
        posY = random.nextInt((int)(Gdx.graphics.getHeight()-spr_enemy.getHeight()));
        spr_enemy.setPosition(Gdx.graphics.getWidth(), posY);

        boundingBox = new Rectangle(spr_enemy.getX(), spr_enemy.getY(), spr_enemy.getWidth(), spr_enemy.getHeight());

    }

    @Override
    public void act(float delta) {
        spr_enemy.setX(spr_enemy.getX()-speed*delta);
        if(spr_enemy.getX() <= 0){
            posY = random.nextInt((int)(Gdx.graphics.getHeight()-spr_enemy.getHeight()));
            spr_enemy.setPosition(Gdx.graphics.getWidth(), posY);
        }
        boundingBox.setPosition(spr_enemy.getX(), spr_enemy.getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        spr_enemy.draw(batch);
    }
}
