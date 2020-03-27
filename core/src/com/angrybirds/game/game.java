package com.angrybirds.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img ;
	Sprite test;
	public final float Gravity =9.8f;
	Everything[] levelObjects;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("BB.png");
		test = new Sprite(img);
		levelObjects = new Everything[1];
		levelObjects[0] = new Bird(new Vector2(0,50));
		levelObjects[0].setVelocity(new Vector2(10 , 10));

	}

	@Override
	public void render () {
		Vector2 renderPoint = levelObjects[0].getMidPoint();
		test.setPosition(renderPoint.x, renderPoint.y);
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(test,test.getX(),test.getY() , levelObjects[0].width , levelObjects[0].height);

		levelObjects[0].applyPhysics(Gravity);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();

	}
}
