package Day_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {

            File file = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_1\\data.txt");
     
            BufferedReader br = new BufferedReader(new FileReader(file));
     
            String eachLine;
            int runningValue = 0;
            int highestValue = 0;
            int secondHighest = 0;
            int thirdHighest = 0;

            while ((eachLine = br.readLine()) != null) {

                    // System.out.println(Integer.parseInt(eachLine));
                    if (eachLine.equals("")) {
                        if (runningValue > highestValue) {
                            thirdHighest = secondHighest;
                            secondHighest = highestValue;
                            highestValue = runningValue;
                        } else if ( runningValue > secondHighest) {
                            thirdHighest = secondHighest;
                            secondHighest = runningValue;
                        } else if (runningValue > thirdHighest) {
                            thirdHighest = runningValue;
                        }

                        runningValue = 0;
                    } else {
                        runningValue = runningValue + Integer.parseInt(eachLine);
                    }
            }

            if (runningValue > highestValue) {
                thirdHighest = secondHighest;
                secondHighest = highestValue;
                highestValue = runningValue;
            } else if ( runningValue > secondHighest) {
                thirdHighest = secondHighest;
                secondHighest = runningValue;
            } else if (runningValue > thirdHighest) {
                thirdHighest = runningValue;
            }
            System.out.println("last runningValue " + runningValue);
            System.out.println("thirdHighest " + thirdHighest);
            System.out.println("secondHighest " + secondHighest);
            System.out.println("highestValue " + highestValue);
            System.out.println("sum " + (thirdHighest + secondHighest + highestValue));
    }
}
