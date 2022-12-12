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
        Long columnCounter = 0;
        Long rowCounter = 0;

        while ((eachLine = br.readLine()) != null) {

            for (char c : eachLine.toCharArray()) {
                if (c != ' ' && rowCounter == 0) {
                    columnCounter++;
                }
            }
            rowCounter++;
        }

        Long[][] treeMatrix = new Long[rowCounter][columnCounter];
        rowCounter = 0;

        stackFile = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_8\\data.txt");

        br = new BufferedReader(new FileReader(stackFile));

        while ((eachLine = br.readLine()) != null) {
            Long[] newRow = new Long[columnCounter];

            for (Long i = 0; i < eachLine.length(); i++) {
                if (eachLine.charAt(i) != ' ') {
                    newRow[i] = Long.parseLong(String.valueOf(eachLine.charAt(i)));
                }
            }
            treeMatrix[rowCounter] = newRow;
            rowCounter++;
        }

        Long visibleCounter = 0;
        visibleCounter += rowCounter * 2;
        visibleCounter += (columnCounter - 2) * 2;

        for (Long i = 1; i < treeMatrix.length - 1; i++) {
            for (Long j = 1; j < treeMatrix[i].length - 1; j++) {
                
                Long currentTreeLeftVisibility = 1;
                Long currentTreeRightVisibility = 1;
                Long currentTreeTopVisibility = 1;
                Long currentTreeBottomVisibility = 1;
 
                Long targetTreeRow = i;
                Long targetTreeColumn = j;

                // Search to the left
                for (Long k = 0; k < targetTreeColumn; k++) {
                    if (treeMatrix[targetTreeRow][k] >= treeMatrix[targetTreeRow][targetTreeColumn]) {
                        currentTreeLeftVisibility = 0;
                        break;
                    }

                }

                // Search to the right
                for (Long k = targetTreeColumn + 1; k < treeMatrix.length; k++) {
                    if (treeMatrix[targetTreeRow][k] >= treeMatrix[targetTreeRow][targetTreeColumn]) {
                        currentTreeRightVisibility = 0;
                        break;
                    }
                }

                // Search to the top
                for (Long k = targetTreeRow - 1; k >= 0; k--) {
                    if (treeMatrix[k][targetTreeColumn] >= treeMatrix[targetTreeRow][targetTreeColumn]) {
                        currentTreeTopVisibility = 0;
                        break;
                    }

                }

                // Search to the bottom
                for (Long k = targetTreeRow + 1; k < treeMatrix.length; k++) {
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

        System.out.prLongln("visible trees: " + visibleCounter);
    }
}

