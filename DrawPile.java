package muno.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by Lo Coco on 02/06/2017.
 */
public class DrawPile {
    private UNOGame game;
    private ArrayList<Card> drawPile;

    public DrawPile(UNOGame game) {
        drawPile = new ArrayList<Card>();
        newDeck();
        this.game = game;
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
        return getDrawPile().remove(getDrawPile().size() - 1);
    }
    
    private ArrayList<Card> getDrawPile(){
      return this.drawPile;
    }

    public void newDeck() {
        String[] cardName = Card.getCardNames();
        String[] cardColors = Card.getCardColors();
        ArrayList<Card> cardList = new ArrayList<Card>(); //PARA QUE SE USA?
        for (int color = 1; color < cardColors.length; color++ ){
            for (int name = 0; name < cardName.length - 3; name++){
                    getDrawPile().add(CardFactory.getCard(cardColors[color], cardName[name], getGame()));
                     getDrawPile().add(CardFactory.getCard(cardColors[color], cardName[name], getGame()));
            }
        }
        for (int count = 0; count < 4;count++ ){
            getDrawPile().add(CardFactory.getCard("black","Wild", getGame()));
            getDrawPile().add(CardFactory.getCard("black","DrawFour", getGame())); 
            getDrawPile().add(CardFactory.getCard("black","Mirror",getGame()));
        }
        shuffle();
    }
    
    public void setDrawPile(ArrayList<Card> cardArray){
        drawPile = cardArray;
        shuffle();
    }
    private UNOGame getGame(){
        return this.game;
    }
    
    
}
