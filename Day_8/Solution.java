package Day_8;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ViewportLayout;

public class Solution {

    public static void main(String[] args) throws IOException {

        File stackFile = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_8\\data.txt");

        BufferedReader br = new BufferedReader(new FileReader(stackFile));

        String eachLine;
        int columnCounter = 0;
        int rowCounter = 0;

        while ((eachLine = br.readLine()) != null) {

            for (char c : eachLine.toCharArray()) {
                if (c != ' ' && rowCounter == 0) {
                    columnCounter++;
                }
            }
            rowCounter++;
        }

        Integer[][] treeMatrix = new Integer[rowCounter][columnCounter];
        rowCounter = 0;

        stackFile = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_8\\data.txt");

        br = new BufferedReader(new FileReader(stackFile));

        while ((eachLine = br.readLine()) != null) {
            Integer[] newRow = new Integer[columnCounter];

            for (int i = 0; i < eachLine.length(); i++) {
                if (eachLine.charAt(i) != ' ') {
                    newRow[i] = Integer.parseInt(String.valueOf(eachLine.charAt(i)));
                }
            }
            treeMatrix[rowCounter] = newRow;
            rowCounter++;
        }

        Integer visibleCounter = 0;
        visibleCounter += rowCounter * 2;
        visibleCounter += (columnCounter - 2) * 2;

        for (int i = 1; i < treeMatrix.length - 1; i++) {
            for (int j = 1; j < treeMatrix[i].length - 1; j++) {
                
                int currentTreeLeftVisibility = 1;
                int currentTreeRightVisibility = 1;
                int currentTreeTopVisibility = 1;
                int currentTreeBottomVisibility = 1;
 
                int targetTreeRow = i;
                int targetTreeColumn = j;

                // Search to the left
                for (int k = 0; k < targetTreeColumn; k++) {
                    if (treeMatrix[targetTreeRow][k] >= treeMatrix[targetTreeRow][targetTreeColumn]) {
                        currentTreeLeftVisibility = 0;
                        break;
                    }

                }

                // Search to the right
                for (int k = targetTreeColumn + 1; k < treeMatrix.length; k++) {
                    if (treeMatrix[targetTreeRow][k] >= treeMatrix[targetTreeRow][targetTreeColumn]) {
                        currentTreeRightVisibility = 0;
                        break;
                    }
                }

                // Search to the top
                for (int k = targetTreeRow - 1; k >= 0; k--) {
                    if (treeMatrix[k][targetTreeColumn] >= treeMatrix[targetTreeRow][targetTreeColumn]) {
                        currentTreeTopVisibility = 0;
                        break;
                    }

                }

                // Search to the bottom
                for (int k = targetTreeRow + 1; k < treeMatrix.length; k++) {
                    if (treeMatrix[k][targetTreeColumn] >= treeMatrix[targetTreeRow][targetTreeColumn]) {
                        currentTreeBottomVisibility = 0;
                        break;
                    }
                }

                if (currentTreeLeftVisibility > 0 || currentTreeRightVisibility > 0 || currentTreeTopVisibility > 0 || currentTreeBottomVisibility > 0) {
                    visibleCounter++;
                }

                currentTreeBottomVisibility = 1;
                currentTreeTopVisibility = 1;   
                currentTreeRightVisibility = 1;
                currentTreeLeftVisibility = 1;


            }

        }

        System.out.println("visible trees: " + visibleCounter);
    }
}

