package com.ms;

import com.ms.action.Action;
import com.ms.action.ActionHandler;
import com.ms.action.HitActionHandler;
import com.ms.action.StandActionHandler;
import com.ms.card.Shoe;
import com.ms.money.Bet;
import com.ms.money.Money;
import com.ms.money.Reward;
import com.ms.player.Dealer;
import com.ms.player.Gambler;
import com.ms.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {

	private final Map<Gambler, Bet> bets;

	private Set<Player> players;

	private final Dealer dealer;

	private final Map<Action, ActionHandler> actionHandlers;

	private final Shoe shoe;

	public Game() {
		this.bets = new HashMap<>();
		this.shoe = new Shoe();
		this.actionHandlers = new HashMap<>();
		dealer = new Dealer(this);

		actionHandlers.put(Action.HIT, new HitActionHandler(this));
		actionHandlers.put(Action.STAND, new StandActionHandler(this));
	}

	public void start() {
		System.out.println("Starting game..");
		Set<Gambler> gamblers = bets.keySet();
		players = gamblers.stream().map(gambler -> (Player) gambler).collect(Collectors.toSet());

		System.out.println("Dealing cards...");
		dealCards();

		// Start player turns
		players.forEach(Player::takeTurn);
		// Start Dealer turn

	}

	private void dealCards() {
		players.stream().peek(player -> player.addCards(List.of(shoe.takeCard(), shoe.takeCard())))
				.forEach(System.out::println);
	}

	public void end() {
		System.out.println("Game has finished!!");
	}

	public void declareWinner(Player player, Money money) {
		var gambler = (Gambler) player;
		gambler.win(new Reward(bets.get(gambler), money.getValue()));
		bets.remove(gambler);
	}

	public void declareLoser(Player player) {
		var gambler = (Gambler) player;
		gambler.lose(bets.get(gambler));
		bets.remove(gambler);
	}

	public void placeBet(Gambler gambler, Bet bet) {
		bets.put(gambler, bet);
	}

	public void play(Player player, Action action) {
		actionHandlers.get(action).handle(player);
	}
}
