package Day_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {

        File file = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_2\\data.txt");
 
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        String eachLine;
        int score = 0;

        while ((eachLine = br.readLine()) != null) {

            if (eachLine.charAt(2) == 'X') {

                score += 1;

                if (eachLine.charAt(0) == 'A') {
                    score += 3;
                }

                if (eachLine.charAt(0) == 'C') {
                    score += 6;
                }
            }

            else if (eachLine.charAt(2) == 'Y') {

                score += 2;

                if (eachLine.charAt(0) == 'A') {
                    score += 6;
                }

                if (eachLine.charAt(0) == 'B') {
                    score += 3;
                }
            }

            else {
                score += 3;

                if (eachLine.charAt(0) == 'B') {
                    score += 6;
                }

                if (eachLine.charAt(0) == 'C') {
                    score += 3;
                }
            }

        }

        System.out.println(score);
    }
}
