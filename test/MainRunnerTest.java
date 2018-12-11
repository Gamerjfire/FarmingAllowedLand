import org.junit.Test;
import static org.junit.Assert.*;

//The main test requirements are to make sure the information is sanitized correctly.
public class MainRunnerTest {
    @Test
    public void sanitizationOfInputs() {
        //In order, success, failure to follow the pattern, too many inputs, failure to follow pattern, too few inputs, out of range error.
        String[] inputs = {"10 20 30 40", "10,20,30,40", "10 20 30 40 50", "yes 20 30 40", "20 30 40", "10 10 600 600"};
        int[] outputs = {10,20,30,40};
        MainRunner tester = new MainRunner(); // MyClass is tested

        int[] mainFunctionReturn = MainRunner.sanitizeInput(inputs[0]);

        assertArrayEquals(outputs, MainRunner.sanitizeInput(inputs[0]));
        assertNull(MainRunner.sanitizeInput(inputs[1]));
        assertNull(MainRunner.sanitizeInput(inputs[2]));
        assertNull(MainRunner.sanitizeInput(inputs[3]));
        assertNull(MainRunner.sanitizeInput(inputs[4]));
        assertNull(MainRunner.sanitizeInput(inputs[5]));


    }
}
