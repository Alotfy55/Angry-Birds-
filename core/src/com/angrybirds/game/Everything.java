package com.angrybirds.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public abstract class Everything {
    protected Vector2 midPoint;
    protected int height , width ;
    protected int weight ;
    protected Vector2 Velocity;
    protected String[] imgPath ;
    protected int health ;
    protected int condition;

    public Vector2 getMidPoint() {
        return midPoint;
    }

    public void setMidPoint(Vector2 midPoint) {
        this.midPoint = midPoint;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Vector2 getVelocity() {
        return Velocity;
    }

    public void setVelocity(Vector2 velocity) {
        Velocity = velocity;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public String getImagePath(int condition) {
        if (condition < imgPath.length)
            return imgPath[condition];
        else
            return "-1";
    }

    public void applyPhysics(float gravity , float V0 , float sinC , float cosC) {
        this.Velocity.x = V0 *  cosC;
        this.Velocity.y = V0 * sinC - gravity/60;
        this.midPoint.x += this.Velocity.x;
        this.midPoint.y += this.Velocity.y;
    }

    public void OnCollision(Vector2 V , float y , float x)
    {

    }

}
