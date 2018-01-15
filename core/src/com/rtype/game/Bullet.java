package com.rtype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet extends GameObject {

    private float speed = 300.0f;

    private boolean playerBullet;

    public Bullet(Texture texture, float posX, float posY, boolean isPlayerBullet){
        sprite = new Sprite(texture);
        sprite.setPosition(posX, posY);
        setScale(1.5f);

        playerBullet = isPlayerBullet;

        if(!isPlayerBullet) speed *= -1;

    }

    public boolean isPlayerBullet(){
        return playerBullet;
    }

    @Override
    public void act(float delta) {
        setX(getX()+speed*delta);
        if((getX() > Gdx.graphics.getWidth() && playerBullet) || (getX() + getWidth() < 0 && !playerBullet)){
            alive = false;
            BulletManager.setClearB(true);
        }
    }

}
