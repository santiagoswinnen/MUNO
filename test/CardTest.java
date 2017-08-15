package test;

import muno.game.Card;
import muno.game.NumberedCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by lmikolas on 25/06/17.
 */
public class CardTest {
    private Card card;
    @Before
    public void before(){
        card = new NumberedCard("Seven", 7, "red");
    }

    @Test
    public void checkColorTest(){
        assertFalse(Card.checkColor("Brown"));
    }
    @Test
    public void checkScoreTest(){
        assertFalse(card.checkScore(123));
    }

    @Test
    public void checkCardNameTest(){
        assertFalse(Card.checkName("Geronimo"));
    }

    /*Verifica que ande bien el constructor*/
    @Test
    public void CardTest(){
        try{
            new NumberedCard("chacho", 0, "red");

        }catch (IllegalArgumentException e){
            System.out.println("Se lanzo la exception esperada");
        }
    }

    @Test
    public void setColorTest(){
        try{
            card.setColor("yellow");
        }catch(UnsupportedOperationException e){
            System.out.println("Se lanzo la excetion esperada");
        }
    }

}
