package com.ggHockey.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Paddle extends GameObject{
	
	private Circle boundingCirclePaddle;
	
	public Paddle(float posX, float posY){
		this.pos = new Vector2(posX, posY);
	}
	
	@Override
	public void dispose() {
	}
	

	
}
