package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.Game;
import ru.geekbrains.GameScreen;
import ru.geekbrains.base.ScaledTouchUpButton;
import ru.geekbrains.math.Rect;

public class ButtonPlay extends ScaledTouchUpButton {

    private Game game;

    public ButtonPlay(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btPlay"));
        this.game = game;
    }

    @Override
    public void resize(Rect worldBound) {
        setHeightProportion(0.25f);
        setBottom(worldBound.getBottom() + 0.04f);
        setLeft(worldBound.getLeft() + 0.04f);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen());
    }
}
