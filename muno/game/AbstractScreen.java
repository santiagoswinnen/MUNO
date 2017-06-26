package muno.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by mlund on 14/06/17.
 */
public abstract class AbstractScreen implements Screen {
	
	protected Game game;
	private SpriteBatch batch;
	protected OrthographicCamera gameCam;
	
	public AbstractScreen(Game game) {
		this.game = game;
		this.batch = new SpriteBatch();
		this.gameCam = new OrthographicCamera();
		this.gameCam.setToOrtho(false, MyGame.WIDTH, MyGame.HEIGHT);
	}
	
	public SpriteBatch getBatch() {
		return batch;
	}
	
	public abstract void render(float dt);
	
	public void show() {
	}
	
	public void hide() {
	}
	
	public void pause() {
	}
	
	public void dispose() {
	}
	
	public void resize(int width, int height) {
	}
	
	public void resume() {
	}
	
}
