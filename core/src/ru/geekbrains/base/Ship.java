package ru.geekbrains.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.sprite.Bullet;

public abstract class Ship extends Sprite {
    protected TextureRegion bulletRegion;
    protected BulletPool bulletPool;
    protected Rect worldBounds;
    protected Vector2 v0;
    protected Vector2 v;
    protected Vector2 bulletV;
    protected float reloadInterval;
    protected float reloadTimer;
    protected Sound shootSound;
    protected int damage;
    protected int hp;
    protected float bulletHeight;

    public Ship() {
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void resize(Rect worldBound) {
        this.worldBounds = worldBound;
    }

    @Override
    public void dispose() {
        shootSound.dispose();
    }

    public void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, damage);
        shootSound.play();
    }

    public int getHp() {
        return hp;
    }

    public void subDamage(int damage) {
        hp -= damage;
    }
}
