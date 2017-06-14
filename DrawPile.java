import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Lo Coco on 02/06/2017.
 */
public class DrawPile {

    private ArrayList<Card>drawPile;

    public DrawPile() {
        drawPile = new ArrayList<Card>();
    }

    public boolean isEmpty(){
        return size()==0;
    }

    private int size(){
        return getDrawPile().size();
    }

    private void shuffle(){
        Collections.shuffle(getDrawPile());
    }

    public Card getCard() {
        return getDrawPile().remove(size()-1);
    }
    
    private ArrayList<Card> getDrawPile(){
      return this.drawPile;
    }

    public void newDeck() {
        String[] cardName = Card.getCardNames();
        String[] cardColors = Card.getCardColors();
        ArrayList<Card> cardList = new ArrayList<>();
        for (int color = 1;color < cardColors.length;color++ ){
            for (int name = 0; name < cardName.length-3;name++){
            getDrawPile().add(CardFactory.getCard(color,name));
            getDrawPile().add(CardFactory.getCard(color,name));
            }
        }
        for (int count = 0; count < 4;count++ ){
            getDrawPile().add(CardFactory.getCard("black","Wild"));
            getDrawPile().add(CardFactory.getCard("black","DrawFour")); 
            getDrawPile().add(CardFactory.getCard("black","Mirror"));
        }
        shuffle();
    }

    public void setDrawPile(ArrayList<Card> cardArray){
        getDrawPile()=cardArray;
        shuffle();
    }
    
    
}
