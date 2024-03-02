package com.learning.game;

import com.learning.board.Piece;
import com.learning.dice.Dice;

import java.util.Objects;

public record Player(int id, Piece piece, Dice dice) {
  public int rollDice() {
    return this.dice.roll();
  }

  @Override
  public String toString() {
    return "Player{" + "id=" + id + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Player player = (Player) o;
    return id() == player.id();
  }

  @Override
  public int hashCode() {
    return Objects.hash(id());
  }
}
