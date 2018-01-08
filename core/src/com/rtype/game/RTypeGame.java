package com.rtype.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class RTypeGame extends Game {

	private Stage stage;

	MainCharacter player;
	Texture player_texture;
	Texture enemy1_texture;
	Texture enemy2_texture;

	Enemy enemy;
	Enemy enemy2;

	/*private InputProcesador inputProc;


	SpriteBatch batch;*/
	
	@Override
	public void create () {

		stage = new Stage();

		player_texture = new Texture("nave.png");
		player = new MainCharacter(player_texture);
		stage.addActor(player);

		enemy1_texture = new Texture("nave2.png");
		enemy = new EnemyL(enemy1_texture);
		stage.addActor(enemy);

		enemy2_texture = new Texture("nave3.png");
		enemy2 = new EnemyZ(enemy2_texture);
		stage.addActor(enemy2);

		/*batch = new SpriteBatch();

		spr_player = new Texture("nave.png");
		player = new MainCharacter(new Sprite(spr_player));

		inputProc = new InputProcesador(player);
		Gdx.input.setInputProcessor(inputProc);*/
	}

	@Override
	public void render () {
		super.render();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		stage.dispose();
		player_texture.dispose();
	}

}
