package Test;

import muno.game.Card;
import muno.game.NumberedCard;
import muno.game.Player;
import muno.game.UNOGame;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by lmikolas on 25/06/17.
 */
public class PlayerTest {
    private Player player;
    private UNOGame game;

    @Before
    public void before(){
        this.game = new UNOGame();
        this.player = new Player("Fernando", game);
        player.addCard(new NumberedCard("Seven", 7, "red"));

    }
    @Test
    public void hasUNOTest(){
        assertTrue(player.hasUNO());
    }

    @Test
    public void emptyHandTest(){
        player.emptyHand();
        assertEquals(0, player.getHand().size());

    }

    @Test(expected = IllegalArgumentException.class)
    public void throwCardTest(){
        Card card = new NumberedCard("Eight", 8, "red");
        assertTrue(player.throwCard(card));
    }



}
