package com.angrybirds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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



		public Everything(World world, int x , int y , int width , int height , boolean isStatic) {
				body = createPlayer(x,y,width,height,isStatic,world);
				this.height=height;
				this.width=width;
				health=100;
		}


		private Body createPlayer(int x , int y , int width , int height , boolean isStatic, World world ) {
				Body pBody;
				BodyDef def = new BodyDef();
				if(isStatic)
						def.type = BodyDef.BodyType.StaticBody;
				else
						def.type = BodyDef.BodyType.DynamicBody;
				def.position.set(x/PPM,y/PPM);
				def.fixedRotation = false ;


				pBody = world.createBody(def);
				FixtureDef fixtureDef = new FixtureDef();
				PolygonShape shape = new PolygonShape();
				shape.setAsBox(width/PPM ,height/PPM);

				fixtureDef.shape = shape ;
				fixtureDef.restitution = 0.6f;
				fixtureDef.friction = 0.1f ;



				pBody.createFixture(fixtureDef);
				shape.dispose();
				return pBody;
		}

}