package edu.binghamton.project6;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends Game {
	SpriteBatch batch;
	Texture img;

	public MyGame() {
		super();
	}

	@Override
	public void create () {
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
