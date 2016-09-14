package com.ggHockey.Screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ggHockey.Helpers.AssetLoader;

public class MainMenu extends StaticScreen {

	private SpriteBatch batch = new SpriteBatch();
	
	private OrthographicCamera camera = new OrthographicCamera();
	
	private Sprite background;
			
	private Stage stage = new Stage();
	
	private Table table = new Table();
	
	private TextButton buttonSinglePlayer,
					   buttonMultiPlayer,
					   buttonHowToPlay,
					   buttonOption,
					   buttonAbout;
	
	private Image title = new Image(AssetLoader.title);
	
	
	public void render(float delta) {
		
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//camera.update();
		//batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		background.draw(batch);
		batch.end();
		
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//background = new Sprite(new Texture("img/BackGround.png"));
		background = new Sprite(AssetLoader.background);
		
		
		addButtons();
		table.add(title).padBottom(20).row();
		table.add(buttonSinglePlayer).size(250,60).padBottom(20).row();
		table.add(buttonMultiPlayer).size(250,60).padBottom(20).row();
		table.add(buttonHowToPlay).size(250,60).padBottom(20).row();
		table.add(buttonOption).size(250,60).padBottom(20).row();
		table.add(buttonAbout).size(250,60).padBottom(20).row();
		//table.debug();
		table.setFillParent(true);
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
	}

	
	public void addButtons(){
		
		buttonSinglePlayer = new TextButton("Single Player", skin);
		buttonMultiPlayer = new TextButton("MultiPlayer", skin);
		buttonHowToPlay = new TextButton("How To Play", skin);
		buttonOption = new TextButton("Option", skin);
		buttonAbout = new TextButton("About", skin);
		
		buttonSinglePlayer.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
			}
		});
		
		buttonMultiPlayer.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game)Gdx.app.getApplicationListener()).setScreen(new SubMenuMultiPlayer());
			}
		});
		
		buttonHowToPlay.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game)Gdx.app.getApplicationListener()).setScreen(new SubMenuHowToPlay());
			}
		});
		
		// About is the secret exit button
		buttonAbout.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
				// or System.exit(0);
			}
		});
	
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
		stage.dispose();
		batch.dispose();
	}

}
