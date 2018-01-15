package com.rtype.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.Vector;

/**
 * Created by egaona on 1/10/2018.
 */

public class BulletManager {

    private static final BulletManager ourInstance = new BulletManager();

    private static Texture player_bullet_texture;
    private static Texture enemy_bullet_texture;

    private static Sound player_shot;
    private static Sound enemy_shot;

    private static Vector<Bullet> bullets;
    private static boolean clearB;

    public static BulletManager getInstance() {
        return ourInstance;
    }

    private BulletManager() {
        bullets = new Vector<Bullet>();
        clearB = false;
    }

    public static Vector<Bullet> getBullets(){
        return bullets;
    }

    public static void setTextures(Texture player, Texture enemy){
        player_bullet_texture = player;
        enemy_bullet_texture = enemy;
    }

    public static void setSounds(Sound player, Sound enemy){
        player_shot = player;
        enemy_shot = enemy;
    }

    public static boolean isClearB(){
        return clearB;
    }

    public static void setClearB(boolean _clearB){
        clearB = _clearB;
    }

    public static void addBullet(float posX, float posY, boolean isPlayerBullet){
        Bullet b = new Bullet(isPlayerBullet ? player_bullet_texture : enemy_bullet_texture, posX, posY, isPlayerBullet);
        bullets.add(b);
        RTypeGame.stage.addActor(b);
        if(isPlayerBullet){
            //player_shot.play();
            player_shot.play(.5f);
        } else {
            //enemy_shot.play();
            enemy_shot.play(.1f);
        }
    }

    public static void clearBullets(boolean clearAll){
        Vector<Bullet> aliveBullets = new Vector<Bullet>();
        if(!clearAll) {
            for (Bullet b : bullets) {
                if (b.isAlive()) aliveBullets.add(b);
            }
        }
        bullets.clear();
        bullets = aliveBullets;
        clearB = false;
    }

}
