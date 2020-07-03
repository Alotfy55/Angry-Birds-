package com.angrybirds.game;

import com.badlogic.gdx.Game;

public class Project_Entery extends Game {
    @Override
    public void create() {
        setScreen(new SplashScreen1(this));
    }

    @Override
    public void dispose()
    {
        getScreen().dispose();
    }

}

