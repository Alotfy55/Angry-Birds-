package com.angrybirds.game;

import TweenManager.SpriteTween;
import aurelienribon.tweenengine.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen1 implements Screen {

    Project_Entery PE;
    private Texture Splash;
    private Sprite Logo;
    private SpriteBatch batch;
    TweenManager manager;

    public SplashScreen1(Project_Entery PE)
    {
        this.PE = PE;
    }
    @Override
    public void show() {
        this.Splash = new Texture("Logo.jpg");
        this.Logo = new Sprite(this.Splash);
        Logo.setColor(1,1,1,0);

        batch = new SpriteBatch();
        Logo.setPosition(0,0);

        Tween.registerAccessor(Sprite.class, new SpriteTween());

        manager = new TweenManager();

        TweenCallback cb = new TweenCallback() {
            @Override
            public void onEvent(int i, BaseTween<?> baseTween) {
                tweenCompleted();
            }
        };

        Tween.to(Logo, SpriteTween.alpha,1.5f).target(1).ease(TweenEquations.easeInQuad).repeatYoyo(1, 1F).setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE).start(manager);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        manager.update(delta);
        batch.begin();
        Logo.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        Splash.dispose();
        batch.dispose();
        PE.dispose();
    }

    private void tweenCompleted() {
        hide();
        PE.setScreen(new SplashScreen2(PE));
    }

}
