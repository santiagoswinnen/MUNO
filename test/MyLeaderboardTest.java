package test;

import muno.game.Leaderboard;
import muno.game.Player;
import muno.game.UNOGame;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by lmikolas on 25/06/17.
 */
public class MyLeaderboardTest {
    private Leaderboard leaderboard;
    private UNOGame game;
    private Player player;
    @Before
    public void before(){
        this.game = new UNOGame();
        ArrayList<Player> players = new ArrayList<Player>();
        player = new Player("Player1", game );
        players.add(player);
        players.add(new Player("Player2", game));
        players.add(new Player("Player 3", game));
        players.add(new Player("Player 4", game));
        game.addPlayers(players);
        this.leaderboard = new Leaderboard(game);
    }
    @Test
    public void getWinnerTest(){
        leaderboard.addScore(player,502);
        assertEquals(player, leaderboard.getWinner());
    }

    @Test
    public void hasWinnerTest(){
        leaderboard.addScore(player,502);
        assertTrue(leaderboard.hasWinner());
    }



}
