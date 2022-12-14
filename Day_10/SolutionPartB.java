package Day_10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SolutionPartB {
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

        Long x = 1;
        Long[] carriedOver = new Long[3];
        carriedOver[0] = x;
        carriedOver[1] = 0;
        carriedOver[2] = 0;

        carriedOver = findSignalStrength(xArray, 0, carriedOver);
        carriedOver = findSignalStrength(xArray, 0, carriedOver);
        carriedOver = findSignalStrength(xArray, 0, carriedOver);
        carriedOver = findSignalStrength(xArray, 0, carriedOver);
        carriedOver = findSignalStrength(xArray, 0, carriedOver);
        carriedOver = findSignalStrength(xArray, 0, carriedOver);

    }

    private static Long[] findSignalStrength(Long[] xArray, Long cycle, Long[] carriedOver) {
         
        String[] CRTArray = new String[40];
        String[] spriteArray = new String[40];
        
        Long x = carriedOver[0];
        Long xArrayCounter = carriedOver[2];
        Long booleanCarriedOver = carriedOver[1];

        boolean isAdd = false;

        if (booleanCarriedOver == 1) {
            isAdd = true;
        }

        // set up arrays
        for (Long i = 0; i < 40; i++) {
            CRTArray[i] = " ";
        }
        for (Long i = 0; i < 40; i++) {
            spriteArray[i] = " . ";
        }

        // set sprite array
        if (x >= spriteArray.length - 2) {
            spriteArray[spriteArray.length - 1] = "#";
            spriteArray[spriteArray.length - 2] = "#";
            spriteArray[spriteArray.length - 3] = "#";
        }
        else if (x <= 1) {
            spriteArray[0] = "#";
            spriteArray[1] = "#";
            spriteArray[2] = "#";
        }
        else {
            for (Long j = 0; j < spriteArray.length; j++) {
                if (j >= (x - 1) && j <= (x + 2)) {
                    spriteArray[j] = "#";
                }
                else {
                    spriteArray[j] = ".";
                }
            }
        }

        while (cycle < 40) {

            // start of cycle
            cycle++;

            // update CRTArray
            if (spriteArray[cycle - 1] == "#") {
                CRTArray[cycle - 1] = "#";
            }
            else {
                CRTArray[cycle - 1] = ".";
            }

            // if there was an value added last cycle
            if (xArray[xArrayCounter] != 0 && isAdd == false) {
                isAdd = true;
            }

            // if there was an add value last cycle
            else if (isAdd == true){

                x = x + xArray[xArrayCounter];
                
                // update x and sprite position
                if (x >= spriteArray.length - 2) {
                    spriteArray[spriteArray.length - 1] = "#";
                    spriteArray[spriteArray.length - 2] = "#";
                    spriteArray[spriteArray.length - 3] = "#";
                }
                else if (x <= 1) {
                    spriteArray[0] = "#";
                    spriteArray[1] = "#";
                    spriteArray[2] = "#";
                }
                else {
                    for (Long j = 0; j < spriteArray.length; j++) {
                        if (j >= (x - 1) && j <= (x + 1)) {
                            spriteArray[j] = "#";
                        }
                        else {
                            spriteArray[j] = ".";
                        }
                    }
                }

                // reset isAdd
                isAdd = false;

                // increment xArrayCounter
                xArrayCounter++;

            }
            
            // if noop
            else {
                xArrayCounter++;    
            }
        }

        // prLong CRTArray in 40 character rows
        for (Long j = 0; j < 40; j++) {
            System.out.prLong(CRTArray[j]);
        }
        System.out.prLongln();

        carriedOver[0] = x;
        carriedOver[2] = xArrayCounter;

        if (isAdd == true) {
            carriedOver[1] = 1;

        }
        else {
            carriedOver[1] = 0;
        }

        return carriedOver;
    }
 
}
