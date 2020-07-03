package com.angrybirds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class ThemeScreen implements Screen {

    Stage stage;
    private Project_Entery PE;
    private Texture WP;
    private Sprite Wallpaper;

    TextButton[] button;
    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
    static Music ThemeMusic;

    public ThemeScreen(Project_Entery PE)
    {
        this.PE = PE;
    }

    @Override
    public void show() {
        MainMenu.mainMusic.stop();
        ThemeMusic = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Sound/ThemesSong.mp3"));
        this.WP = new Texture("core/assets/Themes.png");
        this.Wallpaper = new Sprite(WP);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        button = new TextButton[5];
        font = new BitmapFont(Gdx.files.internal("core/assets/font/font34.fnt"), false);
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/Button/Button.atlas"));
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("Button");
        textButtonStyle.down = skin.getDrawable("ButtonPressed");
        button[0] = new TextButton("SPURS", textButtonStyle);
        button[0].setBounds(90,400,170,170);
        button[0].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                MainMenu.ThemeName = "Spurs";
                ThemeMusic.stop();
                hide();
                PE.setScreen(new MainMenu(PE));

            }
        });
        stage.addActor(button[0]);

        button[1] = new TextButton("STAR WARS", textButtonStyle);
        button[1].setBounds(480,350,300,170);
        button[1].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                MainMenu.ThemeName = "StarWars";
                ThemeMusic.stop();
                hide();
                PE.setScreen(new MainMenu(PE));
            }
        });
        stage.addActor(button[1]);

        button[2] = new TextButton("AVENGERS", textButtonStyle);
        button[2].setBounds(1000,400,270,170);
        button[2].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                MainMenu.ThemeName = "Avengers";
                ThemeMusic.stop();
                hide();
                PE.setScreen(new MainMenu(PE));
            }
        });
        stage.addActor(button[2]);

        button[3] = new TextButton("Normal", textButtonStyle);
        button[3].setBounds(840,100,200,170);
        button[3].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                MainMenu.ThemeName = "Normal";
                ThemeMusic.stop();
                hide();
                PE.setScreen(new MainMenu(PE));
            }
        });
        stage.addActor(button[3]);

        button[4] = new TextButton("SPACE", textButtonStyle);
        button[4].setBounds(200,100,200,170);
        button[4].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MainMenu.ThemeName = "Space";
                ThemeMusic.stop();
                hide();
                PE.setScreen(new MainMenu(PE));
            }
        });
        stage.addActor(button[4]);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ThemeMusic.play();
        stage.act(delta);
        stage.getBatch().begin();
        stage.getBatch().draw(Wallpaper,0,0);
        stage.getBatch().end();
        stage.draw();
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

    }
}
