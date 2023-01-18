package com.willian.imperialist.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

import com.willian.imperialist.ImperialistCollector;

public class GwtLauncher extends GwtApplication {
		@Override
		public GwtApplicationConfiguration getConfig() {
			GwtApplicationConfiguration config = new GwtApplicationConfiguration(true);
			config.padVertical = 0;
			config.padHorizontal = 0;
			config.useAccelerometer = false;
			return config;
		}

		@Override
		public ApplicationListener createApplicationListener() {
			return new ImperialistCollector();
		}
}
