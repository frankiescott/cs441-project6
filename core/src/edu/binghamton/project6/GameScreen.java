package edu.binghamton.project6;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
    private final MyGame app;
    private SpriteBatch batch;
    private Texture splashImg, objectImage;

    private float w, h;

    private int score;

    private Array<Rectangle> objects;
    private long spawnTime;

    public void gameEnd(int score) {
        app.setScreen(new ScoreScreen(app, score));
    }

    public GameScreen(final MyGame app) {
        super();
        this.app = app;

        batch = new SpriteBatch();
        splashImg = new Texture("bg.png");
        objectImage = new Texture("block.png");

        score = 0;

        objects = new Array<Rectangle>();
        spawnObject();
    }

    private void spawnObject() {
        Rectangle object = new Rectangle();
        object.x = MathUtils.random(0, app.width - 65);
        object.y = MathUtils.random(0, app.height - 65);
        object.width = 65;
        object.height = 65;
        spawnTime = TimeUtils.millis();
        objects.add(object);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(splashImg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        for(Rectangle object: objects) {
            batch.draw(objectImage, object.x, object.y);
        }
        if (TimeUtils.millis() - spawnTime > 1000) {
            spawnObject();
        }
        batch.end();

        //gameEnd(5000);
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
