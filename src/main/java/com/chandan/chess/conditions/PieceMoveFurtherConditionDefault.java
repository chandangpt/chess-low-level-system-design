package com.chandan.chess.conditions;

import com.chandan.chess.model.Board;
import com.chandan.chess.model.Cell;
import com.chandan.chess.model.Piece;

public class PieceMoveFurtherConditionDefault implements PieceMoveFurtherCondition{
    @Override
    public boolean canPieceMoveFurtherFromCell(Piece piece, Cell cell, Board board) {
        return cell.isFree();
    }
}
