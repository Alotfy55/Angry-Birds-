package com.angrybirds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public abstract class Everything extends Rectangle{
    protected int weight ;
    protected Vector2 Velocity;
    protected String[] imgPath ;
    protected int health ;
    protected int condition;
    protected final int groundLevel = 20;
    protected float BounceValue = 0.69f ;


    public Vector2 getMidPoint() {
        return new Vector2(this.x , this.y);
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

    public void applyPhysics(float gravity ) {
        this.Velocity.y -= gravity/60;
        this.x += this.Velocity.x;
        this.y += this.Velocity.y;

        if (this.y <= groundLevel)
        {
            this.Velocity.y = ((float) this.Velocity.y * (-BounceValue));
            this.Velocity.x -= ((float) this.Velocity.x * (0.1));
        }
        else if ((this.Velocity.y > 0) &&  this.y <= groundLevel)
        {
            this.y = groundLevel ;
            this.Velocity.y = 0;

            if(Velocity.x > 0)
            {
                this.Velocity.x -= ((float) this.Velocity.x * (0.9));
            }
        }
        if (this.y < groundLevel)
            this.y = groundLevel;
        System.out.println(this.Velocity.x + " " + this.Velocity.y + " " + y);
    }


    public boolean includes (int x , int y){
        if (x > (this.x - (width) ) && x < (this.x + (width) ) ){
            if (y > (this.y - (height) ) && y < (this.y + (height) ) )
                return true;
        }
        return false;
    }
    public void OnCollision(Everything collidedObject)
    {
    	float factorx = 3 ;
    	float factory = 5 ;
    	//double totalEnergy =
		//		this.weight*Math.sqrt(Math.pow(this.Velocity.x,2) + Math.pow(this.Velocity.y,2)) +
		//		collidedObject.weight* Math.sqrt(Math.pow(collidedObject.Velocity.x,2) + Math.pow(collidedObject.Velocity.y,2));

    	//float factorx = ( (this.weight*this.Velocity.x) - (collidedObject.Velocity.x * collidedObject.weight)) / (this.weight * collidedObject.weight);
		//float factory = ( (this.weight*this.Velocity.y) - (collidedObject.Velocity.y * collidedObject.weight)) / (this.weight * collidedObject.weight);

		collidedObject.setVelocity(new Vector2(this.Velocity.x - this.Velocity.x /factorx , this.Velocity.y - this.Velocity.y/factory));
		this.setVelocity(new Vector2(this.Velocity.x/factorx , -this.Velocity.y/factory));

    }
}
