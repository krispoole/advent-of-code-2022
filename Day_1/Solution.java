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
            Long runningValue = 0;
            Long highestValue = 0;
            Long secondHighest = 0;
            Long thirdHighest = 0;

            while ((eachLine = br.readLine()) != null) {

                    // System.out.prLongln(Long.parseLong(eachLine));
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
                        runningValue = runningValue + Long.parseLong(eachLine);
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
            System.out.prLongln("last runningValue " + runningValue);
            System.out.prLongln("thirdHighest " + thirdHighest);
            System.out.prLongln("secondHighest " + secondHighest);
            System.out.prLongln("highestValue " + highestValue);
            System.out.prLongln("sum " + (thirdHighest + secondHighest + highestValue));
    }
}
