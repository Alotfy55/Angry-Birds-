package com.angrybirds.Everything;

import com.angrybirds.game.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

public class Yellow_Bird extends Bird {

    boolean Special_Used;
    com.badlogic.gdx.audio.Sound boost;


    public Yellow_Bird(World world, int x , int y , int width , int height , boolean isStatic) {
        super(world,x,y,width,height,isStatic);

        Sound = Gdx.audio.newSound(Gdx.files.internal("core/assets/Sound/YellowFlying.mp3"));
        boost = Gdx.audio.newSound(Gdx.files.internal("core/assets/Sound/boost.wav"));
        SoundDone = false;
        Special_Used = false;

        this.texture = new Texture[7];
        this.sprite = new Sprite[7];

        this.texture[0] = GameScreen.theme.Yellow;
        this.texture[1] = GameScreen.theme.Yellow_Hurt;
        this.texture[2] = GameScreen.theme.Woosh1;
        this.texture[3] = GameScreen.theme.Woosh2;
        this.texture[4] = GameScreen.theme.Woosh3;
        this.texture[5] = GameScreen.theme.Woosh4;
        this.texture[6] = GameScreen.theme.Woosh5;

        for(int i = 0 ; i < 7 ; i++)
        {
            this.sprite[i] = new Sprite(this.texture[i]);
        }
    }

    @Override
    public void Special_ability()
    {
        if(GameScreen.pausePhysics == false) {
            if (!Special_Used && this.condition == 0) {
                if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    Special_Used = true;
                    this.body.setLinearVelocity(this.body.getLinearVelocity().x + 30, this.body.getLinearVelocity().y);
                    boost.play();
                }
            }
        }
    }



}
