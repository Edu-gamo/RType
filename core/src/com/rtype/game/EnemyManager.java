package com.rtype.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.Vector;

/**
 * Created by egaona on 1/10/2018.
 */

public class EnemyManager {

    private static final EnemyManager ourInstance = new EnemyManager();

    private static Vector<Enemy> enemies;

    private static boolean clearEnem;

    public static EnemyManager getInstance() {
        return ourInstance;
    }

    private EnemyManager() {
        enemies = new Vector<Enemy>();
        clearEnem = false;
    }

    public static Vector<Enemy> getEnemies(){
        return enemies;
    }

    public static int totalEnemies(){
        return enemies.size();
    }

    public static boolean isClearEnem(){
        return clearEnem;
    }

    public static void setClearEnem(boolean _clearEnem){
        clearEnem = _clearEnem;
    }

    public static void addEnemy(int type, TextureAtlas texture){
        Enemy e = null;
        switch (type){
            case 0:
                e = new EnemyL(texture);
                break;
            case 1:
                e = new EnemyZ(texture);
                break;
        }
        enemies.add(e);
        RTypeGame.stage.addActor(e);
    }

    public static void clearEnemies(boolean clearAll){
        Vector<Enemy> aliveEnemies = new Vector<Enemy>();
        if(!clearAll) {
            for (Enemy e : enemies) {
                if (e.isAlive()) aliveEnemies.add(e);
            }
        }
        enemies.clear();
        enemies = aliveEnemies;
        clearEnem = false;
    }
}
