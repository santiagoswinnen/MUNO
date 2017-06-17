package muno.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;


public abstract class AbstractScreen implements Screen {
	protected Game game;
	public SpriteBatch batch;
	protected OrthographicCamera gameCam;
	private boolean isWaitingColor;
	//protected Viewport gamePort;
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
	private String strPlayer;
	private Update upd;
	private UNOGame myGame;


	public UNOGame getMyGame() {
		return myGame;
	}

	public void setMyGame(UNOGame myGame) {
		this.myGame = myGame;
	}
	
	public AbstractScreen(Game game){
		this.game = game;
		batch = new SpriteBatch();
		gameCam = new OrthographicCamera();
		gameCam.setToOrtho(false, MyGame.WIDTH, MyGame.HEIGHT);
/*		gamePort = new FitViewport(800,480, gameCam);*/
	}
	
	public abstract void render(float dt);
	public void resize(int width, int height){
		//gamePort.update(width, height);
	}
	public abstract void show();
	public abstract void hide();
	public abstract void pause();
	
	public void resume(){
		
	}
	
	public void dispose(){
		
	}
	public boolean isWaitingColor() {
		return isWaitingColor;
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

	public Game getGame() {
		return game;
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
		return gameCam;
	}

	public void setGameCam(OrthographicCamera gameCam) {
		this.gameCam = gameCam;
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

	public void settDraw(Texture tDraw) {
		this.tDraw = tDraw;
	}

	public Texture gettRight() {
		return tRight;
	}

	public void settRight(Texture tRight) {
		this.tRight = tRight;
	}

	public Texture gettUpside() {
		return tUpside;
	}

	public void settUpside(Texture tUpside) {
		this.tUpside = tUpside;
	}

	public Texture gettLeft() {
		return tLeft;
	}

	public void settLeft(Texture tLeft) {
		this.tLeft = tLeft;
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

	public void setArrow(Texture arrow) {
		this.arrow = arrow;
	}

	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
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

	public String getStrPlayer() {
		return strPlayer;
	}

	public void setStrPlayer(String strPlayer) {
		this.strPlayer = strPlayer;
	}

	public Update getUpd() {
		return upd;
	}

	public void setUpd(Update upd) {
		this.upd = upd;
	}
}