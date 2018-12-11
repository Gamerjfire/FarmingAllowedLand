package SquareSubclass;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FieldTest {
    Field field = new Field();
    @Test
    public void findFarmableAreaHalf() {
        int[] barrenLand =  {0,292,399,307};
        List<Integer> expectedOutput = new ArrayList<>();
        expectedOutput.add(116800);
        expectedOutput.add(116800);
        field.setBarrenSquare(barrenLand);
        assertEquals(expectedOutput,field.findFarmableArea());
    }

    @Test
    public void findFarmableAreaNothing() {
        List<Integer> expectedOutput = new ArrayList<>();
        expectedOutput.add(240000);
        assertEquals(expectedOutput,field.findFarmableArea());
    }

    @Test
    public void findFarmableAreHashTag() {

        int[] barrenLand1 =  {48,192,351,207};
        int[] barrenLand2 =  {48,392,351,407};
        int[] barrenLand3 =  {120,52,135,547};
        int[] barrenLand4 =  {260,52,275,547};

        List<Integer> expectedOutput = new ArrayList<>();
        expectedOutput.add(192608);
        expectedOutput.add(22816);

        //As we sanitize and then allocate information immediately we must do this many times.
        field.setBarrenSquare(barrenLand1);
        field.setBarrenSquare(barrenLand2);
        field.setBarrenSquare(barrenLand3);
        field.setBarrenSquare(barrenLand4);

        assertEquals(expectedOutput,field.findFarmableArea());
    }
}