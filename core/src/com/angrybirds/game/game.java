package com.angrybirds.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import org.graalvm.compiler.virtual.phases.ea.PartialEscapeClosure;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.security.Key;
import java.util.Random;

public class game extends ApplicationAdapter {
		SpriteBatch batch;

		Texture img;
		Texture Birds;
		Texture background;
		Texture Wood_texture;

		Sprite test2;
		Sprite test;
		Sprite test1;

		Sprite Wood1Sprite;
		Sprite Wood2Sprite;
		Sprite sprite;

		public World world;
		Box2DDebugRenderer dDebugRenderer;
		OrthographicCamera camera;


		int NumOfObjects;
		Everything player, levelObjects[], enemies[] ,bedRock;

		boolean pausePhysics;
		boolean hold;
		public static final float PPM = 32;
		public Vector2 mouseOrigin;
		final int MouseLimit = 50;
		@Override
		public void create() {
				Box2D.init();
				world = new World(new Vector2(0, -10), false);
				dDebugRenderer = new Box2DDebugRenderer();
				camera = new OrthographicCamera(20,10);
				//camera.setToOrtho(false, (float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
				pausePhysics = true;
				hold = false;

				NumOfObjects = 10;
				batch = new SpriteBatch();
				batch.setProjectionMatrix(camera.combined);
				img = new Texture("Pig.png");
				Wood_texture = new Texture("Wood1.png");
				background = new Texture("background.png");
				Birds = new Texture("RED.png");
				test2 = new Sprite(Birds);
				test = new Sprite(img);
				test1 = new Sprite(background);

				Wood1Sprite= new Sprite(Wood_texture);
				test2.setSize(50,50);
				Wood1Sprite.setSize(10/PPM , 30/PPM);
				test2.setSize(10/PPM , 10/PPM);

				player = new Bird(world, 50, 300, 10, 10, false , test2);
				player.body.setUserData(test2);
				enemies = new Pig[5];
				levelObjects = new Obstacle[NumOfObjects];

				for ( int i = 0 ; i < NumOfObjects ; i++){
					levelObjects[i] = new Obstacle(world,190 , 75+ i*40,  5,20 , false , Wood1Sprite);
				}

				for (int i = 0 ; i < 5 ; i++) {
					enemies[i] = new Pig(world,250 , 75+ i*40,  10,10 , false , Wood1Sprite);
				}


				bedRock = new Obstacle( world ,Gdx.graphics.getWidth() / 2, 55, 1000, 1, true , test1);
		}

