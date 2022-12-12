package Day_11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws NumberFormatException, IOException {
        
        File stackFile = new File("Day_11/dataTest.txt");

        ArrayList<Monkey> monkeys = generateMonkeyList(stackFile);

        monkeys = processOneMonkey(monkeys, 20);

        ArrayList<Integer> inspectionCounts = new ArrayList<Integer>();

        for (Monkey monkey : monkeys) {
            inspectionCounts.add(monkey.getInspectionCount());
            System.out.println("Monkey " + monkey.getName() + ": " + monkey.getInspectionCount());
        }

        Collections.sort(inspectionCounts);

        int mostInspected = inspectionCounts.get(inspectionCounts.size() - 1);
        int secondMostInspected = inspectionCounts.get(inspectionCounts.size() - 2);
        int answer = mostInspected * secondMostInspected;
        System.out.println("Answer: " + answer);
        
    }

    private static ArrayList<Monkey> processOneMonkey(ArrayList<Monkey> monkeys, int rounds) {
        
        for (int k = 1; k <= rounds; k++) {

            for (Monkey monkey : monkeys) {
                ArrayList<Integer> items = monkey.getItems();
                String[] operation = monkey.getOperation();
                int testDenominator = monkey.getTestDenominator();
                int ifTrue = monkey.getIfTrue();
                int ifFalse = monkey.getIfFalse();
                int inspectionCount = monkey.getInspectionCount();
                int itemCounter = 0;

                monkey.setInspectionCount(inspectionCount + items.size());


                // for each item in items process with operation

                for (Integer item : items) {

                    switch (operation[1]) {
                        case "+":
                            item += Integer.parseInt(operation[2]);
                            break;
                        case "-":
                            item -= Integer.parseInt(operation[2]);
                            break;
                        case "*":
                            if ((operation[2]).equals("old")) {
                                item *= item;
                            } 
                            else {
                                item *= Integer.parseInt(operation[2]);
                            }
                            break;
                        case "/":
                            item /= Integer.parseInt(operation[2]);
                            break;
                        default:
                            break;
                    }

                    item /= 3;

                    items.set(itemCounter, item);

                    itemCounter++;
                }

                monkey.setItems(items);

                for (int i = 0; i < items.size(); i++) {

                    int toMonkeyNumber;
                    
                    if (items.get(i) % testDenominator == 0) {
                        toMonkeyNumber = ifTrue;
                    } else {
                        toMonkeyNumber = ifFalse;
                    }

                    Monkey toMonkey = monkeys.get(toMonkeyNumber);
                    ArrayList<Integer> toMonkeyItems = toMonkey.getItems();
                    
                    // add item to monkeys.get(toMonkey).getItems()
                    toMonkeyItems.add(items.get(i));
                    toMonkey.setItems(toMonkeyItems);

                    // set monkey in monkeys
                    monkeys.set(toMonkeyNumber, toMonkey);

                }
                ArrayList<Integer> emptyItems = new ArrayList<Integer>();
                monkeys.get(monkey.getName()).setItems(emptyItems);
                monkeys.set(monkey.getName(), monkey);

            }
            System.out.println("********** Round " + k + " **********");
            for (Monkey monkey : monkeys) {
                System.out.print("Monkey: " + monkey.getName() + " ");
                for (Integer item : monkey.getItems()) {
                    System.out.print(item + " ");
                }
                System.out.println();
            }
        }

        return monkeys;

    }

    private static ArrayList<Monkey> generateMonkeyList(File stackFile) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(stackFile));

        String eachLine;
        int counter = 0;

        ArrayList<Monkey> monkeys = new ArrayList<Monkey>();

        while ((eachLine = br.readLine()) != null) {

            // checks if line has characters, sort of a null check
            char[] charArray = eachLine.toCharArray();
            if (charArray.length > 0) {

                eachLine = eachLine.replaceFirst("^\\s*", "");

                String[] splitLine = eachLine.split(" ");

                switch (splitLine[0]) {
                    case "Monkey":
                        monkeys.add(new Monkey(counter));
                        break;
                    case "Starting":
                        String[] startingItemsSplit = eachLine.split(" ");
                        ArrayList<Integer> startingItems = new ArrayList<Integer>();

                        for (int i = 2; i < startingItemsSplit.length; i++) {
                            String item = startingItemsSplit[i].replaceAll(",", "");
                            Integer newItem = Integer.parseInt(item);
                            startingItems.add(newItem);
                        }

                        monkeys.get(counter).setItems(startingItems);
                    break;
                    case "Operation:":
                        String[] operation = new String[3];
                        for (int i = 3; i < splitLine.length; i++) {
                            operation[i - 3] = splitLine[i];
                        }
                        monkeys.get(counter).setOperation(operation);
                        break;
                    case "Test:":
                        String testDenominator = splitLine[splitLine.length - 1];
                        monkeys.get(counter).setTestDenominator(Integer.parseInt(testDenominator));
                        break;
                    case "If":
                        if (splitLine[1].equals("true:")) {
                            monkeys.get(counter).setIfTrue(Integer.parseInt(splitLine[splitLine.length - 1]));
                        }
                        else if (splitLine[1].equals("false:")) {
                            monkeys.get(counter).setIfFalse(Integer.parseInt(splitLine[splitLine.length - 1]));
                            counter++;
                        }
                        break;
                    }
            }
        }
        return monkeys;
    }   

    
}
