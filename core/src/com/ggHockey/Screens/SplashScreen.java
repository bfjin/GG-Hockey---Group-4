package com.ggHockey.Screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ggHockey.Helpers.AssetLoader;
import com.ggHockey.TweenAccessors.SpriteAccessor;
import com.ggHockey.game.GGHockey;

public class SplashScreen implements Screen {
	
	private TweenManager manager;
	private SpriteBatch batcher;
	private Sprite sprite;
	private GGHockey game;

	
	public SplashScreen(GGHockey game) {
		this.game = game;
	}

	@Override
	public void show() {
		sprite = new Sprite(AssetLoader.logo);
		sprite.setColor(1, 1, 1, 0);
		setupTween();
		batcher = new SpriteBatch();
		AssetLoader.queueLoading(); 
	}
	
	private void setupTween() {
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		manager = new TweenManager();

		TweenCallback cb = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				game.setScreen(new MainMenu());
			}
		};

		Tween.to(sprite, SpriteAccessor.ALPHA, .8f).target(1)
				.ease(TweenEquations.easeInOutQuad).repeatYoyo(1, .4f)
				.setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
				.start(manager);
	}
	
	@Override
	public void render(float delta) {
		manager.update(delta);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		AssetLoader.update(); //loads a file
		if(AssetLoader.manager.getProgress()==1){ // check if all files are loaded
			AssetLoader.setMenuSkin(); // uses files to create menuSkin
		}
		
		batcher.begin();
		sprite.draw(batcher);
		batcher.end();
	}
	
	@Override
	public void resize(int width, int height) {	
		
	}
	
	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {		
	}

	@Override
	public void resume() {		
	}

	@Override
	public void dispose() {

	}
}
