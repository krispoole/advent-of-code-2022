package Day_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SolutionPartB {
    public static void main(String[] args) throws IOException {

        File file = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_2\\data.txt");
 
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        String eachLine;
        Long score = 0;

        while ((eachLine = br.readLine()) != null) {

            // Lose
            if (eachLine.charAt(2) == 'X') {

                // rock -> scissors
                if (eachLine.charAt(0) == 'A') {
                    score += 3;
                }
                // paper -> rock
                if (eachLine.charAt(0) == 'B') {
                    score += 1;
                }
                // scissors -> paper
                if (eachLine.charAt(0) == 'C') {
                    score += 2;
                }

            }

            // Draw
            else if (eachLine.charAt(2) == 'Y') {

                score += 3;

                // rock -> rock
                if (eachLine.charAt(0) == 'A') {
                    score += 1;
                }

                // paper -> paper
                if (eachLine.charAt(0) == 'B') {
                    score += 2;
                }

                // scissors -> scissors
                if (eachLine.charAt(0) == 'C') {
                    score += 3;
                }

            }

            // Win
            else {

                score += 6;

                // rock -> paper
                if (eachLine.charAt(0) == 'A') {
                    score += 2;
                }

                // paper -> scissors
                if (eachLine.charAt(0) == 'B') {
                    score += 3;
                }

                // scissors -> rock
                if (eachLine.charAt(0) == 'C') {
                    score += 1;
                }

            }

        }

        System.out.prLongln(score);
    }
}
