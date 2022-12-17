package com.mygdx.fastfood;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.fastfood.FastFood;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class JogoStarter {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Fast Food");
		config.setWindowedMode(800, 600);
		config.setResizable(false);
		new Lwjgl3Application(new FastFood(), config);
	}

}

