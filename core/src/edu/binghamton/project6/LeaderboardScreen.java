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

public class LeaderboardScreen implements Screen {
    private final MyGame app;
    private SpriteBatch batch;
    private Texture splashImg;

    private Stage stage;
    private TextButton back;

    public LeaderboardScreen(final MyGame app) {
        super();
        this.app = app;

        batch = new SpriteBatch();
        splashImg = new Texture("splash.png");

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        configureButton();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(splashImg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act();
        stage.draw();
    }

    public void configureButton() {
        float row_height = Gdx.graphics.getHeight() / 12;
        float col_width = Gdx.graphics.getWidth() / 12;
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        back = new TextButton("Back", skin, "small");
        back.getLabel().setFontScale(4.0f);
        back.setSize(col_width * 4, row_height * 1.5F);
        back.setPosition(col_width * 4, row_height);
        back.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                app.setScreen(new MenuScreen(app));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(back);
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
        splashImg.dispose();
        batch.dispose();
    }
}