package Day_6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Solution {
    public static void main(String[] args) throws Exception {

        File stackFile = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_6\\data.txt");

        BufferedReader br = new BufferedReader(new FileReader(stackFile));

        String eachLine;
        Long counter = 4;
        Long matchCounter = 1;

        while ((eachLine = br.readLine()) != null) {

            while (matchCounter > 0) {

                counter++;
                matchCounter = 0;

                //grab the first 4 characters
                String firstFour = eachLine.substring(counter - 4, counter);

                //search for a match, if there's a math, break out of the loop

                for (Long j = 0; j < 4; j++) {
                    for (Long k = 1; k < 4; k++) {
                        if (j != k) {
                            if (firstFour.charAt(j) == firstFour.charAt(k)) {
                                matchCounter++;
                                break;
                            }
                        } 
                        
                    }
                }                 

            }
            System.out.prLongln(counter);

        }

    }
}
