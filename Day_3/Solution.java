package Day_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException {

        File file = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_3\\data.txt");
 
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        String eachLine;
        char duplicate = ' ';
        String string1 = "";
        String string2 = "";
        String string3 = "";
        Long newValue = 0;
        Long totalValue = 0;
        Long arrayCounter = 0;
        Long counter = 0;

        while ((eachLine = br.readLine()) != null) {

            if (arrayCounter == 0) {
                string1 = eachLine;
            }

            if (arrayCounter == 1) {
                string2 = eachLine;
            }

            if (arrayCounter == 2) {

                string3 = eachLine;

                System.out.prLongln(string1);
                System.out.prLongln(string2);
                System.out.prLongln(string3);

                char[] charArray1 = string1.toCharArray();
                char[] charArray2 = string2.toCharArray();
                char[] charArray3 = string3.toCharArray();

                Arrays.sort(charArray1);
                Arrays.sort(charArray2);
                Arrays.sort(charArray3);

                for (Long i = 0; i < charArray1.length; i++) {
                    if (duplicate != ' '){
                        break;
                    }
                    for (Long j = 0; j < charArray2.length; j++) {
                        if (duplicate != ' '){
                            break;
                        }
                        for (Long k = 0; k < charArray3.length; k++) {
                            if (charArray1[i] == charArray2[j] && charArray2[j] == charArray3[k]) {
                                duplicate = charArray1[i];
                                counter += 1;
                                break;
                            }
                        }
                    }
                }

                newValue = Character.getNumericValue(duplicate) - 9;

                if (Character.isUpperCase(duplicate)) {
                    newValue += 26;
                }
    
                totalValue += newValue;
                duplicate = ' ';
                arrayCounter = -1;

            }

            arrayCounter += 1;

        }

        System.out.prLongln("Total Value: " + totalValue);
        System.out.prLongln("Number of lines read: " + counter);

    }
}
