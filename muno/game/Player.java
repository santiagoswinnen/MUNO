package muno.game;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that represents a player and its behavior.
 */
public class Player implements Serializable {
	
    private ArrayList<Card> hand;
    private UNOGame game;
    private String name;
    
   /** Constructor method of the class.
    *  @param name Player's name
    *  @param game Reference to the Muno Game.
    */
    public Player(String name, UNOGame game) {
    	this.hand = new ArrayList<Card>();
    	this.game = game;
        this.name = name;    
    }
    
   /** Checks if the player has only one card left */
    public boolean hasUNO() {
        if(hand.size() == 1)
            return true;
        return false;
    }

   /** Adds a card to the player's hand
    *
    *  @param card Card to add
    */
    public void addCard(Card card) {
        this.hand.add(card);
    }

   /** Empties the player's hand leaving him with no cards */
    public void emptyHand() {
       getHand().clear();
    }

    /**Removes card from a player's hand.
     *
     *@param card The card that the player is throwing. Will be removed from his hand.
     *@throws IllegalArgumentException if the card to be removed is not in the player's hand.
     */
    public boolean throwCard(Card card) {
        int i = hand.indexOf(card);
        if(i == -1)
            throw new IllegalArgumentException("No such card in hand");
        if(card.match(game.getDealer().lastCard())) {
            game.getDealer().discardCard(hand.remove(i));
            return true;
        }
        return false;
    }

    /**Checks if the player is in the game
     *
     *@return true if the player is in the game, false if not.
     */
    public boolean checkGame(){
        if(game.getPlayers().contains(this)){
            return true;
        }
        return false;
    }
    
   /** Returns the player's name */
    public String getName(){
        return name;
    }
    
   /** Shows the hand of a player.
    *
    *  @return hand Player's hand 
	*/
    public ArrayList<Card> getHand() {
       return hand;
   }
    
   /** Returns a reference to the game */
    public UNOGame getGame(){
    	return this.game;
    }

    public String toString(){
        return getName();
    }

}
