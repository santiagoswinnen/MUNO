package muno.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class SingleGameScreen extends AbstractGameScreen {
	
	public SingleGameScreen(Game game){
		super(game);
		
		Player player1 = new Player("Player 1", getMyGame());
		PlayerIA player2 = new PlayerIA("Player 2", getMyGame());
		PlayerIA player3 = new PlayerIA("Player 3", getMyGame());
		PlayerIA player4 = new PlayerIA("Player 4", getMyGame());
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		
		getMyGame().addPlayers(players);
		
		getMyGame().getDealer().deal();
		settDiscard(new Texture(getMyGame().getDealer().lastCard().getColor() + getMyGame().getDealer().lastCard().getName() + ".png"));
		
		setPositions(new int[getMyGame().getPlayers().size() - 1]);
		
//		nextPlayer();
//		setTexturesHand();
//		setUpd(new Update(this));
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
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}
}
