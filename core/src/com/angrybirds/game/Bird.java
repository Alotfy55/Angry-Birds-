package com.angrybirds.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
/**
public class Bird extends Everything {

    public Bird(Vector2 midPoint) {
        imgPath = new String[2];
        health = 100 ;
        height = 40 ;
        width = 40 ;
        weight = 10;
        this.x = midPoint.x ;
        this.y = midPoint.y;
        this.Velocity = new Vector2(0,0);
        this.BounceValue = 0.69f;
    }
    public int getCondition() {
        if (health > 50)
            return 0 ;
        else return 1;
    }
}
 */