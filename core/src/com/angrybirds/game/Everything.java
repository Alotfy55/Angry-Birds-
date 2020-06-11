package com.angrybirds.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Everything  {
	protected int health ;
	protected int condition;
	int height;
	int width;
	protected Body body;
	protected Texture [] texture;
	protected Sprite[] sprite;
	protected boolean exist;
	protected boolean destroyed;


	public Everything(int width , int height , int health){
		this.health = health ;

		this.height = height ;

		this.width = width ;

		this.condition = 0;

		exist = true;

		destroyed = false;
	}

	protected abstract Body create(int x , int y , int width , int height , boolean isStatic, World world );

	protected abstract void Condition();

}