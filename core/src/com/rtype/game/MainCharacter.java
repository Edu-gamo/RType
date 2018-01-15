package com.rtype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by egaona on 12/18/2017.
 */

public class MainCharacter extends GameObject {

    private float speed = 250.0f;
    private float maxShootDelay = 0.25f;
    private float shootDelay = 0.0f;

    private int lives = 5;
    private boolean invulnerable = false;
    private float maxInvulnerableTime = 1.0f;
    private float invulnerableTime = 0.0f;

    private TextureAtlas textureAtlas;
    private Animation currentAnimation, idle, up, down, transUp, transDown, transUpRev, transDownRev;

    private float time = 0f;

    public MainCharacter(TextureAtlas texture){
        textureAtlas = texture;

        idle = new Animation(1/9f, textureAtlas.findRegions("Ship_idle"));
        idle.setPlayMode(Animation.PlayMode.LOOP);

        up = new Animation(1/9f, textureAtlas.findRegions("Ship_up"));
        up.setPlayMode(Animation.PlayMode.LOOP);

        down = new Animation(1/9f, textureAtlas.findRegions("Ship_down"));
        down.setPlayMode(Animation.PlayMode.LOOP);

        transUp = new Animation(1/9f, textureAtlas.findRegions("Ship_transitionUp"));
        transUp.setPlayMode(Animation.PlayMode.NORMAL);

        transDown = new Animation(1/9f, textureAtlas.findRegions("Ship_transitionDown"));
        transDown.setPlayMode(Animation.PlayMode.NORMAL);

        transUpRev = new Animation(1/9f, textureAtlas.findRegions("Ship_transitionUp"));
        transUpRev.setPlayMode(Animation.PlayMode.REVERSED);

        transDownRev = new Animation(1/9f, textureAtlas.findRegions("Ship_transitionDown"));
        transDownRev.setPlayMode(Animation.PlayMode.REVERSED);

        currentAnimation = idle;

        TextureRegion txtrgn = (TextureRegion) currentAnimation.getKeyFrame(time, true);
        sprite = new Sprite(txtrgn);
        setScale(1.5f);
        setPosition(0, Gdx.graphics.getHeight()/2-getHeight()/2);
    }

    public void goUp(float delta){
        if(getY() + getHeight() <= Gdx.graphics.getHeight()){
            setY(getY() + speed*delta);
            if(currentAnimation != up && currentAnimation != transUp){
                time = 0f;
                currentAnimation = transUp;
            }
            if(currentAnimation == transUp && currentAnimation.isAnimationFinished(time)) currentAnimation = up;
        }else{
            currentAnimation = idle;
        }
    }

    public void goDown(float delta){
        if(getY() >= 0){
            setY(getY() - speed*delta);
            if(currentAnimation != down && currentAnimation != transDown){
                time = 0f;
                currentAnimation = transDown;
            }
            if(currentAnimation == transDown && currentAnimation.isAnimationFinished(time)) currentAnimation = down;
        }else{
            currentAnimation = idle;
        }
    }

    public void goRight(float delta){
        if(getX() + getWidth() <= Gdx.graphics.getWidth()) setX(getX() + speed*delta);
    }

    public void goLeft(float delta){
        if(getX() >= 0) setX(getX() - speed*delta);
    }

    public void getHit(){
        lives--;
        invulnerable = true;
        invulnerableTime = 0.0f;
        setPosition(0, Gdx.graphics.getHeight()/2-getHeight()/2);
        RTypeGame.addScore(-25);
        if(lives == 0) alive = false;
    }

    public boolean isInvulnerable(){
        return invulnerable;
    }

    public int getLives(){
        return lives;
    }

    public void setLives(int _lives){
        lives = _lives;
        if(lives > 0) alive = true;
    }

    @Override
    public void act(float delta) {
        time += delta;
        sprite.setRegion((TextureRegion) currentAnimation.getKeyFrame(time, true));

        if(invulnerable){
            sprite.setAlpha(invulnerableTime%0.25f);
            invulnerableTime += delta;
            if(invulnerableTime >= maxInvulnerableTime){
                invulnerable = false;
                sprite.setAlpha(1f);
            }
        }

        shootDelay += delta;

        if(!Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyPressed(Input.Keys.W) &&
                !Gdx.input.isKeyPressed(Input.Keys.DOWN) && !Gdx.input.isKeyPressed(Input.Keys.S)){
            if(currentAnimation == up || currentAnimation == transUp){
                time = 0f;
                currentAnimation = transUpRev;
            }else if(currentAnimation == down || currentAnimation == transDown){
                time = 0f;
                currentAnimation = transDownRev;
            }else if((currentAnimation == transUpRev || currentAnimation == transDownRev) && currentAnimation.isAnimationFinished(time)) {
                currentAnimation = idle;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) goUp(delta);
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) goDown(delta);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) goRight(delta);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) goLeft(delta);
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && shootDelay >= maxShootDelay){
            shootDelay = 0.0f;
            //Shoot
            BulletManager.addBullet(getX()+getWidth(), getY()+getHeight()/2, true);
        }
    }

}
