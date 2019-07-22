package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class Ship extends Sprite {

    private Vector2 v;
    private static final float SPEED = 0.02f;
    private Vector2 buffer;
    private Vector2 endPos;
    private Rect worldBounds;

    public Ship(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"));
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(0.20f);
        setBottom(worldBounds.getBottom() + 0.005f);
    }

    public void show() {
        v = new Vector2();
        buffer = new Vector2();
        endPos = new Vector2();
    }

    @Override
    public void update(float delta) {
        buffer.set(endPos);
        if (buffer.sub(pos).len() > SPEED) {
            pos.add(v);
        } else {
            pos.set(endPos);
            v.set(0, 0);
            endPos.set(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
        }
        checkBounds();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        endPos.set(touch);
        if (touch.x - pos.x > 0) {
            v.set(SPEED, 0);
        } else {
            v.set(-SPEED, 0);
        }
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        v.set(0, 0);
        if (keycode == 19) {
            v.set(0, SPEED);
        }
        if (keycode == 20) {
            v.set(0, -SPEED);
        }
        if (keycode == 21) {
            v.set(-SPEED, 0);
        }
        if (keycode == 22) {
            v.set(SPEED, 0);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode >= 19 && keycode <= 22 ) {
            v.set(0, 0);
        }
        return true;
    }

    private void checkBounds() {
        if (getRight() > worldBounds.getRight() && v.x > 0) {
            v.set(0, 0);
            setRight(worldBounds.getRight());
        }
        if (getLeft() < worldBounds.getLeft() && v.x < 0) {
            v.set(0, 0);
            setLeft(worldBounds.getLeft());
        }
    }
}
