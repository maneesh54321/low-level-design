package com.learning.units;

import com.learning.board.Cell;
import java.util.List;

public abstract class Piece {
  private Color color;
  private Cell cell;

  public abstract boolean validateMovement(List<Cell> cells);

  public Color getColor() {
    return color;
  }

  protected Cell getCell() {
    return cell;
  }

  public void moveToCell(Cell cell) {
    this.cell = cell;
  }
}
