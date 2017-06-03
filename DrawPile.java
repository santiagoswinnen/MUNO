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
        return drawPile.size();
    }

    private void shuffle(){
        Collections.shuffle(drawPile);
    }

    public Card getCard() {
        return drawPile.remove(size()-1);
    }

    public void newDeck() {
        // Crea el mazo de cartas
           shuffle();
    }

    public void setDrawPile(ArrayList<Card> cardArray){
        drawPile=cardArray;
        shuffle();
    }
}
