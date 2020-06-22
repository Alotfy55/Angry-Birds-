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
//import org.graalvm.compiler.virtual.phases.ea.PartialEscapeClosure;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.security.Key;
import java.util.Random;

public class game extends ApplicationAdapter {

		SpriteBatch batch;

		Texture img;
		Texture background;

		Sprite test1;

		public static World world;
		Box2DDebugRenderer dDebugRenderer;
		OrthographicCamera camera;

		static int NumOfObjects;
		static int NumOfPigs , layoutWidth , layoutHeight[];
		static Everything player, levelObjects[], enemies[] ,bedRock;
		static Bird_Launcher launcher;


		boolean pausePhysics;
		boolean hold;
		public static final float PPM = 32;
		public Vector2 mouseOrigin;
		final int MouseLimit = 50;
		ListenerClass lis;

		@Override
		public void create() {
			Box2D.init();
			world = new World(new Vector2(0, -10), false);
			lis = new ListenerClass();
			world.setContactListener(lis);
			dDebugRenderer = new Box2DDebugRenderer();
			camera = new OrthographicCamera(20,10);
			pausePhysics = true;
			hold = false;

			NumOfObjects = 0;
			NumOfPigs = 0;
			batch = new SpriteBatch();
			batch.setProjectionMatrix(camera.combined);
			background = new Texture("background.png");
			test1 = new Sprite(background);

			createLevel();
			//launcher = new Bird_Launcher(50,100, 20 ,40);
			player = new Bird(world, 50, 100, 10, 10, false);

			bedRock = new Obstacle( world ,Gdx.graphics.getWidth() / 2, 55, 1000, 1, true);
		}

		@Override
		public void render() {
			if (pausePhysics)
			    player.body.setAwake(false);
			else
				player.body.setAwake(true);
			world.step(1 / 60f, 6, 2);
			batch.setProjectionMatrix(camera.combined);

			Gdx.gl.glClearColor(135, 206, 236, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			batch.begin();
			batch.draw(test1, 0, 0, 30, 5);



			if ( Gdx.input.getPressure() == 1 && pausePhysics && includes((Bird) player , new Vector2(Gdx.input.getX()/2,(Gdx.graphics.getHeight() - Gdx.input.getY()) /2 )))
			{
				hold =true;
				if (mouseOrigin == null)
					mouseOrigin = new Vector2(Gdx.input.getX() ,Gdx.graphics.getHeight() -  Gdx.input.getY());
			}
			if (hold)
			{
				getMouseInput();
			}

			//batch.draw(launcher.body, launcher.position.x, launcher.position.y, launcher.width, launcher.height);

			for ( int i = 0 ; i < NumOfObjects ; i++) {
				if(levelObjects[i].exist) {
					levelObjects[i].sprite[levelObjects[i].condition].setBounds(levelObjects[i].body.getPosition().x - levelObjects[i].sprite[levelObjects[i].condition].getWidth() / (2), levelObjects[i].body.getPosition().y - levelObjects[i].sprite[levelObjects[i].condition].getHeight() / (2), 2 * (levelObjects[i].width / PPM), 2 * (levelObjects[i].height / PPM));
					levelObjects[i].sprite[levelObjects[i].condition].setOrigin(levelObjects[i].sprite[levelObjects[i].condition].getWidth() / 2, levelObjects[i].sprite[levelObjects[i].condition].getHeight() / 2);
					levelObjects[i].sprite[levelObjects[i].condition].setRotation(MathUtils.radiansToDegrees * levelObjects[i].body.getAngle());
					levelObjects[i].sprite[levelObjects[i].condition].draw(batch);
				}
			}
			for ( int i = 0 ; i < NumOfPigs ; i++) {
				if(enemies[i].exist) {
					enemies[i].sprite[enemies[i].condition].setBounds(enemies[i].body.getPosition().x - enemies[i].sprite[enemies[i].condition].getWidth() / (2), enemies[i].body.getPosition().y - enemies[i].sprite[enemies[i].condition].getHeight() / (2), 2 * (enemies[i].width / PPM), 2 * (enemies[i].height / PPM));
					enemies[i].sprite[enemies[i].condition].setOrigin(enemies[i].sprite[enemies[i].condition].getWidth() / 2, enemies[i].sprite[enemies[i].condition].getHeight() / 2);
					enemies[i].sprite[enemies[i].condition].setRotation(MathUtils.radiansToDegrees * enemies[i].body.getAngle());
					enemies[i].sprite[enemies[i].condition].draw(batch);
				}
			}
			player.sprite[player.condition].setBounds(player.body.getPosition().x - player.sprite[player.condition].getWidth()/(2) , player.body.getPosition().y - player.sprite[player.condition].getHeight()/(2) , 2*(player.width/PPM),2*(player.height/PPM));
			player.sprite[player.condition].setOrigin(player.sprite[player.condition].getWidth()/2 , player.sprite[player.condition].getHeight()/2);
			player.sprite[player.condition].setRotation(MathUtils.radiansToDegrees * player.body.getAngle());
			player.sprite[player.condition].draw(batch);

			for(int i = 0 ; i < game.NumOfObjects ; i++)
			{
				levelObjects[i].Condition();
			}
			for(int i = 0 ; i < game.NumOfPigs ; i++)
			{
				enemies[i].Condition();
			}
			player.Condition();

			checkExist();
			batch.end();
			//dDebugRenderer.render(world,camera.combined);
		}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
		background.dispose();
		for ( int i = 0 ; i < NumOfObjects ; i++) {
			for (int j = 0 ; j < 3 ; j++)
			{
				levelObjects[i].texture[j].dispose();
			}
		}
		for ( int i = 0 ; i < NumOfPigs ; i++) {
			for (int j = 0 ; j < 2 ; j++)
			{
				enemies[i].texture[j].dispose();
			}
		}
		for (int i = 0 ; i < 2 ; i ++)
		{
			player.texture[i].dispose();
		}
		world.dispose();
		dDebugRenderer.dispose();
	}

