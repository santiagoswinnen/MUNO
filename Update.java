package muno.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by sswinnen on 17/06/17.
 */
public class Update {
    /*log de la carta anterior a la last card*/
    private Card log;
    private MultiGameScreen screen;

    public Update(MultiGameScreen screen){
        this.screen=screen;
    }

    public void changeToGreen(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.G)){
            screen.nextPlayer();
            ((ActionCard)(screen.getMyGame().getDealer().lastCard())).makeAction("green");
            stopWaiting();
        }
    }
    
    public void changeToBlue(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.B)){
            screen.nextPlayer();
            ((ActionCard)(screen.getMyGame().getDealer().lastCard())).makeAction("blue");
            stopWaiting();
        }
    }
    
    public void changeToYellow(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.Y)){
            screen.nextPlayer();
            ((ActionCard)(screen.getMyGame().getDealer().lastCard())).makeAction("yellow");
            stopWaiting();
        }
    }
    
    public void changeToRed(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
            screen.nextPlayer();
            ((ActionCard)(screen.getMyGame().getDealer().lastCard())).makeAction("red");
            stopWaiting();
        }
    }

    public void stopWaiting(){
        screen.setWaitingColor(false);
        screen.setTexturesHand();
    }

    public void moveRight(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && !screen.isWaitingColor()){
            if(screen.getCurrentCard() == screen.getMyGame().getCurrentPlayer().getHand().size() - 1)
                screen.setCurrentCard(0);
            else
                screen.setCurrentCard(screen.getCurrentCard()+1);
        }
    }
    
    public void moveLeft(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && !screen.isWaitingColor()){
            if(screen.getCurrentCard() == 0)
                screen.setCurrentCard(screen.getMyGame().getCurrentPlayer().getHand().size() - 1);
            else
                screen.setCurrentCard(screen.getCurrentCard()-1);
        }
    }

    public void nonColorCard(){
        if(((Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) || screen.isUNO()) && !screen.isWaitingColor()){
            log = screen.getMyGame().getDealer().lastCard();
            if(screen.getMyGame().getCurrentPlayer().throwCard(screen.getMyGame().getCurrentPlayer().getHand().get(screen.getCurrentCard()))){
                screen.setCurrentCard(0);
                setUNOpenalty();
                roundEndCheck();
                if(screen.getMyGame().getDealer().lastCard().isActionCard()){
                    if(screen.getMyGame().getDealer().lastCard().isWildCard()){
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
                screen.settDiscard(new Texture(screen.getMyGame().getDealer().lastCard().getColor() + screen.getMyGame().getDealer().lastCard().getName() + ".png"));
                screen.setTexturesHand();
            }
        }
    }
    
    public void setUNOpenalty(){
        if(screen.getMyGame().getCurrentPlayer().hasUNO() && !screen.isUNO()){
            screen.getMyGame().getCurrentPlayer().addCard(screen.getMyGame().getDealer().drawCard());
            screen.getMyGame().getCurrentPlayer().addCard(screen.getMyGame().getDealer().drawCard());
        }
        screen.setUNO(false);
    }
    
    /* declarar UNO. Tira la carta seleccionada y declara UNO!. En caso de que no tenga uno levanta dos cartas como penalizacion

     */
    public void callUNO(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1) && !screen.isWaitingColor() ){
            screen.setUNO(true);

        }
    }

    public void cardDraw(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.D) && !screen.isCardDrawn()){
            screen.getMyGame().getCurrentPlayer().addCard(screen.getMyGame().getDealer().drawCard());
            screen.setCardDrawn(true);
            screen.setTexturesHand();
        }
    }

    public void pass(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.P) && screen.isCardDrawn() && !screen.isWaitingColor()){
            screen.setCurrentCard(0);
            screen.nextPlayer();
            screen.setTexturesHand();
        }
    }

    public void roundEndCheck(){
        if (screen.getMyGame().getCurrentPlayer().getHand().size() == 0){
            screen.getLeaderboard().updateScores();
            screen.getMyGame().getDealer().deal();
            screen.getLeaderboard().toString();
        }
    }
}
