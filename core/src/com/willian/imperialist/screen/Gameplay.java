package com.willian.imperialist.screen;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.willian.imperialist.Bucket;
import com.willian.imperialist.Default;
import com.willian.imperialist.Drop;
import com.willian.imperialist.ImperialistCollector;

public class Gameplay implements Screen {

    final ImperialistCollector game;

    private final OrthographicCamera camera;

    private final HashMap<String, Texture> textures;
    private final Bucket bucket;
    private final Drop drop;

    private int lives = 3;
    private int points = 0;
    private boolean lost = false;

    public Gameplay(final ImperialistCollector game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Default.screen.width, Default.screen.height);

        textures = new HashMap<String, Texture>();
        textures.put("background", new Texture("background.png"));
        textures.put("unclesam", new Texture("unclesam.png"));
        textures.put("money", new Texture("money.png"));
        textures.put("dim", new Texture("dim.png"));

        bucket = new Bucket();
        drop = new Drop();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.3f, 0.3f, 0.3f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
            game.batch.draw(textures.get("background"), 0, 0);
            game.batch.draw(textures.get("unclesam"), bucket.rect.x, bucket.rect.y - 16);
            bucket.draw(game.batch);
            drop.draw(game.batch);
            for(int i = 1; i <= lives; i++) {
				game.batch.draw(textures.get("money"), Default.screen.width - 18 * i, Default.screen.height - 18);
			}

            game.font.setColor(Color.FOREST);
            game.font.getData().setScale(0.5f);
            game.font.draw(game.batch, Integer.toString(points), 2, Default.screen.height - 2);

            // TODO: implement a high-score system
            if (lost) {
                game.batch.draw(textures.get("dim"), 0, 0);

                game.font.setColor(Color.RED);
                game.font.getData().setScale(1);
                game.font.draw(game.batch, game.lang.get("lost"), 0, Default.screen.height / 2 + game.font.getXHeight() / 2, Default.screen.width, Align.center, true);

                if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                    game.setScreen(new MainMenu(game));
                    dispose();
                }
            }
        game.batch.end();

        if (!lost) {
            update(delta);
            input(delta);
        }
    }

    public void update(float delta) {
        for (int rectIter = 0; rectIter < drop.rect.size(); rectIter++) {
            if (drop.rect.get(rectIter).y > 0) {
                // if drop reaches the bucket
                if (drop.rect.get(rectIter).overlaps(bucket.rect)) {
					points++;
					if (points % 5 == 0) {
						bucket.nextFrame();
					}
                    drop.resetDrop(drop.rect.get(rectIter));
                    drop.addDrop();
                }
                drop.rect.get(rectIter).y -= drop.velocity * delta;
            } else {
				lives--;
                drop.resetDrop(drop.rect.get(rectIter));
            }
        }

		if (lives < 1) {
			lost = true;
		}
    }

    public void input(float delta) {
        if (Gdx.input.isTouched() && (bucket.rect.x >= 0 || bucket.rect.x + bucket.width <= Default.screen.width)) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.rect.x = touchPos.x - bucket.width / 2;
        }

        final boolean isOnTurbo = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && bucket.rect.x >= 0) 
			bucket.rect.x -= (isOnTurbo ? (bucket.velocity * 2) : bucket.velocity) * delta;
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && bucket.rect.x + bucket.width <= Default.screen.width) 
			bucket.rect.x += (isOnTurbo ? (bucket.velocity * 2) : bucket.velocity) * delta;
		
		if (Gdx.input.isKeyPressed(Input.Keys.L))
			lost = true;
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
        bucket.dispose();
        drop.dispose();

        // most efficient way up to 100 elements. see: https://stackoverflow.com/a/35558955/13959383
        textures.forEach((key, texture) -> texture.dispose());
    }
}
