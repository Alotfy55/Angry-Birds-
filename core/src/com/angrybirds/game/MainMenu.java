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

public class MainMenu implements Screen {

    Stage stage;
    public static String ThemeName = "Normal";
    private Project_Entery PE;
    private Texture WP;
    private Sprite Wallpaper;

    TextButton[] button;
    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
    static boolean set;

    static Music mainMusic;
    static boolean playedMusic = false;

    public MainMenu(Project_Entery PE)
    {
        this.PE = PE;
    }

    @Override
    public void show() {
        set = false;
        mainMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/song.mp3"));
        this.WP = new Texture("MenuWP.jpg");
        this.Wallpaper = new Sprite(WP);
        Wallpaper.setPosition(-50,-280);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        button = new TextButton[4];
        font = new BitmapFont(Gdx.files.internal("font/font40.fnt"), false);
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("Button/Button.atlas"));
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("Button");
        textButtonStyle.down = skin.getDrawable("ButtonPressed");
        button[0] = new TextButton("PLAY", textButtonStyle);
        button[0].setBounds(200,400,300,200);
        button[0].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!set) {
                    PE.setScreen(new GameScreen(PE, ThemeName));
                }
            }
        });
        stage.addActor(button[0]);
        button[1] = new TextButton("Themes", textButtonStyle);
        button[1].setBounds(200,280,300,200);
        button[1].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(!set) {
                    PE.setScreen(new ThemeScreen(PE));
                }
            }
        });
        stage.addActor(button[1]);
        button[2] = new TextButton("Scores", textButtonStyle);
        button[2].setBounds(200,160,300,200);
        stage.addActor(button[2]);
        button[3] = new TextButton("Exit", textButtonStyle);
        button[3].setBounds(200,40,300,200);
        button[3].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(!set) {
                    dispose();
                }
            }
        });
        stage.addActor(button[3]);



    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(playedMusic == false) {
            mainMusic.play();
        }
        stage.act(delta);
        stage.getBatch().begin();
        stage.getBatch().draw(Wallpaper,-50,-280);
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
        WP.dispose();
        PE.dispose();
        stage.dispose();
    }
}
