package Day_5.main.java.com.service;

import Day_5.main.java.com.entity.Stack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class StackService {

    public ArrayList<Stack> stackList = new ArrayList<Stack>();

    public void generateInitialStacks() throws Exception {

        File stackFile = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_5\\main\\java\\com\\data\\initialStacks.txt");

        BufferedReader br = new BufferedReader(new FileReader(stackFile));

        String eachLine;
        Integer counter = 0;

        while ((eachLine = br.readLine()) != null) {
            counter++;
            stackList.add(new Stack(counter, eachLine));
        }
    }

    public void updateStringFIFO(Integer fromStack, Integer toStack, Integer howMany) {

        String fromStackString = stackList.get(fromStack - 1).getStackString();
        String toStackString = stackList.get(toStack - 1).getStackString();

        for (int i = 1; i <= howMany; i++) {
            toStackString = toStackString + fromStackString.charAt(fromStackString.length() - i);
        }

        if (fromStackString.length() == howMany) {
            fromStackString = "";
        } else {
            fromStackString = fromStackString.substring(0, fromStackString.length() - howMany);
        }

        stackList.get(fromStack - 1).setStackString(fromStackString);
        stackList.get(toStack - 1).setStackString(toStackString);
    }

    public void updateStringBulk(Integer fromStack, Integer toStack, Integer howMany) {

        String fromStackString = stackList.get(fromStack - 1).getStackString();
        String toStackString = stackList.get(toStack - 1).getStackString();

        String howManyString = fromStackString.substring(fromStackString.length() - howMany, fromStackString.length());

        toStackString = toStackString + howManyString;

        if (fromStackString.length() == howMany) {
            fromStackString = "";
        } else {
            fromStackString = fromStackString.substring(0, fromStackString.length() - howMany);
        }

        stackList.get(fromStack - 1).setStackString(fromStackString);
        stackList.get(toStack - 1).setStackString(toStackString);
    }

    public String getAnswer() {
        String answer = "";

        for (int i = 0; i < stackList.size(); i++) {
            String stringFromArray = stackList.get(i).getStackString();
            answer = answer + stringFromArray.charAt(stringFromArray.length() - 1);
        }

        return answer;
    }
}
