package com.willian.imperialist.screen;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

import com.willian.imperialist.Default;
import com.willian.imperialist.ImperialistCollector;

public class MainMenu implements Screen {
    
    final ImperialistCollector game;

    private OrthographicCamera camera;
    private HashMap<String, Texture> textures;

    private boolean wasTouching;

    final private BitmapFont titleFont;
    final private BitmapFont buttonFont;

    public MainMenu(final ImperialistCollector game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Default.screen.width, Default.screen.height);

        textures = new HashMap<String, Texture>();
        wasTouching = Gdx.input.isTouched();

        // font styling
        final FreeTypeFontParameter titleFontParameter = new FreeTypeFontParameter();
        titleFontParameter.size = 15;
        titleFontParameter.color = Color.BLACK;
        titleFont = game.getFont(titleFontParameter);

        final FreeTypeFontParameter buttonFontParameter = new FreeTypeFontParameter();
        buttonFontParameter.size = 10;
        buttonFontParameter.color = Color.FOREST;
        buttonFont = game.getFont(buttonFontParameter);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.3f, 1, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
            titleFont.draw(game.batch,
                       game.lang.get("menu_title"),
                       0, getFontCenterY(titleFont) + 25,
                       Default.screen.width, Align.center, true);

            buttonFont.draw(game.batch,
                      "$ " + game.lang.get("menu_play") + " $",
                      0, getFontCenterY(buttonFont) - 25,
                      Default.screen.width, Align.center, true);
        game.batch.end();

        input();
    }

    public void input() {
        if (wasTouching) {
            wasTouching = Gdx.input.isTouched();
        }

        if ((!wasTouching && Gdx.input.isTouched()) 
            || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
        
            game.setScreen(new Gameplay(game));
            dispose();
        }
    }

    @Override
    public void resize(int w, int h) {
        // inherited method
    }
    
    @Override
    public void show() {
        // inherited method
    }
    
    @Override
    public void hide() {
        // inherited method
    }

    @Override
    public void pause() {
        // inherited method
    }

    @Override
    public void resume() {
        // inherited method
    }

    @SuppressWarnings("NewApi")
    @Override
    public void dispose() {
        titleFont.dispose();
        buttonFont.dispose();

        textures.forEach((key, texture) -> texture.dispose());
    }

    // just so I don't need to type this every single time
    private float getFontCenterY(BitmapFont font) {
        return Default.screen.height / 2 + font.getXHeight() / 2;
    }
}
