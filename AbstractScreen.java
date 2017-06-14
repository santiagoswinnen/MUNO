package muno.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractScreen implements Screen {
	protected Game game;
	public SpriteBatch batch;
	protected OrthographicCamera gameCam;
	
	public AbstractScreen(Game game){
		this.game = game;
		batch = new SpriteBatch();
		gameCam = new OrthographicCamera();
		gameCam.setToOrtho(false, MyGame.WIDTH, MyGame.HEIGHT);
	}
	
	public abstract void render(float dt);
	public void resize(int width, int height){
	}
	
	public abstract void show();
	public abstract void hide();
	public abstract void pause();
	
	public void resume(){
		
	}
	
	public void dispose(){
		
	}
}
