package Day_17;

import java.lang.reflect.WildcardType;

public class Chamber {

    private int chamberWidth;
    private int chamberHeight;
    private String[][] chamberMatrix;

    public Chamber(int width, int height) {
        chamberMatrix = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                chamberMatrix[i][j] = ".";
            }
        }
    }

    public String[][] getchamberMatrix() {
        return chamberMatrix;
    }

    public void setchamberMatrix(String[][] chamber) {
        this.chamberMatrix = chamber;
    }

    public int getChamberWidth() {
        return chamberWidth;
    }

    public void setChamberWidth(int chamberWidth) {
        this.chamberWidth = chamberWidth;
    }

    public int getChamberHeight() {
        return chamberHeight;
    }

    public void setChamberHeight(int chamberHeight) {
        this.chamberHeight = chamberHeight;
    }

    
    
}
