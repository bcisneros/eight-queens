package com.tiempo.pdx;


import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@SuppressWarnings("ConstantConditions")
enum BoardPosition {
    A1, A2, A3, A4, A5, A6, A7, A8,
    B1, B2, B3, B4, B5, B6, B7, B8,
    C1, C2, C3, C4, C5, C6, C7, C8,
    D1, D2, D3, D4, D5, D6, D7, D8,
    E1, E2, E3, E4, E5, E6, E7, E8,
    F1, F2, F3, F4, F5, F6, F7, F8,
    G1, G2, G3, G4, G5, G6, G7, G8,
    H1, H2, H3, H4, H5, H6, H7, H8;

    public static final int LAST_ROW = 8;
    public static final String FIRST_COLUMN = "A";
    public static final String LAST_COLUMN = "H";
    public static final int UP = 1;
    public static final int DOWN = -1;
    public static final int RIGHT = 1;
    public static final int LEFT = -1;
    public static final int FIRST_ROW = 1;
    List<BoardPosition> riskPositions = new ArrayList<BoardPosition>();

    public List<BoardPosition> riskPositions() {
        findUpPositions();
        findRightUpPositions();
        findRightPositions();
        findRightDownPositions();
        findDownPositions();
        findLeftDownPositions();
        findLeftPositions();
        findLeftUpPositions();
        return riskPositions;
    }

    private void findLeftUpPositions() {
        BoardPosition leftUpPosition = leftUp();

        while (leftUpPosition != null) {
            riskPositions.add(leftUpPosition);
            leftUpPosition = leftUpPosition.leftUp();
        }
    }

    private BoardPosition leftUp() {
        return left() != null ? left().up() : null;
    }

    private void findLeftPositions() {
        BoardPosition leftPosition = left();
        while (leftPosition != null) {
            riskPositions.add(leftPosition);
            leftPosition = leftPosition.left();
        }
    }

    private void findLeftDownPositions() {
        BoardPosition leftDownPosition = leftDown();

        while (leftDownPosition != null) {
            riskPositions.add(leftDownPosition);
            leftDownPosition = leftDownPosition.leftDown();
        }
    }

    private BoardPosition leftDown() {
        return left() != null ? left().down() : null;
    }

    private void findRightPositions() {
        BoardPosition rightPosition = right();

        while (rightPosition != null) {
            riskPositions.add(rightPosition);
            rightPosition = rightPosition.right();
        }
    }

    private void findDownPositions() {
        BoardPosition downPosition = down();

        while (downPosition != null) {
            riskPositions.add(downPosition);
            downPosition = downPosition.down();
        }
    }

    private void findRightDownPositions() {
        BoardPosition rightDownPosition = rightDown();
        while (rightDownPosition != null) {
            riskPositions.add(rightDownPosition);
            rightDownPosition = rightDownPosition.rightDown();
        }
    }

    private BoardPosition rightDown() {
        return right() != null ? right().down() : null;
    }

    private void findRightUpPositions() {
        BoardPosition rightUpPosition = rightUp();
        while (rightUpPosition != null) {
            riskPositions.add(rightUpPosition);
            rightUpPosition = rightUpPosition.rightUp();
        }
    }

    private BoardPosition rightUp() {
        return right() != null ? right().up() : null;
    }

    private void findUpPositions() {
        BoardPosition upPosition = up();
        while (upPosition != null) {
            riskPositions.add(upPosition);
            upPosition = upPosition.up();
        }
    }

    public String column() {
        return name().substring(0, 1);
    }

    public int row() {
        return parseInt(name().substring(1));
    }


    public BoardPosition right() {
        return isLastColumn() ?
                null :
                horizontalPositionTo(RIGHT);
    }

    public BoardPosition left() {
        return isFirstColumn() ?
                null :
                horizontalPositionTo(LEFT);
    }

    private boolean isFirstColumn() {
        return isColumn(FIRST_COLUMN);
    }

    private boolean isLastColumn() {
        return isColumn(LAST_COLUMN);
    }

    private boolean isColumn(String column) {
        return column.equals(column());
    }

    private BoardPosition horizontalPositionTo(int position) {
        String next = String.valueOf((char) ((int) column().charAt(0) + position));
        return valueOf(next + row());
    }

    public BoardPosition up() {
        return isLastRow() ?
                null :
                verticalPositionTo(UP);
    }

    public BoardPosition down() {
        return isFirstRow() ?
                null :
                verticalPositionTo(DOWN);
    }

    private BoardPosition verticalPositionTo(int position) {
        return valueOf(column() + (row() + position));
    }

    private boolean isLastRow() {
        return isRow(LAST_ROW);
    }

    private boolean isFirstRow() {
        return isRow(FIRST_ROW);
    }

    private boolean isRow(int i) {
        return row() == i;
    }
}
