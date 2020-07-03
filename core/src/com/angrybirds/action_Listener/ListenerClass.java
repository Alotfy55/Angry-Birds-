package com.angrybirds.action_Listener;

import com.angrybirds.Everything.Bird;
import com.angrybirds.Everything.Obstacle;
import com.angrybirds.Everything.Pig;
import com.angrybirds.Everything.Yellow_Bird;
import com.angrybirds.game.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.*;

public class ListenerClass implements ContactListener {

    private Sound BirdCollision = Gdx.audio.newSound(Gdx.files.internal("core/assets/Sound/birdCollision.wav"));
    private Sound PigCollision = Gdx.audio.newSound(Gdx.files.internal("core/assets/Sound/pigCollision.wav"));
    private Sound WoodCollision = Gdx.audio.newSound(Gdx.files.internal("core/assets/Sound/woodCollision.wav"));

    @Override
    public void endContact(Contact cp) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    @Override
    public void beginContact(Contact cp) {

        float PlayerVelocity = (float)Math.sqrt(Math.pow(GameScreen.player.body.getLinearVelocity().x,2)+Math.pow(GameScreen.player.body.getLinearVelocity().y,2));

        float []ObjectVelocity = new float[GameScreen.NumOfObjects];
        for(int i = 0 ; i < GameScreen.NumOfObjects ; i++)
        {
            ObjectVelocity[i] = (float)Math.sqrt(Math.pow(GameScreen.levelObjects[i].body.getLinearVelocity().x,2)+Math.pow(GameScreen.levelObjects[i].body.getLinearVelocity().y,2));
        }

        float []EnemiesVelocity = new float[GameScreen.NumOfPigs];
        for(int i = 0 ; i < GameScreen.NumOfPigs ; i++)
        {
            EnemiesVelocity[i] = (float)Math.sqrt(Math.pow(GameScreen.enemies[i].body.getLinearVelocity().x,2)+Math.pow(GameScreen.enemies[i].body.getLinearVelocity().y,2));
        }


        Fixture f1 = cp.getFixtureA();
        Fixture f2 = cp.getFixtureB();

        Body b1 = f1.getBody();
        Body b2 = f2.getBody();

        if (b1.getUserData().getClass() == Bird.class && b2.getUserData().getClass() == Obstacle.class)
        {
            GameScreen.player.health = 45;
            for (int i = 0; i < GameScreen.NumOfObjects; i++) {
                if(GameScreen.levelObjects[i].exist == true) {
                    if (b2.getUserData() == GameScreen.levelObjects[i]) {
                        GameScreen.levelObjects[i].health -= PlayerVelocity*5 + ObjectVelocity[i];
                        this.BirdCollision.play();
                        this.WoodCollision.play();
                    }
                }
            }
        }

        if (b1.getUserData().getClass() == Yellow_Bird.class && b2.getUserData().getClass() == Obstacle.class)
        {
            GameScreen.player.health = 45;
            for (int i = 0; i < GameScreen.NumOfObjects; i++) {
                if(GameScreen.levelObjects[i].exist == true) {
                    if (b2.getUserData() == GameScreen.levelObjects[i]) {
                        GameScreen.levelObjects[i].health -= PlayerVelocity*5 + ObjectVelocity[i];
                        this.BirdCollision.play();
                        this.WoodCollision.play();
                    }
                }
            }
        }

        if (b1.getUserData().getClass() == Obstacle.class && b2.getUserData().getClass() == Bird.class)
        {
            GameScreen.player.health = 45;
            for (int i = 0; i < GameScreen.NumOfObjects; i++) {
                if(GameScreen.levelObjects[i].exist == true) {
                    if (b1.getUserData() == GameScreen.levelObjects[i]) {
                        GameScreen.levelObjects[i].health -= PlayerVelocity*5 + ObjectVelocity[i];
                        this.BirdCollision.play();
                        this.WoodCollision.play();
                    }
                }
            }
        }

        if (b1.getUserData().getClass() == Obstacle.class && b2.getUserData().getClass() == Yellow_Bird.class)
        {
            GameScreen.player.health = 45;
            for (int i = 0; i < GameScreen.NumOfObjects; i++) {
                if(GameScreen.levelObjects[i].exist == true) {
                    if (b1.getUserData() == GameScreen.levelObjects[i]) {
                        GameScreen.levelObjects[i].health -= PlayerVelocity*5 + ObjectVelocity[i];
                        this.BirdCollision.play();
                        this.WoodCollision.play();
                    }
                }
            }
        }

        if (b1.getUserData().getClass() == Bird.class && b2.getUserData().getClass() == Pig.class)
        {
            for (int i = 0; i < GameScreen.NumOfPigs; i++) {
                if(GameScreen.enemies[i].exist == true) {
                    if (b2.getUserData() == GameScreen.enemies[i]) {
                        GameScreen.enemies[i].health -= PlayerVelocity*5 + EnemiesVelocity[i];
                        this.BirdCollision.play();
                        this.PigCollision.play();
                    }
                }
            }
        }

        if (b1.getUserData().getClass() == Yellow_Bird.class && b2.getUserData().getClass() == Pig.class)
        {
            for (int i = 0; i < GameScreen.NumOfPigs; i++) {
                if(GameScreen.enemies[i].exist == true) {
                    if (b2.getUserData() == GameScreen.enemies[i]) {
                        GameScreen.enemies[i].health -= PlayerVelocity*5 + EnemiesVelocity[i];
                        this.BirdCollision.play();
                        this.PigCollision.play();
                    }
                }
            }
        }

        if (b1.getUserData().getClass() == Pig.class && b2.getUserData().getClass() == Bird.class)
        {
            for (int i = 0; i < GameScreen.NumOfPigs; i++) {
                if(GameScreen.enemies[i].exist == true) {
                    if (b1.getUserData() == GameScreen.enemies[i]) {
                        GameScreen.enemies[i].health -= PlayerVelocity*5 + EnemiesVelocity[i];
                        this.BirdCollision.play();
                        this.PigCollision.play();
                    }
                }
            }
        }

        if (b1.getUserData().getClass() == Pig.class && b2.getUserData().getClass() == Yellow_Bird.class)
        {
            for (int i = 0; i < GameScreen.NumOfPigs; i++) {
                if(GameScreen.enemies[i].exist == true) {
                    if (b1.getUserData() == GameScreen.enemies[i]) {
                        GameScreen.enemies[i].health -= PlayerVelocity*5 + EnemiesVelocity[i];
                        this.BirdCollision.play();
                        this.PigCollision.play();
                    }
                }
            }
        }

        if (b1.getUserData().getClass() == Obstacle.class && b2.getUserData().getClass() == Pig.class)
        {

            float v1 = 0;
            float v2 = 0;
            if (b1.getUserData() == GameScreen.bedRock)
            {
                   v1 = 0;
            }
            else {
                for (int i = 0; i < GameScreen.NumOfObjects; i++) {
                    if (GameScreen.levelObjects[i].exist == true) {
                        if (b1.getUserData() == GameScreen.levelObjects[i]) {
                            v1 = ObjectVelocity[i];
                        }
                    }
                }
            }
            for (int i = 0; i < GameScreen.NumOfPigs; i++) {
                if(GameScreen.enemies[i].exist == true) {
                    if (b2.getUserData() == GameScreen.enemies[i]) {
                        v2 = Math.abs(EnemiesVelocity[i]);
                        if (v1 != 0) {
                            GameScreen.enemies[i].health -= v1 * 15 + v2 * 2;
                        }
                        else
                        {
                            GameScreen.enemies[i].health -= v2 * 10;
                        }
                    }
                }
            }
        }

        if (b1.getUserData().getClass() == Pig.class && b2.getUserData().getClass() == Obstacle.class)
        {

            float v1 = 0;
            float v2 = 0;
            if(b2.getUserData() == GameScreen.bedRock)
            {
                v2 = 0;
            }
            else {
                for (int i = 0; i < GameScreen.NumOfObjects; i++) {
                    if (GameScreen.levelObjects[i].exist == true) {
                        if (b2.getUserData() == GameScreen.levelObjects[i]) {
                            v1 = ObjectVelocity[i];
                        }
                    }
                }
            }

            for (int i = 0; i < GameScreen.NumOfPigs; i++) {
                if (GameScreen.enemies[i].exist == true) {
                    if (b1.getUserData() == GameScreen.enemies[i]) {
                        v2 = EnemiesVelocity[i];
                        if (v1 != 0) {
                            GameScreen.enemies[i].health -= v1 * 15 + v2 * 2;
                        }
                        else
                        {
                            GameScreen.enemies[i].health -= v2 * 10;
                        }
                    }
                }
            }

        }

        if (b1.getUserData().getClass() == Obstacle.class && b2.getUserData().getClass() == Obstacle.class)
        {

            float v1 = 0 ;
            float v2 = 0 ;
            int p1 = 0;
            int p2 = 0;

            for (int i = 0; i < GameScreen.NumOfObjects; i++) {
                if(GameScreen.levelObjects[i].exist == true) {
                    if (b1.getUserData() == GameScreen.levelObjects[i]) {
                        v1 = ObjectVelocity[i];
                        p1 = i;
                    }
                }
            }
            for (int i = 0; i < GameScreen.NumOfObjects; i++) {
                if(GameScreen.levelObjects[i].exist == true) {
                    if (b2.getUserData() == GameScreen.levelObjects[i]) {
                        v2 = ObjectVelocity[i];
                        p2 = i;
                    }
                }
            }

            GameScreen.levelObjects[p1].health -= v1*5+v2*3;
            GameScreen.levelObjects[p2].health -= v1*5+v2*3;
        }
    }
};