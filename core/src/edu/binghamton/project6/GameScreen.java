package edu.binghamton.project6;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {
    private final MyGame app;
    private SpriteBatch batch;
    private Stage stage;
    private Texture splashImg, objectImage;
    private BitmapFont titleFont;

    private long startCount;
    private int countdown;
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
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        splashImg = new Texture("bg.png");
        objectImage = new Texture("block.png");

        score = 0;

        FreeTypeFontGenerator title = new FreeTypeFontGenerator(Gdx.files.internal("fonts/consola.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter titleParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        titleParameter.size = 148;
        titleFont = title.generateFont(titleParameter);
        countdown = 3;
        startCount = TimeUtils.millis();

        objects = new Array<Rectangle>();
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

        //batch rendering
        batch.begin();
        //draw background
        batch.draw(splashImg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //check if we are in the countdown period upon start of the game
        if (countdown > 0) {
            titleFont.draw(batch, Integer.toString(countdown),app.width / 2,app.height / 2);
            if (TimeUtils.millis() - startCount > 1000) {
                countdown -= 1;
                startCount = TimeUtils.millis();
            }
            batch.end();
        } else { //if we arent, generate game objects
            //draws array of rectangles to screen
            for (Rectangle object : objects) {
                batch.draw(objectImage, object.x, object.y);
            }
            //spawns a new rectangle every second
            if (TimeUtils.millis() - spawnTime > 1000) {
                spawnObject();
            }
            //listens for input to see if a rectangle is touched
            if (Gdx.input.justTouched()) {
                int touched = 0;
                Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                stage.getCamera().unproject(touch);
                for (int i = 0; i < objects.size; ++i) {
                    if (objects.get(i).contains(touch.x, touch.y)) {
                        touched++; //will use for bonus score if user gets rid of 2 boxes at once
                        objects.removeIndex(i);
                    }
                }
            }
            batch.end();
        }

        stage.act();
        stage.draw();
        //gameEnd(5000);
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
        titleFont.dispose();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    //unneeded overrides from interfaces
    @Override
    public void hide() {
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
}
