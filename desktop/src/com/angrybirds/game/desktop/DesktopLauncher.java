package com.angrybirds.game.desktop;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.angrybirds.game.game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		System.out.println("rerf");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height=720;
		config.width=1280;
		new LwjglApplication(new game(), config);

	}
}
