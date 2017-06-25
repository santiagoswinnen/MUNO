package muno.game;

import java.io.Serializable;

/**
 * Class that represent a numbered card that has no special behaviour.
 */
public class NumberedCard extends Card implements Serializable {
    private Integer number;
    
    /**Constructor method of the class. 
     *
     *@throws IllegalArgumentException if the number of the card is invalid.
     *@param name Card's name
     *param color Card's color
     *param number Card's number
     */
    NumberedCard(String name, Integer number, String color) {
        super(name, number, color);
        if(number < 0 || number > 9)
            throw new IllegalArgumentException("Not valid number for card.");
        this.number = number;
    }
    
    /**Gets card's number
     *@return number Card's number.
     */
    public int getNumber(){
        return number;
    }
    
    /** Verifies if a card matches with another card. 
     *Two numbered cards will match if they have the same color or the same number.
     *
     *@param card The card to compare with.
     *@return true if they match, false if they don´t.
     */
    public boolean match(Card card){
        if(this.getColor().equals(card.getColor())){
            return true;
        }
        else if(card.isNumbered() && ((NumberedCard)card).getNumber() == this.number){
            return true;
        }
        return false;
    }
}
