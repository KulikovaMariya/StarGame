package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class GameOver extends Sprite {

    private Rect worldBounds;

    public GameOver(TextureRegion region, Rect worldBounds) {
        super(region);
        this.worldBounds = worldBounds;
        pos.set(worldBounds.getHalfWidth(), worldBounds.getHalfHeight());
        setHeightProportion(0.07f);
    }
}
