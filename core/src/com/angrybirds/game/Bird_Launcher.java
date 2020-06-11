package com.angrybirds.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bird_Launcher {

    public Vector2 position;
    public float width;
    public float height;
    Texture part1;
    Texture part2;
    public Sprite body;
    public Sprite arm;

    public Bird_Launcher(int x, int y, float width, float height)
    {
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.part1 = new Texture("Launcher_body.png");
        this.part2 = new Texture("Launcher_arm.png");
        this.body = new Sprite(part1);
        this.arm = new Sprite(part2);
    }
}
