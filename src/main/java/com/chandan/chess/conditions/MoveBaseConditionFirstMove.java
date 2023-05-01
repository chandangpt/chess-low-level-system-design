package com.chandan.chess.conditions;

import com.chandan.chess.model.Piece;

public class MoveBaseConditionFirstMove implements MoveBaseCondition {
    @Override
    public boolean isBaseConditionFullfilled(Piece piece) {
        return piece.getNumMoves() == 0;
    }
}
