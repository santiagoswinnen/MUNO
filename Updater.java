package muno.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import java.io.*;

/**
 * Created by sswinnen on 17/06/17.
 */
public class Updater {
	
    private Card log;
    private AbstractGameScreen screen;

    public Updater(AbstractGameScreen screen) {
        this.screen = screen;
    }

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

    public void changeToGreen() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
            screen.nextPlayer();
            ((ActionCard) (screen.getMyGame().getDealer().lastCard())).makeAction("green");
            stopWaiting();
        }
    }

    public void changeToBlue() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            screen.nextPlayer();
            ((ActionCard) (screen.getMyGame().getDealer().lastCard())).makeAction("blue");
            stopWaiting();
        }
    }

    public void changeToYellow() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
            screen.nextPlayer();
            ((ActionCard) (screen.getMyGame().getDealer().lastCard())).makeAction("yellow");
            stopWaiting();
        }
    }

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

    public void moveRight() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && !screen.isWaitingColor()) {
            if (screen.getCurrentCard() == screen.getMyGame().getCurrentPlayer().getHand().size() - 1)
                screen.setCurrentCard(0);
            else
                screen.setCurrentCard(screen.getCurrentCard() + 1);
        }
    }

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

    public void nonColorCard() {
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
    
    public void setUNOpenalty() {
        if(screen.getMyGame().getCurrentPlayer().hasUNO() && !screen.isUNO()) {
            screen.getMyGame().getCurrentPlayer().addCard(screen.getMyGame().getDealer().drawCard());
            screen.getMyGame().getCurrentPlayer().addCard(screen.getMyGame().getDealer().drawCard());
        }
        screen.setUNO(false);
    }
    
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