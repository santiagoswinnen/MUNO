package muno.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by mlund on 14/06/17.
 */
public class GameScreen extends AbstractScreen {

	public GameScreen(Game game) {
		super(game);
		
		setMyGame(new UNOGame());
		
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
		
		settDraw(new Texture("CardBack.jpg"));
		settRight(new Texture("CardBackRight.jpg"));
		settUpside(new Texture("CardBackUpside.jpg"));
		settLeft(new Texture("CardBackLeft.jpg"));
		setArrow(new Texture("arrow.png"));
		
		texturesHand = new ArrayList<Texture>();
		
		setCurrentCard(0);
		setPositions(new int[getMyGame().getPlayers().size() - 1]);
		nextPlayer();
		setTexturesHand();
		
		settDiscard(new Texture(getMyGame().getDealer().lastCard().getColor() + getMyGame().getDealer().lastCard().getName() + ".png"));
		
		setFont(new BitmapFont());
		getFont().setColor(Color.WHITE);
		getFont().getData().setScale(1.4f);
		setWaitingColor(false);
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
		
		/* Dibujo las cartas del jugador actual */
		for(int i = 0; i < texturesHand.size(); i++){
			super.batch.draw(texturesHand.get(i), 150 + i*641/(texturesHand.size() - 1), 25, texturesHand.get(i).getWidth()*0.3f, texturesHand.get(i).getHeight()*0.3f);
		}
		
		/* Dibujo las cartas de los jugadores a la derecha, enfrente e izquierda del currentPlayer */
		for(int i = 0; i < getMyGame().getPlayers().get(getPositions()[0]).getHand().size(); i++){
			super.batch.draw(gettRight(), 904, 70 + i*435/(getMyGame().getPlayers().get(getPositions()[0]).getHand().size() - 1), 110, 75);
		}
		
		for(int i = 0; i < getMyGame().getPlayers().get(getPositions()[1]).getHand().size(); i++){
			super.batch.draw(gettUpside(), 150 + i*641/(getMyGame().getPlayers().get(getPositions()[1]).getHand().size() - 1), 515, 75, 110);
		}
		
		for(int i = 0; i < getMyGame().getPlayers().get(getPositions()[2]).getHand().size(); i++){
			super.batch.draw(gettLeft(), 10, 70 + i*435/(getMyGame().getPlayers().get(getPositions()[2]).getHand().size() - 1), 110, 75);
		}
		
		/* Dibujo DrawPile */
		super.batch.draw(gettDraw(), (MyGame.WIDTH / 2) - 95, (MyGame.HEIGHT / 2) - 55, 75, 110);
		
		/* Dibujo DiscardPile */
		super.batch.draw(gettDiscard(), (MyGame.WIDTH / 2) + 20, (MyGame.HEIGHT / 2) - 55, 75, 110);
		
		super.batch.draw(getArrow(), 165 + getCurrentCard() * 641 / (texturesHand.size() - 1), 140, 45, 50);
		getFont().draw(super.batch, getStrPlayer(), 15, 30);
		getFont().draw(super.batch, getMyGame().getLeaderboard().toString(), (MyGame.WIDTH /6), (MyGame.HEIGHT /2)+100);
		
		super.batch.end();
	}


	public void update(){
//		if(myGame.getGameState() ==  false){
//			setScreen(new EndScreen(game));
//		}
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
		setStrPlayer(getMyGame().getCurrentPlayer().getName());
	}
	
	/* Vacia el ArrayList y mete las texturas de las cartas del nuevo currentPlayer */
	public void setTexturesHand(){
		texturesHand.clear();
		Card card;
		Texture t;
		for(int i = 0; i < getMyGame().getCurrentPlayer().getHand().size(); i++){
			card = getMyGame().getCurrentPlayer().getHand().get(i);
			t = new Texture(card.getColor() + card.getName() + ".png");
			texturesHand.add(t);
		}
	}
	


	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

}
