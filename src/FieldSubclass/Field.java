package FieldSubclass;

import java.util.*;

public class Field {
    private int[][] fieldSquares = new int[400][600];
    private List<Integer> farmableAreas = new ArrayList<>();

    /**
     * Instantiates the field with 0s everywhere, assuming they are allowable farmland.
     */
    public Field(){
        for (int[] squareLine:fieldSquares){
            for(int squareValue:squareLine){
                squareValue=0;
            }
        }
    }

    /**
     * Function to label the coordinates of a barren area with indication that they are barren.
     * @param barrenCorners The corners of the barren area as given by the problem
     */
    public void setBarrenSquare(int[] barrenCorners){
        for(int horizontal=barrenCorners[0]; horizontal<=barrenCorners[2]; horizontal++){
            for(int vertical=barrenCorners[1]; vertical<=barrenCorners[3]; vertical++){
                fieldSquares[horizontal][vertical]=1;
            }
        }
    }

    /**This function calls through each square and when it sees a new square of value 0 (Which indicates it is unseen) it
    /*adds a new farmable square to the set to be allocated to.

     @return The full set of congruent areas within the field of which are non-barren.
    **/
    public List<Integer> findFarmableArea(){
        //Hardcoded for efficiency.
        for(int horizontal= 0; horizontal<=399; horizontal++){
            for(int vertical= 0; vertical<=599; vertical++){
                if(fieldSquares[horizontal][vertical]==0){
                    Coordinate pair = new Coordinate(horizontal,vertical);
                    List<Coordinate> listedPair = new ArrayList<>();
                    listedPair.add(pair);
                    fieldSquares[horizontal][vertical] = 2;
                    farmableAreas.add(findFarmableNeighbor(listedPair));
                }
            }
        }
        //Check for a fully barren field.  Assuming the client requires some input back.  Default option of 0 is found after.
        if(farmableAreas.isEmpty()) {
            return Collections.singletonList(0);
        }
        return farmableAreas;
    }

    /**
     *
     * @param pairs The initial list of known coordinate pairs that will be the basis of the function.
     * @return The total area found congruent to the original Pair (which is a coordinate)
     */
    private int findFarmableNeighbor(List<Coordinate> pairs){
        List<Coordinate> functioningPairs = pairs;
        int summationTotal = 0;
        while (!functioningPairs.isEmpty()){
            Coordinate pair = functioningPairs.get(0);
            functioningPairs.remove(0);
            summationTotal+=1;
            //The below checks each neighbor and sees if it is an unseen and non-barren value and adds it to the set of searching values.
            if (pair.getxCoordinate() >= 0 && pair.getxCoordinate() < 399) {
                if (fieldSquares[pair.getxCoordinate() + 1][pair.getyCoordinate()] == 0) {
                    fieldSquares[pair.getxCoordinate() + 1][pair.getyCoordinate()] = 2;
                    Coordinate toVerifyPair = new Coordinate(pair.getxCoordinate()+1, pair.getyCoordinate());
                    functioningPairs.add(toVerifyPair);
                }
            }
            if (pair.getxCoordinate() > 0 && pair.getxCoordinate() <= 399) {
                if (fieldSquares[pair.getxCoordinate() - 1][pair.getyCoordinate()] == 0) {
                    fieldSquares[pair.getxCoordinate() - 1][pair.getyCoordinate()] = 2;
                    Coordinate toVerifyPair = new Coordinate(pair.getxCoordinate()-1, pair.getyCoordinate());
                    functioningPairs.add(toVerifyPair);
                }
            }
            if (pair.getyCoordinate() >= 0 && pair.getyCoordinate() < 599) {
                if (fieldSquares[pair.getxCoordinate()][pair.getyCoordinate() + 1] == 0) {
                    fieldSquares[pair.getxCoordinate()][pair.getyCoordinate() + 1] = 2;
                    Coordinate toVerifyPair = new Coordinate(pair.getxCoordinate(), pair.getyCoordinate()+1);
                    functioningPairs.add(toVerifyPair);
                }
            }
            if (pair.getyCoordinate() > 0 && pair.getyCoordinate() <= 599) {
                if (fieldSquares[pair.getxCoordinate()][pair.getyCoordinate() - 1] == 0) {
                    fieldSquares[pair.getxCoordinate()][pair.getyCoordinate() - 1] = 2;
                    Coordinate toVerifyPair = new Coordinate(pair.getxCoordinate(), pair.getyCoordinate()-1);
                    functioningPairs.add(toVerifyPair);
                }
            }
        }
        return summationTotal;
    }
}
