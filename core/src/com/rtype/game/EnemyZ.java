package com.rtype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by egaona on 1/8/2018.
 */

public class EnemyZ extends Enemy {
    float speed = 100;
    int posY;

    public EnemyZ(Texture texture){
        spr_enemy = new Sprite(texture);
        spr_enemy.setSize(Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/5);
        spr_enemy.setSize(spr_enemy.getWidth()*-1, spr_enemy.getHeight());
        posY = random.nextInt((int)(Gdx.graphics.getHeight()-spr_enemy.getHeight()));
        spr_enemy.setPosition(Gdx.graphics.getWidth(), posY);

    }

    @Override
    public void act(float delta) {
        spr_enemy.setX(spr_enemy.getX()-speed*delta);
        if(spr_enemy.getX() <= 0){
            posY = random.nextInt((int)(Gdx.graphics.getHeight()-spr_enemy.getHeight()));
            spr_enemy.setPosition(Gdx.graphics.getWidth(), posY);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        spr_enemy.draw(batch);
    }
}
