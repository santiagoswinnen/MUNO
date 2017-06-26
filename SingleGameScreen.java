package muno.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class SingleGameScreen extends AbstractGameScreen {

	boolean IAplayed;

	public SingleGameScreen(Game game){
		super(game);

		Player player1 = new Player("Player 1", getMyGame());
		PlayerIA player2 = new PlayerIA("IA Player 2", getMyGame());
		PlayerIA player3 = new PlayerIA("IA Player 3", getMyGame());
		PlayerIA player4 = new PlayerIA("IA Player 4", getMyGame());

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

	public void nextPlayer(){
		getMyGame().getNextPlayer();
		setCardDrawn(false);
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
		drawSideHand(gettRight(), 1, 904);
		drawFrontHand();
		drawSideHand(gettLeft(), 3, 10);
		
		drawData();

		super.batch.end();
	}
	
	public void update() {
		if(getMyGame().getGameState() ==  false){
			game.setScreen(new EndScreen(game, getMyGame().getLeaderboard(), getMyGame().getPlayers()));
		}
		
		if (getMyGame().getCurrentPlayer() instanceof PlayerIA) {
			getUpd().IAplay();
		}
		else {
			if (this.isWaitingColor()) {
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
			getUpd().saveGame("singledata");
			getUpd().loadGame("singledata");
		}
	}
	
	public void drawSideHand(Texture t, int index, int n){
		int size = getMyGame().getPlayers().get(index).getHand().size();
		for(int i = 0; i < size; i++){
			if(size == 1){
				super.batch.draw(t, n, 287.5f, 110, 75);
			}
			else
				super.batch.draw(t, n, 70 + i*435/(size - 1), 110, 75);
		}
	}
	
	public void drawFrontHand(){
		int size = getMyGame().getPlayers().get(2).getHand().size();
		for(int i = 0; i < size; i++){
			if(size == 1){
				super.batch.draw(gettUpside(), 474.5f, 515, 75, 110);
			}
			else
				super.batch.draw(gettUpside(), 150 + i*641/(size - 1), 515, 75, 110);
		}
	}
	
	public void setPositionsArray(){
	}
	
	public void setTexturesHand(){
		getTexturesHand().clear();
		Card card;
		Texture t;
		for(int i = 0; i < getMyGame().getPlayers().get(0).getHand().size(); i++){
			card = getMyGame().getPlayers().get(0).getHand().get(i);
			t = new Texture(card.getColor() + card.getName() + ".png");
			getTexturesHand().add(t);
		}
	}
	
	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}
}
