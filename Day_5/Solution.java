package Day_5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Solution {

    public static void main(String[] args) throws IOException {

        char[] stack1 = {'Z','P','M','H','R'};
        char[] stack2 = {'P','C','J','B'};
        char[] stack3 = {'S','N','H','G','L','C','D'};
        char[] stack4 = {'F','T','M','D','Q','S','R','L'};
        char[] stack5 = {'F','S','P','Q','B','T','Z','M'};
        char[] stack6 = {'T','F','S','Z','B','G'};
        char[] stack7 = {'N','R','V'};
        char[] stack8 = {'P','G','L','T','D','V','C','M'};
        char[] stack9 = {'W','Q','N','J','F','M','L'};

        String eachLine;

        File file = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_5\\data.txt");
 
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((eachLine = br.readLine()) != null) {

            String[] splitData = eachLine.split(" ");

            int moveAmount = Integer.parseInt(splitData[1]);
            int fromStackNumber = Integer.parseInt(splitData[3]);
            int toStackNumber = Integer.parseInt(splitData[5]);

            char[] toStackArray = 
                switch (toStackNumber) {
                    case 1 -> stack1;
                    case 2 -> stack2;
                    case 3 -> stack3;
                    case 4 -> stack4;
                    case 5 -> stack5;
                    case 6 -> stack6;
                    case 7 -> stack7;
                    case 8 -> stack8;
                    case 9 -> stack9;
                    default -> throw new IllegalStateException("Unexpected value: " + toStackNumber);
                };

            char[] fromStackArray = 
                switch (fromStackNumber) {
                    case 1 -> stack1;
                    case 2 -> stack2;
                    case 3 -> stack3;
                    case 4 -> stack4;
                    case 5 -> stack5;
                    case 6 -> stack6;
                    case 7 -> stack7;
                    case 8 -> stack8;
                    case 9 -> stack9;
                    default -> throw new IllegalStateException("Unexpected value: " + fromStackNumber);
                };

            char[] tempToStackArray = new char[toStackArray.length + moveAmount];
            char[] tempFromStackArray = new char[fromStackArray.length - moveAmount];

            System.arraycopy(fromStackArray, 0, tempFromStackArray, 0, tempFromStackArray.length);
            System.arraycopy(toStackArray, 0, tempToStackArray, 0, toStackArray.length);

            for (int i = 0; i < moveAmount; i++) {
                tempToStackArray[tempToStackArray.length - moveAmount + i] = fromStackArray[fromStackArray.length - 1 - i];
            }

            toStackArray = tempToStackArray;
            fromStackArray = tempFromStackArray;

            switch (toStackNumber) {
                case 1 -> stack1 = toStackArray;
                case 2 -> stack2 = toStackArray;
                case 3 -> stack3 = toStackArray;
                case 4 -> stack4 = toStackArray;
                case 5 -> stack5 = toStackArray;
                case 6 -> stack6 = toStackArray;
                case 7 -> stack7 = toStackArray;
                case 8 -> stack8 = toStackArray;
                case 9 -> stack9 = toStackArray;
                default -> throw new IllegalStateException("Unexpected value: " + toStackNumber);
            }

            switch (fromStackNumber) {
                case 1 -> stack1 = fromStackArray;
                case 2 -> stack2 = fromStackArray;
                case 3 -> stack3 = fromStackArray;
                case 4 -> stack4 = fromStackArray;
                case 5 -> stack5 = fromStackArray;
                case 6 -> stack6 = fromStackArray;
                case 7 -> stack7 = fromStackArray;
                case 8 -> stack8 = fromStackArray;
                case 9 -> stack9 = fromStackArray;
                default -> throw new IllegalStateException("Unexpected value: " + fromStackNumber);
            }
        }

        String answer = "";

        for (int i = 0; i < 9; i++) {
            answer += switch (i) {
                case 0 -> stack1[stack1.length - 1];
                case 1 -> stack2[stack2.length - 1];
                case 2 -> stack3[stack3.length - 1];
                case 3 -> stack4[stack4.length - 1];
                case 4 -> stack5[stack5.length - 1];
                case 5 -> stack6[stack6.length - 1];
                case 6 -> stack7[stack7.length - 1];
                case 7 -> stack8[stack8.length - 1];
                case 8 -> stack9[stack9.length - 1];
                default -> throw new IllegalStateException("Unexpected value: " + i);
            };
        }

        System.out.println(answer);
    }
}
