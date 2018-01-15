package com.rtype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by egaona on 1/8/2018.
 */

public class EnemyZ extends Enemy {

    private int minY;
    private int maxY;
    private boolean goUp = false;

    public EnemyZ(TextureAtlas texture){
        textureAtlas = texture;
        animation = new Animation(1/6f, textureAtlas.findRegions("blue_enemy"));
        animation.setPlayMode(Animation.PlayMode.LOOP);

        TextureRegion txtrgn = (TextureRegion) animation.getKeyFrame(time, true);
        sprite = new Sprite(txtrgn);
        setScale(1.5f);

        boolean positionCorrect;
        do {
            posY = random.nextInt((int)(Gdx.graphics.getHeight()-getHeight()));
            positionCorrect = true;
            for (Enemy e : EnemyManager.getEnemies()) {
                if(e != this && e.getX() + e.getBoundingBox().getWidth() >= Gdx.graphics.getWidth()){
                    if(Math.sqrt(Math.pow(e.getY() - posY, 2)) <= getHeight()) positionCorrect = false;
                }
            }
        }while (!positionCorrect);

        setPosition(Gdx.graphics.getWidth(), posY);

        if(posY < (int)getHeight()*2){
            minY = 0;
            maxY = (int)getHeight()*2;
        }else if(posY > (int)(Gdx.graphics.getHeight()-getHeight()*3)){
            minY = (int)(Gdx.graphics.getHeight()-getHeight()*3);
            maxY = (int)(Gdx.graphics.getHeight()-getHeight());
        }else {
            minY = posY - (int)getHeight();
            maxY = posY + (int)getHeight();
        }

        speed = 100.0f;

        maxShootDelay = 2.5f;
        shootDelay = 0.0f;

    }

    @Override
    public void act(float delta) {
        time += delta;
        sprite.setRegion((TextureRegion) animation.getKeyFrame(time, true));

        shootDelay += delta;
        if(shootDelay >= maxShootDelay){
            shootDelay = 0.0f;
            //Shoot
            BulletManager.addBullet(getX(), getY()+getHeight()/2, false);
        }

        movement(delta);
        if(getX() + getWidth() <= 0){
            setAlive(false);
            EnemyManager.setClearEnem(true);
        }
    }

    public void movement(float delta){

        setX(getX()-speed*delta);

        if(goUp){
            float dist = maxY - getY();
            setY(getY()+(speed/4+dist)*delta);
        }else {
            float dist = getY() - minY;
            setY(getY()-(speed/4+dist)*delta);
        }

        if(getY() >= maxY && goUp){
            goUp = false;
        }else if(getY() <= minY && !goUp){
            goUp = true;
        }
    }

}
