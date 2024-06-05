package com.ms.player;

import com.ms.money.Bet;
import com.ms.money.Reward;

public interface Gambler {
	void placeBet(Bet bet);
	void win(Reward reward);
	void lose(Bet bet);
	void draw(Bet bet);
}
