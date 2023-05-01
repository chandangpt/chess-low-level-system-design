package com.chandan.chess.model;

import com.chandan.chess.exceptions.PieceNotFoundException;
import com.chandan.gameplay.contracts.PlayerMove;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class Player {
    List<Piece> pieces;

    public Player(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Piece getPiece(PieceType pieceType) {
        for(Piece piece: pieces) {
            if(piece.getPieceType() == pieceType) {
                return piece;
            }
        }
        throw new PieceNotFoundException();
    }

    abstract public PlayerMove makeMove();

}
