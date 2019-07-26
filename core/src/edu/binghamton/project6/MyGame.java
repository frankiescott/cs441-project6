package edu.binghamton.project6;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MyGame extends Game{
	SpriteBatch batch;
	Texture img;

	public float row_height;
	public float col_width;
	public float height;
	public float width;
	public Skin skin;

	public MyGame() {
		super();
	}

	@Override
	public void create () {
		height = Gdx.graphics.getHeight();
		row_height = Gdx.graphics.getHeight() / 12F;
		col_width = Gdx.graphics.getWidth() / 12F;
		width = Gdx.graphics.getWidth();
		skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
