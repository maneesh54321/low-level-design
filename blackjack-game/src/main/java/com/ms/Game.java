package com.ms;

import com.ms.action.Action;
import com.ms.action.ActionHandler;
import com.ms.action.HitActionHandler;
import com.ms.action.StandActionHandler;
import com.ms.card.CardShuffler;
import com.ms.card.Shoe;
import com.ms.money.Bet;
import com.ms.money.Money;
import com.ms.money.Reward;
import com.ms.player.CasinoPlayer;
import com.ms.player.Dealer;
import com.ms.player.Gambler;
import com.ms.player.Player;
import com.ms.player.Table;
import com.ms.turn.DealerTurn;
import com.ms.turn.PlayerTurn;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Game {

	public static final Scanner SCANNER = new Scanner(System.in);

	private final Map<Gambler, Bet> bets;

	private final Table table;

	private final Map<Action, ActionHandler> actionHandlers;

	private final Shoe shoe;

	public Game() {
		this.bets = new HashMap<>();
		this.shoe = new Shoe();
		var cardShuffler = new CardShuffler();
		this.shoe.setDeck(cardShuffler.shuffle(shoe.getDeck()));

		var dealer = new Dealer("Dealer", this);
		var players = new HashSet<CasinoPlayer>();
		this.table = new Table(players, dealer);

		this.actionHandlers = new EnumMap<>(Action.class);
		actionHandlers.put(Action.HIT, new HitActionHandler(this));
		actionHandlers.put(Action.STAND, new StandActionHandler());
	}

	public void start() {
		System.out.println("Starting game..");

		Dealer dealer = table.dealer();
		dealer.dealHands();

		table.printAllPlayersCards();

		// Start player turns
		table.players().forEach(casinoPlayer -> {
			var turn = new PlayerTurn(casinoPlayer, this);
			casinoPlayer.takeTurn(turn);
		});

		// Start Dealer turn
		var dealerTurn = new DealerTurn(dealer, this);
		dealer.takeTurn(dealerTurn);
		end();
	}

	private void end() {
		System.out.println("Game has finished!!");
		SCANNER.close();
	}

	public void declareWinner(CasinoPlayer player, Money money) {
		System.out.printf("Player %s has won!!%n", player);
		player.win(new Reward(bets.get(player), money.getValue()));
		table.removePlayer(player);
	}

	public void declareLoser(CasinoPlayer player) {
		System.out.printf("Player %s lost the game!!%n", player);
		player.lose(bets.get(player));
		table.removePlayer(player);
	}

	public void declareDraw(CasinoPlayer player) {
		player.draw(bets.get(player));
		table.removePlayer(player);
	}

	public Table getTable(){
		return table;
	}

	public Shoe getShoe() {
		return shoe;
	}

	public void placeBet(Gambler gambler, Bet bet) {
		bets.put(gambler, bet);
		table.addPlayer((CasinoPlayer) gambler);
	}

	public Bet getBet(Gambler gambler){
		return bets.get(gambler);
	}

	public void play(Player player, Action action) {
		actionHandlers.get(action).handle(player);
	}
}
