package com.chandan.chess.model;

import com.chandan.chess.conditions.PieceCellOccupyBlocker;
import lombok.Getter;

import java.util.List;

import static com.chandan.chess.conditions.PieceCellOccupyBlockerFactory.kingCheckEvaluationBlockers;

@Getter
public class Board {
    int boardSize;
    Cell[][] cells;

    public Board(int boardSize, Cell[][] cells) {
        this.boardSize = boardSize;
        this.cells = cells;
    }

    public Cell getLeftCell(Cell cell) {
        return getCellAtLocation(cell.getX(), cell.getY() - 1);
    }

    public Cell getRightCell(Cell cell) {
        return getCellAtLocation(cell.getX(), cell.getY() + 1);
    }

    public Cell getUpCell(Cell cell) {
        return getCellAtLocation(cell.getX() + 1, cell.getY());
    }

    public Cell getDownCell(Cell cell) {
        return getCellAtLocation(cell.getX() - 1, cell.getY());
    }

    public Cell getCellAtLocation(int x, int y) {
        if(x < 0 || y < 0 || x >= boardSize || y >= boardSize) {
            return null;
        }
        return cells[x][y];
    }

    public boolean isPlayerOnCheck(Player player) {
        return checkIfPlayerCanBeKilled(player.getPiece(PieceType.KING), kingCheckEvaluationBlockers(), player);
    }

    public boolean checkIfPlayerCanBeKilled(Piece targetPiece, List<PieceCellOccupyBlocker> cellOccupyBlockers, Player player) {
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                Piece currentPiece = getCellAtLocation(i, j).getCurrentPiece();
                if(currentPiece != null && !currentPiece.isPieceFromSamePlayer(targetPiece)) {
                    List<Cell> nextPossibleCells = currentPiece.nextPossibleCells(this, cellOccupyBlockers, player);
                    if(nextPossibleCells.contains(targetPiece.getCurrentCell()))
                        return true;
                }
            }
        }
        return false;
    }
}
