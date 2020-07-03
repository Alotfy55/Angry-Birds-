package com.angrybirds.Everything;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.angrybirds.game.GameScreen.PPM;

public class Bird_Launcher {

    public float width;
    public float height;
    Texture part1;
    Texture part2;
    public Sprite body;
    public Sprite arm;
    public ShapeRenderer shapeRenderer;
    public Sound stretchSound;
    public boolean strechPlayed;

    public Bird_Launcher()
    {
        strechPlayed = false;
        stretchSound = Gdx.audio.newSound(Gdx.files.internal("core/assets/Sound/Stretch.mp3"));

        this.part1 = new Texture("core/assets/Launcher_body.png");
        this.part2 = new Texture("core/assets/Launcher_arm.png");

        this.body = new Sprite(part1);
        this.arm = new Sprite(part2);


        this.body.setSize(25/PPM,60/PPM);
        this.body.setOrigin(this.body.getWidth()/2,this.body.getHeight()/2);

        this.arm.setSize(22/PPM,36/PPM);
        this.arm.setOrigin(this.body.getWidth()/2,this.body.getHeight()/2);
        this.arm.setPosition(39/PPM,49/PPM);

        this.shapeRenderer = new ShapeRenderer();
    }
}
