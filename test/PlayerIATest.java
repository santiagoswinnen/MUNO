package Test;

import muno.game.Card;
import muno.game.PlayerIA;
import muno.game.UNOGame;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by lmikolas on 25/06/17.
 */
public class PlayerIATest {

    private PlayerIA player;
    private UNOGame game;
    private static final String [] CARD_COLORS={"black", "red", "yellow", "green", "blue"};

    @Before
    public void before(){
        this.game = new UNOGame();
        this.player = new PlayerIA("Fernando", game);
    }

    @Test
    public void chooseColorTest(){
        assertTrue(Card.checkColor(player.chooseColor()));
    }

    @Test
    public void makeMoveTest(){
        game.getDealer().deal();
        assertFalse(player.makeMove());
    }
}
