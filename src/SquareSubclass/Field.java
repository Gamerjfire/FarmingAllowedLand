package SquareSubclass;

import java.util.HashSet;
import java.util.Set;

public class Field {
    int[][] fieldSquares = new int[400][600];
    Set<Integer> farmableAreas = new HashSet<>();
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
    public Set<Integer> findFarmableArea(){
        //Hardcoded for efficiency.  Might be better as variables but to do minor improvements on runtime I have it hardcoded.
        for(int horizontal= 0; horizontal<=399; horizontal++){
            for(int vertical= 0; vertical<=599; vertical++){
                if(fieldSquares[horizontal][vertical]==0){
                    farmableAreas.add(setSeenAndCallNeighbors(horizontal,vertical,0));
                    /*Set<String> pairsAnalyzed = new HashSet<>();
                    int[] testArrayX = {horizontal};
                    int[] testArrayY = {vertical};
                    farmableAreas.add(checkNeighboringAreas(testArrayX,testArrayY,pairsAnalyzed));*/
                }
            }
        }
        for(int horizontal= 0; horizontal<=399; horizontal++){
            System.out.println();
            for(int vertical= 0; vertical<=599; vertical++){
                System.out.print(fieldSquares[horizontal][vertical]);
            }
        }
        return farmableAreas;
    }

//    //The arrays will be the same size as they are pairs, they are in this form to allow for future information
//    private int checkNeighboringAreas(int[] availableSpacesX, int[] availableSpacesY, Set<String> pairsAnalyzed){
//
//    }

    //Recursive searching functionality to find the odd shapes effectively.
    private int findFarmableNeighbor(int x, int y, int totalAreaInSet){
        int totalAreaOverall = totalAreaInSet;
        System.out.println("Current location is [" + x + "," + y + "]");
        if(x>=0&&x<399){
            if(fieldSquares[x+1][y]==0){
                totalAreaOverall = setSeenAndCallNeighbors(x+1,y,totalAreaOverall);
            }
        }
        if(x>0&&x<=399){
            if(fieldSquares[x-1][y]==0){
                totalAreaOverall = setSeenAndCallNeighbors(x-1,y,totalAreaOverall);
            }
        }
        if(y>=0&&y<599){
            if(fieldSquares[x][y+1]==0){
                totalAreaOverall = setSeenAndCallNeighbors(x,y+1,totalAreaOverall);
            }
        }
        if(y>0&&y<=599){
            if(fieldSquares[x][y-1]==0){
                totalAreaOverall = setSeenAndCallNeighbors(x,y-1,totalAreaOverall);
            }
        }
        return  totalAreaOverall;

    }

    //Previous attempts at this version have yielded a solution with a poor runtime and a stack-overflow potential.  Scrapped for a more iterative solution.
    private int setSeenAndCallNeighbors(int x, int y, int totalAreaInSet){
        fieldSquares[x][y]=2;
        return findFarmableNeighbor(x, y, totalAreaInSet+1);
    }
}
