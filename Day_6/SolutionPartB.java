package Day_6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SolutionPartB {
    public static void main(String[] args) throws Exception {

        File stackFile = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_6\\data.txt");

        BufferedReader br = new BufferedReader(new FileReader(stackFile));

        String eachLine;
        Integer counter = 14;
        Integer matchCounter = 1;

        while ((eachLine = br.readLine()) != null) {

            while (matchCounter > 0) {

                counter++;
                matchCounter = 0;

                //grab the first 4 characters
                String firstFourteenString = eachLine.substring(counter - 14, counter);

                //search for a match, if there's a math, break out of the loop

                for (int j = 0; j < 14; j++) {
                    for (int k = 1; k < 14; k++) {
                        if (j != k) {
                            if (firstFourteenString.charAt(j) == firstFourteenString.charAt(k)) {
                                matchCounter++;
                                break;
                            }
                        } 
                        
                    }
                }                 

            }
            System.out.println(counter);

        }

    }
}

