package muno.game;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *Class that represents the discard pile.
 */
public class DiscardPile implements Serializable {
    private ArrayList<Card> discardPile;
    
    /**Constructor method of the class. Creates a discard pile.*/
    public DiscardPile(){
        discardPile = new ArrayList<Card>();
    }
    
    /**Gets the last card of the discard pile.
	 *
	 *@return last card in the pile.
	 */
    public Card lastCard(){
        return getDiscardPile().get(getDiscardPile().size() - 1);
    }
   
    /**
	 *Adds a card to the discard pile. 
	 *If the last card was a wild card then changes its color to black before adding the new one.
	 */
    public void throwCard(Card card){
        if (!isEmpty() && lastCard().isWildCard()){
            lastCard().setColor("black");
        }
        getDiscardPile().add(card);
    }
    
    /**Gets the discard pile.
	 *
	 *@return this.dicardPile The discard pile.
	 */
    private ArrayList<Card> getDiscardPile(){
        return this.discardPile;
    }
    
    /**Returns the size of the discard pile*/
    private int size(){
        return getDiscardPile().size();
    }
   
   	/**Removes all the cards from the discard pile except the last one.
	 *
	 *@return aux An array with all the cards that were on the discard pile except the last one.
	 */
    public ArrayList<Card> askCards(){
        Card cardAux= lastCard();
        ArrayList <Card> aux = new ArrayList<Card>();
        for (int t = 0 ; t < size() - 2; t++ ) {
            aux.add(getDiscardPile().remove(t));
        }
        getDiscardPile().clear();
        throwCard(cardAux);
        return aux;
    }
    
    /**Checks if the discard pile is empty*/
    public boolean isEmpty(){
    	return this.discardPile.size() == 0;
    }

}
