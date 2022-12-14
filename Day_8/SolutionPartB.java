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

        Long topScenicScore = 0;

        for (Long i = 1; i < treeMatrix.length - 1; i++) {
            for (Long j = 1; j < treeMatrix[i].length - 1; j++) {
                
                Long currentTreeLeftVisibility = 0;
                Long currentTreeRightVisibility = 0;
                Long currentTreeTopVisibility = 0;
                Long currentTreeBottomVisibility = 0;
 
                Long targetTreeRow = i;
                Long targetTreeColumn = j;

                // Search to the left
                for (Long k = targetTreeColumn - 1; k >= 0; k--) {
                    currentTreeLeftVisibility++;
                    if (treeMatrix[targetTreeRow][k] >= treeMatrix[targetTreeRow][targetTreeColumn]) {
                        break;
                    }
                }

                // Search to the right
                for (Long k = targetTreeColumn + 1; k < treeMatrix.length; k++) {
                    currentTreeRightVisibility++;
                    if (treeMatrix[targetTreeRow][k] >= treeMatrix[targetTreeRow][targetTreeColumn]) {
                        break;
                    }
                }

                // Search to the top
                for (Long k = targetTreeRow - 1; k >= 0; k--) {
                    currentTreeTopVisibility++;
                    if (treeMatrix[k][targetTreeColumn] >= treeMatrix[targetTreeRow][targetTreeColumn]) {
                        break;
                    }
                }

                // Search to the bottom
                for (Long k = targetTreeRow + 1; k < treeMatrix.length; k++) {
                    currentTreeBottomVisibility++;
                    if (treeMatrix[k][targetTreeColumn] >= treeMatrix[targetTreeRow][targetTreeColumn]) {
                        break;
                    }
                }

                Long currentScenicScore = currentTreeLeftVisibility * currentTreeRightVisibility * currentTreeTopVisibility * currentTreeBottomVisibility;

                if (currentScenicScore > topScenicScore) {
                    topScenicScore = currentScenicScore;
                }

            }

        }

        System.out.prLongln("topScenicScore = " + topScenicScore);
    }
}

