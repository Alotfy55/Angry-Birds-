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
import java.security.Key;
import java.util.Random;

public class game extends ApplicationAdapter {
		SpriteBatch batch;

		Texture img;
		Texture Birds;
		Texture background;

		Sprite test2;
		Sprite test;
		Sprite test1;


		public World world;
		Box2DDebugRenderer dDebugRenderer;
		OrthographicCamera camera;


		int NumOfObjects;
		Everything player, player1[], bedRock;

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
				camera = new OrthographicCamera();
				camera.setToOrtho(false, (float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
				pausePhysics = true;
				hold = false;

				NumOfObjects = 10;
				batch = new SpriteBatch();
				img = new Texture("BB.png");
				background = new Texture("background.png");
				Birds = new Texture("RED.png");
				test2 = new Sprite(Birds);
				test = new Sprite(img);
				test1 = new Sprite(background);


				player1 = new Bird[NumOfObjects];
				player = new Bird(world, 150, 300, 20, 20, false);

				for (int i = 0; i < NumOfObjects; i++) {
						player1[i] = new Bird(world, 700 + i * 10, 200 + i * 50, 20, 20, false);
				}
				bedRock = new Bird(world, Gdx.graphics.getWidth() / 2, 90, 1000, 10, true);
		}

		@Override
		public void render() {
				if (!pausePhysics)
					world.step(1 / 60f, 6, 2);


				Gdx.gl.glClearColor(135, 206, 236, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

				batch.begin();

				batch.draw(test1, 0, 0, 1280, 300);

				for (int i = 0; i < NumOfObjects; i++) {
						batch.draw(test, player1[i].body.getPosition().x * PPM, player1[i].body.getPosition().y * PPM, 50, 50); // draw other birds
				}

				if ( Gdx.input.getPressure() == 1 && pausePhysics)
				{
						hold =true;
						if (mouseOrigin == null)
						mouseOrigin = new Vector2(Gdx.input.getX() ,Gdx.graphics.getHeight() -  Gdx.input.getY());
				}
				if (hold)
				{
						getMouseInput();
						if (Math.abs(mouseOrigin.x - Gdx.input.getX()) <= MouseLimit )
								test2.setPosition(Gdx.input.getX() , test2.getY());
						if (Math.abs(mouseOrigin.y - Gdx.input.getY()) <= MouseLimit )
								test2.setPosition(test2.getX() , Gdx.graphics.getHeight() - Gdx.input.getY());

						batch.draw(test2, test2.getX(),test2.getY(),50 , 50 ); //player.getPosition().x*PPM, player.getPosition().y*PPM
				}
				if (!hold)
				batch.draw(test2, player.body.getPosition().x * PPM, player.body.getPosition().y * PPM ,50 , 50 ); //player.getPosition().x*PPM, player.getPosition().y*PPM
				batch.end();

				System.out.println(test2.getY()+ " " + (Gdx.graphics.getHeight() - Gdx.input.getY()) +" "+ player.body.getPosition().x*PPM +" "+ Gdx.input.getX() );




				dDebugRenderer.render(world, camera.combined.scl(PPM));
				camera.combined.scl(1 / PPM);
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

/**
	public void inCollision(Everything[] levelObjects)
	{
		for (int i = 0 ; i < NumOfObjects ; i++)
		{
			for (int j = i+1 ; j < NumOfObjects ; j++)
			{
				if (levelObjects[i].overlaps(levelObjects[j]) && !collided[i][j])
				{
					levelObjects[i].OnCollision(levelObjects[j]);
					collided[i][j] = true;
					System.out.println("collision here");
				}
			}
		}
	}
*/

public void getMouseInput()
{

		if (includes((Bird) player , new Vector2(Gdx.input.getX(),Gdx.graphics.getHeight() - Gdx.input.getY() )) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)  )
		{
				player.body.setTransform((float) Gdx.input.getX()/32 ,(float) (Gdx.graphics.getHeight()- Gdx.input.getY())/32,0);
		}
		if(Gdx.input.getPressure() == 0)
		{
				player.body.setTransform(test2.getX()/32 , test2.getY()/32,0);
				hold=false;
				pausePhysics = false;
				player.body.setLinearVelocity( ((mouseOrigin.x - Gdx.input.getX()) /5 ) , (mouseOrigin.y - (Gdx.graphics.getHeight() - Gdx.input.getY()))/5);

		}
}



	public boolean includes(Bird player,Vector2 mousePoint )
	{
			if (mousePoint.x > player.body.getPosition().x*PPM - (player.width) && mousePoint.x < player.body.getPosition().x*PPM + (player.width) &&
			mousePoint.y > player.body.getPosition().y*PPM - (player.height)  && mousePoint.y < player.body.getPosition().y*PPM + (player.height))
					return true;
			else
					return false;
	}

}
 ////  solve mouse controls