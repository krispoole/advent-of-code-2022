package Day_12;

import java.util.ArrayList;

public class HeightPath {

    private ArrayList<int[]> visitedPositions;
    private int[] currentPosition;
    private int steps;
    private int currentPositionValue;

    public HeightPath() {
    }

    public ArrayList<int[]> getVisitedPositions() {
        return visitedPositions;
    }

    public void setVisitedPositions(ArrayList<int[]> visitedPositions) {
        this.visitedPositions = visitedPositions;
    }

    public int[] getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int[] currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getCurrentPositionValue() {
        return currentPositionValue;
    }

    public void setCurrentPositionValue(int currentPositionValue) {
        this.currentPositionValue = currentPositionValue;
    }

    
    
}
