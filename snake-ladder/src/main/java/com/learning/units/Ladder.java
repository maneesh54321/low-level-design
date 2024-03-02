package com.learning.units;

public class Ladder extends Obstacle {

  public Ladder(int start, int end) {
    super(start, end);
  }

  @Override
  public String getType() {
    return "Ladder";
  }

  @Override
  public String toString() {
    return String.format("Ladder{%s}", super.toString());
  }
}
