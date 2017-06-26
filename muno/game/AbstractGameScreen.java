package muno.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public abstract class AbstractGameScreen extends AbstractScreen {
	
	private UNOGame myGame;
	private Updater upd;
	/** Whether or not the game is waiting Color input */
	private boolean isWaitingColor;
	/** Whether current player declared UNO or not */
	private boolean UNO;
	/** Whether current player drew a card or not */
	private boolean cardDrawn;
	/** Selected card index within the player's hand */
	private int currentCard;
    /** Player's order regarding current player so as to print the amount of cards they have in hand */
	private int[] positions;
	private ArrayList<Texture> texturesHand;
	private Texture tDraw;
	private Texture tRight;
	private Texture tUpside;
	private Texture tLeft;
	private Texture tDiscard;
	private Texture arrow;
	private BitmapFont font;
	private BitmapFont font2;
	
	public AbstractGameScreen(Game game) {
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
	
	public boolean isWaitingColor() {
		return isWaitingColor;
	}

	public void setUNO(boolean bool) {
		this.UNO = bool;
	}
	
	public boolean isUNO() {
		return UNO;
	}

	public void setWaitingColor(boolean bool) {
		this.isWaitingColor = bool;
	}

	public boolean chooseColor() {
		if(Gdx.input.isKeyJustPressed(Input.Keys.B)) {
			myGame.getDealer().lastCard().setColor("blue");
			return true;
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.G)) {
			myGame.getDealer().lastCard().setColor("green");
			return true;
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
			myGame.getDealer().lastCard().setColor("red");
			return true;
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
			myGame.getDealer().lastCard().setColor("yellow");
			return true;
		}
		return false;
	}
  
	/** Draw current player's hand */
	public void drawPlayerHand() {
		for(int i = 0; i < getTexturesHand().size(); i++) {
			if(getTexturesHand().size() == 1) {
				getBatch().draw(getTexturesHand().get(i), 512 - getTexturesHand().get(i).getWidth()*0.3f / 2, 25,
						getTexturesHand().get(i).getWidth()*0.3f, getTexturesHand().get(i).getHeight()*0.3f);
				getBatch().draw(getArrow(), 490, 140, 45, 50);
			}
			else {
				getBatch().draw(getTexturesHand().get(i), 150 + i*641/(getTexturesHand().size() - 1), 25,
						getTexturesHand().get(i).getWidth()*0.3f, getTexturesHand().get(i).getHeight()*0.3f);
				getBatch().draw(getArrow(), 165 + getCurrentCard() * 641 / (getTexturesHand().size() - 1), 140, 45, 50);
			}
		}
	}
	
	public abstract void drawSideHand(Texture t, int index, int n);
	public abstract void drawFrontHand();

	public void drawData() {
		/** Draw card back as DrawPile */
		getBatch().draw(getTDraw(), (MyGame.WIDTH / 2) - 95, (MyGame.HEIGHT / 2) - 55, 75, 110);
		
		/** Draw last card from DiscardPile */
		getBatch().draw(getTDiscard(), (MyGame.WIDTH / 2) + 20, (MyGame.HEIGHT / 2) - 55, 75, 110);
		
		getFont().draw(getBatch(), getMyGame().getCurrentPlayer().getName(), 15, 30);
		getFont2().draw(getBatch(), getMyGame().getLeaderboard().toString(), (MyGame.WIDTH / 7),
				(MyGame.HEIGHT / 2) + 100);
		getFont().draw(getBatch(), "Controls: \"D\" draw, \"P\" pass, Arrow keys + \"ENTER\" to choose card,"
				+ "\"S\" save, \"L\" load", (MyGame.WIDTH /7), (MyGame.HEIGHT /2)+130);
		
		if(getMyGame().getCurrentPlayer().getHand().size() == 2) {
			getFont().draw(getBatch(), "Remember to press 1 to throw selected card and declare UNO!",
					(MyGame.WIDTH /6), (MyGame.HEIGHT /3));
		}
		
		if(this.isWaitingColor()) {
			getFont().draw(getBatch(), "Choose color: \"R\" RED, \"Y\" YELLOW, \"B\" BLUE, \"G\" GREEN",
					(MyGame.WIDTH /6), (MyGame.HEIGHT / 2) - 100);
		}
		else {
			getFont().draw(getBatch(), getMyGame().getDealer().lastCard().getColor(),
					(MyGame.WIDTH / 2) + 150, (MyGame.HEIGHT / 2) + 10);
		}
	}
	
	public int[] getPositions() {
		return positions;
	}

	public void setPositions(int[] positions) {
		this.positions = positions;
	}
	
	public UNOGame getMyGame() {
		return myGame;
	}

	public void setMyGame(UNOGame savedGame) {
		this.myGame = savedGame;
	}

	public ArrayList<Texture> getTexturesHand() {
		return texturesHand;
	}

	public void setTexturesHand(ArrayList<Texture> texturesHand) {
		this.texturesHand = texturesHand;
	}

	public Texture getTDraw() {
		return tDraw;
	}

	public Texture getTRight() {
		return tRight;
	}

	public Texture getTUpside() {
		return tUpside;
	}

	public Texture getTLeft() {
		return tLeft;
	}

	public Texture getTDiscard() {
		return tDiscard;
	}

	public void setTDiscard(Texture tDiscard) {
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

	public boolean isCardDrawn() {
		return cardDrawn;
	}

	public void setCardDrawn(boolean cardDrawn) {
		this.cardDrawn = cardDrawn;
	}

	public Updater getUpd() {
		return upd;
	}

	public void setUpd(Updater upd) {
		this.upd = upd;
	}
	
	public abstract void setPositionsArray();
	public abstract void nextPlayer();
	public abstract void setTexturesHand();
	
	public abstract void render(float dt);
	
}