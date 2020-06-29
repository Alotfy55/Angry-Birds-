package com.angrybirds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

public class Yellow_Bird extends Bird {

    boolean Special_Used;

    public Yellow_Bird(World world, int x , int y , int width , int height , boolean isStatic) {
        super(world,x,y,width,height,isStatic);

        Special_Used = false;

        this.texture = new Texture[2];
        this.sprite = new Sprite[2];

        this.texture[0] = GameScreen.theme.Yellow;
        this.texture[1] = GameScreen.theme.Yellow_Hurt;

        this.sprite[0] = new Sprite(this.texture[0]);
        this.sprite[1] = new Sprite(this.texture[1]);
    }

    @Override
    public void Special_ability()
    {
        if(GameScreen.pausePhysics == false) {
            if (!Special_Used && this.exist) {
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    Special_Used = true;
                    this.body.setLinearVelocity(this.body.getLinearVelocity().x + 30, this.body.getLinearVelocity().y);
                }
            }
        }
    }


}
