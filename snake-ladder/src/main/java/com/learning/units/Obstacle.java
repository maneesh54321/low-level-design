package com.learning.units;

public abstract class Obstacle {
  private int start;
  private int end;

  public Obstacle(int start, int end) {
    this.start = start;
    this.end = end;
  }

  public int getFinalPosition() {
    return end;
  }

  public boolean isApplicable(int position) {
    return position == start;
  }

  public abstract String getType();

  @Override
  public String toString() {
    return "Obstacle{" + "start=" + start + ", end=" + end + '}';
  }
}
