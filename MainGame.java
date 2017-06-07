package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import Screens.MenuScreen;

public class MainGame extends Game {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 576;
	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}