package muno.game;

import java.io.Serializable;

/**
 * Created by lmikolas on 07/06/17.
 */
public class NumberedCard extends Card implements Serializable {
    private Integer number;

    NumberedCard(String name, Integer number, String color) {
        super(name, number, color);
        if(number < 0 || number > 9)
            throw new IllegalArgumentException("Not valid number for card.");
        this.number = number;
    }

    public int getNumber(){
        return number;
    }

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
