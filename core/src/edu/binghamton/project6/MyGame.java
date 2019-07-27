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

	//these are all values we can access from any screen using app.variable
	public float row_height;
	public float col_width;
	public float height;
	public float width;
	public Skin skin;
    public Preferences prefs;

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
        prefs = Gdx.app.getPreferences("leaderboard");

		setScreen(new SplashScreen(this)); //pass in this to constructor to make public variables accessible
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	public void saveToLeaderboard(String name, int score) {
		String last = prefs.getString("10");
		String[] split = last.split(" "); //separate name and score
		if (score < Integer.valueOf(split[1])) {
			//score is less than #10 on the leaderboard and does not qualify for a high score
			//to do - implement posting recent score to web server
			return;
		} else {
			int position = 0; //save position on leaderboard here
			for (int i = 1; i <= 10; ++i) {
				String current = prefs.getString(Integer.valueOf(i).toString());
				split = current.split(" ");
				if (score < Integer.valueOf(split[1])) {
					continue; //score too low, go to next spot on leaderboard
				} else {
					position = i; //found a score to replace
					break; //leave the loop
				}
			}
			//push down the other entries to make room for new high score
			for (int i = 9; i >= position; --i) {
				String data = prefs.getString(Integer.toString(i));
				prefs.putString(Integer.toString(i+1), data);
			}
			//add new high score
			prefs.putString(Integer.toString(position), name + " " + score);
		}
	}
}