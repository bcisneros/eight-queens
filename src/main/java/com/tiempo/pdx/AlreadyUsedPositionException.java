package com.tiempo.pdx;


class AlreadyUsedPositionException extends RuntimeException {

    AlreadyUsedPositionException(BoardPosition position) {
        super("Position already taken " + position);
    }
}
