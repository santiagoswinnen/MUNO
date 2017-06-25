package muno.game;

import java.io.Serializable;

/**
 * Class that represents a PC player.
 */

public class PlayerIA extends Player implements Serializable {

    private UNOGame game;
    
    /**Creates a new PlayerIA.
     *@param name Player's name
     *@game The Muno Game.
     */
    PlayerIA(String name, UNOGame game){
        super(name, game);
        this.game = game;
    }

    /**Looks for a card to throw
     * if none of them can be thrown then just gets a card.
     */
    public boolean makeMove(){
        for(int ind=0; ind<getHand().size();ind++) {
            if (getHand().get(ind).match(getGame().getDealer().lastCard())) {
                throwCard(getHand().get(ind));
                return true;
            }
        }
        Card d = getGame().getDealer().drawCard();
        addCard(d);
        if (d.match(getGame().getDealer().lastCard())) {
            throwCard(d);
            return true;
        } else {
            return false;
        }
    }
    
    /**Chooses a color randomly*/
    public String chooseColor(){
        int i = 1 + (int)(Math.random() * ((4 - 1)+ 1));
        return Card.getCardColors()[i];
    }
}
