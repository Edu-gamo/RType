package com.rtype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by egaona on 1/8/2018.
 */

public class EnemyL extends Enemy {

    public EnemyL(TextureAtlas texture){
        textureAtlas = texture;
        animation = new Animation(1/6f, textureAtlas.findRegions("red_enemy"));
        animation.setPlayMode(Animation.PlayMode.LOOP);

        TextureRegion txtrgn = (TextureRegion) animation.getKeyFrame(time, true);
        sprite = new Sprite(txtrgn);
        setScale(1.5f);

        boolean positionCorrect;
        do {
            posY = random.nextInt((int)(Gdx.graphics.getHeight()-sprite.getHeight()));
            positionCorrect = true;
            for (Enemy e : EnemyManager.getEnemies()) {
                if(e != this && e.getX() + e.getWidth() >= Gdx.graphics.getWidth()){
                    if(Math.sqrt(Math.pow(e.getY() - posY, 2)) <= sprite.getHeight()) positionCorrect = false;
                }
            }
        }while (!positionCorrect);

        setPosition(Gdx.graphics.getWidth() + getWidth(), posY);

        speed = 150.0f;

        maxShootDelay = 2f;
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
            BulletManager.addBullet(getX(), getY()+ getWidth()/2, false);
        }

        setX(getX()-speed*delta);
        if(getX() + getWidth() <= 0){
            setAlive(false);
            EnemyManager.setClearEnem(true);
        }
    }
}
