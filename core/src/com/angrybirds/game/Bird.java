package com.angrybirds.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Bird extends Everything {

    public Bird(World world, int x , int y , int width , int height , boolean isStatic) {
		super( world,  x ,  y ,  width ,  height ,  isStatic);
    }
    public int getCondition() {
        if (health > 50)
            return 0 ;
        else return 1;
    }
}
