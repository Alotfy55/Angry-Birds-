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
        Fixture f1 = cp.getFixtureA();
        Fixture f2 = cp.getFixtureB();

        Body b1 = f1.getBody();
        Body b2 = f2.getBody();

        Object o1 = b1.getUserData();
        Object o2 = b2.getUserData();

        if (b1.getUserData() instanceof Obstacle) {
            for (int i = 0; i < game.NumOfObjects; i++) {
                if (game.levelObjects[i].exist && (Obstacle) o1 == game.levelObjects[i]) {
                    game.levelObjects[i].exist = false;
                    System.out.println("Destroyed");
                }
            }
        }
    }
};