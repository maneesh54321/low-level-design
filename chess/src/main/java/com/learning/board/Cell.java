package com.learning.board;

import com.learning.units.Piece;

public class Cell {
    private final int id;
    private final String cellLabel;
    private Piece piece;
    private final Row row;

    public Cell(int id, String cellLabel, Piece piece, Row row) {
        this.id = id;
        this.cellLabel = cellLabel;
        this.piece = piece;
        this.row = row;
    }

    public int getId() {
        return id;
    }

    public String getCellLabel() {
        return cellLabel;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Row getRow() {
        return row;
    }
}
