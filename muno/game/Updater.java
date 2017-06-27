package muno.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import java.io.*;

/**
 * Updates the screen.
 */
public class Updater {
	
    private Card log;
    private AbstractGameScreen screen;

    public Updater(AbstractGameScreen screen) {
        this.screen = screen;
    }

   /** Method the save the game.
    * 
    *  @param filename File name to save the game with.
    */
    public void saveGame(String filename) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            try {
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
                os.writeObject(screen.getMyGame());
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /** Method to load the game.
     * 
     *  @param filename Name of the game to load.
     */
    public void loadGame(String filename) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            try {
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
                UNOGame loadedGame = (UNOGame) is.readObject();
                is.close();
                
                screen.setMyGame(loadedGame);
                
                Card lastCard = screen.getMyGame().getDealer().lastCard();
                if(lastCard.isWildCard())
                	screen.setTDiscard(new Texture("black" + lastCard.getName() + ".png"));
                else
                	screen.setTDiscard(new Texture(lastCard.getColor() + lastCard.getName() + ".png"));

                screen.setPositions(new int[screen.getMyGame().getPlayers().size() - 1]);
                screen.setPositionsArray();

                screen.setTexturesHand();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
   /** If the player presses G then changes the action card color to green*/
    public void changeToGreen() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
            screen.nextPlayer();
            ((ActionCard) (screen.getMyGame().getDealer().lastCard())).makeAction("green");
            stopWaiting();
        }
    }
    
   /** If the player presses B then changes the action card color to blue*/
    public void changeToBlue() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            screen.nextPlayer();
            ((ActionCard) (screen.getMyGame().getDealer().lastCard())).makeAction("blue");
            stopWaiting();
        }
    }
    
    /** If the player presses Y then changes the action card color to yellow*/ 
    public void changeToYellow() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
            screen.nextPlayer();
            ((ActionCard) (screen.getMyGame().getDealer().lastCard())).makeAction("yellow");
            stopWaiting();
        }
    }

    /** If the player presses R then changes the action card color to red*/
    public void changeToRed() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            screen.nextPlayer();
            ((ActionCard) (screen.getMyGame().getDealer().lastCard())).makeAction("red");
            stopWaiting();
        }
    }

    public void stopWaiting() {
        screen.setWaitingColor(false);
        screen.setTexturesHand();
    }
    
    /** Moves the graphic pointer in the screen to the card in the right*/
    public void moveRight() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && !screen.isWaitingColor()) {
            if (screen.getCurrentCard() == screen.getMyGame().getCurrentPlayer().getHand().size() - 1)
                screen.setCurrentCard(0);
            else
                screen.setCurrentCard(screen.getCurrentCard() + 1);
        }
    }
    
    /** Moves the graphic pointer in the screen to the card in the left*/
    public void moveLeft() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && !screen.isWaitingColor()) {
            if (screen.getCurrentCard() == 0)
                screen.setCurrentCard(screen.getMyGame().getCurrentPlayer().getHand().size() - 1);
            else
                screen.setCurrentCard(screen.getCurrentCard() - 1);
        }
    }

    public void IAplay() {
        if (((PlayerIA) screen.getMyGame().getCurrentPlayer()).makeMove()) {
            roundEndCheck();
            Card lastCard = screen.getMyGame().getDealer().lastCard();
            if (lastCard.isActionCard()) {
                if (lastCard.isWildCard()) {
                    ((ActionCard) lastCard).makeAction(((PlayerIA) screen.getMyGame().getCurrentPlayer()).chooseColor());
                    screen.nextPlayer();
                    screen.setTDiscard(new Texture("black" + lastCard.getName() + ".png"));
                } else {
                    screen.nextPlayer();
                    ((ActionCard) lastCard).makeAction();
                    screen.setTDiscard(new Texture(lastCard.getColor() + lastCard.getName() + ".png"));
                }
            } else {
                screen.setTDiscard(new Texture(lastCard.getColor() + lastCard.getName() + ".png"));
                screen.nextPlayer();
            }
            screen.setTexturesHand();
        } else {
            screen.nextPlayer();
        }
    }
    
    /** When a player throws a card this method makes all the validations to continue correctly with the game*/
    public void makeTurn() {
        if(((Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) || screen.isUNO()) && !screen.isWaitingColor()) {
            log = screen.getMyGame().getDealer().lastCard();
            if(screen.getMyGame().getCurrentPlayer().throwCard(screen.getMyGame().getCurrentPlayer().getHand().get(screen.getCurrentCard()))){
                screen.setCurrentCard(0);
                setUNOpenalty();
                roundEndCheck();
                if(screen.getMyGame().getDealer().lastCard().isActionCard()) {
                    if(screen.getMyGame().getDealer().lastCard().isWildCard()) {
                        screen.setWaitingColor(true);
                    }
                    else {
                        screen.nextPlayer();
                        ((ActionCard)screen.getMyGame().getDealer().lastCard()).makeAction();
                    }
                }
                else {
                    screen.nextPlayer();
                }
                screen.setTDiscard(new Texture(screen.getMyGame().getDealer().lastCard().getColor() + screen.getMyGame().getDealer().lastCard().getName() + ".png"));
                screen.setTexturesHand();
            }
        }
        screen.setPositionsArray();
    }
    
   /** Adds two cards to a player's hand as a penalty when had UNO and didn't call it.*/
    public void setUNOpenalty() {
        if(screen.getMyGame().getCurrentPlayer().hasUNO() && !screen.isUNO()) {
            screen.getMyGame().getCurrentPlayer().addCard(screen.getMyGame().getDealer().drawCard());
            screen.getMyGame().getCurrentPlayer().addCard(screen.getMyGame().getDealer().drawCard());
        }
        screen.setUNO(false);
    }
    
   /** Method to allow the players to call UNO when they have only one card left*/ 
    public void callUNO() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1) && !screen.isWaitingColor() ) {
            screen.setUNO(true);
        }
    }

    public void cardDraw() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.D) && !screen.isCardDrawn() && !screen.isCardDrawn()) {
            screen.getMyGame().getCurrentPlayer().addCard(screen.getMyGame().getDealer().drawCard());
            screen.setCardDrawn(true);
            screen.setTexturesHand();
        }
    }

    public void pass() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.P) && screen.isCardDrawn() && !screen.isWaitingColor()) {
            screen.setCurrentCard(0);
            screen.nextPlayer();
            screen.setTexturesHand();
        }
    }

    public void roundEndCheck() {
        if (screen.getMyGame().getCurrentPlayer().getHand().size() == 0) {
            screen.getMyGame().getLeaderboard().updateScores();
            screen.getMyGame().getDealer().deal();
        }
    }
    
}