package com.willian.imperialist.screen;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
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
    private int pointGoal = 2;
    private boolean lost = false;
    private boolean wasTouching;

    private final BitmapFont lostFont;
    private final BitmapFont pointsFont;

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

        // font styling
        final FreeTypeFontParameter lostFontParameter = new FreeTypeFontParameter();
        lostFontParameter.size = 15;
        lostFontParameter.color = Color.RED;
        lostFont = game.getFont(lostFontParameter);

        final FreeTypeFontParameter pointsFontParameter = new FreeTypeFontParameter();
        pointsFontParameter.size = 10;
        pointsFontParameter.color = Color.FOREST;
        pointsFont = game.getFont(pointsFontParameter);
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

            pointsFont.draw(game.batch, Integer.toString(points), 2, Default.screen.height - 2);

            // TODO: implement a high-score system
            if (lost) {
                // check if before having lost the player was still touching
                // so it can wait for the player to stop touching
                if (wasTouching) {
                    wasTouching = Gdx.input.isTouched();
                }

                game.batch.draw(textures.get("dim"), 0, 0);

                lostFont.draw(game.batch,
                              game.lang.get("lost"),
                              0, Default.screen.height / 2 + lostFont.getXHeight() / 2,
                              Default.screen.width, Align.center, true);

                if ((!wasTouching && Gdx.input.isTouched()) 
                    || Gdx.input.isKeyJustPressed(Input.Keys.SPACE) 
                    || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                
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
        // check drops collision
        for(int i = 0; i < drop.rect.size(); i++) {
            final Rectangle rect = drop.rect.get(i);

            if (rect.y > 0) {
                // if drop reaches the bucket
                if (rect.overlaps(bucket.rect)) {
                    addPoint();
                    drop.resetDrop(rect);
                }

                rect.y -= drop.velocity * delta;
            } else { // if touched the ground
                lives--;
                drop.resetDrop(rect);
            }
        }

		if (lives < 1) {
			lost = true;
		}

        wasTouching = Gdx.input.isTouched();
    }

    public void input(float delta) {
        if (Gdx.input.isTouched() && (bucket.rect.x >= 0 || bucket.rect.x + bucket.width <= Default.screen.width)) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.rect.x = touchPos.x - bucket.width / 2;
        }

        final boolean isOnTurbo = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
        final float velocity = (isOnTurbo ? (bucket.velocity * 2) : bucket.velocity) * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && bucket.rect.x >= 0) 
			bucket.rect.x -= velocity;
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && bucket.rect.x + bucket.width <= Default.screen.width) 
			bucket.rect.x += velocity;
		
		if (Gdx.input.isKeyPressed(Input.Keys.L))
			lost = true;
    }

    private void addPoint() {
        points++;

        // advance bucket fill for each 5 points
        if (points % 5 == 0) {
            bucket.nextFrame();
        }

        // increasingly add more drop
        if (points == pointGoal) {
            drop.addDrop();
            pointGoal = points * 2;
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
        bucket.dispose();
        drop.dispose();
        lostFont.dispose();
        pointsFont.dispose();

        textures.forEach((key, texture) -> texture.dispose());
    }
}
