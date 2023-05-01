package com.chandan.chess.moves;

import com.chandan.chess.conditions.MoveBaseCondition;
import com.chandan.chess.conditions.PieceCellOccupyBlocker;
import com.chandan.chess.conditions.PieceMoveFurtherCondition;
import com.chandan.chess.model.Board;
import com.chandan.chess.model.Cell;
import com.chandan.chess.model.Piece;
import com.chandan.chess.model.Player;

import java.util.List;

public class PossibleMovesProviderDiagonal extends PossibleMovesProvider {
    public PossibleMovesProviderDiagonal(int maxSteps, MoveBaseCondition baseCondition, PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker) {
        super(maxSteps, baseCondition, moveFurtherCondition, baseBlocker);
    }

    @Override
    protected List<Cell> possibleMovesAsPerCurrentType(Piece piece, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        return null;
    }
}
