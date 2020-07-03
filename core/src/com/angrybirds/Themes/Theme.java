package com.angrybirds.Themes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class Theme {

    public String Name;
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
        this.Name = Name;
        if(Name.equalsIgnoreCase("Normal"))
        {
            Background = new Texture("core/assets/Normal/background.png");
            RED = new Texture("core/assets/Normal/RED.png");
            RED_Hurt = new Texture("core/assets/Normal/Red_hurt.png");
            Yellow = new Texture("core/assets/Normal/Yellow.png");
            Yellow_Hurt = new Texture("core/assets/Normal/Yellow_hurt.png");
            Pig = new Texture("core/assets/Normal/Pig.png");
            Pig_Hurt = new Texture("core/assets/Normal/Pig_hurt.png");
            Wood1 = new Texture("core/assets/Normal/Wood1.png");
            Wood2 = new Texture("core/assets/Normal/Wood2.png");
            Wood3 = new Texture("core/assets/Normal/Wood3.png");
            Woosh1 = new Texture("core/assets/Normal/Woosh1.png");
            Woosh2 = new Texture("core/assets/Normal/Woosh2.png");
            Woosh3 = new Texture("core/assets/Normal/Woosh3.png");
            Woosh4 = new Texture("core/assets/Normal/Woosh4.png");
            Woosh5 = new Texture("core/assets/Normal/Woosh5.png");
            GameMusic = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Sound/Normal.wav"));
        }
        else if(Name.equalsIgnoreCase("StarWars"))
        {
            Background = new Texture("core/assets/StarWars/background.png");
            RED = new Texture("core/assets/StarWars/RED.png");
            RED_Hurt = new Texture("core/assets/StarWars/Red_hurt.png");
            Yellow = new Texture("core/assets/StarWars/Yellow.png");
            Yellow_Hurt = new Texture("core/assets/StarWars/Yellow_hurt.png");
            Pig = new Texture("core/assets/StarWars/Pig.png");
            Pig_Hurt = new Texture("core/assets/StarWars/Pig_hurt.png");
            Wood1 = new Texture("core/assets/StarWars/Wood1.png");
            Wood2 = new Texture("core/assets/StarWars/Wood2.png");
            Wood3 = new Texture("core/assets/StarWars/Wood3.png");
            Woosh1 = new Texture("core/assets/StarWars/Woosh1.png");
            Woosh2 = new Texture("core/assets/StarWars/Woosh2.png");
            Woosh3 = new Texture("core/assets/StarWars/Woosh3.png");
            Woosh4 = new Texture("core/assets/StarWars/Woosh4.png");
            Woosh5 = new Texture("core/assets/StarWars/Woosh5.png");
            GameMusic = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Sound/StarWars.mp3"));
        }
        else if(Name.equalsIgnoreCase("Space"))
        {
            Background = new Texture("core/assets/Space/background.png");
            RED = new Texture("core/assets/Space/RED.png");
            RED_Hurt = new Texture("core/assets/Space/Red_hurt.png");
            Yellow = new Texture("core/assets/Space/Yellow.png");
            Yellow_Hurt = new Texture("core/assets/Space/Yellow_hurt.png");
            Pig = new Texture("core/assets/Space/Pig.png");
            Pig_Hurt = new Texture("core/assets/Space/Pig_hurt.png");
            Wood1 = new Texture("core/assets/Space/Wood1.png");
            Wood2 = new Texture("core/assets/Space/Wood2.png");
            Wood3 = new Texture("core/assets/Space/Wood3.png");
            Woosh1 = new Texture("core/assets/Space/Woosh1.png");
            Woosh2 = new Texture("core/assets/Space/Woosh2.png");
            Woosh3 = new Texture("core/assets/Space/Woosh3.png");
            Woosh4 = new Texture("core/assets/Space/Woosh4.png");
            Woosh5 = new Texture("core/assets/Space/Woosh5.png");
            GameMusic = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Sound/Space.mp3"));
        }
        else if(Name.equalsIgnoreCase("Avengers"))
        {
            Background = new Texture("core/assets/Avengers/background.png");
            RED = new Texture("core/assets/Avengers/RED.png");
            RED_Hurt = new Texture("core/assets/Avengers/Red_hurt.png");
            Yellow = new Texture("core/assets/Avengers/Yellow.png");
            Yellow_Hurt = new Texture("core/assets/Avengers/Yellow_hurt.png");
            Pig = new Texture("core/assets/Avengers/Pig.png");
            Pig_Hurt = new Texture("core/assets/Avengers/Pig_hurt.png");
            Wood1 = new Texture("core/assets/Avengers/Wood1.png");
            Wood2 = new Texture("core/assets/Avengers/Wood2.png");
            Wood3 = new Texture("core/assets/Avengers/Wood3.png");
            Woosh1 = new Texture("core/assets/Avengers/Woosh1.png");
            Woosh2 = new Texture("core/assets/Avengers/Woosh2.png");
            Woosh3 = new Texture("core/assets/Avengers/Woosh3.png");
            Woosh4 = new Texture("core/assets/Avengers/Woosh4.png");
            Woosh5 = new Texture("core/assets/Avengers/Woosh5.png");
            GameMusic = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Sound/Avengers.mp3"));
        }
        else if(Name.equalsIgnoreCase("Spurs"))
        {
            Background = new Texture("core/assets/Spurs/background.png");
            RED = new Texture("core/assets/Spurs/RED.png");
            RED_Hurt = new Texture("core/assets/Spurs/Red_hurt.png");
            Yellow = new Texture("core/assets/Spurs/Yellow.png");
            Yellow_Hurt = new Texture("core/assets/Spurs/Yellow_hurt.png");
            Pig = new Texture("core/assets/Spurs/Pig.png");
            Pig_Hurt = new Texture("core/assets/Spurs/Pig_hurt.png");
            Wood1 = new Texture("core/assets/Spurs/Wood1.png");
            Wood2 = new Texture("core/assets/Spurs/Wood2.png");
            Wood3 = new Texture("core/assets/Spurs/Wood3.png");
            Woosh1 = new Texture("core/assets/Spurs/Woosh1.png");
            Woosh2 = new Texture("core/assets/Spurs/Woosh2.png");
            Woosh3 = new Texture("core/assets/Spurs/Woosh3.png");
            Woosh4 = new Texture("core/assets/Spurs/Woosh4.png");
            Woosh5 = new Texture("core/assets/Spurs/Woosh5.png");
            GameMusic = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Sound/Spurs.mp3"));
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
