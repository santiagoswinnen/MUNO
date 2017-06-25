package muno.game;

import java.io.Serializable;

/**
 * Class that represents a PC player.
 */
public class PlayerIA extends Player implements Serializable {

    private UNOGame game;
    PlayerIA(String name, UNOGame game){
        super(name, game);
        this.game = game;
    }
   
    /**Looks for a card to throw, 
     *if none of them can be thrown then just gets a card.
     */
    public void makeMove(){
        boolean foundCard = false;
        for(int ind=0; ind<getHand().size();ind++) {
            if (!foundCard && getHand().get(ind).match(getGame().getDealer().lastCard())) {
                throwCard(getHand().get(ind));
                foundCard = true;
            }
        }
        if(!foundCard) {
            addCard(getGame().getDealer().drawCard());
            game.getNextPlayer();
        }
    }
    public String chooseColor(){
        int i = 1 + (int)(Math.random() * ((4 - 1)+ 1));
        return Card.getCardColors()[i];
    }
}
