package Day_9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Solution {
    
    public static void main(String[] args) throws IOException {

        File stackFile = new File("Day_9/data.txt");
        BufferedReader br = new BufferedReader(new FileReader(stackFile));

        String eachLine;

        ArrayList<Knot> knotList = new ArrayList<Knot>();

        // Create the starting position
        Knot startingPosition = new Knot();
        startingPosition.setOccupiedByHead(true);
        startingPosition.setOccupiedByTail(true);
        startingPosition.setWasVisitedByTail(true);
        knotList.add(startingPosition);

        while ((eachLine = br.readLine()) != null) {

            String[] splitLine = eachLine.split(" ");
            String direction = splitLine[0];
            int steps = Integer.parseInt(splitLine[1]);

            Knot currentHeadKnot = new Knot();
            Knot currentTailKnot = new Knot();

            currentHeadKnot = figureOutCurrentHeadPosition(knotList);
            currentTailKnot = figureOutCurrentTailPosition(knotList);

            for (int i = 0; i < steps; i++) {

                Knot newHeadKnot = new Knot();
                Knot newTailKnot = new Knot();

                // Set new head and tail knot positions
                newHeadKnot = setNewHeadKnot(currentHeadKnot, direction);
                newTailKnot = setNewTailKnot(currentTailKnot, newHeadKnot, direction);

                // add new head and tail knot to knotList if not already in it
                if (checkIfKnotIsInKnottList(newHeadKnot, knotList) == false) {
                    knotList.add(newHeadKnot);
                }
                if (checkIfKnotIsInKnottList(newTailKnot, knotList) == false) {
                    knotList.add(newTailKnot);
                }

                // Set updates in knotList
                knotList = updateKnotList(knotList, newHeadKnot, newTailKnot);

                currentHeadKnot = newHeadKnot;
                currentTailKnot = newTailKnot;

            }

            System.out.println("Head: " + currentHeadKnot.getRow() + " " + currentHeadKnot.getCol());
            System.out.println("Tail: " + currentTailKnot.getRow() + " " + currentTailKnot.getCol());

        }

        int tailCounter = 0;

        for (Knot knot : knotList) {
            if (knot.isWasVisitedByTail() == true) {
                tailCounter++;  
            }
        }

        System.out.println(tailCounter);

    }

    private static ArrayList<Knot> updateKnotList(ArrayList<Knot> knotList, Knot newHeadKnot, Knot newTailKnot) {
        for (Knot knot : knotList) {
            if (knot.getRow() == newHeadKnot.getRow() && knot.getCol() == newHeadKnot.getCol()) {
                knot.setOccupiedByHead(true);
            } else {
                knot.setOccupiedByHead(false);
            }
            if (knot.getRow() == newTailKnot.getRow() && knot.getCol() == newTailKnot.getCol()) {
                knot.setOccupiedByTail(true);
                knot.setWasVisitedByTail(true);
            } else {
                knot.setOccupiedByTail(false);
            }
        }
        return knotList;
    }

    private static Knot setNewHeadKnot(Knot currentHeadKnot, String direction) {
        Knot newHeadKnot = new Knot();

        switch (direction) {
            case "U":
                newHeadKnot.setRow(currentHeadKnot.getRow() + 1);
                newHeadKnot.setCol(currentHeadKnot.getCol());
                newHeadKnot.setOccupiedByHead(true);
                break;
            case "D":
                newHeadKnot.setRow(currentHeadKnot.getRow() - 1);
                newHeadKnot.setCol(currentHeadKnot.getCol());
                newHeadKnot.setOccupiedByHead(true);
                break;
            case "L":
                newHeadKnot.setRow(currentHeadKnot.getRow());
                newHeadKnot.setCol(currentHeadKnot.getCol() - 1);
                newHeadKnot.setOccupiedByHead(true);
                break;
            case "R":
                newHeadKnot.setRow(currentHeadKnot.getRow());
                newHeadKnot.setCol(currentHeadKnot.getCol() + 1);
                newHeadKnot.setOccupiedByHead(true);
                break;
            default:
                newHeadKnot = currentHeadKnot;
                break;
        }

        return newHeadKnot;
    }

    private static Knot setNewTailKnot(Knot currentTailKnot, Knot newHeadKnot, String direction) {

        Knot newTailKnot = new Knot();

        switch(direction) {
            case "U":
                // if newHeadKnotgetRow is 2 greater than currentTailKnotRow, then set newTailKnotRow to currentTailKnotRow + 1
                if (newHeadKnot.getRow() - currentTailKnot.getRow() == 2 && newHeadKnot.getCol() == currentTailKnot.getCol()) {
                    newTailKnot.setRow(currentTailKnot.getRow() + 1);
                    newTailKnot.setCol(currentTailKnot.getCol());
                } 
                else if (newHeadKnot.getRow() - currentTailKnot.getRow() == 2 && newHeadKnot.getCol() != currentTailKnot.getCol()) {
                    newTailKnot.setRow(currentTailKnot.getRow() + 1);
                    newTailKnot.setCol(newHeadKnot.getCol());
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
                else if (currentTailKnot.getRow() - newHeadKnot.getRow() == 2 && newHeadKnot.getCol() != currentTailKnot.getCol()) {
                    newTailKnot.setRow(currentTailKnot.getRow() - 1);
                    newTailKnot.setCol(newHeadKnot.getCol());
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
                else if (currentTailKnot.getCol() - newHeadKnot.getCol() == 2 && newHeadKnot.getRow() != currentTailKnot.getRow()) {
                    newTailKnot.setRow(newHeadKnot.getRow());
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
                else if (newHeadKnot.getCol() - currentTailKnot.getCol() == 2 && newHeadKnot.getRow() != currentTailKnot.getRow()) {
                    newTailKnot.setRow(newHeadKnot.getRow());
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

        newTailKnot.setOccupiedByTail(true);
        newTailKnot.setWasVisitedByTail(true);

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

    private static Knot figureOutCurrentHeadPosition(ArrayList<Knot> knotList) {

        Knot currentHeadPosition = new Knot();

        for (int i = 0; i < knotList.size(); i++) {
            if (knotList.get(i).isOccupiedByHead() == true) {
                currentHeadPosition = knotList.get(i);
            }
        }

        return currentHeadPosition;
    }

    private static Knot figureOutCurrentTailPosition(ArrayList<Knot> knotList) {

        Knot currentTailPosition = new Knot();

        for (int i = 0; i < knotList.size(); i++) {
            if (knotList.get(i).isOccupiedByTail() == true) {
                currentTailPosition = knotList.get(i);
            }
        }
        
        return currentTailPosition;
    }
}
