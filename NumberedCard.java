/**
 * Created by lmikolas on 07/06/17.
 */
public class NumberedCard extends Card {
    int number;

    NumberedCard(int number, String color) {
        super(color);
        if(number<0 || number>9)
            throw new IllegalArgumentException("Not valid number for card.")
        this.number = number;
    }

    public int getNumber(){
        return number;
    }


    public boolean match(NumberedCard card){
        if(card.color.equals("black") || card.getColor().equals(card.getColor())){
            return true;
        }
        else if(number == card.getNumber()){
            return true;
        }
        return false;
    }





}
