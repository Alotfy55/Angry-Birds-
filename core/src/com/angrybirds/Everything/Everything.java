package com.angrybirds.Everything;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Everything  {
	public int Time_counter;
    public boolean SoundDone;
	public int health ;
	public int condition;
	public int height;
	public int width;
	public Body body;
	public Texture [] texture;
	public Sprite[] sprite;
	public boolean exist;
	public boolean destroyed;
	public Sound Destroy;
	public Sound Sound;

	public Everything(int width , int height , int health){

		Destroy = Gdx.audio.newSound(Gdx.files.internal("core/assets/Sound/Destroy.mp3"));

		this.health = health ;

		this.height = height ;

		this.width = width ;

		this.condition = 0;

		exist = true;

		destroyed = false;
	}

	protected abstract Body create(int x , int y , int width , int height , boolean isStatic, World world );

	public abstract void Condition();

	public abstract void Special_ability();

}