package com.rtype.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Eduard on 14/01/2018.
 */

public class Explosion extends GameObject {

    private TextureAtlas textureAtlas;
    private Animation animation;

    private float time = 0f;

    public Explosion(TextureAtlas texture, float posX, float posY){
        textureAtlas = texture;
        animation = new Animation(1/8f, textureAtlas.findRegions("explosion"));
        animation.setPlayMode(Animation.PlayMode.NORMAL);

        TextureRegion txtrgn = (TextureRegion) animation.getKeyFrame(time, true);
        sprite = new Sprite(txtrgn);
        setScale(1.5f);

        setPosition(posX, posY);

    }

    @Override
    public void act(float delta){
        time += delta;
        sprite.setRegion((TextureRegion) animation.getKeyFrame(time, true));
        if(animation.isAnimationFinished(time)){
            remove();
            alive = false;
            ExplosionManager.setClearExplo(true);
        }
    }

}
