package com.chandan.gameplay.contracts;

import com.chandan.chess.model.Cell;
import com.chandan.chess.model.Piece;
import lombok.Getter;

@Getter
public class PlayerMove {
    Piece piece;
    Cell toCell;
}
