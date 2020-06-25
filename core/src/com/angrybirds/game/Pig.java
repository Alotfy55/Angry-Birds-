package com.angrybirds.game;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

import static com.angrybirds.game.GameScreen.PPM;


public class Pig extends Everything{
		boolean rep ;
		public Pig(World world, int x , int y , int width , int height , boolean isStatic)
		{
			super(width, height , 70);

			rep = true;

			this.texture = new Texture[7];
			this.sprite = new Sprite[7];

			this.texture[0] = new Texture("Pig.png");
			this.texture[1] = new Texture("Pig_hurt.png");
			this.texture[2] = new Texture("Woosh1.png");
			this.texture[3] = new Texture("Woosh2.png");
			this.texture[4] = new Texture("Woosh3.png");
			this.texture[5] = new Texture("Woosh4.png");
			this.texture[6] = new Texture("Woosh5.png");

			for(int i = 0 ; i < 7 ; i++)
			{
				this.sprite[i] = new Sprite(this.texture[i]);
			}

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
			def.angularDamping = 4f;


			pBody = world.createBody(def);
			FixtureDef fixtureDef = new FixtureDef();
			CircleShape shape = new CircleShape();
			shape.setRadius(width/PPM);


			fixtureDef.shape = shape ;
			fixtureDef.friction = 1f;
			fixtureDef.density= 0.2f;



			pBody.createFixture(fixtureDef);
			pBody.setUserData(this);
			shape.dispose();
			return pBody;
		}

	@Override
	protected void Condition() {
		if(this.exist == true) {
			if (this.health >= 25) {
				this.condition = 0;
			}
			else if (this.health > 0 && this.health < 25) {
				this.condition = 1;
			}
			else if (this.health <= 0){
				this.condition++;
				if (rep && this.condition > 2)
				{
					this.condition--;
				}
				rep = !rep;
				if (condition == 7)
					this.exist = false;
			}
		}
	}

}
