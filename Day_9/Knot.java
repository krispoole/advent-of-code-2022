package Day_9;

public class Knot {

    private boolean occupiedByHead;
    private boolean occupiedByTail;
    private boolean wasVisitedByTail;   
    private boolean[] currentlyOccupiedByTailArray = new boolean[9];
    private Long row;
    private Long col;
    private String[] occupiedArray;

    public Knot(Long row, Long col) {
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

    public Long getRow() {
        return row;
    }

    public void setRow(Long row) {
        this.row = row;
    }

    public Long getCol() {
        return col;
    }

    public void setCol(Long col) {
        this.col = col;
    }

    public boolean[] getCurrentlyOccupiedByTailArray() {
        return currentlyOccupiedByTailArray;
    }

    public void setCurrentlyOccupiedByTailArray(Long position, boolean value) {
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
