package com.ggHockey.game;

import com.badlogic.gdx.Game;
import com.ggHockey.Screens.SplashScreen;
import com.ggHockey.Helpers.AssetLoader;

public class GGHockey extends Game {

	public static final String TITLE="Game Project";
	
	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}