package edu.binghamton.project6;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {
    private SpriteBatch batch;
    private Stage stage;

    public MenuScreen() {
        super();
        batch = new SpriteBatch();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        float row_height = Gdx.graphics.getWidth() / 12;
        float col_width = Gdx.graphics.getWidth() / 12;

        TextButton play = new TextButton("Play", skin,"small");
        TextButton controls = new TextButton("Controls", skin,"small");
        TextButton leaderboard = new TextButton("Leaderboard", skin,"small");

        play.getLabel().setFontScale(4.0f);
        play.setSize(col_width*5,row_height);
        play.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*4);
        play.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        controls.getLabel().setFontScale(4.0f);
        controls.setSize(col_width*5,row_height);
        controls.setPosition(col_width*4,(float) (Gdx.graphics.getHeight() - row_height*5.5));

        leaderboard.getLabel().setFontScale(4.0f);
        leaderboard.setSize(col_width*5,row_height);
        leaderboard.setPosition(col_width*4,Gdx.graphics.getHeight() - row_height*7);

        stage.addActor(play);
        stage.addActor(controls);
        stage.addActor(leaderboard);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
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