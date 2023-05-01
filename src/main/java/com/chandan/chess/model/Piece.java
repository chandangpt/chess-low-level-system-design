package com.chandan.chess.model;

import com.chandan.chess.conditions.PieceCellOccupyBlocker;
import com.chandan.chess.exceptions.InvalidMoveException;
import com.chandan.chess.moves.PossibleMovesProvider;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.chandan.chess.helpers.ListHelpers.removeDuplicates;

@Getter
public class Piece {
    private boolean isKilled = false;
    private final Color color;
    private final List<PossibleMovesProvider> movesProviders;
    private Integer numMoves = 0;
    PieceType pieceType;

    @Setter
    private Cell currentCell;

    public Piece(@NonNull final Color color, @NonNull final List<PossibleMovesProvider> movesProviders, @NonNull final PieceType pieceType) {
        this.color = color;
        this.movesProviders = movesProviders;
        this.pieceType = pieceType;
    }

    public void killIt() {
        this.isKilled = isKilled;
    }

    public void move(Player player, Cell toCell, Board board, List<PieceCellOccupyBlocker> additionalBlockers) {
        if(isKilled) {
            throw new InvalidMoveException();
        }
        List<Cell> nextPossibleCells = nextPossibleCells(board, additionalBlockers, player);
        if(!nextPossibleCells.contains(toCell)) {
            throw new InvalidMoveException();
        }
        killPieceInCell(toCell);
        this.currentCell.setCurrentPiece(null);
        this.currentCell = toCell;
        this.currentCell.setCurrentPiece(this);
        this.numMoves++;
    }

    private void killPieceInCell(Cell targetCell) {
        if(targetCell.getCurrentPiece() != null) {
            targetCell.getCurrentPiece().killIt();
        }
    }

    public List<Cell> nextPossibleCells(Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        List<Cell> results = new ArrayList<>();
        for(PossibleMovesProvider movesProvider : this.movesProviders) {
            List<Cell> cells = movesProvider.possibleMoves(this, board, additionalBlockers, player);
            if(cells != null) {
                results.addAll(cells);
            }
        }
        return removeDuplicates(results);
    }

    public boolean isPieceFromSamePlayer(Piece piece) {
        return piece.getColor().equals(this.color);
    }
}
