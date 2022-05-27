package com.willian.imperialist;

import java.util.Map;

public class Default {

    public static class screen {
        public final static int width = 160;
        public final static int height = 90;
        public final static float aspect_ratio = (float)width / (float)height;
    }

    // this is a clusterfuck, but it *kinda* makes sense when I'm setting the language
    public final static Map<String, Map<String, String>> locale = Map.of(
        "en", Map.of(
            "menu_title",   "Imperialist Collector",
            "menu_play",    "PLAY", 
            "menu_options", "OPTIONS", 
            "menu_quit",    "QUIT", 
            "lost",         "YOU LOST!"
        ),
        "pt_br", Map.of(
            "menu_title",   "Imperialist Collector",
            "menu_play",    "JOGAR", 
            "menu_options", "OPÇÕES", 
            "menu_quit",    "SAIR", 
            "lost",         "VOCÊ PERDEU!"
        )
    );
}
