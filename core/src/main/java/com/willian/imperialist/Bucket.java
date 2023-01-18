package com.willian.imperialist;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bucket {
    // bucket's structure objects
    public Rectangle rect;
    private Texture[] textures;

    // bucket's final properties
    public final int width = 16;
    public final int height = 16;
    private final float xStart = Default.screen.width / 2 - width / 2; // horizontally centered
    private final float yStart = 16;
    private final int frames = 10;

    // values
    private int animFrame = 0; // current animation frame
    public int velocity = 150;

    public Bucket() {
        rect = new Rectangle();
        rect.x = xStart;
        rect.y = yStart;
        rect.width = width;
        rect.height = height;

        textures = new Texture[frames];
		for (int frame = 0; frame < frames; frame++) {
			textures[frame] = new Texture("bucket" + Integer.toString(frame + 1) + ".png");
		}
    }

    // jumps to the next animation frame
    public void nextFrame() {
        animFrame++;
    }

    // gets current bucket animation frame texture
    public Texture getTextureFrame() {
        // makes sure the array does not go OutOfBounds
        if (animFrame < 0) {
            animFrame = 0;
        }
        else if (animFrame > frames - 1) {
            animFrame = frames - 1;
        }

        return textures[animFrame];
    }

    // only call this method when a draw batch has begun, obviously
    public void draw(SpriteBatch batch) {
        batch.draw(getTextureFrame(), rect.x, rect.y);
    }

    public void dispose() {
        for (int frame = 0; frame < textures.length; frame++) {
			textures[frame].dispose();
		}
    }
}
