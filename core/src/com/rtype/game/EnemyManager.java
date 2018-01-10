package com.rtype.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.Vector;

/**
 * Created by egaona on 1/10/2018.
 */

public class EnemyManager {
    private static final EnemyManager ourInstance = new EnemyManager();

    public static Vector<Enemy> enemies;

    public static EnemyManager getInstance() {
        return ourInstance;
    }

    private EnemyManager() {
        enemies = new Vector<Enemy>();
    }

    public static void addEnemy(int type, Texture texture){
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
}
