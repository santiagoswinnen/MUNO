package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MainGame;


public abstract class AbstractScreen implements Screen {
	protected Game game;
	public SpriteBatch batch;
	protected OrthographicCamera gameCam;
	protected Viewport gamePort;
	
	public AbstractScreen(Game game){
		this.game = game;
		batch = new SpriteBatch();
		gameCam = new OrthographicCamera();
		gameCam.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);
		gamePort = new FitViewport(800, 480, gameCam);
	}
	
	public abstract void render(float dt);
	public void resize(int width, int height){
		gamePort.update(width, height);
	}
	public abstract void show();
	public abstract void hide();
	public abstract void pause();
	
	public void resume(){
		
	}
	
	public void dispose(){
		
	}
}