package Day_10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Solution {
    public static void main(String[] args) throws NumberFormatException, IOException {
        
    
        File stackFile = new File("Day_10/data.txt");
        BufferedReader br = new BufferedReader(new FileReader(stackFile));

        String eachLine;
        Long counter = 0;

        while ((eachLine = br.readLine()) != null) {
            counter++;
        }

        Long[] xArray = new Long[counter];

        stackFile = new File("Day_10/data.txt");
        br = new BufferedReader(new FileReader(stackFile));

        counter = 0;

        while ((eachLine = br.readLine()) != null) {

            String[] split = eachLine.split(" ");

            if (split[0].equals("noop")){
                xArray[counter] = 0;
            }
            else {
                xArray[counter] = Long.parseLong(split[1]);
            }

            counter++;

        }

        Long[] signalStrength = new Long[6];

        signalStrength[0] = findSignalStrength(xArray, 20);
        signalStrength[1] = findSignalStrength(xArray, 60);
        signalStrength[2] = findSignalStrength(xArray, 100);
        signalStrength[3] = findSignalStrength(xArray, 140);
        signalStrength[4] = findSignalStrength(xArray, 180);
        signalStrength[5] = findSignalStrength(xArray, 220);

        Long sum = signalStrength[0] + signalStrength[1] + signalStrength[2] + signalStrength[3] + signalStrength[4] + signalStrength[5];
        
        System.out.prLongln("Part 1: " + sum);
    }

    private static Long findSignalStrength(Long[] xArray, Long i) {
       Long cycle = 0;
       Long x = 1;
       Long cyclesNeeded = i;
       Long noopCounter = 0;
       Long addCounter = 0;

       Long j = 0;
       while (cycle < cyclesNeeded) {

            if (xArray[j] == 0) {
                noopCounter++;
            }
            else {
                addCounter++;
                cycle++;
            }

            if (cycle >= cyclesNeeded) {
                break;
            }
            j++;
            
            cycle++;

       }
    
       // second to last = noop
       if (xArray[xArray.length - 2] == 0) {
            addCounter--;
       }
       // second to last = add
       else {
            noopCounter--;
       }

       Long linesToAdd = noopCounter + addCounter;

       for (Long k = 0; k < linesToAdd; k++) {
           if (xArray[k] != 0) {
                x = x + xArray[k];
           }
       }
       Long signalStrength = x * i;
       return signalStrength;
    }
 
}
