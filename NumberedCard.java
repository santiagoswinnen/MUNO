/**
 * Created by lmikolas on 07/06/17.
 */
public class NumberedCard extends Card {
    int number;

    NumberedCard(String name,int number, String color) {
        super(name,color);
        if(number<0 || number>9)
            throw new IllegalArgumentException("Not valid number for card.")
        this.number = number;
    }

    public int getNumber(){
        return number;
    }


    public boolean match(Card card){
        if(card.color.equals("black") || card.getColor().equals(card.getColor())){
            return true;
        }
        else if(card.getName().equals("NumberedCard") && card.getNumber().equals(number)){
            return true;
        }
        return false;
    }





}
