package com.tiempo.pdx;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
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

    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();
        game.start();
    }

    @Test
    public void should_start_a_new_game_with_an_empty_board() throws Exception {
        for (BoardPosition position : BoardPosition.values()) {
            assertThat(game.pieceAt(position), is(NONE));
        }
    }

    @Test
    public void should_place_a_queen_in_a_board_position() throws Exception {
        BoardPosition position = A1;
        game.placeAt(position);
        assertThat(game.pieceAt(position), is(QUEEN));
    }

    @Test(expected = AlreadyUsedPositionException.class)
    public void should_not_place_a_queen_in_a_position_that_is_already_used() throws Exception {
        game.placeAt(A1);
        game.placeAt(A1);
    }

    @Test(expected = MoreThanEightQueensException.class)
    public void should_not_allow_to_place_more_than_eight_queens() throws Exception {
        game.placeAt(E1);
        game.placeAt(G2);
        game.placeAt(A3);
        game.placeAt(D4);
        game.placeAt(B5);
        game.placeAt(H6);
        game.placeAt(F7);
        game.placeAt(C8);
        game.placeAt(E2);
    }

    @Test(expected = GameOverException.class)
    @Parameters(method = "riskPositions")
    public void should_end_game_when_next_queen_is_placed_in_loosing_position(BoardPosition initialPosition,
                                                                              BoardPosition riskPosition) throws Exception {
        game.placeAt(initialPosition);
        game.placeAt(riskPosition);
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
