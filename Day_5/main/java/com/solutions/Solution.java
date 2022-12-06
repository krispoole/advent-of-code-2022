package Day_5.main.java.com.solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import Day_5.main.java.com.entity.Stack;
import Day_5.main.java.com.service.StackService;

public class Solution {
    public static void main(String[] args) throws Exception {

        StackService stackService = new StackService();
        stackService.generateInitialStacks();

        File stackFile = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_5\\main\\java\\com\\data\\data.txt");

        BufferedReader br = new BufferedReader(new FileReader(stackFile));

        String eachLine;


        while ((eachLine = br.readLine()) != null) {
          

            String[] eachLineArray = eachLine.split(" ");

            Integer fromStack = Integer.parseInt(eachLineArray[3]);
            Integer toStack = Integer.parseInt(eachLineArray[5]);
            Integer howMany = Integer.parseInt(eachLineArray[1]);

            stackService.updateStringFIFO(fromStack, toStack, howMany);

        }
        
        System.out.println(stackService.getAnswer());
    }
}
