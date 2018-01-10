package com.rtype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by egaona on 1/8/2018.
 */

public class EnemyZ extends Enemy {
    float speed = 100.0f;
    int posY;
    int minY;
    int maxY;
    boolean goUp = false;

    public EnemyZ(Texture texture){
        spr_enemy = new Sprite(texture);
        spr_enemy.setSize(Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/5);
        spr_enemy.setSize(spr_enemy.getWidth()*-1, spr_enemy.getHeight());
        posY = random.nextInt((int)(Gdx.graphics.getHeight()-spr_enemy.getHeight()));
        spr_enemy.setPosition(Gdx.graphics.getWidth(), posY);

        boundingBox = new Rectangle(spr_enemy.getX(), spr_enemy.getY(), spr_enemy.getWidth(), spr_enemy.getHeight());

        if(posY < (int)spr_enemy.getHeight()){
            minY = 0;
            maxY = (int)spr_enemy.getHeight();
        }else if(posY > (int)(Gdx.graphics.getHeight()-spr_enemy.getHeight()*2)){
            minY = (int)(Gdx.graphics.getHeight()-spr_enemy.getHeight()*2);
            maxY = (int)(Gdx.graphics.getHeight()-spr_enemy.getHeight());
        }else {
            minY = posY - (int)spr_enemy.getHeight()/2;
            maxY = posY + (int)spr_enemy.getHeight()/2;
        }

    }

    @Override
    public void act(float delta) {
        //spr_enemy.setX(spr_enemy.getX()-speed*delta);
        movement(delta);
        if(spr_enemy.getX() <= 0){
            /*posY = random.nextInt((int)(Gdx.graphics.getHeight()-spr_enemy.getHeight()));
            spr_enemy.setPosition(Gdx.graphics.getWidth(), posY);*/
            resetEnemy();
        }
        boundingBox.setPosition(spr_enemy.getX(), spr_enemy.getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        spr_enemy.draw(batch);
    }

    public void movement(float delta){

        spr_enemy.setX(spr_enemy.getX()-speed*delta);

        if(goUp){
            spr_enemy.setY(spr_enemy.getY()+speed*delta);
        }else {
            spr_enemy.setY(spr_enemy.getY()-speed*delta);
        }

        if(spr_enemy.getY() >= maxY && goUp){
            goUp = false;
        }else if(spr_enemy.getY() <= minY && !goUp){
            goUp = true;
        }
    }

    public void resetEnemy(){
        posY = random.nextInt((int)(Gdx.graphics.getHeight()-spr_enemy.getHeight()));
        spr_enemy.setPosition(Gdx.graphics.getWidth(), posY);

        if(posY < (int)spr_enemy.getHeight()){
            minY = 0;
            maxY = (int)spr_enemy.getHeight();
        }else if(posY > (int)(Gdx.graphics.getHeight()-spr_enemy.getHeight()*2)){
            minY = (int)(Gdx.graphics.getHeight()-spr_enemy.getHeight()*2);
            maxY = (int)(Gdx.graphics.getHeight()-spr_enemy.getHeight());
        }else {
            minY = posY - (int)spr_enemy.getHeight()/2;
            maxY = posY + (int)spr_enemy.getHeight()/2;
        }
    }

}
