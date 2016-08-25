package com.tiempo.pdx;


import java.util.HashMap;
import java.util.Map;

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
        for (BoardPosition riskPosition : position.riskPositions()) {
            boardMap.put(riskPosition, RISK);
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
