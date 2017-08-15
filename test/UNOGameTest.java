package test;

import muno.game.Player;
import muno.game.UNOGame;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by lmikolas on 25/06/17.
 */
public class UNOGameTest {
    private UNOGame game;
    private Player playertest;

    @Before
    public void before(){
        this.game = new UNOGame();
        ArrayList<Player> players = new ArrayList<Player>();
        this.playertest = new Player("Player1", game );
        players.add(playertest);
        players.add(new Player("Player2", game));
        players.add(new Player("Player 3", game));
        players.add(new Player("Player 4", game));
        game.addPlayers(players);
    }
    @Test
    public void GameFlowNextTest(){
        assertEquals(playertest, game.getNextPlayer());
    }
    @Test
    public void GameFlowCurrentTest(){
        game.getNextPlayer();
        assertEquals(playertest, game.getCurrentPlayer());
    }

    @Test
    public void GameFlowReverseTest(){
        game.getNextPlayer();
        game.getNextPlayer();
        game.reverseGameFLow();
        assertEquals(playertest,game.getNextPlayer());
    }



}
