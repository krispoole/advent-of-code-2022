package Day_9;

public class Knot {

    private boolean occupiedByHead;
    private boolean occupiedByTail;
    private boolean wasVisitedByTail;   
    private boolean[] currentlyOccupiedByTailArray = new boolean[9];
    private int row;
    private int col;
    private String[] occupiedArray;

    public Knot(int row, int col) {
        this.row = 0;
        this.col = 0;
        this.occupiedByHead = false;
        this.occupiedByTail = false;
        this.wasVisitedByTail = false;
    }

    public boolean isOccupiedByHead() {
        return occupiedByHead;
    }

    public void setOccupiedByHead(boolean occupiedByHead) {
        this.occupiedByHead = occupiedByHead;
    }

    public boolean isOccupiedByTail() {
        return occupiedByTail;
    }

    public void setOccupiedByTail(boolean occupiedByTail) {
        this.occupiedByTail = occupiedByTail;
    }

    public boolean isWasVisitedByTail() {
        return wasVisitedByTail;
    }

    public void setWasVisitedByTail(boolean wasVisitedByTail) {
        this.wasVisitedByTail = wasVisitedByTail;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean[] getCurrentlyOccupiedByTailArray() {
        return currentlyOccupiedByTailArray;
    }

    public void setCurrentlyOccupiedByTailArray(int position, boolean value) {
        this.getCurrentlyOccupiedByTailArray()[position] = value;
    }

    public void setCurrentlyOccupiedByTailArray(boolean[] currentlyOccupiedByTailArray) {
        this.currentlyOccupiedByTailArray = currentlyOccupiedByTailArray;
    }

    public String[] getOccupiedArray() {
        return occupiedArray;
    }

    public void setOccupiedArray(String[] occupiedArray) {
        this.occupiedArray = occupiedArray;
    }
    
    
}
