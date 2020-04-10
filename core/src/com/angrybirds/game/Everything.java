package com.angrybirds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.angrybirds.game.Constants.PPM;

public abstract class Everything  {
		protected String[] imgPath ;
	protected int health ;
	protected int condition;
	int height;
	int width;
	protected Body body;

		protected abstract Body create(int x , int y , int width , int height , boolean isStatic, World world ) ;

}