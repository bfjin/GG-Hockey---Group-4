package com.ggHockey.GameObjects;

import com.ggHockey.game.GGHockey;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Puck extends GameObject{
	
	private Circle boundingCirclePuck;
	
	public Puck(float posX, float posY){
		this.pos = new Vector2(posX, posY);
	}
	

	@Override
	public void dispose() {
	}


}