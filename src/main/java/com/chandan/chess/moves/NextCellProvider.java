package com.chandan.chess.moves;

import com.chandan.chess.model.Cell;

public interface NextCellProvider {
    Cell nextCell(Cell cell);
}
