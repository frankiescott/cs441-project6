package edu.binghamton.project6;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreScreen implements Screen {
    private final MyGame app;
    private SpriteBatch batch;
    private Texture splashImg;

    public ScoreScreen(final MyGame app) {
        super();
        this.app = app;
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        splashImg = new Texture("bg.png");

        batch.begin();
        batch.draw(splashImg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {
        batch.dispose();
        splashImg.dispose();
    }
}
