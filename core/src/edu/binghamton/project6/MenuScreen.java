package edu.binghamton.project6;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {
    private final MyGame app;
    private SpriteBatch batch;
    private Texture splashImg;

    private Stage stage;
    private TextButton play, controls, leaderboard;

    public MenuScreen(final MyGame app) {
        super();
        this.app = app;
        batch = new SpriteBatch();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        configureButtons();
    }

    public void configureButtons() {
        float row_height = Gdx.graphics.getHeight() / 12;
        float col_width = Gdx.graphics.getWidth() / 12;
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        play = new TextButton("Play", skin,"small");
        controls = new TextButton("How To Play", skin,"small");
        leaderboard = new TextButton("Leaderboard", skin,"small");

        play.getLabel().setFontScale(4.0f);
        play.setSize(col_width*4,row_height*1.5F);
        play.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*7);

        controls.getLabel().setFontScale(4.0f);
        controls.setSize(col_width*4,row_height*1.5F);
        controls.setPosition(col_width*4,(float) (Gdx.graphics.getHeight() - row_height*9));

        leaderboard.getLabel().setFontScale(4.0f);
        leaderboard.setSize(col_width*4,row_height*1.5F);
        leaderboard.setPosition(col_width*4,Gdx.graphics.getHeight() - row_height*11);

        play.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                app.setScreen(new GameScreen(app));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        controls.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                app.setScreen(new HowToPlayScreen(app));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(play);
        stage.addActor(controls);
        stage.addActor(leaderboard);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        splashImg = new Texture("splash.png");

        batch.begin();
        batch.draw(splashImg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act();
        stage.draw();
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
    }
}