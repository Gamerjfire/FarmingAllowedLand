import Exceptions.FieldBoundingException;
import Exceptions.NotSized4Exception;
import SquareSubclass.Field;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainRunner {

    public static void main(String args[]){

        Scanner input=new Scanner(System.in);
        Field totalField = new Field();
        try {
            //TODO Make a test with the below inputs
            //String[] inputs = {"10 20 30 40", "10,20,30,40", "10 20 30 40 50", "yes 20 30 40"};
            Set<int[]> inputs = new HashSet<>();
            boolean running = true;
            //Main runtime which pulls in information via the input scanner.
            while (running) {
                System.out.println("Please enter the next input for barren area.  If you are finished, press enter without typing anything else.");
                String inputtedValue = input.nextLine();
                if(inputtedValue==null || inputtedValue.equals("")){
                    running=false;
                } else {
                    int[] sanitizedInput = sanitizeInput(inputtedValue);
                    if(sanitizedInput != null) {
                        totalField.setBarrenSquare(sanitizedInput);
                        //inputs.add(sanitizedInput);
                    }
                }
            }
            System.out.println("Total Area of Farmable Area:"+totalField.findFarmableArea());
        } catch(Exception exception){
            System.out.println(exception);
        }
    }

    //To grab the information and format it to an integer so that it can be calculated.  Also error checks in case of poor coding.
    //Used instead of a scanner so immediate feedback on if there is a typo.
    private static int[] sanitizeInput(String input){
        int[] integerInput = new int[4];
        String[] stringInputArray;
        //Try catch for the purpose of checking for differently sized input and wrong types of input
        try{
            stringInputArray = input.split(" ");
            if(stringInputArray.length!=4){
                throw new NotSized4Exception("Too few or too many characters found.");
            }
            for (int index = 0; index<stringInputArray.length; index++) {
                if(index%2==0&&integerInput[index]>399){
                    throw new FieldBoundingException("Please stay within the bounds of the field");
                }
                integerInput[index] = Integer.valueOf(stringInputArray[index]);
            }
        //For inputs over/under 4
        } catch(NotSized4Exception e){
            System.out.println("Please enter only in sets of 4 for inputs");
            return null;
        } catch(NumberFormatException e){
            System.out.println("Please follow the listed format {## ## ## ##}");
            return null;
        } catch(FieldBoundingException Q){
            System.out.println("Please stay without the bounds of the field.");
            return null;
        }
        catch(Exception e){
            System.out.println("An unknown error has occurred when analyzing the inputs");
            throw e;
        }
        return integerInput;
    }
}