		@Override
		public void render() {
		if (!pausePhysics)
			world.step(1 / 60f, 6, 2);
			batch.setProjectionMatrix(camera.combined);


				Gdx.gl.glClearColor(135, 206, 236, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

				batch.begin();
				batch.draw(test1, 0, 0, 30, 5);


				if ( Gdx.input.getPressure() == 1 && pausePhysics && includes((Bird) player , new Vector2(Gdx.input.getX()/2,(Gdx.graphics.getHeight() - Gdx.input.getY()) /2 )))
				{
					//System.out.println("sfgnskjdfnas");
					hold =true;
					if (mouseOrigin == null)
					mouseOrigin = new Vector2(Gdx.input.getX() ,Gdx.graphics.getHeight() -  Gdx.input.getY());
				}
				if (hold)
				{
						getMouseInput();
						/**if (Math.abs(mouseOrigin.x - Gdx.input.getX()) <= MouseLimit )
								player.body.setTransform(Gdx.input.getX() , player.body.getPosition().y , 00);
						if (Math.abs(mouseOrigin.y - Gdx.input.getY()) <= MouseLimit )
								player.body.setTransform(player.body.getPosition().x , Gdx.graphics.getHeight() - Gdx.input.getY() , 0);
						 */
				}

			for ( int i = 0 ; i < NumOfObjects ; i++) {
				Wood1Sprite.setBounds(levelObjects[i].body.getPosition().x - Wood1Sprite.getWidth()/(2) , levelObjects[i].body.getPosition().y - Wood1Sprite.getHeight()/(2) , 2*(levelObjects[i].width/PPM),2*(levelObjects[i].height/PPM));
				Wood1Sprite.setOrigin(Wood1Sprite.getWidth()/2 , Wood1Sprite.getHeight()/2);
				Wood1Sprite.setRotation(MathUtils.radiansToDegrees * levelObjects[i].body.getAngle());
				Wood1Sprite.draw(batch);
			}
			for ( int i = 0 ; i < 5 ; i++) {
				test.setBounds(enemies[i].body.getPosition().x - test.getWidth()/(2) , enemies[i].body.getPosition().y - test.getHeight()/(2) , 2*(enemies[i].width/PPM),2*(enemies[i].height/PPM));
				test.setOrigin(test.getWidth()/2 , test.getHeight()/2);
				test.setRotation(MathUtils.radiansToDegrees * enemies[i].body.getAngle());
				test.draw(batch);
			}
			player.sprite.setBounds(player.body.getPosition().x - player.sprite.getWidth()/(2) , player.body.getPosition().y - player.sprite.getHeight()/(2) , 2*(player.width/PPM),2*(player.height/PPM));
			player.sprite.setOrigin(player.sprite.getWidth()/2 , player.sprite.getHeight()/2);
			player.sprite.setRotation(MathUtils.radiansToDegrees * player.body.getAngle());
			player.sprite.draw(batch);

/*
			//((Sprite)player.body.getUserData()).draw(batch);
			player.render(batch);
			batch.end();

				System.out.println(test2.getY()+ " " + (Gdx.graphics.getHeight() - Gdx.input.getY()) +" "+ player.body.getPosition().x*PPM +" "+ Gdx.input.getX() );




				dDebugRenderer.render(world, camera.combined.scl(PPM));
<<<<<<< Updated upstream
				camera.combined.scl(1 / PPM);
=======
				camera.combined.scl((1/PPM));
				//moveCamera();

			Gdx.gl.glClearColor(0/ 255f, 0/ 255f, 0/ 255f, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			float deltaTime = Gdx.graphics.getRawDeltaTime();
			// This tells sprite location is 0,0 and size is 1m x 1m
			sprite.setBounds(0,0,1,1);
			sprite.setOriginCenter();
			// Draw sprite.
			batch.begin();
/**
			// This draws same sprite on all the bodies we have in the world
			sprite.setBounds(b.getPosition().x - sprite.getWidth()/2, b.getPosition().y - sprite.getHeight()/2, 1, 1);
			// Set origin center for the sprite to guarantee proper rotation with physicsBody.
			sprite.setOriginCenter();
			sprite.setRotation(MathUtils.radiansToDegrees * b.getAngle());

			sprite.draw(batch);

			sprite.draw(batch);
*/

			batch.end();
			dDebugRenderer.render(world,camera.combined);
			//System.out.println(Gdx.input.getX()/PPM + " " + (Gdx.graphics.getHeight() - Gdx.input.getY())/PPM + " " + player.body.getPosition().x +" " +  player.body.getPosition().y);
		}

	@Override
	public void dispose() {
				batch.dispose();
				img.dispose();
				Birds.dispose();
				background.dispose();
				world.dispose();
				dDebugRenderer.dispose();
		}

	@Override
	public void resize(int width, int height) {
		float screenAR = width / (float) height;
		// Our camera needs to be created with new aspect ratio
		// Our visible gameworld width is still 20m but we need to
		// calculate what height keeps the AR correct.
		camera = new OrthographicCamera(20, 20/screenAR);
		// Finally set camera position so that (0,0) is at bottom left
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		camera.update();

		// If we use spritebatch to draw lets update it here for new camera
		batch = new SpriteBatch();
		// This line says:"Camera lower left corner is 0,0. Width is 20 and height is 20/AR. Draw there!"
		batch.setProjectionMatrix(camera.combined);

	}


	public void moveCamera() {
			Vector3 position = camera.position;
			position.x = player.body.getPosition().x /PPM +400 ;
			position.y = bedRock.body.getPosition().y /PPM+ 200;
			camera.position.set(position);
			camera.update();
	}

	public void getMouseInput() {
		//System.out.println(player.body.getPosition().x + " " + player.body.getPosition().y);
		System.out.println(Gdx.input.getX()/PPM + " " + (Gdx.graphics.getHeight() - Gdx.input.getY())/PPM +" "+ player.body.getPosition().x +" " + player.body.getPosition().y);

		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)  )
			{
				System.out.println("EFregrh");
				player.body.setTransform((float) Gdx.input.getX()/PPM/2 ,(float) (Gdx.graphics.getHeight() - Gdx.input.getY())/PPM/2,0);
			}
			if(Gdx.input.getPressure() == 0)
			{
					hold=false;
					pausePhysics = false;
					player.body.setLinearVelocity( ((mouseOrigin.x - Gdx.input.getX()) /8 ) , (mouseOrigin.y - (Gdx.graphics.getHeight() - Gdx.input.getY()))/4);
			}
	}



	public boolean includes(Bird player,Vector2 mousePoint ) {
			if (mousePoint.x > player.body.getPosition().x*PPM - (player.width) && mousePoint.x < player.body.getPosition().x*PPM + (player.width) &&
			mousePoint.y > player.body.getPosition().y*PPM - (player.height)  && mousePoint.y < player.body.getPosition().y*PPM + (player.height))
					return true;
			else
					return false;
	}

}
	 ////  solve mouse controls