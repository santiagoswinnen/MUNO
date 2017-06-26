package Test;

import muno.game.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by lmikolas on 25/06/17.
 */
public class CardFactoryTest {
    private UNOGame game = new UNOGame();
    @Test
    public void factoryTest1(){
        String name = "Seven";
        String color = "red";
        assertEquals(new NumberedCard("Seven", 7, "red").getName(), CardFactory.getCard(color,name,game).getName());
        assertEquals(new NumberedCard("Seven", 7, "red").getScore(), CardFactory.getCard(color,name,game).getScore());
    }
    @Test
    public void factoryTest2(){
        String name = "Wild";
        String color = "black";
        assertEquals(new ActionCard(name,50, color, new ColorableBehaviour(game)).getName(), CardFactory.getCard(color, name, game).getName());
    }

}
