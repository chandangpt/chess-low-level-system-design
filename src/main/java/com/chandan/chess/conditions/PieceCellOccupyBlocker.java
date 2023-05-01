package com.chandan.chess.conditions;

import com.chandan.chess.model.Board;
import com.chandan.chess.model.Cell;
import com.chandan.chess.model.Piece;
import com.chandan.chess.model.Player;

public interface PieceCellOccupyBlocker {
    boolean isCellNonOccupiableForPiece(Cell cell, Piece piece, Board boar, Player player);
}
