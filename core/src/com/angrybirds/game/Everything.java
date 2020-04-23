package com.angrybirds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.angrybirds.game.game.PPM;

public abstract class Everything  {
		protected String[] imgPath ;
	protected int health ;
	protected int condition;
	int height;
	int width;
	protected Body body;
	protected Sprite sprite;


	public Everything(World world, int x , int y , int width , int height , boolean isStatic , Sprite sprite){
		health = 100 ;

		this.height = height ;

		this.width = width ;

		this.sprite = sprite ;
	}
	protected abstract Body create(int x , int y , int width , int height , boolean isStatic, World world );

	public void render(SpriteBatch batch) {
		// First we position and rotate the sprite correctly
		int posX = (int)((int)body.getPosition().x*PPM) - (int) sprite.getWidth()/2;
		int posY = (int)((int)body.getPosition().y*PPM) - (int)sprite.getHeight()/2 ;
		float rotation = (float) Math.toDegrees(body.getAngle());
		sprite.setPosition(posX, posY);
		//sprite.setRotation(rotation);
		// Then we simply draw it as a normal sprite.
		sprite.draw(batch);
	}
}