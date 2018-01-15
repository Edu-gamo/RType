package com.rtype.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.Vector;

/**
 * Created by Eduard on 14/01/2018.
 */

public class ExplosionManager {
    private static final ExplosionManager ourInstance = new ExplosionManager();

    private static Vector<Explosion> explosions;

    private static Sound explosionSound;

    private static boolean clearExplo;

    public static ExplosionManager getInstance() {
        return ourInstance;
    }

    private ExplosionManager() {
        explosions = new Vector<Explosion>();
        clearExplo = false;
    }

    public static Vector<Explosion> getExplosions(){
        return explosions;
    }

    public static boolean isClearExplo(){
        return clearExplo;
    }

    public static void setClearExplo(boolean _clearExplo){
        clearExplo = _clearExplo;
    }

    public static void setSound(Sound explosion){
        explosionSound = explosion;
    }

    public static void addExplosion(float posX, float posY, TextureAtlas texture){
        Explosion explosion = new Explosion(texture, posX, posY);
        explosions.add(explosion);
        RTypeGame.stage.addActor(explosion);
        explosionSound.play();
    }

    public static void clearExplosions(boolean clearAll){
        Vector<Explosion> aliveExplosions = new Vector<Explosion>();
        if(!clearAll) {
            for (Explosion explosion : explosions) {
                if (explosion.isAlive()) aliveExplosions.add(explosion);
            }
        }
        explosions.clear();
        explosions = aliveExplosions;
        clearExplo = false;
    }
}
