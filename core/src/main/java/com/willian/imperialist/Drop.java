package com.willian.imperialist;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Drop {
    // drop structure
    public ArrayList<Rectangle> rect;
    private Texture texture;

    // values
    public final int velocity = 25;
    private final int dropsLimit = 5;
    private int drops = 0;

    // utility objects (the ones I don't want to create every frame)
    private final Random random = new Random();

    public Drop() {
        rect = new ArrayList<Rectangle>();
        texture = new Texture("drop.png");

        addDrop();
    }
    
    public Texture getTexture() {
        return texture;
    }

    // adds one more drop to the game
    public void addDrop() {
        if (drops < 0 ) {
            drops = 0;
        }
        else if (drops > rect.size()) {
            drops = rect.size();
        }

        if (drops > dropsLimit) {
            return;
        }
        
        rect.add(new Rectangle());
        resetDrop(rect.get(rect.size() - 1));
        drops++;
    }

    // resets specified drop to the top of the screen, in a random position
    public void resetDrop(Rectangle dropRect) {
        dropRect.y = Default.screen.height + random.nextInt(Default.screen.height) + 1;
        dropRect.x = random.nextInt(Default.screen.width) - (int)dropRect.width;
    }

    // only call this method when a draw batch has begun, obviously
    public void draw(SpriteBatch batch) {
        rect.forEach(iRect -> batch.draw(getTexture(), iRect.x, iRect.y));
    }
    
    public void dispose() {
        texture.dispose();
    }
}
