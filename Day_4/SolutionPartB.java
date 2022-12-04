package Day_4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SolutionPartB {
    public static void main(String[] args) throws IOException {

        String eachLine;
        int dataCounter = 0;
        int counter = 0;

        
        File file = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_4\\data.txt");
 
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((eachLine = br.readLine()) != null) {

            String[] dataArrayString = eachLine.split("[-\\,]");
            int[] dataArrayInt = new int[4];

            for (int i = 0; i < dataArrayString.length; i++) {
                dataArrayInt[i] = Integer.parseInt(dataArrayString[i]);
            }

            if (dataArrayInt[0] > dataArrayInt[2]) {

                int[] tempArray = new int[2];

                tempArray[0] = dataArrayInt[2];
                tempArray[1] = dataArrayInt[3];
                dataArrayInt[2] = dataArrayInt[0];
                dataArrayInt[3] = dataArrayInt[1];
                dataArrayInt[0] = tempArray[0];
                dataArrayInt[1] = tempArray[1];
                
            }

            if (dataArrayInt[2] <= dataArrayInt[1]) {
                counter++;
            }

            dataCounter++;
        }

        System.out.println("dataCounter: " + dataCounter);
        System.out.println("counter: " + counter);
    }
}