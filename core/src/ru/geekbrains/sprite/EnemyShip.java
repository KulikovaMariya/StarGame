package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class EnemyShip extends Sprite {

    private Vector2 v = new Vector2(-0.5f, 0);

    private Rect worldBounds;


    public EnemyShip() {
        this.regions = new TextureRegion[1];
    }

    public void set(TextureRegion[] region, Rect worldBounds) {
        this.regions = region;
        this.worldBounds = worldBounds;
        setHeightProportion(0.15f);
        setTop(worldBounds.getTop() - 0.05f);
        setRight(worldBounds.getRight());
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroyed();
            System.out.println("enemy ship is destroyed");
        }
    }
}
