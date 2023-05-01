package com.chandan.chess.conditions;

import com.chandan.chess.model.Piece;

public class NoMoveBaseCondition implements MoveBaseCondition {
    @Override
    public boolean isBaseConditionFullfilled(Piece piece) {
        return true;
    }
}
