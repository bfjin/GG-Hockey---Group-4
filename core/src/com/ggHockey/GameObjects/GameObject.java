package com.ggHockey.GameObjects;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

	public Vector2 pos;
	
	public abstract void dispose();
	
	public float getX(){
		return pos.x;
	}


}
