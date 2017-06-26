package muno.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by mlund on 14/06/17.
 */
public class MenuScreen extends AbstractScreen {
	private Texture bg;
	private BitmapFont font;
	
	public MenuScreen(Game game){
		super(game);
		bg = new Texture("screen.jpg");
        this.font = new BitmapFont();
        this.font.setColor(Color.WHITE);
		this.font.getData().setScale(1.4f);
	}

	@Override
	public void render(float dt) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gameCam.update();
		super.batch.setProjectionMatrix(gameCam.combined);
		
		super.batch.begin();
		super.batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		font.draw(super.batch, "Press \"S\" for Singleplayer Mode or \"M\" for Multiplayer Mode", 240, 70);

		if(Gdx.input.isKeyJustPressed(Input.Keys.M)) { //Modo multi player
			game.setScreen(new MultiGameScreen(game));
			dispose();
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.S)) { //Modo single player
			game.setScreen(new SingleGameScreen(game)); //ACA VA LA CLASE NUEVA
			dispose();
		}
		super.batch.end();
	}

	@Override
	public void resize(int width, int height){
		
	}

	@Override
	public void show(){
		
	}

	@Override
	public void hide(){
		
	}

	@Override
	public void pause(){
		
	}

	@Override
	public void dispose() {
		
	}
}
