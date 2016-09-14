package com.ggHockey.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class SubMenuMultiPlayer extends StaticScreen {

	private SpriteBatch batch = new SpriteBatch();
	
	private Sprite background;
	
	private Stage stage = new Stage();
	private Table table = new Table();
	
	
	private TextButton buttonBlueTooth,
					   buttonSameDevice;
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
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
		
		background = new Sprite(new Texture("img/BackGround.png"));
		
		addButtons();
		
		table.add(buttonSameDevice).size(250,60).padBottom(40).row();
		table.add(buttonBlueTooth).size(250,60).padBottom(20).row();
		//table.debug();
		table.setFillParent(true);
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
	}

	
	public void addButtons(){
		
		buttonBlueTooth = new TextButton("Blue Tooth", skin);
		buttonSameDevice = new TextButton("Same Device", skin);
		
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
