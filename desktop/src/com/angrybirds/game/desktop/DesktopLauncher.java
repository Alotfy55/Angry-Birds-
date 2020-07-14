package com.angrybirds.game.desktop;


import com.angrybirds.game.Project_Entery;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height=720;
		config.width=1280;
		config.addIcon("core/assets/Icon.png", Files.FileType.Internal);
		new LwjglApplication(new Project_Entery(), config);
	}
}
