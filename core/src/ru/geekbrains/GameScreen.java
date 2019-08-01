package ru.geekbrains;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.EnemyPool;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.Bullet;
import ru.geekbrains.sprite.EnemyShip;
import ru.geekbrains.sprite.MainShip;
import ru.geekbrains.sprite.Star;
import ru.geekbrains.utils.EnemyGenerator;

public class GameScreen extends BaseScreen {

    private enum State {PLAYING, PAUSE, GAME_OVER}

    private State state;
    private State stateBuffer;
    private TextureAtlas atlas;
    private Texture bg;
    private Background background;
    private Star[] starArray;
    private MainShip mainShip;
    private EnemyPool enemyPool;
    private EnemyGenerator enemyGenerator;
    private BulletPool bulletPool;
    private static final int STAR_COUNT = 64;
    private Music music;

    @Override
    public void show() {
        super.show();
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setVolume(1);
        music.setLooping(true);
        music.play();
        atlas = new TextureAtlas("mainAtlas.tpack");
        bg = new Texture("bg.png");
        background = new Background(new TextureRegion(bg));
        starArray = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            starArray[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        enemyPool = new EnemyPool(bulletPool, worldBounds);
        enemyGenerator = new EnemyGenerator(enemyPool, atlas, worldBounds);
        mainShip = new MainShip(atlas, bulletPool);
        state = State.PLAYING;
        stateBuffer = State.PLAYING;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions(enemyPool, bulletPool);
        freeAllDestroyedActiveSprites();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : starArray) {
            star.resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void pause() {
        super.pause();
        stateBuffer = state;
        state = State.PAUSE;
    }

    @Override
    public void resume() {
        super.resume();
        state = stateBuffer;
    }

    @Override
    public void dispose() {
        super.dispose();
        atlas.dispose();
        bg.dispose();
        bulletPool.dispose();
        enemyPool.dispose();
        music.dispose();
        mainShip.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state == State.PLAYING) {
            mainShip.keyDown(keycode);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (state == State.PLAYING) {
            mainShip.keyUp(keycode);
        }
        return true;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (state == State.PLAYING) {
            mainShip.touchDown(touch, pointer, button);
        }
        return true;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (state == State.PLAYING) {
            mainShip.touchUp(touch, pointer, button);
        }
        return true;
    }

    private void update(float delta) {
        for (Star star : starArray) {
            star.update(delta);
        }
        if (state == State.PLAYING) {
            bulletPool.updateActiveSprites(delta);
            enemyPool.updateActiveSprites(delta);
            mainShip.update(delta);
            enemyGenerator.generate(delta);
        }
    }

    private void checkCollisions(EnemyPool enemyPool, BulletPool bulletPool) {
        if (state != State.PLAYING) {
            return;
        }
        for (EnemyShip enemy : enemyPool.getActiveObjects()) {
            float minDistance = enemy.getHalfWidth() + mainShip.getHalfWidth();
            if ((enemy.pos.dst(mainShip.pos) < minDistance) && !enemy.isDestroyed()) {
                enemy.setDestroyed();
                mainShip.setDestroyed();
                state = State.GAME_OVER;
            }
        }
        for (Bullet bullet : bulletPool.getActiveObjects()) {
            if (!bullet.getOwner().equals(mainShip)) {
                if (bullet.isOutside(mainShip) && !mainShip.isDestroyed()) {
                    mainShip.damage(bullet.getDamage());
                    bullet.setDestroyed();
                    if (mainShip.getHp() <= 0) {
                        mainShip.setDestroyed();
                        state = State.GAME_OVER;
                    }
                }
            } else {
                for (EnemyShip enemy : enemyPool.getActiveObjects()) {
                    if (!bullet.isOutside(enemy) && !enemy.isDestroyed()) {
                        enemy.damage(bullet.getDamage());
                        bullet.setDestroyed();
                        if (enemy.getHp() <= 0) {
                            enemy.setDestroyed();
                        }
                    }
                }
            }
        }
    }


    private void freeAllDestroyedActiveSprites() {
        bulletPool.freeAllDestroyedActiveSprites();
        enemyPool.freeAllDestroyedActiveSprites();
    }

    private void draw() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star : starArray) {
            star.draw(batch);
        }
        if (state == State.PLAYING) {
            bulletPool.draw(batch);
            mainShip.draw(batch);
            enemyPool.draw(batch);
        }
        batch.end();
    }
}
