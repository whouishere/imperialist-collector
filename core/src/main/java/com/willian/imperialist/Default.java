package com.willian.imperialist;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Default {
	
	public static class screen {
		public final static int width = 160;
		public final static int height = 90;
		public final static float aspect_ratio = (float)width / (float)height;
	}
	
	// TODO: use XML instead
	// this is a clusterfuck, but it *kinda* makes sense when I'm setting the language
	public final static Map<String, Map<String, String>> locale;
	static {
		Map<String, Map<String, String>> staticLocale = new HashMap<String, Map<String, String>>();
		
		Map<String, String> enLocale = new HashMap<String, String>();
		enLocale.put("menu_title", "Imperialist Collector");
		enLocale.put("menu_play", "PLAY");
		enLocale.put("lost", "YOU LOST!");
		
		Map<String, String> ptbrLocale = new HashMap<String, String>();
		ptbrLocale.put("menu_title", "Imperialist Collector");
		ptbrLocale.put("menu_play", "JOGAR");
		ptbrLocale.put("lost", "VOCÊ PERDEU!");
		
		staticLocale.put("en", enLocale);
		staticLocale.put("pt_br", ptbrLocale);
		
		locale = Collections.unmodifiableMap(staticLocale);
	}

}
