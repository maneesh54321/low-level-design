package com.ms;

import com.ms.action.Action;
import com.ms.action.ActionHandler;
import com.ms.action.HitActionHandler;
import com.ms.action.StandActionHandler;
import com.ms.card.Shoe;
import com.ms.money.Bet;
import com.ms.money.Money;
import com.ms.money.Reward;
import com.ms.player.CasinoPlayer;
import com.ms.player.Dealer;
import com.ms.player.Gambler;
import com.ms.player.Player;
import com.ms.turn.DealerTurn;
import com.ms.turn.PlayerTurn;
import com.ms.turn.Turn;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Game {

	private final Map<Gambler, Bet> bets;

	private final Set<CasinoPlayer> players;

	private final Dealer dealer;

	private final Map<Action, ActionHandler> actionHandlers;

	private final Shoe shoe;

	public Game() {
		this.bets = new HashMap<>();
		this.shoe = new Shoe();
		this.actionHandlers = new HashMap<>();
		dealer = new Dealer("Dealer", this);
		players = new HashSet<>();

		actionHandlers.put(Action.HIT, new HitActionHandler(this));
		actionHandlers.put(Action.STAND, new StandActionHandler());
	}

	public void start() {
		System.out.println("Starting game..");

		dealer.dealHands();

		// Start player turns
		players.forEach(player -> {
			Turn turn = new PlayerTurn(player, this);
			player.takeTurn(turn);
		});

		// Start Dealer turn
		var dealerTurn = new DealerTurn(dealer, this);
		dealer.takeTurn(dealerTurn);
	}

	public void end() {
		System.out.println("Game has finished!!");
	}

	public void declareWinner(Player player, Money money) {
		System.out.println(player + " has won!!");
		var gambler = (Gambler) player;
		gambler.win(new Reward(bets.get(gambler), money.getValue()));
		players.remove(player);
	}

	public void declareLoser(Player player) {
		System.out.println("Player lost the game!!");
		var gambler = (Gambler) player;
		gambler.lose(bets.get(gambler));
		players.remove(player);
	}

	public Set<CasinoPlayer> getPlayers(){
		return players;
	}

	public Shoe getShoe() {
		return shoe;
	}

	public void placeBet(Gambler gambler, Bet bet) {
		bets.put(gambler, bet);
		players.add((CasinoPlayer) gambler);
	}

	public Bet getBet(Gambler gambler){
		return bets.get(gambler);
	}

	public void play(Player player, Action action) {
		actionHandlers.get(action).handle(player);
	}

	public void declareDraw(Player player) {
		players.remove(player);
	}
}
