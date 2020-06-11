package com.angrybirds.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import static com.angrybirds.game.game.PPM;

public class Bird extends Everything {


		public Bird(World world, int x , int y , int width , int height , boolean isStatic) {
			super(width, height , 100);

			this.texture = new Texture[2];
			this.sprite = new Sprite[2];

			this.texture[0] = new Texture("RED.png");
			this.texture[1] = new Texture("Red_hurt.png");

			this.sprite[0] = new Sprite(this.texture[0]);
			this.sprite[1] = new Sprite(this.texture[1]);

			body = create(x,y,width,height,isStatic,world);
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
				
				def.angularDamping = 3f;


				pBody = world.createBody(def);
				FixtureDef fixtureDef = new FixtureDef();
				CircleShape shape = new CircleShape();
				shape.setRadius(width/PPM);

				fixtureDef.shape = shape ;
				fixtureDef.friction = 1f ;
				fixtureDef.density = 1f;

				pBody.createFixture(fixtureDef);
				pBody.setUserData(this);
				shape.dispose();
				return pBody;
		}


		public void Condition() {
			if(this.health >= 50)
			{
				this.condition = 0;
			}
			else if (this.health > 0 && this.health < 50)
			{
				this.condition = 1;
			}
		}
}
