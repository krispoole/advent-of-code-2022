package Day_4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {

        String eachLine;
        Long dataCounter = 0;
        Long counter = 0;

        
        File file = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_4\\data.txt");
 
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((eachLine = br.readLine()) != null) {

            String[] dataArrayString = eachLine.split("[-\\,]");
            Long[] dataArrayLong = new Long[4];

            for (Long i = 0; i < dataArrayString.length; i++) {
                dataArrayLong[i] = Long.parseLong(dataArrayString[i]);
            }

            if (dataArrayLong[0] <= dataArrayLong[2] && dataArrayLong[1] >= dataArrayLong[3]) {
                counter++;
            }

            else if (dataArrayLong[2] <= dataArrayLong[0] && dataArrayLong[3] >= dataArrayLong[1]) {
                counter++;
            }

            dataCounter++;
        }

        System.out.prLongln("dataCounter: " + dataCounter);
        System.out.prLongln("counter: " + counter);
    }
}