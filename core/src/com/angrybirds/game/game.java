package com.angrybirds.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.security.Key;

public class game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img ;
	Texture background ;
	Sprite test;
	Sprite test1;
	public final float Gravity =9.8f;
	Everything[] levelObjects;
	boolean hold; // to determine if mouse button is held down
	boolean pausePhysics; // to start physics when the mouse button is released

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("BB.png");
		background = new Texture("background.png");
		test = new Sprite(img);
		test1 = new Sprite(background);
		levelObjects = new Everything[2];
		levelObjects[0] = new Bird(new Vector2(150,200));
		levelObjects[1] = new Bird(new Vector2(10,60));
		hold = false;
		pausePhysics = true;
		test.setPosition(levelObjects[0].x,levelObjects[0].y);
		test1.setPosition(0,-130);

	}

	@Override
	public void render () {
		//System.out.println(Gdx.input.getX() + " " + Gdx.input.getY() +" "+ levelObjects[0].x +" "+ levelObjects[0].y) ;

		Vector2 renderPoint = levelObjects[0].getMidPoint();


		Gdx.gl.glClearColor(135, 206, 236, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(test1,test1.getX(),test1.getY() ,1280 , 400 ); // draw background

		batch.draw(test,test.getX(),test.getY() , levelObjects[0].width , levelObjects[0].height); // draw first bird
		//batch.draw(test1,test1.getX(),test1.getY() , 0,0); // draw second bird

		if (!pausePhysics) // apply bird physics
		{
			test.setPosition(renderPoint.x, renderPoint.y);
			levelObjects[0].applyPhysics(Gravity);
		}

		if(levelObjects[0].overlaps(levelObjects[1])) {  // tests for collision
			levelObjects[1].setVelocity(new Vector2(50,60));
		}
		batch.end();


		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)  && levelObjects[0].includes(Gdx.input.getX(),Gdx.graphics.getHeight() -Gdx.input.getY()) && pausePhysics) {
			levelObjects[0].setVelocity(new Vector2(5 ,5));

			test.setPosition(Gdx.input.getX() - test.getWidth()/2,
					Gdx.graphics.getHeight() - Gdx.input.getY() - test.getHeight()/2);

			hold = true;
		}

		if (hold){
			test.setPosition(Gdx.input.getX() -25,
					Gdx.graphics.getHeight() - Gdx.input.getY() -25);
			if (!Gdx.input.isButtonPressed(Input.Buttons.LEFT)){

				hold = false ;

				levelObjects[0].setVelocity(new Vector2(-(test.getX() - levelObjects[0].x) /10, ( levelObjects[0].y - test.getY())/8));

				levelObjects[0].setCenter(test.getX() , test.getY());

				pausePhysics = false;

			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();

	}
}
