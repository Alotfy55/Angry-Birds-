package com.angrybirds.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;

public class Project_Entery extends Game {
    @Override
    public void create() {
        setScreen(new GameScreen());
    }

    @Override
    public void dispose()
    {
        getScreen().dispose();
    }

}
