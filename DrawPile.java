package muno.game;

import java.util.ArrayList;
import java.util.Collections;

/**
* @author jlococo
*/

public class DrawPile {
    private UNOGame game;
    private ArrayList<Card> drawPile;

    /**
     * Se crea un DrawPile
     * @param Game referencia a Game
     */
    public DrawPile(UNOGame game) {
            drawPile = new ArrayList<Card>();
            this.game = game;
            newDeck();

        }

    public boolean isEmpty(){
        return size() == 0;
    }

        private int size(){
            return getDrawPile().size();
        }

    /**
     * Metodo para mezclar el drawPile
     */
    private void shuffle(){
            Collections.shuffle(getDrawPile());
        }

    /**
     * Se resta una carta del DrawPile y se manda.
     * @return Card ultima del arrayList. 
     */
    public Card getCard() {
          return getDrawPile().remove(size() - 1);
        }

        private ArrayList<Card> getDrawPile(){
            return this.drawPile;
        }

    /**
     * Clase que se usa para crear las cartas.
     */
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
     * El drawPile empieza a ser el ArrayList que se pasa
     * por parametro
     * 
     * @param cardArray Lo asigna como drawPile
     *  
     * @deprecated Este metodo esta pensado para cuando
     *      se acababan las cartas del Drawpile; en conjunto
     *      con el metodo askCards del DiscardPile; se pase 
     *      el mazo de descarte menos la ultima carta al DrawPile.
     */
    public void setDrawPile(ArrayList<Card> cardArray){
            drawPile = cardArray;
            shuffle();
        }

        private UNOGame getGame(){
            return this.game;
        }
}
