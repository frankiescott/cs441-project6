package edu.binghamton.project6;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import static java.lang.Math.abs;

public class GameScreen implements Screen {
    private final MyGame app;
    private SpriteBatch batch;
    private Texture splashImg;

    private Player player;
    private float w, h;
    private float gravity;
    private BitmapFont textFont;

    public GameScreen(final MyGame app) {
        super();
        this.app = app;

        batch = new SpriteBatch();
        splashImg = new Texture("bg.png");

        gravity = -4;
        player = new Player(0, 0, 15, 0, "badlogic.jpg", 500);

        FreeTypeFontGenerator title = new FreeTypeFontGenerator(Gdx.files.internal("fonts/BRUSHSCI.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter titleParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        titleParameter.size = 148;
        textFont = title.generateFont(titleParameter);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(splashImg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        textFont.draw(batch, "Game Screen",app.col_width*4,Gdx.graphics.getHeight()-50);
        batch.end();

        player.update();
        player.render();
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

    class GameObject {
        float x, y;
        float dx, dy;
        Texture img;
        int imgWidth, imgHeight;

        GameObject(int x, int y, int dx, int dy, String img) {
            this.x = (float) x;
            this.y = (float) y;
            this.dx = (float) dx;
            this.dy = (float) dy;
            this.img = new Texture(img);
            this.imgHeight = this.img.getHeight();
            this.imgWidth = this.img.getWidth();
        }

        public void render() {
            batch.begin();
            batch.draw(this.img, this.x, this.y);
            batch.end();
        }

        public void update() {
            //jumping physics
            if ((this.y <= 4) && (abs(this.dy) <= 4)) {
                this.y = 0;
                this.dy = 0;
            } else {
                this.dy = this.dy + gravity;
            }

            //update position on screen
            this.x = this.x + this.dx;
            this.y = this.y + this.dy;

            //wall collision
            if ((this.x > (w - this.imgWidth)) || (this.x < 0)) {
                this.dx = -this.dx;
            }

            //ground collision
            if (this.y < 0) {
                this.dy = 0;
                this.y = 0;
            }
        }
    }

    class Player extends GameObject {
        int health;

        Player(int x, int y, int dx, int dy, String img, int health) {
            super(x, y, dx, dy, img);
            this.health = health;
        }

        @Override
        public void update() {
            //jumping physics
            if ((this.y <= 4) && (abs(this.dy) <= 4)) {
                this.y = 0;
                this.dy = 0;
            } else {
                this.dy = this.dy + gravity;
            }

            //update position on screen
            this.x = this.x + this.dx;
            this.y = this.y + this.dy;

            //wall collision
            if ((this.x > (w - this.imgWidth)) || (this.x < 0)) {
                this.dx = -this.dx;
            }

            //ground collision
            if (this.y < 0) {
                this.dy = 0;
                this.y = 0;
            }
        }
    }
}
