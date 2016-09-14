package com.ggHockey.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetLoader {
	
	public static AssetManager manager = new AssetManager();
	public static Skin menuSkin;
	public static Texture logoTexture;
	public static TextureRegion logo, title, background, puck, paddle;
	
	public static void load() {
	
		logoTexture = new Texture(Gdx.files.internal("img/Team-4-Production.png"));
		logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		logo = new TextureRegion(logoTexture, 480, 800);
		
		title = new TextureRegion(new Texture(Gdx.files.internal("img/Title.png")));
		
		background = new TextureRegion(new Texture(Gdx.files.internal("img/BackGround.png")));
		
		puck = new TextureRegion(new Texture(Gdx.files.internal("img/taiji.png")));
		
		paddle = new TextureRegion(new Texture(Gdx.files.internal("img/blue paddle.png")));
		
		

	}
	
	public static void queueLoading() {
		manager.load("skins/menuSkin.pack", TextureAtlas.class);
	}
	
	public static void setMenuSkin() {
		if (menuSkin == null)
			menuSkin = new Skin(Gdx.files.internal("skins/menuSkin.json"),
					manager.get("skins/menuSkin.pack",
							TextureAtlas.class));
	}

	public static boolean update() {
		return manager.update();
	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		//texture.dispose();
		
		logo.getTexture().dispose();
		title.getTexture().dispose();
		puck.getTexture().dispose();
		paddle.getTexture().dispose();
		
		

	}


}