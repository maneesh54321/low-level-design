package com.ms.player;

import java.util.HashSet;
import java.util.Set;

public record Table(Set<CasinoPlayer> players, Dealer dealer) {

	@Override
	public Set<CasinoPlayer> players() {
		return new HashSet<>(players);
	}

	public void removePlayer(CasinoPlayer casinoPlayer) {
		this.players.remove(casinoPlayer);
	}
}
