package Day_17;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    
    public static void main(String[] args) throws IOException {

        File file = new File("Day_17/data.txt");

        Shapes shapes = new Shapes();
        Chamber chamber = makeStartingChamber(9, 5);
        String[] jetAirDir = generateJetAirDir(file);

        boolean canMoveDown = true;

        int jetCounter = 0;
        int shapeCounter = 0;
        int iterations = 2022;
        String[][] shape;

        for (int i = 0; i < iterations; i++) {

            int shapeTopLeftColumn = 3;
            int shapeTopLeftRow = 0;

            shape = shapes.getShape(shapeCounter);  

            if (i > 0) {
                int freeRows = getRowsTilHighest(chamber);
                if (freeRows == 3) {
                    chamber = makeChamberTaller(chamber, shape.length);
                }
                else if (freeRows < 3) {
                    int rowsToAdd = 3 - freeRows;
                    chamber = makeChamberTaller(chamber, rowsToAdd);
                    chamber = makeChamberTaller(chamber, shape.length);
                }
                else if (freeRows > shape.length + 3) {
                    chamber = makeChamberSmaller(chamber, shape.length, freeRows);
                }
                else if (freeRows > 3) {
                    chamber = makeChamberTaller(chamber, shape.length - freeRows + 3);
                }

            } 

            chamber = addShapeToChamber(chamber, shape, shapeTopLeftRow, shapeTopLeftColumn);            

            while (canMoveDown) {

                boolean canMoveSideways = checkIfShapeCanMoveSideways(chamber, shape, shapeTopLeftRow, shapeTopLeftColumn, jetAirDir[jetCounter]);

                if (canMoveSideways) {

                    chamber = pushedByJetAir(chamber, shape, shapeTopLeftRow, shapeTopLeftColumn, jetAirDir[jetCounter]);

                    if (jetAirDir[jetCounter].equals("<")) {
                        shapeTopLeftColumn -= 1;
                    } 
                    else if (jetAirDir[jetCounter].equals(">")) {
                        shapeTopLeftColumn += 1;
                    }

                }
                
                canMoveDown = canShapeMoveDown(chamber, shape, shapeTopLeftRow, shapeTopLeftColumn);
                
                if (canMoveDown){
                    chamber = moveShapeDown(chamber, shape, shapeTopLeftRow, shapeTopLeftColumn);

                    shapeTopLeftRow += 1;
                }

                jetCounter++;
                if (jetCounter == jetAirDir.length) {
                    jetCounter = 0;
                }
            }

            canMoveDown = true;

            shapeCounter++;
            if (shapeCounter == 5) {
                shapeCounter = 0;
            }
        }

        int freeSpace = getRowsTilHighest(chamber);
        int height = chamber.getChamberHeight() - freeSpace - 1;


        System.out.println("Height: " + height);

    }

    private static Chamber makeChamberSmaller(Chamber chamber, int length, int freeRows) {

        int toReduce = freeRows - length - 3;

        String[][] chamberMatrix = chamber.getchamberMatrix();
        int chamberHeight = chamberMatrix.length;
        int chamberWidth = chamberMatrix[0].length;

        String[][] newChamberMatrix = new String[chamberHeight - toReduce][chamberWidth];

        for (int i = 0; i < chamberWidth; i++) {
            for (int j = 0; j < toReduce; j++) {
                if (i == 0 || i == chamberWidth - 1){
                    newChamberMatrix[j][i] = "|";
                }
                else {
                newChamberMatrix[j][i] = ".";
                }
            }

        }

        for (int i = 0; i < chamberHeight - toReduce; i++) {
            for (int j = 0; j < chamberWidth; j++) {
                newChamberMatrix[i][j] = chamberMatrix[i+ toReduce][j];
            }
        }

        chamber.setchamberMatrix(newChamberMatrix);
        chamber.setChamberHeight(chamberHeight - toReduce);

        return chamber;
    }

    private static int getRowsTilHighest(Chamber chamber) {

        int freeRow = 0;

        // find the first occurence of "#" and change it to "@"
        for (int i = 0; i < chamber.getChamberHeight(); i++) {
            for (int j = 0; j < chamber.getChamberWidth(); j++) {
                if (chamber.getchamberMatrix()[i][j].equals("#") || chamber.getchamberMatrix()[i][j].equals("-")) {
                    if (freeRow == 0) {
                        freeRow = i;
                        break;
                    }
                }
            }
        }

        return freeRow;
    }

    private static boolean checkIfShapeCanMoveSideways(Chamber chamber, String[][] shape, int shapeTopLeftRow,
            int shapeTopLeftColumn, String string) {

        if (shapeTopLeftColumn == 0 && string.equals("<")) {
            return false;
        }
        else if (shapeTopLeftColumn == chamber.getChamberWidth() - shape[0].length - 1 && string.equals(">")) {
            return false;
        }

        // if jet air is pushing shape left, check if shape can move left
        // if jet air is pushing shape right, check if shape can move right
        if (string.equals("<")) {
            // for each value in left column of shape, if there is "#" and value to left is ".", return true
            for (int i = 0; i < shape.length; i++) {
                if (chamber.getchamberMatrix()[shapeTopLeftRow + i][shapeTopLeftColumn].equals("#") && chamber.getchamberMatrix()[shapeTopLeftRow + i][shapeTopLeftColumn - 1].equals("#")){ 
                    return false;
                }
                if (chamber.getchamberMatrix()[shapeTopLeftRow + i][shapeTopLeftColumn].equals("#") && chamber.getchamberMatrix()[shapeTopLeftRow + i][shapeTopLeftColumn - 1].equals("|")) {
                    return false;
                }
            }
        }

        else if (string.equals(">")) {
            // search each value in right column of shape, if there is "#" and value to right is "#" return false
            for (int i = 0; i < shape.length; i++) {
                if (chamber.getchamberMatrix()[shapeTopLeftRow + i][shapeTopLeftColumn + shape[0].length].equals("#") && chamber.getchamberMatrix()[shapeTopLeftRow + i][shapeTopLeftColumn + shape[0].length].equals("#")) {
                    return false;
                }
                if (chamber.getchamberMatrix()[shapeTopLeftRow + i][shapeTopLeftColumn + shape[0].length].equals("#") && chamber.getchamberMatrix()[shapeTopLeftRow + i][shapeTopLeftColumn + shape[0].length].equals("|")) {
                    return false;
                }
            }
        }
        
        return true;
            
    }

    private static Chamber pushedByJetAir(Chamber chamber, String[][] shape, int shapeTopLeftRow, int shapeTopLeftColumn, String direction) {
        // determine if jet air is pushing shape left or right
        // if jet air is pushing shape left, move shape left 1 column
        // if jet air is pushing shape right, move shape right 1 column

        String[][] chamberMatrix = chamber.getchamberMatrix();

        // if jet air is pushing shape left
        if (direction.equals("<")) {
            // check if there is "|"
            for (int i = 0; i < shape.length; i++) {
                // if there is not, move shape left 1 column
                for (int j = 0; j < shape[0].length; j++) {
                    if (shape[i][j].equals("#")) {
                        chamberMatrix[shapeTopLeftRow + i][shapeTopLeftColumn + j - 1] = shape[i][j];
                        chamberMatrix[shapeTopLeftRow + i][shapeTopLeftColumn + j] = ".";
                    }
                }
            }
        }

        // if jet air is pushing shape right
        if (direction.equals(">")) {
            // check if there is "|"
            for (int i = 0; i < shape.length; i++) {
                // if there is not, move shape right 1 column
                for (int j = shape[0].length - 1; j >= 0; j--) {
                    if (shape[i][j].equals("#")) {
                        chamberMatrix[shapeTopLeftRow + i][shapeTopLeftColumn + j + 1] = shape[i][j];
                        chamberMatrix[shapeTopLeftRow + i][shapeTopLeftColumn + j] = ".";
                    }
                }
            }
        }

        chamber.setchamberMatrix(chamberMatrix);

        return chamber;
    }

    private static boolean canShapeMoveDown(Chamber chamber, String[][] shape, int shapeTopLeftRow, int shapeTopLeftColumn) {
        
        String[][] chamberMatrix = chamber.getchamberMatrix();
        boolean canMoveDown = true;

        for (int i = 0; i < shape[0].length; i++) {
            // if position in bottom row of shape contains "#"
            if (shape[shape.length - 1][i].equals("#")) {
                // if position below bottom row of shape contains "#" or "-"
                if (chamberMatrix[shapeTopLeftRow + shape.length][shapeTopLeftColumn + i].equals("#") || chamberMatrix[shapeTopLeftRow + shape.length][shapeTopLeftColumn + i].equals("-")) {
                    canMoveDown = false;
                    break;
                }
            }
            else if (shape[shape.length - 1][i].equals(".")) {
                if (shape[shape.length - 2][i].equals("#")) {
                    if (chamberMatrix[shapeTopLeftRow + shape.length - 1][shapeTopLeftColumn + i].equals("#") || chamberMatrix[shapeTopLeftRow + shape.length - 1][shapeTopLeftColumn + i].equals("-")) {
                        canMoveDown = false;
                        break;
                    }
                }
            }
            
        }

        return canMoveDown;
    }

    private static Chamber makeChamberTaller(Chamber chamber, int rowsToAdd) {
            
            String[][] chamberMatrix = chamber.getchamberMatrix();
            int chamberHeight = chamberMatrix.length;
            int chamberWidth = chamberMatrix[0].length;
    
            String[][] newChamberMatrix = new String[chamberHeight + rowsToAdd][chamberWidth];

            for (int i = 0; i < chamberWidth; i++) {
                for (int j = 0; j < rowsToAdd; j++) {
                    if (i == 0 || i == chamberWidth - 1){
                        newChamberMatrix[j][i] = "|";
                    }
                    else {
                    newChamberMatrix[j][i] = ".";
                    }
                }

            }
    
            for (int i = 0; i < chamberHeight; i++) {
                for (int j = 0; j < chamberWidth; j++) {
                    newChamberMatrix[i+rowsToAdd][j] = chamberMatrix[i][j];
                }
            }
    
            chamber.setchamberMatrix(newChamberMatrix);
            chamber.setChamberHeight(chamberHeight + rowsToAdd);
    
            return chamber;
    }

    private static Chamber addShapeToChamber(Chamber chamber, String[][] shape, int shapeTopLeftRow, int shapeTopLeftColumn) {

        String[][] chamberMatrix = chamber.getchamberMatrix();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                chamberMatrix[i + shapeTopLeftRow][j + shapeTopLeftColumn] = shape[i][j];
            }
        }
        chamber.setchamberMatrix(chamberMatrix);
        
        return chamber;
    }

    private static Chamber moveShapeDown(Chamber chamber, String[][] shape, int shapeTopLeftRow, int shapeTopLeftColumn) {

        String[][] chamberMatrix = chamber.getchamberMatrix();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                chamberMatrix[i + shapeTopLeftRow][j + shapeTopLeftColumn] = ".";
            }
        }
        shapeTopLeftRow++;
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j].equals("#")) {
                    chamberMatrix[i + shapeTopLeftRow][j + shapeTopLeftColumn] = shape[i][j];
                }
            }
        }

        chamber.setchamberMatrix(chamberMatrix);
        return chamber;
                
    }

    private static String[] generateJetAirDir(File file) throws IOException {
        
        BufferedReader br = new BufferedReader(new FileReader(file));

        String eachLine;
        String jetAirDirString = "";

        while ((eachLine = br.readLine()) != null) {
            jetAirDirString = eachLine;
        }

        String[] jetAirDir = jetAirDirString.split("");

        return jetAirDir;
    }

    private static void printChamber(Chamber chamber) {

        for (String[] row : chamber.getchamberMatrix()) {
            for (String col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }

    }

    public static Chamber makeStartingChamber(int width, int height) {

        Chamber chamber = new Chamber(width, height);
        chamber.setChamberHeight(height);
        chamber.setChamberWidth(width);

        String[][] tempChamberMatrix = chamber.getchamberMatrix();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (j == 0 || j == width - 1 && i != height - 1) {
                tempChamberMatrix[i][j] = "|";
                }
                if (i == height - 1 && j != 0 && j != width - 1) {
                    tempChamberMatrix[i][j] = "-";
                }
                if (i == height - 1 && j == 0 || i == height - 1 && j == width - 1) {
                    tempChamberMatrix[i][j] = "+";
                }
            }
        }

        chamber.setchamberMatrix(tempChamberMatrix);
        return chamber;
    }
    
    
}
