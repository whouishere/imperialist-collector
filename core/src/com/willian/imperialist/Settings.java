package com.willian.imperialist;

import com.badlogic.gdx.math.Rectangle;

public class Settings {
    
    private String locale;
    private int volume_sfx;
    private int volume_music;
    private Rectangle physical_viewport;

    // default settings
    public Settings() {
        locale = "en";
        volume_sfx = 100;
        volume_music = 100;
        physical_viewport = new Rectangle();
    }

    public Settings(String locale, int volume_sfx, int volume_music) {
        this.locale = locale;
        setVolumeSfx(volume_sfx);
        setVolumeMusic(volume_music);
        physical_viewport = new Rectangle();
    }

    public String getLocale() {
        return locale;
    }

    public Rectangle getPhysicalViewport() {
        return physical_viewport;
    }

    public int getVolumeSfx() {
        return volume_sfx;
    }

    public int getVolumeMusic() {
        return volume_music;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setPhysicalViewport(Rectangle physical_viewport) {
        this.physical_viewport = physical_viewport;
    }

    public void setVolumeSfx(int volume_sfx) {
        if (volume_sfx >= 0 && volume_sfx <= 100)
            this.volume_sfx = volume_sfx;
        else if (volume_sfx < 0)
            this.volume_sfx = 0;
        else if (volume_sfx > 100)
            this.volume_sfx = 100;
    }

    public void setVolumeMusic(int volume_music) {
        if (volume_music >= 0 && volume_music <= 100)
            this.volume_music = volume_music;
        else if (volume_music < 0)
            this.volume_music = 0;
        else if (volume_music > 100)
            this.volume_music = 100;
    }
}
