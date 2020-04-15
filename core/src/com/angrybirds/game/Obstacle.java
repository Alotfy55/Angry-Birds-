package com.angrybirds.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import static com.angrybirds.game.Constants.PPM;


public class Obstacle extends Everything{
	public Obstacle(World world, int x , int y , int width , int height , boolean isStatic) {

			body = create(x,y,width,height,isStatic,world);

			health = 100 ;

			this.height = height ;

			this.width = width ;

			this.angle = 0;

	}
	public int getCondition() {
		if (health > 50)
			return 0 ;
		else return 1;
	}

		@Override
		protected Body create(int x, int y, int width, int height, boolean isStatic, World world) {

				Body pBody;
				BodyDef def = new BodyDef();
				if(isStatic)
						def.type = BodyDef.BodyType.StaticBody;
				else
						def.type = BodyDef.BodyType.DynamicBody;
				def.position.set(x/PPM,y/PPM);
				def.fixedRotation = false ;
				def.angularDamping = 15f;



				pBody = world.createBody(def);
				FixtureDef fixtureDef = new FixtureDef();
				PolygonShape shape = new PolygonShape();
				shape.setAsBox(width/PPM , height/PPM);

				fixtureDef.shape = shape ;
				fixtureDef.restitution = 0.5f;
				fixtureDef.friction = 1.1f ;
				fixtureDef.density = 1f;



				pBody.createFixture(fixtureDef);
				shape.dispose();
				return pBody;
		}
}
