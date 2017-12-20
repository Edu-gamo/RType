package com.rtype.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by egaona on 12/18/2017.
 */

public class InputProcesador extends InputAdapter {

    private MainCharacter player;
    private Vector2 lastTouch = new Vector2();

    public InputProcesador(MainCharacter p){
        player = p;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        lastTouch.set(screenX, screenY);
        return true;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer){
        Vector2 newTouch = new Vector2(screenX, screenY);
        Vector2 delta = newTouch.cpy().sub(lastTouch);
        lastTouch = newTouch;
        if (delta.y < 0){
            player.goUp();
        }else{
            player.goDown();
        }
        return true;
    }

}
