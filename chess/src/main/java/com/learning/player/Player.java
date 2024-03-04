package com.learning.player;

import com.learning.board.Cell;
import com.learning.board.Move;
import com.learning.game.Controller;
import com.learning.units.Color;
import com.learning.units.Piece;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player {
  private final Color color;
  private final Controller controller;

  public Player(Color color, Controller controller) {
    this.color = color;
    this.controller = controller;
  }

  public Color color() {
    return color;
  }

  public Optional<Piece> play() {
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      String line = br.readLine();
      controller.execute(new Move(getCells(line)));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return Optional.empty();
  }

  private List<Cell> getCells(String input) {
    var cells = new ArrayList<Cell>();
    return cells;
  }

  @Override
  public String toString() {
    return "Player[" + "color=" + color  + ']';
  }
}
