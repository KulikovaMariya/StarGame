package ru.geekbrains;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    private Texture img;
    private Texture background;
    private Vector2 touch;
    private Vector2 v;
    private Vector2 position;
    private static final float speed = 1;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        background = new Texture("original.jpg");
        touch = new Vector2();
        v = new Vector2(); //вектор скорости
        position = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (!(Math.abs(touch.x - position.x) < 1 && Math.abs(touch.y - position.y) < 1)) {
            position.add(v);
        }
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(img, position.x, position.y);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
        background.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        updateSpeed();
        return false;
    }

    private void updateSpeed() {
        float x;
        float y;
        Vector2 touch2 = touch.cpy();
        Vector2 direction = touch2.sub(position);
        float length = direction.len();
        x = (direction.x * speed) / length;
        y = (direction.y * speed) / length;
        v.set(x, y);
    }
}
