package com.learning.units;

import com.learning.board.Cell;
import java.util.List;

public class Bishop extends Piece {
  @Override
  public boolean validateMovement(List<Cell> cells) {
    return false;
  }
}
