package com.angrybirds.game;

import static com.angrybirds.game.Constants.PPM;
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
		float firstAngle = 0;
		float finalAngle;
		SpriteBatch batch;

		Texture img;
		Texture Birds;
		Texture background;
		Texture Wood_texture;
		Texture Wood2_texture;

		Sprite test2;
		Sprite test;
		Sprite test1;
		Sprite Wood1Sprite;
		Sprite Wood2Sprite;

		public World world;
		Box2DDebugRenderer dDebugRenderer;
		OrthographicCamera camera;


		int NumOfObjects;
		Everything player, player2, player1[] ,bedRock;

		boolean pausePhysics;
		boolean hold;

		public Vector2 mouseOrigin;
		final int MouseLimit = 150;


		@Override
		public void create() {
				Box2D.init();
				world = new World(new Vector2(0, -10), false);
				dDebugRenderer = new Box2DDebugRenderer();
				camera = new OrthographicCamera();
				camera.setToOrtho(false, (float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
				pausePhysics = true;
				hold = false;

				NumOfObjects = 6;
				batch = new SpriteBatch();
				img = new Texture("Pig.png");
				background = new Texture("background.png");
				Birds = new Texture("RED.png");
				Wood_texture = new Texture("Wood1.png");
			    Wood2_texture = new Texture("Wood2.png");
				test2 = new Sprite(Birds);
				test = new Sprite(img);
				test1 = new Sprite(background);
				Wood1Sprite= new Sprite(Wood_texture);
				Wood2Sprite = new Sprite(Wood2_texture);



				player = new Bird(world, 150, 300, 20, 20, false);
				player1 = new Obstacle[NumOfObjects];
				player1[0] = new Obstacle(world , 700 , 115 , 10 , 30 , false);
				player1[1] = new Obstacle(world , 800 , 115 , 10 , 30 , false);
				player1[2] = new Obstacle(world , 700 , 180 , 30 , 10 , false);
				player2 = new Pig(world , 710 , 270 , 20 ,20 , false);

				//for (int i = 0; i < NumOfObjects; i++) {
				//		player1[i] = new Pig(world, 700 + i + 10, 200 + i + 50, 20, 20, false);
				//}

				bedRock = new Obstacle( world ,Gdx.graphics.getWidth() / 2, 75, 1000, 10, true);
		}

		@Override
		public void render() {
				if (!pausePhysics)
					world.step(1 / 60f, 6, 2);


				Gdx.gl.glClearColor(135, 206, 236, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				batch.begin();

				batch.draw(test1, 0, 0, 1280, 300);





				 // this block for getting rotation angle for body1
				player1[0].angle += player1[0].body.getAngularVelocity();
				batch.draw(Wood1Sprite, player1[0].body.getPosition().x * PPM, player1[0].body.getPosition().y * PPM , (player1[0].body.getPosition().x)  ,(player1[0].body.getPosition().y  )   , 30, 90 , 1,1, player1[0].angle , (player1[0].angle > 0));
				player1[1].angle += player1[1].body.getAngularVelocity(); // this block for getting rotation angle for body1
				batch.draw(Wood1Sprite, player1[1].body.getPosition().x * PPM, player1[1].body.getPosition().y * PPM , (player1[1].body.getPosition().x)   ,(player1[1].body.getPosition().y  )   , 30, 90 , 1,1, player1[1].angle , (player1[1].angle > 0) );
				player1[2].angle += player1[2].body.getAngularVelocity(); // this block for getting rotation angle for body1
				batch.draw(Wood2Sprite, player1[2].body.getPosition().x * PPM, player1[2].body.getPosition().y * PPM , (player1[2].body.getPosition().x)   ,(player1[2].body.getPosition().y  )   , 90, 30 , 1,1, player1[2].angle , (player1[2].angle > 0));

				batch.draw(test, player2.body.getPosition().x * PPM, player2.body.getPosition().y * PPM ,50 , 50 );
				//for (int i = 0; i < NumOfObjects; i++) {
				//		batch.draw(test, player1[i].body.getPosition().x * PPM, player1[i].body.getPosition().y * PPM, 50, 50); // draw other birds
				//}

				if ( Gdx.input.getPressure() == 1 && pausePhysics && includes((Bird) player , new Vector2(Gdx.input.getX(),Gdx.graphics.getHeight() - Gdx.input.getY() )))
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



				//remove before production
				dDebugRenderer.render(world, camera.combined.scl(PPM));
				camera.combined.scl(1 / PPM);
				moveCamera();
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

public void moveCamera()
{
		Vector3 position = camera.position;
		position.x = player.body.getPosition().x / PPM +500;
		camera.position.set(position);

		camera.update();

}
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
				player.body.setLinearVelocity( ((mouseOrigin.x - Gdx.input.getX()) /8 ) , (mouseOrigin.y - (Gdx.graphics.getHeight() - Gdx.input.getY()))/4);

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