package com.rtype.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by egaona on 1/8/2018.
 */

import java.util.Random;

public abstract class Enemy extends GameObject {

    protected Random random = new Random();
    protected float speed;
    protected int posY;

    protected float maxShootDelay;
    protected float shootDelay;

    protected TextureAtlas textureAtlas;
    protected Animation animation;
    protected float time = 0f;

    @Override
    public abstract void act(float delta);

}
