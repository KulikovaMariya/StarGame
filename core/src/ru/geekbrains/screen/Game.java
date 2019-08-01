package ru.geekbrains.screen;

public class Game extends com.badlogic.gdx.Game {

	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}
}