	@Override
	public void resize(int width, int height) {
		float screenAR = width / (float) height;
		// Our camera needs to be created with new aspect ratio
		// Our visible game world width is still 20m but we need to
		// calculate what height keeps the AR correct.
		camera = new OrthographicCamera(20, 20/screenAR);
		// Finally set camera position so that (0,0) is at bottom left
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		camera.update();

		// If we use sprite batch to draw lets update it here for new camera
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

		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)  )
			{
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

	public void checkExist()
	{
		for ( int i = 0 ; i < NumOfObjects ; i++) {
			if(levelObjects[i].exist == false && levelObjects[i].destroyed == false)
			{
				world.destroyBody(levelObjects[i].body);
				levelObjects[i].destroyed = true;
			}
		}
		for ( int i = 0 ; i < NumOfPigs ; i++) {
			if(enemies[i].exist == false && enemies[i].destroyed == false)
			{
				world.destroyBody(enemies[i].body);
				enemies[i].destroyed = true;
			}
		}
	}

	public void createLevel(){
		layoutWidth = (int) ((Math.random() * 10) % 5) + 1 ;
		layoutHeight = new int [layoutWidth];
		for ( int i = 0; i < layoutWidth;i++)
		{
			layoutHeight[i] = (int) ((Math.random() * 10) % 5) + 1 ;
			NumOfPigs += layoutHeight[i];
		}
		enemies = new Pig[NumOfPigs];
		NumOfObjects = 3*NumOfPigs;
		levelObjects = new Obstacle[NumOfObjects];
		createBodies(57 , 400);
	}

	private void createBodies(int groundlevel , int startingX) {
		int enemiesCounter = 0;
		int woodCounter = 0;
		for (int i = 0 ; i < layoutWidth ; i++){
			startingX += (4*5 + 20) ;
			for (int j = 0 ; j < layoutHeight[i] ; j++){

				enemies[enemiesCounter++] = new Pig(world,startingX, groundlevel + j*10 + j*(2*20 + 2*5) +10,  10,10 , false );

				levelObjects[woodCounter++] = new Obstacle(world,startingX - 17 , groundlevel + 20 + j*(40 + 10),  5,20 , false );
				levelObjects[woodCounter++] = new Obstacle(world,startingX + 17 , groundlevel + 20 + j*(40 + 10),  5,20 , false );

				levelObjects[woodCounter] = new Obstacle(world , startingX , groundlevel + (j+1)*(10 + 40) , 5 , 20 ,false);
				levelObjects[woodCounter].body.setTransform(levelObjects[woodCounter].body.getPosition().x , levelObjects[woodCounter].body.getPosition().y , (float) (90*(Math.PI/180)));
				woodCounter++;

			}
		}
	}

}