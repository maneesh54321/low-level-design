package com.learning.board;

public class Piece {
  private int position;

  public Piece(int position) {
    this.position = position;
  }

  public int getPosition() {
    return position;
  }

  public void move(int position) {
    this.position = position;
  }
}
