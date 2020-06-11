package com.angrybirds.game;

import com.badlogic.gdx.physics.box2d.*;

public class ListenerClass implements ContactListener {
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

        float PlayerVelocity = (float)Math.sqrt(Math.pow(game.player.body.getLinearVelocity().x,2)+Math.pow(game.player.body.getLinearVelocity().y,2));

        float []ObjectVelocity = new float[game.NumOfObjects];
        for(int i = 0 ; i < game.NumOfObjects ; i++)
        {
            ObjectVelocity[i] = (float)Math.sqrt(Math.pow(game.levelObjects[i].body.getLinearVelocity().x,2)+Math.pow(game.levelObjects[i].body.getLinearVelocity().y,2));
        }

        float []EnemiesVelocity = new float[game.NumOfPigs];
        for(int i = 0 ; i < game.NumOfPigs ; i++)
        {
            EnemiesVelocity[i] = (float)Math.sqrt(Math.pow(game.enemies[i].body.getLinearVelocity().x,2)+Math.pow(game.enemies[i].body.getLinearVelocity().y,2));
        }


        Fixture f1 = cp.getFixtureA();
        Fixture f2 = cp.getFixtureB();

        Body b1 = f1.getBody();
        Body b2 = f2.getBody();

        if (b1.getUserData().getClass() == Bird.class && b2.getUserData().getClass() == Obstacle.class)
        {
            game.player.health = 45;
            for (int i = 0; i < game.NumOfObjects; i++) {
                if(game.levelObjects[i].exist == true) {
                    if (b2.getUserData() == game.levelObjects[i]) {
                        game.levelObjects[i].health -= PlayerVelocity*5 + ObjectVelocity[i];
                    }
                }
            }
        }

        if (b1.getUserData().getClass() == Obstacle.class && b2.getUserData().getClass() == Bird.class)
        {
            game.player.health = 45;
            for (int i = 0; i < game.NumOfObjects; i++) {
                if(game.levelObjects[i].exist == true) {
                    if (b1.getUserData() == game.levelObjects[i]) {
                        game.levelObjects[i].health -= PlayerVelocity*5 + ObjectVelocity[i];
                    }
                }
            }
        }

        if (b1.getUserData().getClass() == Bird.class && b2.getUserData().getClass() == Pig.class)
        {
            for (int i = 0; i < game.NumOfPigs; i++) {
                if(game.enemies[i].exist == true) {
                    if (b2.getUserData() == game.enemies[i]) {
                        game.enemies[i].health -= PlayerVelocity*5 + EnemiesVelocity[i];
                    }
                }
            }
        }

        if (b1.getUserData().getClass() == Pig.class && b2.getUserData().getClass() == Bird.class)
        {
            for (int i = 0; i < game.NumOfPigs; i++) {
                if(game.enemies[i].exist == true) {
                    if (b1.getUserData() == game.enemies[i]) {
                        game.enemies[i].health -= PlayerVelocity*5 + EnemiesVelocity[i];
                    }
                }
            }
        }

        if (b1.getUserData().getClass() == Obstacle.class && b2.getUserData().getClass() == Pig.class)
        {
            float v1 = 0;
            float v2 = 0;
            if (b1.getUserData() == game.bedRock)
            {
                   v1 = 0;
            }
            else {
                for (int i = 0; i < game.NumOfObjects; i++) {
                    if (game.levelObjects[i].exist == true) {
                        if (b1.getUserData() == game.levelObjects[i]) {
                            v1 = ObjectVelocity[i];
                        }
                    }
                }
            }
            for (int i = 0; i < game.NumOfPigs; i++) {
                if(game.enemies[i].exist == true) {
                    if (b2.getUserData() == game.enemies[i]) {
                        v2 = Math.abs(EnemiesVelocity[i]);
                        if (v1 != 0) {
                            game.enemies[i].health -= v1 * 15 + v2 * 2;
                        }
                        else
                        {
                            game.enemies[i].health -= v2 * 10;
                        }
                    }
                }
            }
        }

        if (b1.getUserData().getClass() == Pig.class && b2.getUserData().getClass() == Obstacle.class)
        {
            float v1 = 0;
            float v2 = 0;
            if(b2.getUserData() == game.bedRock)
            {
                v2 = 0;
            }
            else {
                for (int i = 0; i < game.NumOfObjects; i++) {
                    if (game.levelObjects[i].exist == true) {
                        if (b2.getUserData() == game.levelObjects[i]) {
                            v1 = ObjectVelocity[i];
                        }
                    }
                }
            }

            for (int i = 0; i < game.NumOfPigs; i++) {
                if (game.enemies[i].exist == true) {
                    if (b1.getUserData() == game.enemies[i]) {
                        v2 = EnemiesVelocity[i];
                        if (v1 != 0) {
                            game.enemies[i].health -= v1 * 15 + v2 * 2;
                        }
                        else
                        {
                            game.enemies[i].health -= v2 * 10;
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

            for (int i = 0; i < game.NumOfObjects; i++) {
                if(game.levelObjects[i].exist == true) {
                    if (b1.getUserData() == game.levelObjects[i]) {
                        v1 = ObjectVelocity[i];
                        p1 = i;
                    }
                }
            }
            for (int i = 0; i < game.NumOfObjects; i++) {
                if(game.levelObjects[i].exist == true) {
                    if (b2.getUserData() == game.levelObjects[i]) {
                        v2 = ObjectVelocity[i];
                        p2 = i;
                    }
                }
            }

            game.levelObjects[p1].health -= v1*5+v2*3;
            game.levelObjects[p2].health -= v1*5+v2*3;
        }
    }
};