package com.angrybirds.game;

import com.angrybirds.Themes.Theme;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class EndGameScreen implements Screen {

    Project_Entery PE;
    Theme theme;
    int Score;
    int ScoreNum;
    Stage stage;
    Texture blue;
    Texture Star1;
    Texture Star2;
    Texture Star3;
    Texture Star4;
    Texture Star5;
    Texture Star6;
    Texture Adv;
    Sprite BG;
    Sprite LStarE;
    Sprite MStarE;
    Sprite RStarE;
    Sprite LStar;
    Sprite MStar;
    Sprite RStar;
    Sprite Cont;
    int ScoreCounter;
    int TimeCounter;

    ImageButton Restart;
    ImageButton Continue;
    ImageButton.ImageButtonStyle imageButtonStyle;
    TextureAtlas buttonAtlas;
    Skin skin;
    TextButton button;
    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;
    BitmapFont font2;
    public static Sound GameComp;


    public EndGameScreen(Project_Entery PE, Theme theme, int Score, int ScoreNum)
    {
        this.PE = PE;
        this.theme = theme;
        this.Score = Score;
        this.ScoreNum = ScoreNum;
    }

    @Override
    public void show() {

        GameComp = Gdx.audio.newSound(Gdx.files.internal("core/assets/Sound/GameEnd.mp3"));
        GameComp.play();
        stage = new Stage();
        ScoreCounter = 0;
        TimeCounter = 0;
        font = new BitmapFont(Gdx.files.internal("core/assets/font/font70.fnt"), false);
        font2 = new BitmapFont(Gdx.files.internal("core/assets/font/font70.fnt"), false);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        button = new TextButton("Score : "+ Score, textButtonStyle);
        button.setBounds(50,590,100,200);
        //stage.addActor(button);

        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/Button/GameButton.atlas"));
        skin.addRegions(buttonAtlas);
        imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = skin.getDrawable("GameButton");
        imageButtonStyle.down = skin.getDrawable("GameButtonPressed");
        Restart = new ImageButton(imageButtonStyle);
        Restart.setBounds(550,100,50,50);
        Restart.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameScreen.GameMusic.stop();
                PE.setScreen(new GameScreen(PE,theme.Name));
            }
        });
        stage.addActor(Restart);

        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/Button/PauseButton.atlas"));
        skin.addRegions(buttonAtlas);
        imageButtonStyle = new ImageButton.ImageButtonStyle();
        Continue = new ImageButton(imageButtonStyle);
        Continue.setBounds(650,100,50,50);
        Continue.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameScreen.GameMusic.stop();
                PE.setScreen(new MainMenu(PE));
            }
        });
        stage.addActor(Continue);

        Gdx.input.setInputProcessor(stage);
        blue = new Texture("core/assets/ScoreBG.png");
        Star1 = new Texture("core/assets/leftStarE.png");
        Star2 = new Texture("core/assets/midStarE.png");
        Star3 = new Texture("core/assets/rightStarE.png");
        Star4 = new Texture("core/assets/leftStar.png");
        Star5 = new Texture("core/assets/midStar.png");
        Star6 = new Texture("core/assets/rightStar.png");
        Adv = new Texture("core/assets/Resume.png");
        LStarE = new Sprite(Star1);
        MStarE = new Sprite(Star2);
        RStarE = new Sprite(Star3);
        LStar = new Sprite(Star4);
        MStar = new Sprite(Star5);
        RStar = new Sprite(Star6);
        Cont = new Sprite(Adv);
        BG = new Sprite(blue);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.getBatch().begin();
        stage.getBatch().draw(theme.Background,0,0,1280,720);
        stage.getBatch().draw(BG,305,20,670,680);
        stage.getBatch().draw(RStarE,700,220,120,120);
        stage.getBatch().draw(LStarE,450,220,120,120);
        stage.getBatch().draw(MStarE,575,250,120,120);
        stage.getBatch().draw(RStarE,700,220,120,120);
        stage.getBatch().draw(Cont,650,100,50,50);
        font.draw(stage.getBatch(),"Game Clear",500,670);
        if(ScoreCounter < Score)
        {
            font.draw(stage.getBatch(),""+ScoreCounter,530,450);
            if (Score < 10000) {
                ScoreCounter += 500;
            }
            else if (Score < 100000)
            {
                ScoreCounter += 1000;
            }
            else
            {
                ScoreCounter +=5000;
            }

        }
        else
        {
            font.draw(stage.getBatch(),"Score  "+Score,480,450);
        }

        TimeCounter++;

        if(TimeCounter > 100)
        {
            stage.getBatch().draw(LStar,418,223,143,143);
            if(ScoreNum >= 1 && TimeCounter > 150)
            {
                stage.getBatch().draw(MStar,560,250,143,143);
            }
            if(ScoreNum >= 5 && TimeCounter > 200)
            {
                stage.getBatch().draw(RStar,702,223,143,143);
            }
        }

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
