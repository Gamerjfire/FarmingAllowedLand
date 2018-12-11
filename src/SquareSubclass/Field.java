package SquareSubclass;

import javafx.util.Pair;

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
                    Pair<Integer,Integer> pair = new Pair<Integer, Integer>(horizontal,vertical);
                    List<Pair<Integer, Integer>> listedPair = new ArrayList<>();
                    listedPair.add(pair);
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
    private int findFarmableNeighbor(List<Pair<Integer,Integer>> pairs){
        List<Pair<Integer, Integer>> functioningPairs = pairs;
        int summationTotal = 0;
        while (!functioningPairs.isEmpty()){
            Pair<Integer, Integer> pair = functioningPairs.get(0);
            functioningPairs.remove(0);
            fieldSquares[pair.getKey()][pair.getValue()]=2;
            summationTotal+=1;
            //The below checks each neighbor and sees if it is an unseen and non-barren value and adds it to the set of searching values.
            if (pair.getKey() >= 0 && pair.getKey() < 399) {
                if (fieldSquares[pair.getKey() + 1][pair.getValue()] == 0) {
                    Pair<Integer, Integer> toVerifyPair = new Pair<>(pair.getKey()+1, pair.getValue());
                    if(!functioningPairs.contains(toVerifyPair)) {
                        functioningPairs.add(toVerifyPair);
                    }
                }
            }
            if (pair.getKey() > 0 && pair.getKey() <= 399) {
                if (fieldSquares[pair.getKey() - 1][pair.getValue()] == 0) {
                    Pair<Integer, Integer> toVerifyPair = new Pair<>(pair.getKey()-1, pair.getValue());
                    if(!functioningPairs.contains(toVerifyPair)) {
                        functioningPairs.add(toVerifyPair);
                    }
                }
            }
            if (pair.getValue() >= 0 && pair.getValue() < 599) {
                if (fieldSquares[pair.getKey()][pair.getValue() + 1] == 0) {
                    Pair<Integer, Integer> toVerifyPair = new Pair<>(pair.getKey(), pair.getValue()+1);
                    if(!functioningPairs.contains(toVerifyPair)) {
                        functioningPairs.add(toVerifyPair);
                    }
                }
            }
            if (pair.getValue() > 0 && pair.getValue() <= 599) {
                if (fieldSquares[pair.getKey()][pair.getValue() - 1] == 0) {
                    Pair<Integer, Integer> toVerifyPair = new Pair<>(pair.getKey(), pair.getValue()-1);
                    if(!functioningPairs.contains(toVerifyPair)) {
                        functioningPairs.add(toVerifyPair);
                    }
                }
            }
        }
        return summationTotal;
    }
}
