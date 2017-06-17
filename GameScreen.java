package muno.game;

import java.util.ArrayList;

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
public class GameScreen extends AbstractScreen {
	private UNOGame myGame;
	
	ArrayList<Texture> texturesHand;
	private Texture tDraw;
	private Texture tRight;
	private Texture tUpside;
	private Texture tLeft;
	private Texture tDiscard;
	private Texture arrow;
	private BitmapFont font;
	/* Indice de la carta seleccionada en la mano del jugador actual */
	private int currentCard;
	/* Contiene los indices para mostrar los reversos de las cartas que no son del actual */
	private int[] positions;
	private boolean cardDrawn;
	private boolean isWaitingColor;
	private String strPlayer;
	private UpdateLibrary lib;


	public UNOGame getMyGame() {
		return myGame;
	}

	public void setMyGame(UNOGame myGame) {
		this.myGame = myGame;
	}

	public boolean isWaitingColor() {
		return isWaitingColor;
	}

	public void setWaitingColor(boolean waitingColor) {
		isWaitingColor = waitingColor;
	}

	public GameScreen(Game game) {
		super(game);
		
		myGame = new UNOGame();
		
		Player player1 = new Player("Player 1", myGame);
		Player player2 = new Player("Player 2", myGame);
		Player player3 = new Player("Player 3", myGame);
		Player player4 = new Player("Player 4", myGame);
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		
		myGame.addPlayers(players);
		
		myGame.getDealer().deal();
		
		tDraw = new Texture("CardBack.jpg");
		tRight = new Texture("CardBackRight.jpg");
		tUpside = new Texture("CardBackUpside.jpg");
		tLeft = new Texture("CardBackLeft.jpg");
		arrow = new Texture("arrow.png");
		
		texturesHand = new ArrayList<Texture>();
		
		currentCard = 0;
		positions = new int[myGame.getPlayers().size() - 1];
		nextPlayer();
		setTexturesHand();
		
		tDiscard = new Texture(myGame.getDealer().lastCard().getColor() + myGame.getDealer().lastCard().getName() + ".png");
		
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(1.4f);
		isWaitingColor=false;
		lib= new UpdateLibrary(this);

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
		for(int i = 0; i < myGame.getPlayers().get(positions[0]).getHand().size(); i++){
			super.batch.draw(tRight, 904, 70 + i*435/(myGame.getPlayers().get(positions[0]).getHand().size() - 1), 110, 75);
		}
		
		for(int i = 0; i < myGame.getPlayers().get(positions[1]).getHand().size(); i++){
			super.batch.draw(tUpside, 150 + i*641/(myGame.getPlayers().get(positions[1]).getHand().size() - 1), 515, 75, 110);
		}
		
		for(int i = 0; i < myGame.getPlayers().get(positions[2]).getHand().size(); i++){
			super.batch.draw(tLeft, 10, 70 + i*435/(myGame.getPlayers().get(positions[2]).getHand().size() - 1), 110, 75);
		}
		
		/* Dibujo DrawPile */
		super.batch.draw(tDraw, (MyGame.WIDTH / 2) - 95, (MyGame.HEIGHT / 2) - 55, 75, 110);
		
		/* Dibujo DiscardPile */
		super.batch.draw(tDiscard, (MyGame.WIDTH / 2) + 20, (MyGame.HEIGHT / 2) - 55, 75, 110);
		
		super.batch.draw(arrow, 165 + currentCard * 641 / (texturesHand.size() - 1), 140, 45, 50);
		font.draw(super.batch, strPlayer, 15, 30);
		font.draw(super.batch, myGame.getLeaderboard().toString(), (MyGame.WIDTH /6), (MyGame.HEIGHT /2)+100);
		
		super.batch.end();
	}

	public int getCurrentCard() {
		return currentCard;
	}

	public void setCurrentCard(int currentCard) {
		this.currentCard = currentCard;
	}

	public Texture gettDiscard() {
		return tDiscard;
	}

	public void settDiscard(Texture tDiscard) {
		this.tDiscard = tDiscard;
	}

	public boolean isCardDrawn() {
		return cardDrawn;
	}

	public void setCardDrawn(boolean cardDrawn) {
		this.cardDrawn = cardDrawn;
	}

	public void update(){
//		if(myGame.getGameState() ==  false){
//			setScreen(new EndScreen(game));
//		}
		if(isWaitingColor){
			lib.changeToRed();
			lib.changeToGreen();
			lib.changeToBlue();
			lib.changeToYellow();
		}
		lib.moveLeft();
		lib.moveRight();
		lib.nonColorCard();
		lib.cardDraw();
		lib.pass();
	}
	
	/* Setea las positions correspondientes a los siguientes jugadores de acuerdo a su posicion
	 * en el ArrayList<Player> de myGame
	 */
	public void setPositions(){
		int num = myGame.getPlayers().indexOf(myGame.getCurrentPlayer());
		for(int i = 0; i < 3; i++){
			num++;
			if(num > myGame.getPlayers().size() - 1)
				num = 0;
			positions[i] = num;
		}
	}

	public void nextPlayer(){
		myGame.getNextPlayer();
		setPositions();
		cardDrawn = false;
		strPlayer = myGame.getCurrentPlayer().getName();
	}
	
	/* Vacia el ArrayList y mete las texturas de las cartas del nuevo currentPlayer */
	public void setTexturesHand(){
		texturesHand.clear();
		Card card;
		Texture t;
		for(int i = 0; i < myGame.getCurrentPlayer().getHand().size(); i++){
			card = myGame.getCurrentPlayer().getHand().get(i);
			t = new Texture(card.getColor() + card.getName() + ".png");
			texturesHand.add(t);
		}
	}
	
	public boolean chooseColor(){
		if(Gdx.input.isKeyJustPressed(Input.Keys.B)){
			myGame.getDealer().lastCard().setColor("blue");
			return true;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.G)){
			myGame.getDealer().lastCard().setColor("green");
			return true;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
			myGame.getDealer().lastCard().setColor("red");
			return true;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.Y)){
			myGame.getDealer().lastCard().setColor("yellow");
			return true;
		}
		return false;
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
