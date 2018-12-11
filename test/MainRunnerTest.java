

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MainRunnerTest {
    @Test
    public void sanitizationOfInputs() {
        String[] inputs = {"10 20 30 40", "10,20,30,40", "10 20 30 40 50", "yes 20 30 40"};
        int[] outputs = {10,20,30,40};
        MainRunner tester = new MainRunner(); // MyClass is tested

        // assert statements
        assertEquals(outputs, MainRunner.sanitizeInput(inputs[0]));
        assertNull(MainRunner.sanitizeInput(inputs[1]));
        assertNull(MainRunner.sanitizeInput(inputs[2]));
        assertNull(MainRunner.sanitizeInput(inputs[3]));


    }
}
