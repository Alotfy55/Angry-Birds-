package Themes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class Theme {

    public Texture Background ;
    public Texture RED ;
    public Texture RED_Hurt ;
    public Texture Yellow ;
    public Texture Yellow_Hurt ;
    public Texture Pig ;
    public Texture Pig_Hurt ;
    public Texture Wood1 ;
    public Texture Wood2 ;
    public Texture Wood3 ;
    public Texture Woosh1 ;
    public Texture Woosh2 ;
    public Texture Woosh3 ;
    public Texture Woosh4 ;
    public Texture Woosh5 ;
    public Music GameMusic;

    public Theme(String Name)
    {
        if(Name.equalsIgnoreCase("Normal"))
        {
            Background = new Texture("Normal/background.png");
            RED = new Texture("Normal/RED.png");
            RED_Hurt = new Texture("Normal/Red_hurt.png");
            Yellow = new Texture("Normal/Yellow.png");
            Yellow_Hurt = new Texture("Normal/Yellow_hurt.png");
            Pig = new Texture("Normal/Pig.png");
            Pig_Hurt = new Texture("Normal/Pig_hurt.png");
            Wood1 = new Texture("Normal/Wood1.png");
            Wood2 = new Texture("Normal/Wood2.png");
            Wood3 = new Texture("Normal/Wood3.png");
            Woosh1 = new Texture("Normal/Woosh1.png");
            Woosh2 = new Texture("Normal/Woosh2.png");
            Woosh3 = new Texture("Normal/Woosh3.png");
            Woosh4 = new Texture("Normal/Woosh4.png");
            Woosh5 = new Texture("Normal/Woosh5.png");
            GameMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Normal.wav"));
        }
        else if(Name.equalsIgnoreCase("StarWars"))
        {
            Background = new Texture("StarWars/background.png");
            RED = new Texture("StarWars/RED.png");
            RED_Hurt = new Texture("StarWars/Red_hurt.png");
            Yellow = new Texture("StarWars/Yellow.png");
            Yellow_Hurt = new Texture("StarWars/Yellow_hurt.png");
            Pig = new Texture("StarWars/Pig.png");
            Pig_Hurt = new Texture("StarWars/Pig_hurt.png");
            Wood1 = new Texture("StarWars/Wood1.png");
            Wood2 = new Texture("StarWars/Wood2.png");
            Wood3 = new Texture("StarWars/Wood3.png");
            Woosh1 = new Texture("StarWars/Woosh1.png");
            Woosh2 = new Texture("StarWars/Woosh2.png");
            Woosh3 = new Texture("StarWars/Woosh3.png");
            Woosh4 = new Texture("StarWars/Woosh4.png");
            Woosh5 = new Texture("StarWars/Woosh5.png");
            GameMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/StarWars.mp3"));
        }
        else if(Name.equalsIgnoreCase("Space"))
        {
            Background = new Texture("Space/background.png");
            RED = new Texture("Space/RED.png");
            RED_Hurt = new Texture("Space/Red_hurt.png");
            Yellow = new Texture("Space/Yellow.png");
            Yellow_Hurt = new Texture("Space/Yellow_hurt.png");
            Pig = new Texture("Space/Pig.png");
            Pig_Hurt = new Texture("Space/Pig_hurt.png");
            Wood1 = new Texture("Space/Wood1.png");
            Wood2 = new Texture("Space/Wood2.png");
            Wood3 = new Texture("Space/Wood3.png");
            Woosh1 = new Texture("Space/Woosh1.png");
            Woosh2 = new Texture("Space/Woosh2.png");
            Woosh3 = new Texture("Space/Woosh3.png");
            Woosh4 = new Texture("Space/Woosh4.png");
            Woosh5 = new Texture("Space/Woosh5.png");
            GameMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Space.mp3"));
        }
        else if(Name.equalsIgnoreCase("Avengers"))
        {
            Background = new Texture("Avengers/background.png");
            RED = new Texture("Avengers/RED.png");
            RED_Hurt = new Texture("Avengers/Red_hurt.png");
            Yellow = new Texture("Avengers/Yellow.png");
            Yellow_Hurt = new Texture("Avengers/Yellow_hurt.png");
            Pig = new Texture("Avengers/Pig.png");
            Pig_Hurt = new Texture("Avengers/Pig_hurt.png");
            Wood1 = new Texture("Avengers/Wood1.png");
            Wood2 = new Texture("Avengers/Wood2.png");
            Wood3 = new Texture("Avengers/Wood3.png");
            Woosh1 = new Texture("Avengers/Woosh1.png");
            Woosh2 = new Texture("Avengers/Woosh2.png");
            Woosh3 = new Texture("Avengers/Woosh3.png");
            Woosh4 = new Texture("Avengers/Woosh4.png");
            Woosh5 = new Texture("Avengers/Woosh5.png");
            GameMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Avengers.mp3"));
        }
        else if(Name.equalsIgnoreCase("Spurs"))
        {
            Background = new Texture("Spurs/background.png");
            RED = new Texture("Spurs/RED.png");
            RED_Hurt = new Texture("Spurs/Red_hurt.png");
            Yellow = new Texture("Spurs/Yellow.png");
            Yellow_Hurt = new Texture("Spurs/Yellow_hurt.png");
            Pig = new Texture("Spurs/Pig.png");
            Pig_Hurt = new Texture("Spurs/Pig_hurt.png");
            Wood1 = new Texture("Spurs/Wood1.png");
            Wood2 = new Texture("Spurs/Wood2.png");
            Wood3 = new Texture("Spurs/Wood3.png");
            Woosh1 = new Texture("Spurs/Woosh1.png");
            Woosh2 = new Texture("Spurs/Woosh2.png");
            Woosh3 = new Texture("Spurs/Woosh3.png");
            Woosh4 = new Texture("Spurs/Woosh4.png");
            Woosh5 = new Texture("Spurs/Woosh5.png");
            GameMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Spurs.mp3"));
        }
        else
        {
            Background = new Texture("Normal/background.png");
            RED = new Texture("Normal/RED.png");
            RED_Hurt = new Texture("Normal/Red_hurt.png");
            Yellow = new Texture("Normal/Yellow.png");
            Yellow_Hurt = new Texture("Normal/Yellow_hurt.png");
            Pig = new Texture("Normal/Pig.png");
            Pig_Hurt = new Texture("Normal/Pig_hurt.png");
            Wood1 = new Texture("Normal/Wood1.png");
            Wood2 = new Texture("Normal/Wood2.png");
            Wood3 = new Texture("Normal/Wood3.png");
            Woosh1 = new Texture("Normal/Woosh1.png");
            Woosh2 = new Texture("Normal/Woosh2.png");
            Woosh3 = new Texture("Normal/Woosh3.png");
            Woosh4 = new Texture("Normal/Woosh4.png");
            Woosh5 = new Texture("Normal/Woosh5.png");
            GameMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/Normal.wav"));
        }
    }

}
