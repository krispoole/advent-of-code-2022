package Day_8;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SolutionPartB {

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

        Integer topScenicScore = 0;

        for (int i = 1; i < treeMatrix.length - 1; i++) {
            for (int j = 1; j < treeMatrix[i].length - 1; j++) {
                
                int currentTreeLeftVisibility = 0;
                int currentTreeRightVisibility = 0;
                int currentTreeTopVisibility = 0;
                int currentTreeBottomVisibility = 0;
 
                int targetTreeRow = i;
                int targetTreeColumn = j;

                // Search to the left
                for (int k = targetTreeColumn - 1; k >= 0; k--) {
                    currentTreeLeftVisibility++;
                    if (treeMatrix[targetTreeRow][k] >= treeMatrix[targetTreeRow][targetTreeColumn]) {
                        break;
                    }
                }

                // Search to the right
                for (int k = targetTreeColumn + 1; k < treeMatrix.length; k++) {
                    currentTreeRightVisibility++;
                    if (treeMatrix[targetTreeRow][k] >= treeMatrix[targetTreeRow][targetTreeColumn]) {
                        break;
                    }
                }

                // Search to the top
                for (int k = targetTreeRow - 1; k >= 0; k--) {
                    currentTreeTopVisibility++;
                    if (treeMatrix[k][targetTreeColumn] >= treeMatrix[targetTreeRow][targetTreeColumn]) {
                        break;
                    }
                }

                // Search to the bottom
                for (int k = targetTreeRow + 1; k < treeMatrix.length; k++) {
                    currentTreeBottomVisibility++;
                    if (treeMatrix[k][targetTreeColumn] >= treeMatrix[targetTreeRow][targetTreeColumn]) {
                        break;
                    }
                }

                Integer currentScenicScore = currentTreeLeftVisibility * currentTreeRightVisibility * currentTreeTopVisibility * currentTreeBottomVisibility;

                if (currentScenicScore > topScenicScore) {
                    topScenicScore = currentScenicScore;
                }

            }

        }

        System.out.println("topScenicScore = " + topScenicScore);
    }
}

