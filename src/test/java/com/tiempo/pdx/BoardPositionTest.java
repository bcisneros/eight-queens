package com.tiempo.pdx;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.tiempo.pdx.BoardPosition.*;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class BoardPositionTest {

    @Test
    @Parameters(method = "data")
    public void should_know_its_risk_positions(BoardPosition position, List<BoardPosition> expectedRiskPositions) throws Exception {
        assertThat(position.riskPositions(), is(expectedRiskPositions));
    }

    private Object[] data() {
        return new Object[]{
                new Object[]{A1, asList(A2, A3, A4, A5, A6, A7, A8,
                        B1, C1, D1, E1, F1, G1, H1,
                        B2, C3, D4, E5, F6, G7, H8)},
                new Object[]{A2, asList(A1, A3, A4, A5, A6, A7, A8,
                        B2, C2, D2, E2, F2, G2, H2,
                        B3, C4, D5, E6, F7, G8, B1)},
                new Object[]{A3, asList(A1, A2, A4, A5, A6, A7, A8,
                        B3, C3, D3, E3, F3, G3, H3,
                        B4, C5, D6, E7, F8, B2, C1)},
                new Object[]{A4, asList(A1, A2, A3, A5, A6, A7, A8,
                        B4, C4, D4, E4, F4, G4, H4,
                        B5, C6, D7, E8, B3, C2, D1)},


                new Object[]{D4, asList(D1, D2, D3, D5, D6, D7, D8,
                        A4, B4, C4, E4, F4, G4, H4,
                        E5, F6, G7, H8, E3, F2, G1,
                        C3, B2, A1, C5, B6, A7)},

                new Object[]{D5, asList(D1, D2, D3, D4, D6, D7, D8,
                        A5, B5, C5, E5, F5, G5, H5,
                        E6, F7, G8, E4, F3, G2, H1,
                        C4, B3, A2, C6, B7, A8)},
        };
    }
}