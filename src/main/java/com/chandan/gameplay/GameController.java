package com.chandan.gameplay;

import com.chandan.chess.model.Board;
import com.chandan.chess.model.Player;
import com.chandan.gameplay.contracts.PlayerMove;

import java.util.List;

import static com.chandan.chess.conditions.PieceCellOccupyBlockerFactory.defaultAdditionalBlockers;

public class GameController {
    public static void gameplay(List<Player> players, Board board) {
        int currentPlayer = 0;
        while(true) {
            Player player = players.get(currentPlayer);
            PlayerMove playerMove = player.makeMove();
            playerMove.getPiece().move(player, playerMove.getToCell(), board, defaultAdditionalBlockers());
            currentPlayer = (currentPlayer + 1) % players.size();
        }
    }
}
