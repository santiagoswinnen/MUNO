package muno.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractGameScreen extends AbstractScreen {
	private UNOGame myGame;
	private Update upd;
	/* para saber si se espera input de color*/
	private boolean isWaitingColor;
	/* PARA saber si el current player dijo que tenia uno*/
	private boolean UNO;
	private ArrayList<Texture> texturesHand;
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
	private Leaderboard leaderboard;

	public AbstractGameScreen(Game game){
		super(game);
		
		this.myGame = new UNOGame();
		this.texturesHand = new ArrayList<Texture>();

		this.tDraw = new Texture("CardBack.jpg");
		this.tRight = new Texture("CardBackRight.jpg");
		this.tUpside = new Texture("CardBackUpside.jpg");
		this.tLeft = new Texture("CardBackLeft.jpg");
		this.arrow = new Texture("arrow.png");
		
		this.font = new BitmapFont();
		this.font.setColor(Color.WHITE);
		this.font.getData().setScale(1.4f);
		this.isWaitingColor = false;
		this.UNO = false;
		this.currentCard = 0;
		this.leaderboard= new Leaderboard(myGame);
	}
	
	public UNOGame getMyGame() {
		return myGame;
	}
	
	public boolean isWaitingColor() {
		return isWaitingColor;
	}

	public void setUNO(boolean bool){
		UNO = bool;
	}
	
	/*devuelve true si un jugador declaro uno*/
	public boolean isUNO(){
		return UNO;
	}

	public void setWaitingColor(boolean waitingColor) {
		isWaitingColor = waitingColor;
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

	public Leaderboard getLeaderboard() {
		return leaderboard;
	}

	public Game getGame() {
		return this.game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public OrthographicCamera getGameCam() {
		return this.gameCam;
	}

	public ArrayList<Texture> getTexturesHand() {
		return texturesHand;
	}

	public void setTexturesHand(ArrayList<Texture> texturesHand) {
		this.texturesHand = texturesHand;
	}

	public Texture gettDraw() {
		return tDraw;
	}

	public Texture gettRight() {
		return tRight;
	}

	public Texture gettUpside() {
		return tUpside;
	}

	public Texture gettLeft() {
		return tLeft;
	}

	public Texture gettDiscard() {
		return tDiscard;
	}

	public void settDiscard(Texture tDiscard) {
		this.tDiscard = tDiscard;
	}

	public Texture getArrow() {
		return arrow;
	}

	public BitmapFont getFont() {
		return font;
	}

	public int getCurrentCard() {
		return currentCard;
	}

	public void setCurrentCard(int currentCard) {
		this.currentCard = currentCard;
	}

	public int[] getPositions() {
		return positions;
	}

	public void setPositions(int[] positions) {
		this.positions = positions;
	}

	public boolean isCardDrawn() {
		return cardDrawn;
	}

	public void setCardDrawn(boolean cardDrawn) {
		this.cardDrawn = cardDrawn;
	}

	public Update getUpd() {
		return upd;
	}

	public void setUpd(Update upd) {
		this.upd = upd;
	}
	
	public abstract void render(float dt);
	
	public void resize(int width, int height){
	}
}