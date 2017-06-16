package muno.game;

import com.badlogic.gdx.Game;

public class MyGame extends Game {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 650;
	
	@Override
	public void create(){
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render(){
		super.render();
	}
}