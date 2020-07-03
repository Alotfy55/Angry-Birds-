package com.angrybirds.game;

import TweenManager.SpriteTween;
import aurelienribon.tweenengine.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen2 implements Screen {

    Project_Entery PE;
    private Texture Splash;
    private Sprite Powered;
    private SpriteBatch batch;
    TweenManager manager;

    public SplashScreen2(Project_Entery PE)
    {
        this.PE = PE;
    }

    @Override
    public void show() {
        this.Splash = new Texture("core/assets/Built with.jpg");
        this.Powered = new Sprite(this.Splash);
        Powered.setColor(1,1,1,0);

        batch = new SpriteBatch();
        Powered.setPosition(0,0);

        Tween.registerAccessor(Sprite.class, new SpriteTween());

        manager = new TweenManager();

        TweenCallback cb = new TweenCallback() {
            @Override
            public void onEvent(int i, BaseTween<?> baseTween) {
                tweenCompleted();
            }
        };

        Tween.to(Powered, SpriteTween.alpha,1.5f).target(1).ease(TweenEquations.easeInQuad).repeatYoyo(1, 1F).setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE).start(manager);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        manager.update(delta);
        batch.begin();
        Powered.draw(batch);
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
        this.PE.setScreen(new MainMenu(PE));
    }
}
