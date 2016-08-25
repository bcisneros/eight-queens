package com.tiempo.pdx;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

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

    public List<Object[]> riskPositions() {
        List<Object[]> data = new ArrayList<Object[]>();
        for (BoardPosition position : BoardPosition.values()) {
            for (BoardPosition riskBoardPosition : position.riskPositions()) {
                data.add(new Object[]{position, riskBoardPosition});
            }
        }
        return data;
    }
}
