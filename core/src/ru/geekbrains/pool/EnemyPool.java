package ru.geekbrains.pool;

import ru.geekbrains.base.SpritesPool;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.EnemyShip;
import ru.geekbrains.sprite.MainShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private BulletPool bulletPool;
    private Rect worldBounds;
    private ExplosionPool explosionPool;
    private MainShip mainShip;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds, ExplosionPool explosionPool, MainShip mainShip) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.explosionPool = explosionPool;
        this.mainShip = mainShip;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool, explosionPool, worldBounds, mainShip);
    }
}
