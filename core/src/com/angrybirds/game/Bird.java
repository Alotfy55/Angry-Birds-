package com.angrybirds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

import static com.angrybirds.game.GameScreen.PPM;

public class Bird extends Everything {


	private boolean rep = true;




	public Bird(World world, int x , int y , int width , int height , boolean isStatic) {
			super(width, height , 100);

			Sound = Gdx.audio.newSound(Gdx.files.internal("Sound/RedFlying.mp3"));
			SoundDone = false;

			this.texture = new Texture[7];
			this.sprite = new Sprite[7];

			this.texture[0] = GameScreen.theme.RED;
			this.texture[1] = GameScreen.theme.RED_Hurt;
			this.texture[2] = GameScreen.theme.Woosh1;
			this.texture[3] = GameScreen.theme.Woosh2;
			this.texture[4] = GameScreen.theme.Woosh3;
			this.texture[5] = GameScreen.theme.Woosh4;
			this.texture[6] = GameScreen.theme.Woosh5;

			for(int i = 0 ; i < 7 ; i++)
			{
				this.sprite[i] = new Sprite(this.texture[i]);
			}

			GameScreen.player_exists = true;

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
			if(this.exist) {
				if (this.health >= 50) {
					this.condition = 0;
				} else if (this.health > 0 && this.health < 50) {
					this.condition = 1;
				} else if (health <= -50) {
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

		public void Special_ability() {

		}
}
