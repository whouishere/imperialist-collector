package com.willian.imperialist;

import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.willian.imperialist.screen.MainMenu;

public class ImperialistCollector extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	public Settings settings;
	
	public Map<String, String> lang;

	private FreeTypeFontGenerator fontGenerator;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		settings = new Settings(); // TODO: check if it's possible to make pre-defined settings on device cache
		lang = Default.locale.get(settings.getLocale());
		
		// FIXME: the font isn't smooth. 
		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("ClearSans-Bold.ttf"));

		this.setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		font.dispose();
		batch.dispose();
		fontGenerator.dispose();
	}

	public BitmapFont getFont(FreeTypeFontParameter parameter) {
		return fontGenerator.generateFont(parameter);
	}

	public void onResize(int width, int height) {
		// calculate viewport on resize
		float aspectRatio = (float)width / (float)height;
		float scale;
		Vector2 crop = new Vector2(0f, 0f);

		if (aspectRatio > Default.screen.aspect_ratio) {
			scale = (float)height / (float)Default.screen.height;
			crop.x = (width - Default.screen.width * scale) / 2f;
		}
		else if (aspectRatio < Default.screen.aspect_ratio) {
			scale = (float)width / (float)Default.screen.width;
			crop.y = (height - Default.screen.height * scale) / 2f;
		}
		else {
			scale = (float)width / (float)Default.screen.width;
		}

		float w = (float)Default.screen.width * scale;
		float h = (float)Default.screen.height * scale;
		settings.setPhysicalViewport(new Rectangle(crop.x, crop.y, w, h));
	}
}
