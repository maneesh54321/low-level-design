package com.ms.action;

import com.ms.Game;
import com.ms.player.Player;

public class StandActionHandler implements ActionHandler {

	private final Game game;

	public StandActionHandler(Game game) {
		this.game = game;
	}

	@Override
	public void handle(Player player) {

	}
}
