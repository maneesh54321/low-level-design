package com.ms.turn;

import com.ms.Game;
import com.ms.player.Player;

public abstract class Turn {

	private boolean shouldContinue;

	private final Player player;

	private final Game game;

	public Turn(Player player, Game game) {
		this.player = player;
		this.game = game;
		shouldContinue = true;
	}

	public void execute(){
		while (shouldContinue) {
			play();
		}
	}

	abstract public void play();

	public Player getPlayer() {
		return player;
	}

	public Game getGame() {
		return game;
	}

	public void over(){
		shouldContinue = false;
	}
}
