package muno.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that represents the draw pile of the UNO game.
 */
public class DrawPile implements Serializable{
    private UNOGame game;
    private ArrayList<Card> drawPile;
    
   /** Constructor method of the class. Creates the draw pile.
    *@param game Reference to the game.
    */
    public DrawPile(UNOGame game) {
        drawPile = new ArrayList<Card>();
        this.game = game;
        newDeck();
        
    }
    
    /**Checks if the draw pile is empty
	 *
	 *@return true if the pile is empty, false if not. */
    public boolean isEmpty(){
        return size() == 0;
    }
    
    /** Returns the size of the draw pile*/
    private int size(){
        return getDrawPile().size();
    }
    
    /**Method to shuffle the cards of the draw pile*/ 
    private void shuffle(){
        Collections.shuffle(getDrawPile());
    }
    
    /** Gets a card from the draw pile, removing the card from it.
     *@return last card in the arraylist
     */
    public Card getCard() {
        return getDrawPile().remove(getDrawPile().size() - 1);
    }
    
    /**Method to get the draw pile*/
    private ArrayList<Card> getDrawPile(){
      return this.drawPile;
    }
    
   /**Creates a new deck of Muno Cards*/
    public void newDeck() {
        String[] cardName = Card.getCardNames();
        String[] cardColors = Card.getCardColors();
        for (int color = 1; color < cardColors.length; color++ ){
            for (int name = 0; name < cardName.length - 3; name++){
                    getDrawPile().add(CardFactory.getCard(cardColors[color], cardName[name], getGame()));
                    getDrawPile().add(CardFactory.getCard(cardColors[color], cardName[name], getGame()));
            }
        }
        for (int count = 0; count < 4; count++ ){
            getDrawPile().add(CardFactory.getCard("black","Wild", getGame()));
            getDrawPile().add(CardFactory.getCard("black","DrawFour", getGame())); 
            getDrawPile().add(CardFactory.getCard("black","Mirror",getGame()));
        }
        shuffle();
    }
    
    /**
     * Receives an array of cards and sets the drawpile, then shuffles the cards.
	 *
	 *@param cardArray ArrayList of cards to set the drawpile.
     * 
     *
     *@deprecated This method is for when the cards of the
     *      Drawpile are finished; 
     *      along the method askCards of the DiscardPile;
     *      
     */
    public void setDrawPile(ArrayList<Card> cardArray){
        this.drawPile = cardArray;
        shuffle();
    }
    
    private UNOGame getGame(){
        return this.game;
    }
    
    
}
