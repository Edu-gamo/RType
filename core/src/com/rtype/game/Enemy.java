package com.rtype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by egaona on 1/8/2018.
 */

import java.util.Random;

public abstract class Enemy extends Actor {
    Sprite spr_enemy;
    Random random = new Random();
    Rectangle boundingBox;

    @Override
    public abstract void act(float delta);

    @Override
    public abstract void draw(Batch batch, float parentAlpha);
}
