package com.angrybirds.game;

import com.angrybirds.Everything.*;
import com.angrybirds.Themes.Theme;
import com.angrybirds.action_Listener.ListenerClass;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class GameScreen implements Screen {

    SpriteBatch batch;

    Stage stage;
    TextButton button;
    TextButton.TextButtonStyle textButtonStyle;
    TextureAtlas buttonAtlas;
    ImageButton Restart;
    ImageButton Exit;
    ImageButton.ImageButtonStyle imageButtonStyle;
    BitmapFont font;
    Skin skin;

    Texture img;
    Texture background;
    Sprite BG;

    public static World world;
    Box2DDebugRenderer dDebugRenderer;
    OrthographicCamera camera;

    public int Score;
    public static int numOfBirds;
    public static Theme theme;
    public static int NumOfObjects;
    public static int NumOfPigs;
    static int layoutWidth;
    static int layoutHeight[];
    public static Everything player;
    public static Everything[] levelObjects;
    public static Everything[] enemies;
    public static Everything bedRock;
    static Bird_Launcher launcher;
    public static boolean player_exists;
    public static String ThemeName;

    public static Music GameMusic;

    Project_Entery PE;
    public static boolean pausePhysics;
    static boolean hold;
    public static final float PPM = 32;
    public Vector2 mouseOrigin;
    ListenerClass lis;
    private int ScoreNum;

    public GameScreen(Project_Entery PE, String Name) {
        this.PE = PE;
        ThemeName = Name;
        theme = new Theme(Name);
    }

    @Override
    public void show() {
        MainMenu.mainMusic.stop();
        GameMusic = theme.GameMusic;
        MainMenu.set = true;

        stage = new Stage();

        font = new BitmapFont(Gdx.files.internal("core/assets/font/font30.fnt"), false);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        button = new TextButton("Score : "+ Score, textButtonStyle);
        button.setBounds(50,590,100,200);
        stage.addActor(button);

        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/Button/GameButton.atlas"));
        skin.addRegions(buttonAtlas);
        imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = skin.getDrawable("GameButton");
        imageButtonStyle.down = skin.getDrawable("GameButtonPressed");
        Restart = new ImageButton(imageButtonStyle);
        Restart.setBounds(1140,660,50,50);
        Restart.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameMusic.stop();
                PE.setScreen(new GameScreen(PE,ThemeName));
            }
        });
        stage.addActor(Restart);

        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("core/assets/Button/ExitButton.atlas"));
        skin.addRegions(buttonAtlas);
        imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.up = skin.getDrawable("ExitButton");
        imageButtonStyle.down = skin.getDrawable("ExitButtonPressed");
        Exit = new ImageButton(imageButtonStyle);
        Exit.setBounds(1200,660,50,50);
        Exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                GameMusic.stop();
                PE.setScreen(new MainMenu(PE));
            }
        });
        stage.addActor(Exit);

        Gdx.input.setInputProcessor(stage);


        Box2D.init();
        lis = new ListenerClass();
        dDebugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(20,10);
        pausePhysics = true;
        hold = false;
        NumOfObjects = 0;
        NumOfPigs = 0;
        numOfBirds = 1;
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        background = theme.Background;
        BG = new Sprite(background);

        world = new World(new Vector2(0, -10), false);
        world.setContactListener(lis);
        createLevel();
        player = new Yellow_Bird(world, 50, 78, 10, 10, false);
        bedRock = new Obstacle( world ,Gdx.graphics.getWidth() / 2, 27, 10000, 1, true);
        launcher = new Bird_Launcher();
    }

    @Override
    public void render(float delta) {

        GameMusic.play();


        if (pausePhysics)
            player.body.setAwake(false);
        else
            player.body.setAwake(true);

        world.step(1 / 60f, 6, 2);

        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(135, 206, 236, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(player.condition > 0)
        {
            player.Time_counter++;
        }
        if(player.Time_counter > 240)
        {
            player.health = 0;
        }


        batch.begin();


        batch.draw(BG, 0,0,20,11.25f);


        launcher.body.setPosition(37/PPM,27/PPM);
        launcher.body.draw(batch);



        for ( int i = 0 ; i < NumOfObjects ; i++) {
            if(levelObjects[i].exist) {
                levelObjects[i].sprite[levelObjects[i].condition].setBounds(levelObjects[i].body.getPosition().x - levelObjects[i].sprite[levelObjects[i].condition].getWidth() / (2), levelObjects[i].body.getPosition().y - levelObjects[i].sprite[levelObjects[i].condition].getHeight() / (2), 2 * (levelObjects[i].width / PPM), 2 * (levelObjects[i].height / PPM));
                levelObjects[i].sprite[levelObjects[i].condition].setOrigin(levelObjects[i].sprite[levelObjects[i].condition].getWidth() / 2, levelObjects[i].sprite[levelObjects[i].condition].getHeight() / 2);
                levelObjects[i].sprite[levelObjects[i].condition].setRotation(MathUtils.radiansToDegrees * levelObjects[i].body.getAngle());
                levelObjects[i].sprite[levelObjects[i].condition].draw(batch);
            }
        }
        for ( int i = 0 ; i < NumOfPigs ; i++) {
            if(enemies[i].exist) {
                enemies[i].sprite[enemies[i].condition].setBounds(enemies[i].body.getPosition().x - enemies[i].sprite[enemies[i].condition].getWidth() / (2), enemies[i].body.getPosition().y - enemies[i].sprite[enemies[i].condition].getHeight() / (2), 2 * (enemies[i].width / PPM), 2 * (enemies[i].height / PPM));
                enemies[i].sprite[enemies[i].condition].setOrigin(enemies[i].sprite[enemies[i].condition].getWidth() / 2, enemies[i].sprite[enemies[i].condition].getHeight() / 2);
                enemies[i].sprite[enemies[i].condition].setRotation(MathUtils.radiansToDegrees * enemies[i].body.getAngle());
                enemies[i].sprite[enemies[i].condition].draw(batch);
            }
        }

        if(player.exist) {
            player.sprite[player.condition].setBounds(player.body.getPosition().x - player.sprite[player.condition].getWidth() / (2), player.body.getPosition().y - player.sprite[player.condition].getHeight() / (2), 2 * (player.width / PPM), 2 * (player.height / PPM));
            player.sprite[player.condition].setOrigin(player.sprite[player.condition].getWidth() / 2, player.sprite[player.condition].getHeight() / 2);
            player.sprite[player.condition].setRotation(MathUtils.radiansToDegrees * player.body.getAngle());
            player.sprite[player.condition].draw(batch);
            if(!pausePhysics && !player.SoundDone)
            {
                player.Sound.play();
                player.SoundDone = true;
            }
        }


        launcher.arm.draw(batch);

        if ( Gdx.input.getPressure() == 1 && pausePhysics && includes((Bird) player , new Vector2(Gdx.input.getX()/2,(Gdx.graphics.getHeight() - Gdx.input.getY()) /2 )))
        {
            hold = true;
            if (mouseOrigin == null)
                mouseOrigin = new Vector2(Gdx.input.getX() ,Gdx.graphics.getHeight() -  Gdx.input.getY());
        }
        if (hold && pausePhysics)
        {
            if(!launcher.strechPlayed) {
                launcher.stretchSound.play();
                launcher.strechPlayed = true;
            }
            getMouseInput();
            launcher.body.setPosition(37/PPM,27/PPM);
            launcher.body.draw(batch);

            launcher.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            launcher.shapeRenderer.setColor(0.2f, 0f, 0f, 1f);
            launcher.shapeRenderer.rectLine(60/PPM, 75/PPM, GameScreen.player.body.getPosition().x, GameScreen.player.body.getPosition().y, 5/PPM);
            launcher.shapeRenderer.end();
            launcher.shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());

            if(player.exist) {
                player.sprite[player.condition].setBounds(player.body.getPosition().x - player.sprite[player.condition].getWidth() / (2), player.body.getPosition().y - player.sprite[player.condition].getHeight() / (2), 2 * (player.width / PPM), 2 * (player.height / PPM));
                player.sprite[player.condition].setOrigin(player.sprite[player.condition].getWidth() / 2, player.sprite[player.condition].getHeight() / 2);
                player.sprite[player.condition].setRotation(MathUtils.radiansToDegrees * player.body.getAngle());
                player.sprite[player.condition].draw(batch);
            }

            launcher.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            launcher.shapeRenderer.setColor(0.2f, 0f, 0f, 1f);
            launcher.shapeRenderer.rectLine(45/PPM, 75/PPM, GameScreen.player.body.getPosition().x, GameScreen.player.body.getPosition().y, 5/PPM);
            launcher.shapeRenderer.end();
            launcher.shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            launcher.arm.draw(batch);
        }


        player.Special_ability();


        for(int i = 0 ; i < GameScreen.NumOfObjects ; i++)
        {
            levelObjects[i].Condition();
        }
        for(int i = 0 ; i < GameScreen.NumOfPigs ; i++)
        {
            enemies[i].Condition();
        }
        player.Condition();

        checkExist();

        if(CheckEndGame())
        {
            ScoreNum = NumOfPigs-numOfBirds;
            this.PE.setScreen(new EndGameScreen(PE,this.theme,this.Score,this.ScoreNum));
        }

        batch.end();

        stage.act(delta);
        button.setText("Score : "+ Score);
        stage.draw();

        CheckRevive();
        //dDebugRenderer.render(world,camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        float screenAR = width / (float) height;
        // Our camera needs to be created with new aspect ratio
        // Our visible game world width is still 20m but we need to
        // calculate what height keeps the AR correct.
        camera = new OrthographicCamera(20, 20/screenAR);
        // Finally set camera position so that (0,0) is at bottom left
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        // If we use sprite batch to draw lets update it here for new camera
        batch = new SpriteBatch();
        // This line says:"Camera lower left corner is 0,0. Width is 20 and height is 20/AR. Draw there!"
        batch.setProjectionMatrix(camera.combined);
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
        batch.dispose();
        img.dispose();
        background.dispose();
        for ( int i = 0 ; i < NumOfObjects ; i++) {
            for (int j = 0 ; j < 3 ; j++)
            {
                levelObjects[i].texture[j].dispose();
            }
        }
        for ( int i = 0 ; i < NumOfPigs ; i++) {
            for (int j = 0 ; j < 2 ; j++)
            {
                enemies[i].texture[j].dispose();
            }
        }
        for (int i = 0 ; i < 2 ; i ++)
        {
            player.texture[i].dispose();
        }
        world.dispose();
        dDebugRenderer.dispose();
    }

    public void getMouseInput() {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            player.body.setTransform((float) Gdx.input.getX() / PPM / 2, (float) (Gdx.graphics.getHeight() - Gdx.input.getY()) / PPM / 2, 0);
        }
        if (Gdx.input.getPressure() == 0) {
            hold = false;
            pausePhysics = false;
            player.body.setLinearVelocity(((mouseOrigin.x - Gdx.input.getX()) / 8), (mouseOrigin.y - (Gdx.graphics.getHeight() - Gdx.input.getY())) / 4);
        }

    }

    public boolean includes(Bird player, Vector2 mousePoint ) {
        if (mousePoint.x > player.body.getPosition().x*PPM - (player.width) && mousePoint.x < player.body.getPosition().x*PPM + (player.width) &&
                mousePoint.y > player.body.getPosition().y*PPM - (player.height)  && mousePoint.y < player.body.getPosition().y*PPM + (player.height))
            return true;
        else
            return false;
    }

    public void checkExist()
    {
        for ( int i = 0 ; i < NumOfObjects ; i++) {
            if(levelObjects[i].exist == false && levelObjects[i].destroyed == false)
            {
                world.destroyBody(levelObjects[i].body);
                levelObjects[i].destroyed = true;
                levelObjects[i].Destroy.play();
                Score += 3003;

            }
        }
        for ( int i = 0 ; i < NumOfPigs ; i++) {
            if(enemies[i].exist == false && enemies[i].destroyed == false)
            {
                world.destroyBody(enemies[i].body);
                enemies[i].destroyed = true;
                enemies[i].Destroy.play();
                Score += 10000;
            }
        }

        if (player.exist == false && player.destroyed == false)
        {
            world.destroyBody(player.body);
            player.destroyed = true;
            GameScreen.player_exists = false;
            player.Destroy.play();
            launcher.strechPlayed = false;
        }
    }

    public void createLevel(){
        layoutWidth = (int) ((Math.random() * 10) % 5) + 1 ;
        layoutHeight = new int [layoutWidth];
        for ( int i = 0; i < layoutWidth;i++)
        {
            layoutHeight[i] = (int) ((Math.random() * 10) % 5) + 1 ;
            NumOfPigs += layoutHeight[i];
        }
        enemies = new Pig[NumOfPigs];
        NumOfObjects = 3*NumOfPigs;
        levelObjects = new Obstacle[NumOfObjects];
        createBodies(28 , 400);
    }

    private void createBodies(int groundlevel , int startingX) {
        int enemiesCounter = 0;
        int woodCounter = 0;
        for (int i = 0 ; i < layoutWidth ; i++){
            startingX += (4*5 + 20) ;
            for (int j = 0 ; j < layoutHeight[i] ; j++){

                enemies[enemiesCounter++] = new Pig(world,startingX, groundlevel + j*10 + j*(2*20 + 2*5) +10,  10,10 , false );

                levelObjects[woodCounter++] = new Obstacle(world,startingX - 17 , groundlevel + 20 + j*(40 + 10),  5,20 , false );
                levelObjects[woodCounter++] = new Obstacle(world,startingX + 17 , groundlevel + 20 + j*(40 + 10),  5,20 , false );

                levelObjects[woodCounter] = new Obstacle(world , startingX , groundlevel + (j+1)*(10 + 40) , 5 , 20 ,false);
                levelObjects[woodCounter].body.setTransform(levelObjects[woodCounter].body.getPosition().x , levelObjects[woodCounter].body.getPosition().y , (float) (90*(Math.PI/180)));
                woodCounter++;

            }
        }
    }

    public void CheckRevive()
    {
        if(player.destroyed == true)
        {
            int num = (int) ((Math.random() * 10) % 2) ;
            if (num == 1)
            {
                player = new Yellow_Bird(world, 50, 78, 10, 10, false);
            }
            else
            {
                player = new Bird(world, 50, 78, 10, 10, false);
            }

            numOfBirds ++;
            Score = (Score/numOfBirds);
            pausePhysics = true;
        }
    }

    public boolean CheckEndGame()
    {
        for ( int i = 0 ; i < NumOfPigs ; i++) {
            if(enemies[i].destroyed == false)
            {
                return false;
            }
        }
        if(player.destroyed)
            return true;
        else
            return false;
    }



}
