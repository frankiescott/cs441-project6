package edu.binghamton.project6;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
    private final MyGame app;
    private SpriteBatch batch;
    private Texture splashImg;

    private float w, h;

    private int score;

    public void gameEnd(int score) {
        app.setScreen(new ScoreScreen(app, score));
    }

    public GameScreen(final MyGame app) {
        super();
        this.app = app;

        batch = new SpriteBatch();
        splashImg = new Texture("bg.png");

        score = 0;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(splashImg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        gameEnd(5000);
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
        w = width;
        h = height;
    }

    @Override
    public void dispose() {
        splashImg.dispose();
        batch.dispose();
    }
}
