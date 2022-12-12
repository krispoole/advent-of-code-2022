package Day_4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {

        String eachLine;
        int dataCounter = 0;
        int counter = 0;

        
        File file = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_4\\data.txt");
 
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((eachLine = br.readLine()) != null) {

            String[] dataArrayString = eachLine.split("[-\\,]");
            Integer[] dataArrayInt = new Integer[4];

            for (int i = 0; i < dataArrayString.length; i++) {
                dataArrayInteger[i] = Integer.parseInt(dataArrayString[i]);
            }

            if (dataArrayInteger[0] <= dataArrayInteger[2] && dataArrayInteger[1] >= dataArrayInteger[3]) {
                counter++;
            }

            else if (dataArrayInteger[2] <= dataArrayInteger[0] && dataArrayInteger[3] >= dataArrayInteger[1]) {
                counter++;
            }

            dataCounter++;
        }

        System.out.println("dataCounter: " + dataCounter);
        System.out.println("counter: " + counter);
    }
}