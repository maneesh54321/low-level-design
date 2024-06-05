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
import com.ms.turn.Turn;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Game {

	public static final Scanner SCANNER = new Scanner(System.in);

	private final Map<Gambler, Bet> bets;

	private final Set<CasinoPlayer> players;

	private final Dealer dealer;

	private Table table;

	private final Map<Action, ActionHandler> actionHandlers;

	private final Shoe shoe;

	public Game() {
		this.bets = new HashMap<>();
		this.shoe = new Shoe();
		var cardShuffler = new CardShuffler();
		shoe.setDeck(cardShuffler.shuffle(shoe.getDeck()));
		this.actionHandlers = new HashMap<>();
		dealer = new Dealer("Dealer", this);
		players = new HashSet<>();

		actionHandlers.put(Action.HIT, new HitActionHandler(this));
		actionHandlers.put(Action.STAND, new StandActionHandler());
	}

	public void start() {
		System.out.println("Starting game..");

		table = new Table(players, dealer);

		table.dealer().dealHands();

		printAllPlayersCards();

		// Start player turns
		table.players().forEach(casinoPlayer -> {
			Turn turn = new PlayerTurn(casinoPlayer, this);
			casinoPlayer.takeTurn(turn);
		});

		// Start Dealer turn
		var dealerTurn = new DealerTurn(dealer, this);
		table.dealer().takeTurn(dealerTurn);
		end();
	}

	private void printAllPlayersCards() {
		System.out.println("\n##### Printing whole table!!");
		System.out.println(dealer);
		players.forEach(System.out::println);
		System.out.println("#####\n");
	}

	private void end() {
		System.out.println("Game has finished!!");
		SCANNER.close();
	}

	public void declareWinner(Player player, Money money) {
		System.out.printf("Player %s has won!!\n", player);
		var gambler = (Gambler) player;
		gambler.win(new Reward(bets.get(gambler), money.getValue()));
		table.removePlayer((CasinoPlayer) player);
	}

	public void declareLoser(Player player) {
		System.out.printf("Player %s lost the game!!\n", player);
		var gambler = (Gambler) player;
		gambler.lose(bets.get(gambler));
		table.removePlayer((CasinoPlayer) player);
	}

	public void declareDraw(Player player) {
		players.remove((CasinoPlayer) player);
	}

	public Table getTable(){
		return table;
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
}
