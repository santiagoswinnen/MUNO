package muno.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by mlund on 14/06/17.
 */
public class MenuScreen extends AbstractScreen {
	private Texture bg;
	private Texture playButton;
	
	public MenuScreen(Game game){
		super(game);
		bg = new Texture("screen.jpg");
		playButton = new Texture("playbutton.png");
	}

	@Override
	public void render(float dt) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gameCam.update();
		super.batch.setProjectionMatrix(gameCam.combined);
		
		super.batch.begin();
		super.batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		super.batch.draw(playButton, 0, 0);
		if(Gdx.input.isKeyJustPressed(Input.Keys.M)) { //Modo multi player
			game.setScreen(new GameScreen(game));
			dispose();
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.S)) { //Modo single player
			//game.setScreen(new SinglePlayerScreen(game)); ACA VA LA CLASE NUEVA
			dispose();
		}
		super.batch.end();
		
		
	}

	@Override
	public void resize(int width, int height){	}

	@Override
	public void show(){	}

	@Override
	public void hide(){	}

	@Override
	public void pause(){	}
}
