package com.angrybirds.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class CreditsScreen implements Screen {

    private Music Song;
    Stage stage;
    private Project_Entery PE;
    private Texture WP;
    private Sprite Wallpaper;
    BitmapFont font;
    BitmapFont font2;
    int timer;
    float x;
    float y;
    int PicTimer;
    float TeamNameY;

    Person[] Team;

    ImageButton Exit;
    ImageButton.ImageButtonStyle imageButtonStyle;
    Skin skin;
    TextureAtlas buttonAtlas;

    public CreditsScreen(Project_Entery PE)
    {
        this.PE =PE;
    }

    @Override
    public void show() {
        MainMenu.mainMusic.stop();
        Song = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Sound/CreditsTheme.mp3"));
        stage = new Stage();
        WP = new Texture("core/assets/CreditsBG.png");
        Wallpaper = new Sprite(WP);
        timer = 0;
        PicTimer = 0;
        x = 500;
        y = 400;

        Team = new Person[6];
        Team[0] = new Person("core/assets/Team/Abdulrahman.png",100,-200,"Abdulrahman Mohammad");
        Team[1] = new Person("core/assets/Team/Abdelrahman.png",100,-600,"Abdulrahman Elsalahy");
        Team[2] = new Person("core/assets/Team/Ahmad.png",100,-1000,"Ahmad Lotfy");
        Team[3] = new Person("core/assets/Team/Mahmoud.png",100,-1400,"Mahmoud Sayed");
        Team[4] = new Person("core/assets/Team/Moamen.png",100,-1800,"Moamen Mohammad");
        Team[5] = new Person("core/assets/Team/Seif.png",100,-2200,"Seif Emad");
        font = new BitmapFont(Gdx.files.internal("core/assets/font/font80.fnt"), false);
        font2 = new BitmapFont(Gdx.files.internal("core/assets/font/font60.fnt"), false);
        TeamNameY = -2900;

        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/Button/ExitButton.atlas"));
        skin.addRegions(buttonAtlas);
        imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = skin.getDrawable("ExitButton");
        imageButtonStyle.down = skin.getDrawable("ExitButtonPressed");
        Exit = new ImageButton(imageButtonStyle);
        Exit.setBounds(20,660,50,50);
        Exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Song.stop();
                PE.setScreen(new MainMenu(PE));
            }
        });
        stage.addActor(Exit);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Song.play();
        stage.act(delta);
        stage.getBatch().begin();

        stage.getBatch().draw(Wallpaper,0,0,1280,720);
        timer++;
        System.out.println(timer);
        if (timer > 180)
        {
            y += 1f;
            for(int i = 0 ; i < 6 ; i++)
            {
                Team[i].pos.y += 1f;
            }
            TeamNameY += 1f;
        }

        for(int i = 0 ; i < 6 ; i++) {
            stage.getBatch().draw(Team[i].Picture,Team[i].pos.x,Team[i].pos.y,200,200);
            font2.draw(stage.getBatch(),Team[i].Name,Team[i].pos.x + 240,Team[i].pos.y+120);
        }

        if(TeamNameY > 450)
        {
            TeamNameY = 450;
            PicTimer++;
            if(PicTimer >= 350)
            {
                this.Song.stop();
                this.PE.setScreen(new MainMenu(PE));
            }
        }



        font.draw(stage.getBatch(),"Credits...",x,y);
        font.draw(stage.getBatch(),"Decode Talkers",400,TeamNameY);
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
        font.dispose();
        font2.dispose();
        for(int i = 0 ; i < 6 ; i++)
        {
            Team[i].pic.dispose();
        }
    }
}

class Person
{
    public Texture pic;
    public Sprite Picture;
    public Vector2 pos;
    public String Name;

    public Person(String Pic, float X,float Y,String Name) {
        this.pic = new Texture(Pic);
        this.Picture = new Sprite(this.pic);
        this.pos = new Vector2(X, Y);
        this.Name = Name;
    }
}