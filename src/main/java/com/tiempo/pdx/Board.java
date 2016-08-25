package com.tiempo.pdx;


import java.util.HashMap;
import java.util.Map;

import static com.tiempo.pdx.BoardPosition.*;
import static com.tiempo.pdx.ChessPiece.*;

class Board {

    private Map<BoardPosition, ChessPiece> boardMap = new HashMap<BoardPosition, ChessPiece>();
    private int placedQueensCounter;

    Board() {
        initializeBoardMap();
    }

    ChessPiece pieceAt(BoardPosition position) {
        return boardMap.get(position);
    }

    void placeAt(BoardPosition position) {
        validateNoQueenPlacedAt(position);
        validateNoMoreThanEightQueensArePlaced();
        if (pieceAt(position) == RISK) {
            throw new GameOverException();
        }
        if (position == A1) {
            boardMap.put(A2, RISK);
            boardMap.put(A3, RISK);
            boardMap.put(A4, RISK);
            boardMap.put(A5, RISK);
            boardMap.put(A6, RISK);
            boardMap.put(A7, RISK);
            boardMap.put(A8, RISK);
            boardMap.put(B1, RISK);
            boardMap.put(C1, RISK);
            boardMap.put(D1, RISK);
            boardMap.put(E1, RISK);
            boardMap.put(F1, RISK);
            boardMap.put(G1, RISK);
            boardMap.put(H1, RISK);
            boardMap.put(B2, RISK);
            boardMap.put(C3, RISK);
            boardMap.put(D4, RISK);
            boardMap.put(E5, RISK);
            boardMap.put(F6, RISK);
            boardMap.put(G7, RISK);
            boardMap.put(H8, RISK);
        }
        boardMap.put(position, QUEEN);
        placedQueensCounter++;
    }

    private void validateNoMoreThanEightQueensArePlaced() {
        if (placedQueensCounter >= 8) {
            throw new MoreThanEightQueensException();
        }
    }

    private void validateNoQueenPlacedAt(BoardPosition position) {
        if (boardMap.get(position) == QUEEN)
            throw new AlreadyUsedPositionException(position);
    }

    private void initializeBoardMap() {
        for (BoardPosition position : BoardPosition.values()) {
            boardMap.put(position, NONE);
        }
    }
}
