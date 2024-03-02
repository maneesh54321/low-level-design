package com.learning.game;

import com.learning.board.Board;
import com.learning.units.Obstacle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Controller {
  private static final Predicate<List<Player>> gameOver = players -> players.size() <= 1;
  private final List<Player> players;
  private final Board board;

  public Controller(List<Player> players, Board board) {
    this.players = new ArrayList<>();
    this.players.addAll(players);
    this.board = board;
  }

  public void start() {
    System.out.println("Starting Game!!!");
    while (gameOver.negate().test(players)) {
      for (int i = 0; i < players.size(); i++) {
        var player = players.get(i);
        int num = player.rollDice();
        System.out.println(player + " rolled: " + num);
        int finalPosition = player.piece().getPosition() + num;
        if (board.isValidPosition(finalPosition)) {
          if (board.getLastTilePosition() == finalPosition) {
            declareWinner(player);
          } else {
            while (true) {
              Optional<Obstacle> obstacle = board.getObstacle(finalPosition);
              if (obstacle.isEmpty()) break;
              System.out.println(obstacle.get() + " found at " + finalPosition);
              finalPosition = obstacle.get().getFinalPosition();
              System.out.println("Moved piece to " + finalPosition);
            }
            System.out.println("Final position of piece is: " + finalPosition);
            player.piece().move(finalPosition);
          }
        }
      }
    }
    System.out.println("Game over!!!");
  }

  private void declareWinner(Player player) {
    System.out.println(player + " won the game!!!");
    removePlayer(player);
  }

  private void removePlayer(Player player) {
    players.remove(player);
  }
}
