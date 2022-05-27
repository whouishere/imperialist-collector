package com.willian.imperialist;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Imperialist Collector");
		config.setWindowedMode(960, 540);
		config.setForegroundFPS(60);
		config.useVsync(true);
		new Lwjgl3Application(new ImperialistCollector(), config);
	}
}
