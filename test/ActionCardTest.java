package test;

import muno.game.ActionCard;
import muno.game.DrawNBehaviour;
import muno.game.UNOGame;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by lmikolas on 25/06/17.
 */
public class ActionCardTest {
    private UNOGame game= new UNOGame();
    private ActionCard card = new ActionCard("DrawTwo", 20, "yellow", new DrawNBehaviour(game,2));

    @Test
    public void matchTest(){
        assertTrue(card.match(new ActionCard("DrawTwo",20, "green", new DrawNBehaviour(game,2))));
    }
}
