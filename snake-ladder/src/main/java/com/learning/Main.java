package com.learning;

import com.learning.board.Board;
import com.learning.board.Piece;
import com.learning.dice.BasicDice;
import com.learning.dice.Dice;
import com.learning.game.Controller;
import com.learning.game.Player;
import com.learning.loader.BoardLoader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    try (InputStream fis = Main.class.getResourceAsStream("/board.txt")) {
      BoardLoader boardLoader = new BoardLoader();
      Board board = (Board) boardLoader.createBoard(fis);
      Dice dice = new BasicDice();
      var players =
          List.of(
              new Player(1, new Piece(0), dice),
              new Player(2, new Piece(0), dice),
              new Player(3, new Piece(0), dice),
              new Player(4, new Piece(0), dice));

      Controller controller = new Controller(players, board);
      controller.start();
    }
  }
}
