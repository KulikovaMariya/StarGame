package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;

public class Logo extends Sprite {

    private Vector2 v;
    private Vector2 buffer;
    private Vector2 endPos;
    private static final float speed = 0.001f;

    public Logo(TextureRegion region) {
        super(region);
        endPos = new Vector2();
        v = new Vector2();
        buffer = new Vector2();
    }

    @Override
    public void update(float delta) {
        buffer.set(endPos);
        if (buffer.sub(pos).len() > speed) {
            pos.add(v);
        } else {
            pos.set(endPos);
            v.set(0, 0);
            endPos.set(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        endPos.set(touch);
        v.set(touch.sub(pos).setLength(speed));
        return true;
    }

    public boolean keyDown(int keycode) {
        v.set(0, 0);
        if (keycode == 19) {
            v.set(0, speed);
        }
        if (keycode == 20) {
            v.set(0, -speed);
        }
        if (keycode == 21) {
            v.set(-speed, 0);
        }
        if (keycode == 22) {
            v.set(speed, 0);
        }
        return true;
    }

    public boolean keyUp(int keycode) {
        if (keycode >= 19 && keycode <= 22 ) {
            v.set(0, 0);
        }
        return true;
    }
}
