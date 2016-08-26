package com.tiempo.pdx;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.tiempo.pdx.BoardPosition.*;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class BoardPositionTest {

    @Test
    @Parameters(method = "data")
    public void should_know_its_risk_positions(BoardPosition position, List<BoardPosition> expectedRiskPositions) throws Exception {
        assertThat(position.riskPositions(), is(expectedRiskPositions));
    }

    @Test
    public void should_now_its_column() throws Exception {
        assertThat(A1.column(), is("A"));
        assertThat(B2.column(), is("B"));
        assertThat(C3.column(), is("C"));
        assertThat(D4.column(), is("D"));
        assertThat(E5.column(), is("E"));
    }

    @Test
    public void should_know_its_row_number() throws Exception {
        assertThat(A1.row(), is(1));
        assertThat(B2.row(), is(2));
        assertThat(C3.row(), is(3));
        assertThat(D4.row(), is(4));
        assertThat(E5.row(), is(5));
    }

    @Test
    public void should_know_its_right_position() throws Exception {
        assertThat(A1.right(), is(B1));
        assertThat(A2.right(), is(B2));
        assertThat(A3.right(), is(B3));
        assertThat(A4.right(), is(B4));
        assertNull(H1.right());
        assertNull(H2.right());
        assertNull(H3.right());
        assertNull(H4.right());
        assertNull(H5.right());
    }

    @Test
    public void should_know_its_left_position() throws Exception {
        assertThat(B2.left(), is(A2));
        assertThat(B3.left(), is(A3));
        assertThat(B5.left(), is(A5));

        assertNull(A1.left());
        assertNull(A6.left());
        assertNull(A3.left());
        assertNull(A2.left());
    }

    @Test
    public void should_know_its_up_position() throws Exception {
        assertThat(B7.up(), is(B8));
        assertThat(B6.up(), is(B7));
        assertThat(B5.up(), is(B6));
        assertThat(B4.up(), is(B5));
        assertThat(B3.up(), is(B4));
        assertThat(B2.up(), is(B3));
        assertThat(B1.up(), is(B2));

        assertNull(B8.up());
    }

    @Test
    public void should_know_its_down_position() throws Exception {
        assertThat(B8.down(), is(B7));
        assertThat(B7.down(), is(B6));
        assertThat(B6.down(), is(B5));
        assertThat(B5.down(), is(B4));
        assertThat(B4.down(), is(B3));
        assertThat(B3.down(), is(B2));
        assertThat(B2.down(), is(B1));
        assertNull(B1.down());
    }

    private Object[] data() {
        return new Object[]{
                new Object[]{
                        A1,
                        asList(
                                A2, A3, A4, A5, A6, A7, A8,
                                B2, C3, D4, E5, F6, G7, H8,
                                B1, C1, D1, E1, F1, G1, H1
                        )},
                new Object[]{
                        A2,
                        asList(
                                A3, A4, A5, A6, A7, A8,
                                B3, C4, D5, E6, F7, G8,
                                B2, C2, D2, E2, F2, G2, H2,
                                B1,
                                A1
                        )},
                new Object[]{
                        A3,
                        asList(
                                A4, A5, A6, A7, A8,
                                B4, C5, D6, E7, F8,
                                B3, C3, D3, E3, F3, G3, H3,
                                B2, C1,
                                A2, A1
                        )},
                new Object[]{
                        A4,
                        asList(
                                A5, A6, A7, A8,
                                B5, C6, D7, E8,
                                B4, C4, D4, E4, F4, G4, H4,
                                B3, C2, D1,
                                A3, A2, A1

                        )},
                new Object[]{
                        A5,
                        asList(
                                A6, A7, A8,
                                B6, C7, D8,
                                B5, C5, D5, E5, F5, G5, H5,
                                B4, C3, D2, E1,
                                A4, A3, A2, A1
                        )},
                new Object[]{
                        C1,
                        asList(
                                C2, C3, C4, C5, C6, C7, C8,
                                D2, E3, F4, G5, H6,
                                D1, E1, F1, G1, H1,
                                B1, A1,
                                B2, A3
                        )},

                new Object[]{
                        D4,
                        asList(
                                D5, D6, D7, D8,
                                E5, F6, G7, H8,
                                E4, F4, G4, H4,
                                E3, F2, G1,
                                D3, D2, D1,
                                C3, B2, A1,
                                C4, B4, A4,
                                C5, B6, A7
                        )},
                /*new Object[]{D5, asList(D1, D2, D3, D4, D6, D7, D8,
                        A5, B5, C5, E5, F5, G5, H5,
                        E6, F7, G8, E4, F3, G2, H1,
                        C4, B3, A2, C6, B7, A8)},
                new Object[]{D6, asList(D1, D2, D3, D4, D5, D7, D8,
                        A6, B6, C6, E6, F6, G6, H6,
                        E7, F8, E5, F4, G3, H2, C5,
                        B4, A3, C7, B8)},
                new Object[]{D7, asList(D1, D2, D3, D4, D5, D6, D8,
                        A7, B7, C7, E7, F7, G7, H7,
                        E8, E6, F5, G4, H3, C6, B5,
                        A4, C8)},
                new Object[]{D8, asList(D1, D2, D3, D4, D5, D6, D7,
                        A8, B8, C8, E8, F8, G8, H8,
                        E7, F6, G5, H4,
                        C7, B6, A5
                )},
                new Object[]{H1, asList(
                        H2, H3, H4, H5, H6, H7, H8,
                        A1, B1, C1, D1, E1, F1, G1,
                        G2, F3, E4, D5, C6, B7, A8
                )},
                new Object[]{H5, asList(
                        H1, H2, H3, H4, H6, H7, H8,
                        A5, B5, C5, D5, E5, F5, G5,
                        G4, F3, E2, D1, G6, F7, E8
                )},
                new Object[]{H8, asList(
                        H1, H2, H3, H4, H5, H6, H7,
                        A8, B8, C8, D8, E8, F8, G8,
                        G7, F6, E5, D4, C3, B2, A1
                )},*/
        };
    }
}