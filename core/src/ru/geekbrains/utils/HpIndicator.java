package ru.geekbrains.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.HpIndicatorTile;
import ru.geekbrains.sprite.MainShip;

public class HpIndicator {

    private MainShip mainShip;
    private HpIndicatorTile[] hpIndicatorTiles;
    private int tilesCount;
    private float damagePerTile;

    public HpIndicator(MainShip mainShip, int tilesCount) {
        this.mainShip = mainShip;
        this.tilesCount = tilesCount;
        damagePerTile = (float) mainShip.getHp() / tilesCount;
        TextureRegion textureRegionTile = new TextureRegion(new Texture("health-01.png"));
        hpIndicatorTiles = new HpIndicatorTile[tilesCount];
        for (int i = 0; i < tilesCount; i++) {
            hpIndicatorTiles[i] = new HpIndicatorTile(textureRegionTile);
        }
    }

    public void resize(Rect worldBounds) {
        for (int i = 0; i < tilesCount; i++) {
            hpIndicatorTiles[i].resize(worldBounds);
            hpIndicatorTiles[i].setLeft(hpIndicatorTiles[i].getWidth() * (i - (tilesCount / 2f)) + 0.005f * (i - (tilesCount - 1) / 2f));
        }
    }

    public void draw(SpriteBatch batch) {
        int tilesToDraw = (int) (mainShip.getHp() / damagePerTile);
        for (int i = 0; i < tilesToDraw; i++) {
            hpIndicatorTiles[i].draw(batch);
        }
    }
}
