package com.learning.game;

import com.learning.board.Board;
import com.learning.board.Move;
import com.learning.player.Player;
import com.learning.units.King;
import com.learning.units.Piece;
import java.util.Optional;

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
    while (true) {
      if (playerWhite.play().ifPresent(piece -> isGameOver(piece))) break;
      if (isGameOver(playerBlack.play())) break;
    }
    System.out.println("Game is over!!!");
  }

  private boolean isGameOver(Piece killedPiece) {
    return return killedPiece instanceof King;
  }

  public void execute(Move move) {}

  private boolean isPlayersPiece(Player player, Piece piece) {
    return player.color() == piece.getColor();
  }
}
