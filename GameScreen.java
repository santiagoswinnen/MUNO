package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.Card;
import com.mygdx.game.CardDeck;

public class GameScreen extends AbstractScreen {
	TextureAtlas atlas;
	CardDeck myDeck;
	
	public GameScreen(Game game){
		super(game);
		atlas  = new TextureAtlas(Gdx.files.internal("UNO_Deck.atlas"));
		myDeck = new CardDeck(atlas);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gameCam.update();
		super.batch.setProjectionMatrix(gameCam.combined);
		
		super.batch.begin();
		Card card1 = myDeck.getCardDeck().get(9);
		card1.draw(super.batch, 0, 0);
		myDeck.getCardDeck().get(0).draw(super.batch, 300, 0);
		super.batch.end();
	}
	
	@Override
	public void resize(int width, int height) {
		
	}
	
	@Override
	public void show() {
		//myMusic.play();
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {	}

	@Override
	public void resume() {	}

}
