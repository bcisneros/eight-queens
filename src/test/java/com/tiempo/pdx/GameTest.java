package com.tiempo.pdx;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.tiempo.pdx.BoardPosition.*;
import static com.tiempo.pdx.ChessPiece.NONE;
import static com.tiempo.pdx.ChessPiece.QUEEN;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class GameTest {

    private Game game = new Game();
    private Board board = game.board();

    @Test
    public void should_start_a_new_game_with_an_empty_board() throws Exception {
        for (BoardPosition position : BoardPosition.values()) {
            assertThat(board.pieceAt(position), is(NONE));
        }
    }

    @Test
    public void should_place_a_queen_in_a_board_position() throws Exception {
        BoardPosition position = A1;
        board.placeAt(position);
        assertThat(board.pieceAt(position), is(QUEEN));
    }

    @Test(expected = AlreadyUsedPositionException.class)
    public void should_not_place_a_queen_in_a_position_that_is_already_used() throws Exception {
        board.placeAt(A1);
        board.placeAt(A1);
    }

    @Test(expected = MoreThanEightQueensException.class)
    public void should_not_allow_to_place_more_than_eight_queens() throws Exception {
        board.placeAt(E1);
        board.placeAt(G2);
        board.placeAt(A3);
        board.placeAt(D4);
        board.placeAt(B5);
        board.placeAt(H6);
        board.placeAt(F7);
        board.placeAt(C8);
        board.placeAt(E2);
    }

    @Test(expected = GameOverException.class)
    @Parameters(method = "riskPositions")
    public void should_end_game_when_next_queen_is_placed_in_loosing_position(BoardPosition initialPosition,
                                                                              BoardPosition riskPosition) throws Exception {
        board.placeAt(initialPosition);
        board.placeAt(riskPosition);
    }


    public Object[] riskPositions() {
        return new Object[]{
                new Object[]{A1, A2},
                new Object[]{A1, A3},
                new Object[]{A1, A4},
                new Object[]{A1, A5},
                new Object[]{A1, A5},
                new Object[]{A1, A7},
                new Object[]{A1, A8},
                new Object[]{A1, B1},
                new Object[]{A1, C1},
                new Object[]{A1, D1},
                new Object[]{A1, E1},
                new Object[]{A1, F1},
                new Object[]{A1, G1},
                new Object[]{A1, H1},
                new Object[]{A1, B2},
                new Object[]{A1, C3},
                new Object[]{A1, D4},
                new Object[]{A1, E5},
                new Object[]{A1, F6},
                new Object[]{A1, G7},
                new Object[]{A1, H8},
        };
    }
}
