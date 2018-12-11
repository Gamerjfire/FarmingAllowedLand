import Exceptions.FieldBoundingException;
import Exceptions.NotSized4Exception;
import FieldSubclass.Field;

import java.util.*;

public class MainRunner {

    /**
     * Main runtime function.  It is what is able to run and does the interaction asking and forwards functionality as needed.
     * @param args Necessary for creating a main function, currently unused.
     */
    public static void main(String args[]){

        Scanner input=new Scanner(System.in);
        Field totalField = new Field();
        try {
            boolean running = true;
            //Main runtime which pulls in information via the input scanner.  Error checking in real time to avoid typos.
            while (running) {
                System.out.println("Please enter the next input for barren area in the format \"## ## ## ##\".  If you are finished, press enter without typing anything else.");
                String inputtedValue = input.nextLine();
                if(inputtedValue==null || inputtedValue.equals("")){
                    running=false;
                } else {
                    int[] sanitizedInput = sanitizeInput(inputtedValue);
                    if(sanitizedInput != null) {
                        totalField.setBarrenSquare(sanitizedInput);
                    }
                }
            }
            List<Integer> farmableArea = totalField.findFarmableArea();
            Collections.sort(farmableArea);
            System.out.println(( ("Total Area of Farmable Area: " + farmableArea.toString())));
        } catch(Exception exception){
            System.out.println(exception);
        }
    }

    /**
     * Takes the input from STDIN and verifies it is acceptably formatted before returning and integer form.
     *
     * @param input The input read from STDIN
     * @return Integer form of the input given it follows the required format.
     */
    public static int[] sanitizeInput(String input){
        int[] integerInput = new int[4];
        String[] stringInputArray;
        try{
            stringInputArray = input.split(" ");
            if(stringInputArray.length==1){
                throw new NotSized4Exception("Dillineation character incorrect.");
            }
            if(stringInputArray.length!=4){
                throw new NotSized4Exception("Too few or too many characters found.");
            }
            //Error checking for if a selected square reaches outside of the field
            for (int index = 0; index<stringInputArray.length; index++) {
                integerInput[index] = Integer.valueOf(stringInputArray[index]);
                if(index%2==0&&integerInput[index]>399){
                    throw new FieldBoundingException("One of the inputs was greater than the width of the field.");
                }
                if(index%2==1&&integerInput[index]>599){
                    throw new FieldBoundingException("One of the inputs was greater than the length of the field.");
                }
            }
        //For inputs over/under 4
        } catch(NotSized4Exception|FieldBoundingException e){
            System.out.println(e.getMessage());
            return null;
        //For odd formating which is not caught by other formating errors
        } catch(NumberFormatException e){
            System.out.println("Please follow the listed format \"## ## ## ##\"");
            return null;
        //For avoiding numbers in excess of the field
        }
        catch(Exception e){
            System.out.println("An unknown error has occurred when analyzing the inputs");
            throw e;
        }
        return integerInput;
    }
}


