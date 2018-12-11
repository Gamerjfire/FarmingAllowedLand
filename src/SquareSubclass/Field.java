package SquareSubclass;

import javafx.util.Pair;

import java.util.*;

public class Field {
    int[][] fieldSquares = new int[400][600];
    List<Integer> farmableAreas = new ArrayList<>();
    int currentTotalArea = 0;

    //Current implementation is allocating neighboring squares to begin with, and then area afterwards
    public Field(){
        for (int[] squareLine:fieldSquares){
            for(int squareValue:squareLine){
                squareValue=0;
            }
        }
    }

    //Set barren square values using the two corners.  Input must be sanitized first.
    public void setBarrenSquare(int[] barrenCorners){
        for(int horizontal=barrenCorners[0]; horizontal<=barrenCorners[2]; horizontal++){
            for(int vertical=barrenCorners[1]; vertical<=barrenCorners[3]; vertical++){
                fieldSquares[horizontal][vertical]=1;
            }
        }
    }

    //This function calls through each square and when it sees a new square of value 0 (Which indicates it is unseen) it
    //adds a new farmable square to the set to be allocated to.
    public List<Integer> findFarmableArea(){
        //Hardcoded for efficiency.  Might be better as variables but to do minor improvements on runtime I have it hardcoded.
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
        return farmableAreas;
    }

    //Search function to find the paths of potential information
    private int findFarmableNeighbor(List<Pair<Integer,Integer>> pairs){
        List<Pair<Integer, Integer>> functioningPairs = pairs;
        int summationTotal = 0;
        while (!functioningPairs.isEmpty()){
            Pair<Integer, Integer> pair = functioningPairs.get(0);
            functioningPairs.remove(0);
            fieldSquares[pair.getKey()][pair.getValue()]=2;
            summationTotal+=1;
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
