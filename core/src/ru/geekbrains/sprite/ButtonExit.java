package ru.geekbrains.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.base.ScaledTouchUpButton;
import ru.geekbrains.math.Rect;

public class ButtonExit extends ScaledTouchUpButton {

    public ButtonExit(TextureAtlas atlas) {
        super(atlas.findRegion("btExit"));
    }

    @Override
    public void resize(Rect worldBound) {
        setHeightProportion(0.20f);
        setBottom(worldBound.getBottom() + 0.04f);
        setRight(worldBound.getRight() - 0.04f);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
