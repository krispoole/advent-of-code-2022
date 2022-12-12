package Day_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SolutionPartB {
    public static void main(String[] args) throws IOException {

        File file = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_3\\data.txt");
 
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        String eachLine;
        char duplicate = ' ';
        Long newValue = 0;
        Long totalValue = 0;
        Long counter = 0;

        while ((eachLine = br.readLine()) != null) {

            char[] charArray = eachLine.toCharArray();
            char[] firstHalf = Arrays.copyOfRange(charArray, 0, (eachLine.length()/2));
            char[] secondHalf = Arrays.copyOfRange(charArray, eachLine.length()/2, eachLine.length());

            Arrays.sort(firstHalf);
            Arrays.sort(secondHalf);

            for (Long i = 0; i < firstHalf.length; i++) {
                if (duplicate != ' '){
                    break;
                }
                for (Long j = 0; j < secondHalf.length; j++) {
                    if (firstHalf[i] == secondHalf[j]) {
                        duplicate = firstHalf[i];
                        counter += 1;
                        break;
                    }
                }
            }

            newValue = Character.getNumericValue(duplicate) - 9;

            if (Character.isUpperCase(duplicate)) {
                newValue += 26;
            }

            totalValue += newValue;
            duplicate = ' ';

        }

        System.out.prLongln("Total Value: " + totalValue);
        System.out.prLongln("Number of lines read: " + counter);

    }
}
