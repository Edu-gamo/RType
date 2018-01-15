package com.rtype.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.Random;
import java.util.Vector;

public class RTypeGame extends Game {

	public static Stage stage;

	private AssetManager assetManager;

	MainCharacter player;

	Sprite bg;
	Batch batch;

	Music music;

	private int maxEnemies = 20;
	private float enemySpawnTime = 0f;
	private float maxEnemySpawnTime = 5f;
	private float minEnemySpawnTime = 1f;
	private Random random;

	private static int score;
	private int maxScore;
	private String scoreText;
	private String maxScoreText;
	private String livesText;
	private BitmapFont font;

	private int maxLives = 5;
	
	@Override
	public void create () {

		stage = new Stage();
		batch = new SpriteBatch();

		assetManager = new AssetManager();
		assetManager.load("bg.png", Texture.class);
		assetManager.load("bullet.png", Texture.class);
		assetManager.load("bullet2.png", Texture.class);
		assetManager.load("ship.atlas", TextureAtlas.class);
		assetManager.load("red_enemy.atlas", TextureAtlas.class);
		assetManager.load("blue_enemy.atlas", TextureAtlas.class);
		assetManager.load("explosion.atlas", TextureAtlas.class);
		assetManager.load("sounds/music.mp3", Music.class);
		assetManager.load("sounds/player_shot.mp3", Sound.class);
		assetManager.load("sounds/enemy_shot.mp3", Sound.class);
		assetManager.load("sounds/explosion.mp3", Sound.class);
		assetManager.finishLoading();

		music = assetManager.get("sounds/music.mp3", Music.class);
		music.setLooping(true);
		music.play();

		bg = new Sprite(assetManager.get("bg.png", Texture.class));
		bg.setSize(bg.getWidth()*2.5f, Gdx.graphics.getHeight());

		player = new MainCharacter(assetManager.get("ship.atlas", TextureAtlas.class));
		player.setLives(maxLives);
		stage.addActor(player);

		random = new Random();
		//EnemyManager.addEnemy(0, assetManager.get("red_enemy.atlas", TextureAtlas.class));
		//EnemyManager.addEnemy(1, assetManager.get("blue_enemy.atlas", TextureAtlas.class));

		BulletManager.setTextures(assetManager.get("bullet.png", Texture.class), assetManager.get("bullet2.png", Texture.class));
		BulletManager.setSounds(assetManager.get("sounds/player_shot.mp3", Sound.class), assetManager.get("sounds/enemy_shot.mp3", Sound.class));

		ExplosionManager.setSound(assetManager.get("sounds/explosion.mp3", Sound.class));

		score = 0;
		maxScore = 0;
		scoreText = "Score: ";
		maxScoreText = "Record: ";
		livesText = "Lives: ";
		font = new BitmapFont();
	}

	@Override
	public void render () {
		super.render();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Crea nuevos enemigos
		enemySpawnTime += .1f;
		if(enemySpawnTime >= maxEnemySpawnTime && EnemyManager.totalEnemies() < maxEnemies){
			if(maxEnemySpawnTime > minEnemySpawnTime) maxEnemySpawnTime -= .1f;
			enemySpawnTime = 0f;
			int type = random.nextInt(2);
			EnemyManager.addEnemy(type, type == 0 ? assetManager.get("red_enemy.atlas", TextureAtlas.class) : assetManager.get("blue_enemy.atlas", TextureAtlas.class));
		}

		//Pinta el fondo, vidas y puntuacion
		batch.begin();
		bg.draw(batch);
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.draw(batch, scoreText + score, Gdx.graphics.getWidth()/2 - scoreText.length()*2, Gdx.graphics.getHeight());
		font.draw(batch, maxScoreText + maxScore, Gdx.graphics.getWidth()/1.25f - maxScoreText.length()*2, Gdx.graphics.getHeight());
		font.draw(batch, livesText + player.getLives(), Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight());
		batch.end();
		bg.setX(bg.getX()-2);
		if(bg.getX() + bg.getWidth() <= Gdx.graphics.getWidth()) bg.setX(0f);

		//Controla todas las colisiones del juego
		collisionDetection();

		//Limia los vectores de objetos ya destruidos
		if(BulletManager.isClearB()) BulletManager.clearBullets(false);
		if(EnemyManager.isClearEnem()) EnemyManager.clearEnemies(false);
		if(ExplosionManager.isClearExplo()) ExplosionManager.clearExplosions(false);

		stage.act();
		stage.draw();

		if(!player.isAlive()) resetGame();

	}
	
	@Override
	public void dispose () {
		super.dispose();
		stage.dispose();
		batch.dispose();
		music.dispose();
		font.dispose();
		assetManager.dispose();
	}

	public void collisionDetection(){
		//Colision disparos
		for (Bullet b : BulletManager.getBullets()) {
			if(b.isPlayerBullet()) {
				//Colision disparos jugador con enemigos
				for (Enemy e : EnemyManager.getEnemies()) {
					if (e.getBoundingBox().overlaps(b.getBoundingBox())) {
						ExplosionManager.addExplosion(e.getX(), e.getY(), assetManager.get("explosion.atlas", TextureAtlas.class));
						e.remove();
						b.remove();
						e.setAlive(false);
						b.setAlive(false);
						BulletManager.setClearB(true);
						EnemyManager.setClearEnem(true);
						addScore(10);
					}
				}
			}else{
				//Colision disparos enemigos con jugador
				if (player.getBoundingBox().overlaps(b.getBoundingBox())) {
					ExplosionManager.addExplosion(player.getX() + player.getBoundingBox().getWidth(), player.getY(), assetManager.get("explosion.atlas", TextureAtlas.class));
					b.remove();
					b.setAlive(false);
					BulletManager.setClearB(true);
					if(!player.isInvulnerable()) player.getHit();
				}
			}
		}

		//Colision jugador con enemigos
		for (Enemy e : EnemyManager.getEnemies()) {
			if (e.getBoundingBox().overlaps(player.getBoundingBox())) {
				ExplosionManager.addExplosion(player.getX() + player.getBoundingBox().getWidth(), player.getY(), assetManager.get("explosion.atlas", TextureAtlas.class));
				e.remove();
				e.setAlive(false);
				EnemyManager.setClearEnem(true);
				if(!player.isInvulnerable()) player.getHit();
			}
		}
	}

	public static void addScore(int val){
		if(RTypeGame.score + val >= 0){
			RTypeGame.score += val;
		}else {
			RTypeGame.score = 0;
		}
	}

	public void resetGame(){

		Vector<Actor> allObjects = new Vector<Actor>();
		allObjects.addAll(BulletManager.getBullets());
		allObjects.addAll(EnemyManager.getEnemies());
		allObjects.addAll(ExplosionManager.getExplosions());
		for (Actor actor : allObjects) {
			actor.remove();
		}

		BulletManager.clearBullets(true);
		EnemyManager.clearEnemies(true);
		ExplosionManager.clearExplosions(true);

		if(score > maxScore) maxScore = score;

		score = 0;

		maxEnemySpawnTime = 5f;

		player.setPosition(0, Gdx.graphics.getHeight()/2-player.getBoundingBox().getHeight()/2);
		player.setLives(maxLives);
	}

}
