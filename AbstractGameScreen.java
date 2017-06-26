package muno.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

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
	private BitmapFont font2;
	/* Indice de la carta seleccionada en la mano del jugador actual */
	private int currentCard;
	/* Contiene los indices para mostrar los reversos de las cartas que no son del actual */
	private int[] positions;
	private boolean cardDrawn;


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
		this.font.getData().setScale(1.3f);
		
		this.font2 = new BitmapFont();
		this.font2.setColor(Color.WHITE);
		
		this.isWaitingColor = false;
		this.UNO = false;
		this.currentCard = 0;

	}
	
	public UNOGame getMyGame() {
		return myGame;
	}

	public void setMyGame(UNOGame savedGame) {
		myGame = savedGame;
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
  
	public abstract void nextPlayer();
	public abstract void setTexturesHand();
	public abstract void setPositionsArray();
	
	public void drawPlayerHand(){
		/* Dibujo las cartas del jugador actual */
		for(int i = 0; i < getTexturesHand().size(); i++){
			if(getTexturesHand().size() == 1){
				super.batch.draw(getTexturesHand().get(i), 512 - getTexturesHand().get(i).getWidth()*0.3f / 2, 25, getTexturesHand().get(i).getWidth()*0.3f, getTexturesHand().get(i).getHeight()*0.3f);
				super.batch.draw(getArrow(), 490, 140, 45, 50);
			}
			else {
				super.batch.draw(getTexturesHand().get(i), 150 + i*641/(getTexturesHand().size() - 1), 25, getTexturesHand().get(i).getWidth()*0.3f, getTexturesHand().get(i).getHeight()*0.3f);
				super.batch.draw(getArrow(), 165 + getCurrentCard() * 641 / (getTexturesHand().size() - 1), 140, 45, 50);
			}
		}
	}
	
	public abstract void drawSideHand(Texture t, int index, int n);
	public abstract void drawFrontHand();

	public void drawData(){
		/* Dibujo DrawPile */
		super.batch.draw(gettDraw(), (MyGame.WIDTH / 2) - 95, (MyGame.HEIGHT / 2) - 55, 75, 110);
		
		/* Dibujo DiscardPile */
		super.batch.draw(gettDiscard(), (MyGame.WIDTH / 2) + 20, (MyGame.HEIGHT / 2) - 55, 75, 110);
		
		getFont().draw(super.batch, getMyGame().getCurrentPlayer().getName(), 15, 30);
		getFont2().draw(super.batch, getMyGame().getLeaderboard().toString(), (MyGame.WIDTH / 7), (MyGame.HEIGHT / 2) + 100);
		getFont().draw(super.batch, "Controls: \"D\" draw, \"P\" pass, Arrow keys + \"ENTER\" to choose card, \"S\" save, \"L\" load", (MyGame.WIDTH /7), (MyGame.HEIGHT /2)+130);
		if(getMyGame().getCurrentPlayer().getHand().size() == 2){
			getFont().draw(super.batch, "Remember to press 1 to throw selected card and declare UNO!", (MyGame.WIDTH /6), (MyGame.HEIGHT /3));
		}
		if(this.isWaitingColor()){
			getFont().draw(super.batch, "Choose color: \"R\" RED, \"Y\" YELLOW, \"B\" BLUE, \"G\" GREEN", (MyGame.WIDTH /6), (MyGame.HEIGHT / 2) - 100);
		}
		else {
			getFont().draw(super.batch, getMyGame().getDealer().lastCard().getColor(), (MyGame.WIDTH / 2) + 150, (MyGame.HEIGHT / 2) + 10);
		}
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
	
	public BitmapFont getFont2() {
		return font2;
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