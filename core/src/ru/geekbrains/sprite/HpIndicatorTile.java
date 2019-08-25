package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class HpIndicatorTile extends Sprite {

    public HpIndicatorTile(TextureRegion textureRegion) {
        super(textureRegion);
    }

    @Override
    public void resize(Rect worldBound) {
        super.resize(worldBound);
        setHeightProportion(0.05f);
        setTop(worldBound.getTop() - 0.005f);
    }
}
