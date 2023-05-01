package com.chandan.chess.conditions;

import com.chandan.chess.model.Piece;

public interface MoveBaseCondition {
    boolean isBaseConditionFullfilled(Piece piece);
}
