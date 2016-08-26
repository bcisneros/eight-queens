package com.tiempo.pdx;


class Game {

    private Board board;

    void start() {
        board = new Board();
    }

    ChessPiece pieceAt(BoardPosition position) {
        return board.pieceAt(position);
    }

    void placeAt(BoardPosition position) {
        board.placeAt(position);
    }
}
