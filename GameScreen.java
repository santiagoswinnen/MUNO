package muno.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen extends AbstractScreen {

	public GameScreen(Game game) {
		super(game);
		UNOGame myGame = new UNOGame();
		Player player1 = new Player("Human Player", myGame);
		PlayerIA player2 = new PlayerIA("Player 2", myGame);
		PlayerIA player3 = new PlayerIA("Player 3", myGame);
		PlayerIA player4 = new PlayerIA("Player 4", myGame);
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		myGame.addPlayers(players);
		myGame.getDealer().deal();
		System.out.println("HOLA");
	}

	@Override
	public void render(float dt) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameCam.update();
		super.batch.setProjectionMatrix(gameCam.combined);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

}
