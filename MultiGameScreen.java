package muno.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created by mlund on 14/06/17.
 */
public class MultiGameScreen extends AbstractGameScreen {

	public MultiGameScreen(Game game) {
		super(game);
		
		Player player1 = new Player("Player 1", getMyGame());
		Player player2 = new Player("Player 2", getMyGame());
		Player player3 = new Player("Player 3", getMyGame());
		Player player4 = new Player("Player 4", getMyGame());
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		
		getMyGame().addPlayers(players);
		
		getMyGame().getDealer().deal();
		settDiscard(new Texture(getMyGame().getDealer().lastCard().getColor() + getMyGame().getDealer().lastCard().getName() + ".png"));
		
		setPositions(new int[getMyGame().getPlayers().size() - 1]);
		
		nextPlayer();
		setTexturesHand();
		setUpd(new Update(this));
	}

	@Override
	public void render(float dt) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameCam.update();
		super.batch.setProjectionMatrix(gameCam.combined);
		
		this.update();
		
		super.batch.begin();
		
		drawPlayerHand();
		drawSideHand(gettRight(), 0, 904);
		drawSideHand(gettLeft(), 2, 10);
		drawFrontHand();
		
		drawData();

		super.batch.end();
	}

	public void update() {
		if(getMyGame().getGameState() ==  false){
			game.setScreen(new EndScreen(game, getMyGame().getLeaderboard(), getMyGame().getPlayers()));
		}

		if(this.isWaitingColor()){
			getUpd().changeToRed();
			getUpd().changeToGreen();
			getUpd().changeToBlue();
			getUpd().changeToYellow();
		}
		getUpd().moveLeft();
		getUpd().moveRight();
		getUpd().nonColorCard();
		getUpd().cardDraw();
		getUpd().pass();
		getUpd().callUNO();
		getUpd().saveGame("multidata.ser");
		getUpd().loadGame("multidata.ser");
	}
	
	public void drawSideHand(Texture t, int index, int n){
		int size = getMyGame().getPlayers().get(getPositions()[index]).getHand().size();
		for(int i = 0; i < size; i++){
			if(size == 1){
				super.batch.draw(t, n, 287.5f, 110, 75);
			}
			else
				super.batch.draw(t, n, 70 + i*435/(size - 1), 110, 75);
		}
	}
	
	public void drawFrontHand(){
		int size = getMyGame().getPlayers().get(getPositions()[1]).getHand().size();
		for(int i = 0; i < size; i++){
			if(size == 1){
				super.batch.draw(gettUpside(), 474.5f, 515, 75, 110);
			}
			else
				super.batch.draw(gettUpside(), 150 + i*641/(size - 1), 515, 75, 110);
		}
	}
	
	/* Setea las positions correspondientes a los siguientes jugadores de acuerdo a su posicion
	 * en el ArrayList<Player> de myGame
	 */
	public void setPositionsArray(){
		int num = getMyGame().getPlayers().indexOf(getMyGame().getCurrentPlayer());
		for(int i = 0; i < 3; i++){
			num++;
			if(num > getMyGame().getPlayers().size() - 1)
				num = 0;
			getPositions()[i] = num;
		}
	}

	public void nextPlayer(){
		getMyGame().getNextPlayer();
		setPositionsArray();
		setCardDrawn(false);
	}
	
	/* Vacia el ArrayList y mete las texturas de las cartas del nuevo currentPlayer */
	public void setTexturesHand(){
		getTexturesHand().clear();
		Card card;
		Texture t;
		for(int i = 0; i < getMyGame().getCurrentPlayer().getHand().size(); i++){
			card = getMyGame().getCurrentPlayer().getHand().get(i);
			t = new Texture(card.getColor() + card.getName() + ".png");
			getTexturesHand().add(t);
		}
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
	
	}
}
