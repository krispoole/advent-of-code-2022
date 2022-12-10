package Day_9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SolutionPartBOld {

    
    public static void main(String[] args) throws IOException {

        File stackFile = new File("Day_9/dataTestB.txt");
        BufferedReader br = new BufferedReader(new FileReader(stackFile));

        String eachLine;

        ArrayList<Knot> knotList = new ArrayList<Knot>();

        // Create the starting position
        Knot startingPosition = new Knot();

        for (int i = 0; i < 10; i++) {
            startingPosition.setOccupiedArray(i, true);
        }

        startingPosition.setWasVisitedByTail(true);

        knotList.add(startingPosition);

        while ((eachLine = br.readLine()) != null) {

            String[] splitLine = eachLine.split(" ");
            String direction = splitLine[0];
            System.out.println(direction);
            int steps = Integer.parseInt(splitLine[1]);

            Knot currentHeadKnot = new Knot();
            Knot currentTailKnot = new Knot();

            currentHeadKnot = figureOutCurrentHeadPosition(knotList, 0);
            currentTailKnot = figureOutCurrentTailPosition(knotList, 1);

            for (int i = 0; i < steps; i++) {

                direction = determineDirectionOfHeadMove(currentHeadKnot, currentTailKnot);

                Knot newHeadKnot = new Knot();
                currentHeadKnot = figureOutCurrentHeadPosition(knotList, 0);

                newHeadKnot = setNewHeadKnot(currentHeadKnot, direction, 0);

                if (checkIfKnotIsInKnottList(newHeadKnot, knotList) == false) {
                    knotList.add(newHeadKnot);
                }

                Knot tempHeadKnot = newHeadKnot;

                for (int j = 1; j < 10; j++) {

                    Knot newTailKnot = new Knot();

                    currentTailKnot = figureOutCurrentTailPosition(knotList, j);
                    direction = determineDirectionOfHeadMove(tempHeadKnot, currentTailKnot);
                    System.out.println(direction);
       
                    // Set new head and tail knot positions
                    newTailKnot = setNewTailKnot(currentTailKnot, tempHeadKnot, direction, j);

                    // add new head and tail knot to knotList if not already in it
                    if (checkIfKnotIsInKnottList(newTailKnot, knotList) == false) {
                        knotList.add(newTailKnot);
                    }

                    // Set updates in knotList
                    knotList = updateKnotList(knotList, tempHeadKnot, newTailKnot, j);
                    tempHeadKnot = currentHeadKnot;
                    currentTailKnot = newTailKnot;

                }

                currentHeadKnot = newHeadKnot;
            }
        }

        int tailCounter = 0;

        for (Knot knot : knotList) {
            if (knot.isWasVisitedByTail() == true) {
                tailCounter++;  
            }
        }

        // for (Knot knot : knotList) {
        //     System.out.println(knot.getRow() + " " + knot.getCol() + " " + knot.isWasVisitedByTail());
        // }

        System.out.println(tailCounter);

    }

    private static String determineDirectionOfHeadMove(Knot currentHeadKnot, Knot currentTailKnot) {
        
        int lastHeadRow = currentHeadKnot.getRow();
        int lastHeadCol = currentHeadKnot.getCol();

        int lastTailRow = currentTailKnot.getRow();
        int lastTailCol = currentTailKnot.getCol();

        if (lastHeadRow == lastTailRow) {
            if (lastHeadCol > lastTailCol) {
                return "L";
            } else {
                return "R";
            }
        } else {
            if (lastHeadRow > lastTailRow) {
                return "U";
            } else {
                return "D";
            }
        }
    }

    private static ArrayList<Knot> updateKnotList(ArrayList<Knot> knotList, Knot newHeadKnot, Knot newTailKnot, int occupiedPosition) {
        for (Knot knot : knotList) {
            if (knot.getRow() == newHeadKnot.getRow() && knot.getCol() == newHeadKnot.getCol()) {
                knot.setOccupiedArray(occupiedPosition, true);
            } else {
                knot.setOccupiedArray(occupiedPosition, false);
            }
            if (knot.getRow() == newTailKnot.getRow() && knot.getCol() == newTailKnot.getCol()) {
                knot.setOccupiedArray(occupiedPosition, true);
                if (occupiedPosition == 9) {
                    knot.setWasVisitedByTail(true);
                }
            } else {
                knot.setOccupiedArray(occupiedPosition, false);
            }
        }
        // System.out.println("knot" + occupiedPosition + " " + newHeadKnot.getRow() + " " + newHeadKnot.getCol());
        return knotList;
    }

    private static Knot setNewHeadKnot(Knot currentHeadKnot, String direction, int occupiedPosition) {
        Knot newHeadKnot = new Knot();

        switch (direction) {
            case "U":
                newHeadKnot.setRow(currentHeadKnot.getRow() + 1);
                newHeadKnot.setCol(currentHeadKnot.getCol());
                newHeadKnot.setOccupiedArray(occupiedPosition, true);
                break;
            case "D":
                newHeadKnot.setRow(currentHeadKnot.getRow() - 1);
                newHeadKnot.setCol(currentHeadKnot.getCol());
                newHeadKnot.setOccupiedArray(occupiedPosition, true);
                break;
            case "L":
                newHeadKnot.setRow(currentHeadKnot.getRow());
                newHeadKnot.setCol(currentHeadKnot.getCol() - 1);
                newHeadKnot.setOccupiedArray(occupiedPosition, true);
                break;
            case "R":
                newHeadKnot.setRow(currentHeadKnot.getRow());
                newHeadKnot.setCol(currentHeadKnot.getCol() + 1);
                newHeadKnot.setOccupiedArray(occupiedPosition, true);
                break;
            default:
                newHeadKnot = currentHeadKnot;
                break;
        }

        return newHeadKnot;
    }

    private static Knot setNewTailKnot(Knot currentTailKnot, Knot newHeadKnot, String direction, int occupiedPosition) {

        Knot newTailKnot = new Knot();

        switch(direction) {
            case "U":
                // if newHeadKnotgetRow is 2 greater than currentTailKnotRow, then set newTailKnotRow to currentTailKnotRow + 1
                if (newHeadKnot.getRow() - currentTailKnot.getRow() == 2 && newHeadKnot.getCol() == currentTailKnot.getCol()) {
                    newTailKnot.setRow(currentTailKnot.getRow() + 1);
                    newTailKnot.setCol(currentTailKnot.getCol());
                } 
                else if (newHeadKnot.getRow() - currentTailKnot.getRow() == 2 && newHeadKnot.getCol() > currentTailKnot.getCol()) {
                    newTailKnot.setRow(currentTailKnot.getRow() + 1);
                    newTailKnot.setCol(currentTailKnot.getCol() + 1);
                }
                else if (newHeadKnot.getRow() - currentTailKnot.getRow() == 2 && newHeadKnot.getCol() < currentTailKnot.getCol()) {
                    newTailKnot.setRow(currentTailKnot.getRow() + 1);
                    newTailKnot.setCol(currentTailKnot.getCol() - 1);
                }
                else {
                    newTailKnot = currentTailKnot;
                }
                break;
            case "D":
                if (currentTailKnot.getRow() - newHeadKnot.getRow() == 2 && newHeadKnot.getCol() == currentTailKnot.getCol()) {
                    newTailKnot.setRow(currentTailKnot.getRow() - 1);
                    newTailKnot.setCol(currentTailKnot.getCol());
                } 
                else if (currentTailKnot.getRow() - newHeadKnot.getRow() == 2 && newHeadKnot.getCol() > currentTailKnot.getCol()) {
                    newTailKnot.setRow(currentTailKnot.getRow() - 1);
                    newTailKnot.setCol(currentTailKnot.getCol() + 1);
                } 
                else if (currentTailKnot.getRow() - newHeadKnot.getRow() == 2 && newHeadKnot.getCol() < currentTailKnot.getCol()) {
                    newTailKnot.setRow(currentTailKnot.getRow() - 1);
                    newTailKnot.setCol(currentTailKnot.getCol() - 1);
                } 
                else {
                    newTailKnot = currentTailKnot;
                }
                break;
            case "L":
                if (currentTailKnot.getCol() - newHeadKnot.getCol() == 2 && newHeadKnot.getRow() == currentTailKnot.getRow()) {
                    newTailKnot.setRow(currentTailKnot.getRow());
                    newTailKnot.setCol(currentTailKnot.getCol() - 1);
                } 
                else if (currentTailKnot.getCol() - newHeadKnot.getCol() == 2 && newHeadKnot.getRow() > currentTailKnot.getRow()) {
                    newTailKnot.setRow(currentTailKnot.getRow() + 1);
                    newTailKnot.setCol(currentTailKnot.getCol() - 1);
                }
                else if (currentTailKnot.getCol() - newHeadKnot.getCol() == 2 && newHeadKnot.getRow() < currentTailKnot.getRow()) {
                    newTailKnot.setRow(currentTailKnot.getRow() - 1);
                    newTailKnot.setCol(currentTailKnot.getCol() - 1);
                }
                else {
                    newTailKnot = currentTailKnot;
                }
                break;
            case "R":
                if (newHeadKnot.getCol() - currentTailKnot.getCol() == 2 && newHeadKnot.getRow() == currentTailKnot.getRow()) {
                    newTailKnot.setRow(currentTailKnot.getRow());
                    newTailKnot.setCol(currentTailKnot.getCol() + 1);
                } 
                else if (newHeadKnot.getCol() - currentTailKnot.getCol() == 2 && newHeadKnot.getRow() > currentTailKnot.getRow()) {
                    newTailKnot.setRow(currentTailKnot.getRow() + 1);
                    newTailKnot.setCol(currentTailKnot.getCol() + 1);
                }
                else if (newHeadKnot.getCol() - currentTailKnot.getCol() == 2 && newHeadKnot.getRow() < currentTailKnot.getRow()) {
                    newTailKnot.setRow(currentTailKnot.getRow() - 1);
                    newTailKnot.setCol(currentTailKnot.getCol() + 1);
                }
                else {
                    newTailKnot = currentTailKnot;
                }
                break;
            default:
                newTailKnot = currentTailKnot;
                break;
        }

        newTailKnot.setOccupiedArray(occupiedPosition, true);

        return newTailKnot;
    }

    private static boolean checkIfKnotIsInKnottList(Knot newHeadKnot, ArrayList<Knot> knotList) {
        boolean knotIsInList = false;
        for (int i = 0; i < knotList.size(); i++) {
            if (newHeadKnot.getRow() == knotList.get(i).getRow() && newHeadKnot.getCol() == knotList.get(i).getCol()) {
                knotIsInList = true;
            }
        }
        return knotIsInList;
    }

    private static Knot figureOutCurrentHeadPosition(ArrayList<Knot> knotList, int occupiedPosition) {

        Knot currentHeadPosition = new Knot();

        for (int i = 0; i < knotList.size(); i++) {
            for (int j = 0; j < 10; j++) {
                if (knotList.get(i).getOccupiedArray()[occupiedPosition] == true) {
                    currentHeadPosition = knotList.get(i);
                }
            }
        }

        return currentHeadPosition;
    }

    private static Knot figureOutCurrentTailPosition(ArrayList<Knot> knotList, int occupiedPosition) {

        Knot currentTailPosition = new Knot();

        for (int i = 0; i < knotList.size(); i++) {
            for (int j = 0; j < 10; j++) {
                if (knotList.get(i).getOccupiedArray()[occupiedPosition] == true) {
                    currentTailPosition = knotList.get(i);
                }
            }
        }
        
        return currentTailPosition;
    }
    
    
}
