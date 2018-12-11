package FieldSubclass;

import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;

public class FieldTest {
    Field field = new Field();
    @Test
    public void findFarmableAreaHalf() {
        int[] barrenLand =  {0,292,399,307};
        List<Integer> expectedOutput = Arrays.asList(116800,116800);

        field.setBarrenSquare(barrenLand);
        assertEquals(expectedOutput,field.findFarmableArea());
    }

    @Test
    public void findFarmableAreaNothing() {
        int[] barrenLand =  {0,0,399,599};
        List<Integer> expectedOutput = Collections.singletonList(0);

        field.setBarrenSquare(barrenLand);
        assertEquals(expectedOutput,field.findFarmableArea());
    }

    @Test
    public void findFarmableAreaEverything() {
        List<Integer> expectedOutput = Collections.singletonList(240000);

        assertEquals(expectedOutput,field.findFarmableArea());
    }

    @Test
    public void findFarmableAreaHashTag() {

        int[] barrenLand1 =  {48,192,351,207};
        int[] barrenLand2 =  {48,392,351,407};
        int[] barrenLand3 =  {120,52,135,547};
        int[] barrenLand4 =  {260,52,275,547};

        //Note:  Sorting takes place as a part of the main function after this is returned.  It is allowed to be out of order.
        List<Integer> expectedOutput = Arrays.asList(192608,22816);

        //As we sanitize and then allocate information immediately we must do this many times.
        field.setBarrenSquare(barrenLand1);
        field.setBarrenSquare(barrenLand2);
        field.setBarrenSquare(barrenLand3);
        field.setBarrenSquare(barrenLand4);

        assertEquals(expectedOutput,field.findFarmableArea());
    }

    @Test
    public void findFarmableAreaHashTagSplitHalf() {

        int[] barrenLand1 =  {48,192,351,207};
        int[] barrenLand2 =  {48,392,351,407};
        int[] barrenLand3 =  {120,52,135,547};
        int[] barrenLand4 =  {260,52,275,547};
        int[] barrenLand5 =  {0,292,399,305};


        List<Integer> expectedOutput = Arrays.asList(94352,94840,10416,10664);

        //As we sanitize and then allocate information immediately we must do this many times.
        field.setBarrenSquare(barrenLand1);
        field.setBarrenSquare(barrenLand2);
        field.setBarrenSquare(barrenLand3);
        field.setBarrenSquare(barrenLand4);
        field.setBarrenSquare(barrenLand5);


        assertEquals(expectedOutput,field.findFarmableArea());
    }
}