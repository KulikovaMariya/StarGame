package ru.geekbrains;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    private Texture img;
    private Vector2 v;
    private Vector2 position;
    private Vector2 buffer;
    private static final float speed = 0.001f;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        touch = new Vector2();
        v = new Vector2();
        position = new Vector2();
        buffer = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        buffer.set(touch);
        if (buffer.sub(position).len() > speed) {
            position.add(v);
        } else {
            position.set(touch);
            v.set(0, 0);
            touch.set(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
        }
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, position.x, position.y, 0.3f, 0.3f);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public void onTouchDown() {
        v.set(touch.cpy().sub(position).setLength(speed));
    }

    @Override
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

    @Override
    public boolean keyUp(int keycode) {
        if (keycode >= 19 && keycode <= 22 ) {
            v.set(0, 0);
        }
        return true;
    }
}
