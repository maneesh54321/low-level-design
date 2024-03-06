package com.learning.game;

import com.learning.board.Board;
import com.learning.board.Move;
import com.learning.exception.GameOverException;
import com.learning.player.Player;
import com.learning.units.Piece;

public class Controller {

	private Board board;
	private Player playerWhite;
	private Player playerBlack;

	public Controller(Board board, Player playerWhite, Player playerBlack) {
		this.board = board;
		this.playerWhite = playerWhite;
		this.playerBlack = playerBlack;
	}

	public void startGame() {
		System.out.println("Game has been started!!!");
		try {
			while (true) {
				boolean playerMoveOver = false;
				while (!playerMoveOver) {
					Move move = playerWhite.play();
					playerMoveOver = execute(playerWhite, move.cells().getFirst().getPiece(), move);
				}
				playerMoveOver = false;
				while (!playerMoveOver) {
					Move move = playerBlack.play();
					playerMoveOver = execute(playerBlack, move.cells().getFirst().getPiece(), move);
				}
			}
		} catch (GameOverException e) {
			System.out.println("Game is over!!");
		}
	}

	private boolean execute(Player player, Piece piece, Move move) throws GameOverException {
		if (isPlayersPiece(player, piece)) {
			return board.execute(piece, move);
		}
		return false;
	}

	private boolean isPlayersPiece(Player player, Piece piece) {
		return player.color() == piece.getColor();
	}
}
