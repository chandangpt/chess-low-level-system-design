package com.chandan.chess.conditions;

import com.chandan.chess.model.Board;
import com.chandan.chess.model.Cell;
import com.chandan.chess.model.Piece;
import com.chandan.chess.model.Player;

public class PieceCellOccupyBlockerKingCheck implements PieceCellOccupyBlocker{
    @Override
    public boolean isCellNonOccupiableForPiece(Cell cell, Piece piece, Board board, Player player) {
        Cell pieceOriginalCell = piece.getCurrentCell();
        piece.setCurrentCell(cell);
        boolean playerGettingCheckByMove = board.isPlayerOnCheck(player);
        piece.setCurrentCell(pieceOriginalCell);
        return playerGettingCheckByMove;
    }
}
