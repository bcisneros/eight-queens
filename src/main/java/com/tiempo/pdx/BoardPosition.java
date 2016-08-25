package com.tiempo.pdx;


import java.util.ArrayList;
import java.util.List;

enum BoardPosition {
    A1, A2, A3, A4, A5, A6, A7, A8,
    B1, B2, B3, B4, B5, B6, B7, B8,
    C1, C2, C3, C4, C5, C6, C7, C8,
    D1, D2, D3, D4, D5, D6, D7, D8,
    E1, E2, E3, E4, E5, E6, E7, E8,
    F1, F2, F3, F4, F5, F6, F7, F8,
    G1, G2, G3, G4, G5, G6, G7, G8,
    H1, H2, H3, H4, H5, H6, H7, H8;

    String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H"};
    List<BoardPosition> riskPositions = new ArrayList<BoardPosition>();

    public List<BoardPosition> riskPositions() {
        horizontal();
        vertical();
        firstDiagonal();
        secondDiagonal();
        thirdDiagonal();
        fourthDiagonal();
        return riskPositions;
    }

    private void fourthDiagonal() {
        Integer previous = previousLetterOf();
        if (previous > 0) {
            for (int i = number() + 1; i <= 8 && previous >= 0; ++i) {
                String name = letters[previous--] + i;
                riskPositions.add(BoardPosition.valueOf(name));
            }
        }
    }

    private void thirdDiagonal() {
        Integer previous = previousLetterOf();
        if (previous >= 0) {
            for (int i = number() - 1; i > 0 && previous >= 0; --i) {
                String name = letters[previous--] + i;
                riskPositions.add(BoardPosition.valueOf(name));
            }
        }
    }

    private void secondDiagonal() {
        Integer nextLetter = nextLetterOf();
        for (int i = number() - 1; i > 0 && nextLetter < 8; i--) {
            riskPositions.add(BoardPosition.valueOf(letters[nextLetter++] + i));
        }
    }

    private void firstDiagonal() {
        Integer nextLetter = nextLetterOf();
        for (int i = number() + 1; i <= 8 && nextLetter < 8; i++) {
            riskPositions.add(BoardPosition.valueOf(letters[nextLetter++] + i));
        }
    }

    private void vertical() {
        for (String currentLetter : letters) {
            BoardPosition boardPosition = BoardPosition.valueOf(currentLetter + (Integer) number());
            if (boardPosition != this)
                riskPositions.add(boardPosition);
        }
    }

    private int number() {
        return Integer.parseInt(name().substring(1));
    }

    private void horizontal() {
        for (int i = 1; i <= 8; i++) {
            BoardPosition boardPosition = BoardPosition.valueOf(letter() + i);
            if (boardPosition != this)
                riskPositions.add(boardPosition);
        }
    }

    private String letter() {
        return name().substring(0, 1);
    }

    private Integer nextLetterOf() {
        for (int i = 0; i < letters.length; i++) {
            if (letter().equals(letters[i])) {
                return i + 1;
            }
        }
        return -1;
    }

    private Integer previousLetterOf() {
        for (int i = 0; i < letters.length; i++) {
            if (letter().equals(letters[i])) {
                return i - 1;
            }
        }
        return -1;
    }
}
