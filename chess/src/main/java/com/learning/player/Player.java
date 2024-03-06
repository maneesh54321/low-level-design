package com.learning.player;

import com.learning.board.Board;
import com.learning.board.Cell;
import com.learning.board.Move;
import com.learning.units.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Player {
  private final Color color;

  private Board board;

  public Player(Color color, Board board) {
    this.color = color;
    this.board = board;
  }

  public Color color() {
    return color;
  }

  public Move play() {
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      String line = br.readLine();
      List<Cell> cells = getCells(line, board);
      return new Move(cells);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
  }

  private List<Cell> getCells(String input, Board board) {
    var cells = new ArrayList<Cell>();
    return cells;
  }

  @Override
  public String toString() {
    return "Player[" + "color=" + color  + ']';
  }
}
