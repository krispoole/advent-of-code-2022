package Day_9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SolutionPartB {
    
    public static void main(String[] args) throws IOException {

        File stackFile = new File("Day_9/dataTestB.txt");
        BufferedReader br = new BufferedReader(new FileReader(stackFile));

        String eachLine;

        ArrayList<Knot> knotList = new ArrayList<Knot>();

        // Create the starting position
        Knot startingPosition = new Knot();
        startingPosition.setOccupiedByHead(true);
        startingPosition.setOccupiedByTail(true);
        startingPosition.setWasVisitedByTail(true);
        
        boolean[] currentlyOccupiedByTail = new boolean[9];

        for (int i = 0; i < 9; i++) {
            currentlyOccupiedByTail[i] = true;
        }

        knotList.add(startingPosition);

        while ((eachLine = br.readLine()) != null) {

            String[] splitLine = eachLine.split(" ");
            String direction = splitLine[0];
            int steps = Integer.parseInt(splitLine[1]);

            Knot currentHeadKnot = new Knot();
            Knot currentTailKnot = new Knot();

            currentHeadKnot = figureOutCurrentHeadPosition(knotList);
            currentTailKnot = figureOutCurrentTailPosition(knotList, 0);

            for (int i = 0; i < steps; i++) {

                Knot newHeadKnot = new Knot();
                Knot newTailKnot = new Knot();

                // Set new head and tail knot positions
                newHeadKnot = setNewHeadKnot(currentHeadKnot, direction);
                newTailKnot = setNewTailKnot(newHeadKnot, currentTailKnot, direction);

                // add new head and tail knot to knotList if not already in it
                if (checkIfKnotIsInKnottList(newHeadKnot, knotList) == false) {
                    knotList.add(newHeadKnot);
                }
                if (checkIfKnotIsInKnottList(newTailKnot, knotList) == false) {
                    knotList.add(newTailKnot);
                }

                // Set updates in knotList
                knotList = updateHeadKnotList(knotList, newHeadKnot);
                knotList = updateTailKnotList(knotList, newTailKnot, 0);

                Knot savedHeadKnot = newHeadKnot;
                Knot savedTailKnot = newTailKnot;

                for (int j = 1; j < 9; j++) {

                    // last tail from this iteration
                    newHeadKnot = figureOutCurrentTailPosition(knotList, (j - 1));

                    direction = figureOutDirection(newHeadKnot, currentTailKnot);

                    // current tail from this iteration
                    currentTailKnot = figureOutCurrentTailPosition(knotList, j);


                    // Set new head and tail knot positions
                    newTailKnot = setNewTailKnot(newHeadKnot, currentTailKnot, direction);

                    if (checkIfKnotIsInKnottList(newTailKnot, knotList) == false) {
                        knotList.add(newTailKnot);
                    }

                    // Set updates in knotList
                    knotList = updateTailKnotList(knotList, newTailKnot, j);

                }

                // update knot positions

                currentHeadKnot = savedHeadKnot;
                currentTailKnot = savedTailKnot;

            }
                                    
        }

        for (Knot knot : knotList) {
            System.out.println(knot.getRow() + " " + knot.getCol() + " " + knot.isOccupiedByHead() +" " + knot.getCurrentlyOccupiedByTailArray()[0] + " " + knot.getCurrentlyOccupiedByTailArray()[1] + " " + knot.getCurrentlyOccupiedByTailArray()[2] + " " + knot.getCurrentlyOccupiedByTailArray()[3] + " " + knot.getCurrentlyOccupiedByTailArray()[4] + " " + knot.getCurrentlyOccupiedByTailArray()[5] + " " + knot.getCurrentlyOccupiedByTailArray()[6] + " " + knot.getCurrentlyOccupiedByTailArray()[7] + " " + knot.getCurrentlyOccupiedByTailArray()[8]);
        }

        int tailCounter = 0;

        for (Knot knot : knotList) {
            if (knot.isWasVisitedByTail() == true) {
                tailCounter++;  
            }
        }

        System.out.println(tailCounter);

    }

    private static String figureOutDirection(Knot newHeadKnot, Knot currentTailKnot) {
        String direction = "";

        if (newHeadKnot.getRow() > currentTailKnot.getRow()) {
            direction = "U";
        } else if (newHeadKnot.getRow() < currentTailKnot.getRow()) {
            direction = "D";
        } else if (newHeadKnot.getCol() > currentTailKnot.getCol()) {
            direction = "L";
        } else if (newHeadKnot.getCol() < currentTailKnot.getCol()) {
            direction = "R";
        }

        return direction;
    }

    private static ArrayList<Knot> updateHeadKnotList(ArrayList<Knot> knotList, Knot newHeadKnot) {
        for (Knot knot : knotList) {
            if (knot.getRow() == newHeadKnot.getRow() && knot.getCol() == newHeadKnot.getCol()) {
                knot.setOccupiedByHead(true);
            } else {
                knot.setOccupiedByHead(false);
            }
        }
        return knotList;
    }

    private static ArrayList<Knot> updateTailKnotList(ArrayList<Knot> knotList, Knot newTailKnot, int arrayPosition) {
        for (Knot knot : knotList) {
            if (knot.getRow() == newTailKnot.getRow() && knot.getCol() == newTailKnot.getCol()) {
                knot.setCurrentlyOccupiedByTailArray(arrayPosition, true);
            } else {
                knot.setCurrentlyOccupiedByTailArray(arrayPosition, false);
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

    private static Knot setNewTailKnot(Knot newHeadKnot, Knot currentTailKnot, String direction) {
        
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

    private static Knot figureOutCurrentTailPosition(ArrayList<Knot> knotList, int occupiedPosition) {

        Knot currentTailPosition = new Knot();

        for (Knot knot : knotList) {
            for (int j = 0; j < 9; j++) {
                if (knot.getCurrentlyOccupiedByTailArray()[j] == true) {
                    currentTailPosition = knot;
                }
            }
        }
        
        return currentTailPosition;
    }
}
