package Day_9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolutionPartB {
    
    public static void main(String[] args) throws IOException {

        File stackFile = new File("Day_9/data.txt");
        BufferedReader br = new BufferedReader(new FileReader(stackFile));

        String eachLine;

        ArrayList<Knot> knotList = new ArrayList<Knot>();

        for (Long i = 0; i < 10; i++) {
            knotList.add(new Knot(0, 0));
        }

        String startingPosition = "0,0";
        String[] occupiedArray = new String[1];
        occupiedArray[0] = startingPosition;
        knotList.get(9).setOccupiedArray(occupiedArray);


        while ((eachLine = br.readLine()) != null) {

            String[] splitLine = eachLine.split(" ");
            String direction = splitLine[0];
            Long steps = Long.parseLong(splitLine[1]);

            for (Long i = 0; i < steps ; i++) {

                // move head
                Knot headKnot = knotList.get(0);
                headKnot = setNewHeadKnot(headKnot, direction);
                knotList.set(0, headKnot);

                for (Long j = 1; j < 10; j++) {

                    // move tail
                    Knot tailKnot = knotList.get(j);
                    headKnot = knotList.get(j - 1);

                    tailKnot = setTailKnot(tailKnot, headKnot);
                }

                String[] tempList = knotList.get(9).getOccupiedArray();
                String tempTailString = knotList.get(9).getCol() + "," + knotList.get(9).getRow();
                
                boolean arrayCheck = false;

                for (Long k = 0; k < tempList.length; k++) {
                    if (tempList[k].equals(tempTailString)) {
                        arrayCheck = true;
                    }
                }

                if (arrayCheck == false) {
                    String[] tempArray = new String[tempList.length + 1];
                    for (Long k = 0; k < tempList.length; k++) {
                        tempArray[k] = tempList[k];
                    }
                    tempArray[tempList.length] = tempTailString;
                    knotList.get(9).setOccupiedArray(tempArray);
                }


            }
        }

        Long counter = 0;

        for (Long i = 0; i < knotList.get(9).getOccupiedArray().length; i++) {
            counter++;
        }

        System.out.prLongln("Answer: " + counter);

    }

    private static Knot setTailKnot(Knot tailKnot, Knot headKnot) {

        Long headCol = headKnot.getCol();
        Long headRow = headKnot.getRow();
        Long tailCol = tailKnot.getCol();
        Long tailRow = tailKnot.getRow();

        if (headCol - tailCol == 2) {
            if (headRow == tailRow) {
                tailKnot.setCol(tailCol + 1);
            } else if (headRow - tailRow > 0) {
                tailKnot.setCol(tailCol + 1);
                tailKnot.setRow(tailRow + 1);
            } else if (tailRow - headRow > 0) {
                tailKnot.setCol(tailCol + 1);
                tailKnot.setRow(tailRow - 1);
            }
        }
       else if (headRow - tailRow == 2) {
            if (headCol == tailCol) {
                tailKnot.setRow(tailRow + 1);
            } else if (headCol - tailCol > 0) {
                tailKnot.setCol(tailCol + 1);
                tailKnot.setRow(tailRow + 1);
            } else if (tailCol - headCol > 0) {
                tailKnot.setCol(tailCol - 1);
                tailKnot.setRow(tailRow + 1);
            }
       }
       else if (tailCol - headCol == 2) {
            if (headRow == tailRow) {
                tailKnot.setCol(tailCol - 1);
            } else if (tailRow - headRow > 0) {
                tailKnot.setCol(tailCol - 1);
                tailKnot.setRow(tailRow - 1);
            } else if (headRow - tailRow > 0) {
                tailKnot.setCol(tailCol - 1);
                tailKnot.setRow(tailRow + 1);
            }
        }
        else if (tailRow - headRow == 2) {
            if (headCol == tailCol) {
                tailKnot.setRow(tailRow - 1);
            } else if (tailCol - headCol > 0) {
                tailKnot.setCol(tailCol - 1);
                tailKnot.setRow(tailRow - 1);
            } else if (headCol - tailCol > 0) {
                tailKnot.setCol(tailCol + 1);
                tailKnot.setRow(tailRow - 1);
            }
        }

        return tailKnot;
    }
    private static boolean checkIfKnotIsLongailList(Knot tailKnot, ArrayList<Knot> tailVisited) {

        boolean knotIsInList = false; 

        for (Long i = 0; i < tailVisited.size(); i++) {
            if (tailKnot.getRow() == tailVisited.get(i).getRow() && tailKnot.getCol() == tailVisited.get(i).getCol()) {
                knotIsInList = true;
            }
        }

        return knotIsInList;
    }
    private static Knot setNewHeadKnot(Knot headKnot, String direction) {

        Knot newHeadKnot = headKnot;

        switch (direction) {
            case "U":
                newHeadKnot.setRow(headKnot.getRow() + 1);
                newHeadKnot.setCol(headKnot.getCol());
                break;
            case "D":
                newHeadKnot.setRow(headKnot.getRow() - 1);
                newHeadKnot.setCol(headKnot.getCol());
                break;
            case "L":
                newHeadKnot.setRow(headKnot.getRow());
                newHeadKnot.setCol(headKnot.getCol() - 1);
                break;
            case "R":
                newHeadKnot.setRow(headKnot.getRow());
                newHeadKnot.setCol(headKnot.getCol() + 1);
                break;
            default:
                break;
        }

        return newHeadKnot;
    }
}
