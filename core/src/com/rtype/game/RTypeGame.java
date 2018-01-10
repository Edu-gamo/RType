package com.rtype.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class RTypeGame extends Game {

	public static Stage stage;

	MainCharacter player;
	Texture player_texture;
	Texture enemy1_texture;
	Texture enemy2_texture;
	
	@Override
	public void create () {

		stage = new Stage();

		player_texture = new Texture("nave.png");
		player = new MainCharacter(player_texture);
		stage.addActor(player);

		EnemyManager.addEnemy(0, new Texture("nave2.png"));
		EnemyManager.addEnemy(1, new Texture("nave3.png"));

		BulletManager.player_bullet_texture = new Texture("disparo.png");
	}

	@Override
	public void render () {
		super.render();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/*for (Enemy e : EnemyManager.enemies) {
			for(Bullet b : BulletManager.playerBullets){
				if(e.boundingBox.overlaps(b.boundingBox)){
					e.remove();
					b.remove();
				}
			}
		}*/

		/*for (int i = 0; i < EnemyManager.enemies.size(); i++){
			for (int j = 0; j < BulletManager.playerBullets.size(); j++){
				if(EnemyManager.enemies[i] == null)
			}
		}*/

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
