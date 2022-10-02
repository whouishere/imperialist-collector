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
		config.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
		new Lwjgl3Application(new ImperialistCollector(), config);
	}
}