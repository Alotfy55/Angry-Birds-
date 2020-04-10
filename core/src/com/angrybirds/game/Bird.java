package com.angrybirds.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import static com.angrybirds.game.Constants.PPM;


public class Bird extends Everything {

		public Bird(World world, int x , int y , int width , int height , boolean isStatic) {

				body = create(x,y,width,height,isStatic,world);

				health = 100 ;

				this.height = height ;

				this.width = width ;
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
				
				def.angularDamping = 1f;


				pBody = world.createBody(def);
				FixtureDef fixtureDef = new FixtureDef();
				CircleShape shape = new CircleShape();
				shape.setRadius(width/PPM);

				fixtureDef.shape = shape ;
				fixtureDef.friction = 1f ;
				fixtureDef.density = 1f;




				pBody.createFixture(fixtureDef);
				shape.dispose();
				return pBody;
		}

		public int getCondition() {
				if (health > 50)
						return 0 ;
				else return 1;
		}
}
