package ru.geekbrains.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class ScaledTouchUpButton extends Sprite {

    private static final float PRESSED_SCALE = 0.9f;
    private boolean isPressed;
    private int pointer;

    public ScaledTouchUpButton(TextureRegion region) {
        super(region);
        isPressed = false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (isPressed || !isMe(touch)) {
            return false;
        }
        this.pointer = pointer;
        scale = PRESSED_SCALE;
        isPressed = true;
        return true;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (this.pointer != pointer || !isPressed) {
            return false;
        }
        if (isMe(touch)) {
            action();
        }
        isPressed = false;
        scale = 1f;
        return true;
    }

    public abstract void action();
}
