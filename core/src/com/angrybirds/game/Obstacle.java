package com.angrybirds.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

import static com.angrybirds.game.GameScreen.PPM;

public class Obstacle extends Everything{
	boolean rep;
	public Obstacle(World world, int x , int y , int width , int height , boolean isStatic) {
		super(width , height , 150);

		rep = true;

		this.texture = new Texture[8];
		this.sprite = new Sprite[8];

		this.texture[0] = new Texture("Wood1.png");
		this.texture[1] = new Texture("Wood1_2.png");
		this.texture[2] = new Texture("Wood1_3.png");
		this.texture[3] = new Texture("Woosh1.png");
		this.texture[4] = new Texture("Woosh2.png");
		this.texture[5] = new Texture("Woosh3.png");
		this.texture[6] = new Texture("Woosh4.png");
		this.texture[7] = new Texture("Woosh5.png");

		for(int i = 0 ; i < 8 ; i++)
		{
			this.sprite[i] = new Sprite(this.texture[i]);
		}

		for (int i = 0 ; i < 8 ; i++)
		{
			this.sprite[i].setSize(10/PPM , 30/PPM);
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
		def.angularDamping = 15f;

		pBody = world.createBody(def);
		FixtureDef fixtureDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width/PPM , height/PPM);

		fixtureDef.shape = shape ;
		fixtureDef.restitution = 0.1f;
		fixtureDef.friction = 0.5f ;
		fixtureDef.density = 1f;



		pBody.createFixture(fixtureDef);
		pBody.setUserData(this);
		shape.dispose();
		return pBody;
	}

	@Override
	protected void Condition() {
		if (this.exist == true) {
			if (this.health >= 100) {
				this.condition = 0;
			}
			else if (this.health >= 50 && this.health < 100) {
				this.condition = 1;
			}
			else if (this.health > 0 && this.health < 50) {
				this.condition = 2;
			}
			else if (this.health <= 0){
				this.condition++;
				if (rep && this.condition > 3)
				{
					this.condition--;
				}
				rep = !rep;
				if (condition == 8)
					this.exist = false;

			}
		}
	}

}
