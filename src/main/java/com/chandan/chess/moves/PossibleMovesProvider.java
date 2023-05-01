package com.chandan.chess.moves;

import com.chandan.chess.conditions.MoveBaseCondition;
import com.chandan.chess.conditions.PieceCellOccupyBlocker;
import com.chandan.chess.conditions.PieceMoveFurtherCondition;
import com.chandan.chess.model.Board;
import com.chandan.chess.model.Cell;
import com.chandan.chess.model.Piece;
import com.chandan.chess.model.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class PossibleMovesProvider {
    int maxSteps;
    MoveBaseCondition baseCondition;
    PieceMoveFurtherCondition moveFurtherCondition;
    PieceCellOccupyBlocker baseBlocker;

    public PossibleMovesProvider(int maxSteps, MoveBaseCondition baseCondition, PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker) {
        this.maxSteps = maxSteps;
        this.baseBlocker = baseBlocker;
        this.baseCondition = baseCondition;
        this.moveFurtherCondition = moveFurtherCondition;
    }

    public List<Cell> possibleMoves(Piece piece, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        if(baseCondition.isBaseConditionFullfilled(piece)) {
            return possibleMovesAsPerCurrentType(piece, board, additionalBlockers, player);
        }
        return null;
    }

    protected abstract List<Cell> possibleMovesAsPerCurrentType(Piece piece, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player);

    protected List<Cell> findAllNextMoves(Piece piece, NextCellProvider nextCellProvider, Board board, List<PieceCellOccupyBlocker> cellOccupyBlockers, Player player) {
        List<Cell> result = new ArrayList<>();
        Cell nextCell = nextCellProvider.nextCell(piece.getCurrentCell());
        int numSteps = 1;
        while(nextCell != null && numSteps <= maxSteps) {
            if(checkIfCellCenBeOccupied(piece, nextCell, board, cellOccupyBlockers, player)) {
                result.add(nextCell);
            }
            if(!moveFurtherCondition.canPieceMoveFurtherFromCell(piece, nextCell, board)) {
                break;
            }
            nextCell = nextCellProvider.nextCell(nextCell);
            numSteps++;
        }
        return result;
    }

    private boolean checkIfCellCenBeOccupied(Piece piece, Cell cell, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        if(baseBlocker != null && baseBlocker.isCellNonOccupiableForPiece(cell, piece, board, player))
        {
            return false;
        }
        for(PieceCellOccupyBlocker cellOccupyBlocker: additionalBlockers) {
            if(cellOccupyBlocker.isCellNonOccupiableForPiece(cell, piece, board, player)) {
                return false;
            }
        }
        return true;
    }
}
