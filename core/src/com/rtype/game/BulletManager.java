package com.rtype.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.Vector;

/**
 * Created by egaona on 1/10/2018.
 */

public class BulletManager {
    private static final BulletManager ourInstance = new BulletManager();

    public static Texture player_bullet_texture;

    //public static Vector<Bullet> playerBullets;
    public static Vector<Bullet> enemyBullets;

    public static Group playerBullets;

    public static BulletManager getInstance() {
        return ourInstance;
    }

    private BulletManager() {
        //playerBullets = new Vector<Bullet>();
        playerBullets = new Group();
        enemyBullets = new Vector<Bullet>();
    }

    public static void addPlayerBullet(float posX, float posY){
        Bullet b = new Bullet(player_bullet_texture, posX, posY);
        playerBullets.addActor(b);
        //playerBullets.add(b);
        RTypeGame.stage.addActor(b);
    }

}
