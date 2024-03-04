package com.learning.board;

import com.learning.exception.InvalidPositionException;
import com.learning.units.Piece;
import java.util.function.Predicate;

public record Board(Row[] rows) {

  private static final Predicate<Position> isValidPosition =
      pos -> pos.row() >= 0 && pos.row() < 8 && pos.col() >= 0 && pos.col() < 8;

  public Piece getPiece(Position position) throws InvalidPositionException {
    if(isValidPosition.test(position)){
      return rows[position.row()].cells()[position.col()].getPiece();
    }
    throw new InvalidPositionException();
  }

  public void removePiece(Position position) throws InvalidPositionException {
    if(isValidPosition.test(position)){
      rows[position.row()].cells()[position.col()].setPiece(null);
    }
    throw new InvalidPositionException();
  }

  public boolean isValid(Move move) {
    return false;
  }

  public void execute(Move move) {

  }

  public Cell getCell(Position position) throws InvalidPositionException {
    if(isValidPosition.test(position)){
      return rows[position.row()].cells()[position.col()];
    }
    throw new InvalidPositionException();
  }
}
