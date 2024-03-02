package com.learning.units;

public class Snake extends Obstacle {

  public Snake(int start, int end) {
    super(start, end);
  }

  @Override
  public String getType() {
    return "Snake";
  }

  @Override
  public String toString() {
    return String.format("Snake{%s}", super.toString());
  }
}
