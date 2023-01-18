package com.willian.imperialist.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import com.willian.imperialist.ImperialistCollector;

public class Lwjgl3Launcher {
	public static void main(String[] args) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Imperialist Collector");
		config.setWindowedMode(960, 540);
		config.setForegroundFPS(60);
		config.useVsync(true);
		// TODO: script to resize a favicon to every icon size and folder
		config.setWindowIcon("favicon128.png", "favicon64.png", "favicon32.png", "favicon16.png");
		new Lwjgl3Application(new ImperialistCollector(), config);
	}
}