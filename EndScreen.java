package muno.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by santiago on 16/06/17.
 */
public class EndScreen extends AbstractScreen {
	
    private Leaderboard leaderboard;
    private ArrayList<Player> players;
    private BitmapFont font;

    public EndScreen(Game game, Leaderboard leaderboard, ArrayList<Player> players) {
        super(game);
        this.leaderboard = leaderboard;
        this.players = players;
        this.font = new BitmapFont();
        this.font.setColor(Color.WHITE);
		this.font.getData().setScale(1.4f);
    }

    @Override
    public void render(float dt) {
    	Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		getBatch().begin();
		
    	font.draw(getBatch(), "RESULT", 470, 500);
    	for(int i = 0; i < 4; i++) {
    		font.draw(getBatch(), players.get(i).getName() + ": .............. "
    				+ leaderboard.getScore(i), 415, 450 - 30 * i);
    	}
    	
    	font.draw(getBatch(), leaderboard.getWinner().getName() + " WINS!", 460, 300);
    	
    	if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
    		game.setScreen(new MenuScreen(game));
    		dispose();
    	}
    	
    	getBatch().end();
    }
    
}