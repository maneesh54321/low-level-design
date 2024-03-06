package com.learning.board;

import com.learning.exception.GameOverException;
import com.learning.exception.InvalidPositionException;
import com.learning.units.King;
import com.learning.units.Piece;
import java.util.Optional;
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

  private void removePiece(Piece piece) {
    piece.getCell().setPiece(null);
    piece.moveToCell(null);
  }

  private boolean isValid(Piece piece, Move move) {
    return piece.validateMovement(move.cells());
  }

  public boolean execute(Piece piece, Move move) throws GameOverException {
    if(isValid(piece, move)){
      Cell srcCell = move.cells().getFirst();
      Cell targetCell = move.cells().getLast();
      Piece enemyPiece = targetCell.getPiece();
      targetCell.setPiece(piece);
      piece.moveToCell(targetCell);
      srcCell.setPiece(null);
      Optional.of(enemyPiece).ifPresent(this::removePiece);
      if(enemyPiece instanceof King){
        throw new GameOverException();
      }
      return true;
    }
    return false;
  }

  public Cell getCell(Position position) throws InvalidPositionException {
    if(isValidPosition.test(position)){
      return rows[position.row()].cells()[position.col()];
    }
    throw new InvalidPositionException();
  }
}
