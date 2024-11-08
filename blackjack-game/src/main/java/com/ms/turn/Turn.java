package com.ms.turn;

import com.ms.Game;
import com.ms.player.Player;

public abstract class Turn<T extends Player> {

	private boolean shouldContinue;

	private final T player;

	private final Game game;

	protected Turn(T player, Game game) {
		this.player = player;
		this.game = game;
		shouldContinue = true;
	}

	public void execute(){
		while (shouldContinue) {
			play();
		}
	}

	public abstract void play();

	public T getPlayer() {
		return player;
	}

	public Game getGame() {
		return game;
	}

	public void over(){
		shouldContinue = false;
	}
}
