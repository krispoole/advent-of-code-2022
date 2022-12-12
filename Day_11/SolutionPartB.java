package Day_11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class SolutionPartB {
    public static void main(String[] args) throws NumberFormatException, IOException {
        
        File stackFile = new File("Day_11/data.txt");

        ArrayList<Monkey> monkeys = generateMonkeyList(stackFile);

        Long lcm = (long) 1;
        for (Monkey monkey : monkeys) {
            lcm *= monkey.getTestDenominator();
        }

        monkeys = processOneMonkey(monkeys, 10000, lcm);

        ArrayList<Long> inspectionCounts = new ArrayList<Long>();

        for (Monkey monkey : monkeys) {
            inspectionCounts.add(monkey.getInspectionCount());
            System.out.println("Monkey " + monkey.getName() + ": " + monkey.getInspectionCount());
        }

        Collections.sort(inspectionCounts);

        Long mostInspected = inspectionCounts.get(inspectionCounts.size() - 1);
        Long secondMostInspected = inspectionCounts.get(inspectionCounts.size() - 2);
        Long answer = mostInspected * secondMostInspected;
        System.out.println("Answer: " + answer);
        
    }

    private static ArrayList<Monkey> processOneMonkey(ArrayList<Monkey> monkeys, int j, Long lcm) {
        
        for (int k = 1; k <= j; k++) {

            for (Monkey monkey : monkeys) {

                ArrayList<Long> items = monkey.getItems();
                String[] operation = monkey.getOperation();
                Long testDenominator = Long.valueOf(monkey.getTestDenominator());
                int ifTrue = monkey.getIfTrue();
                int ifFalse = monkey.getIfFalse();
                Long inspectionCount = monkey.getInspectionCount();
                int itemCounter = 0;

                
                monkey.setInspectionCount(inspectionCount + items.size());


                // for each item in items process with operation

                for (Long item : items) {

                    switch (operation[1]) {
                        case "+":
                            item += Long.parseLong(operation[2]);
                            break;
                        case "-":
                            item -= Long.parseLong(operation[2]);
                            break;
                        case "*":
                            if ((operation[2]).equals("old")) {
                                item *= item;
                            } 
                            else {
                                item *= Long.parseLong(operation[2]);
                            }
                            break;
                        case "/":
                            item /= Long.parseLong(operation[2]);
                            break;
                        default:
                            break;
                    }

                    Long tester = item % lcm;
                    
                    if (tester != 0) {
                        item = tester;
                    }

                    items.set(itemCounter, item);

                    itemCounter++;
                }

                monkey.setItems(items);
                items = monkey.getItems();

                for (int i = 0; i < items.size(); i++) {

                    int toMonkeyNumber;
                    Long item = items.get(i);

                    if (item % testDenominator == 0) {
                        toMonkeyNumber = ifTrue;
                    } else {
                        toMonkeyNumber = ifFalse;
                    }

                    Monkey toMonkey = monkeys.get(toMonkeyNumber);
                    ArrayList<Long> toMonkeyItems = toMonkey.getItems();

                    // add item to monkeys.get(toMonkey).getItems()
                    toMonkeyItems.add(items.get(i));
                    toMonkey.setItems(toMonkeyItems);

                    // set monkey in monkeys
                    monkeys.set(toMonkeyNumber, toMonkey);

                }
                ArrayList<Long> emptyItems = new ArrayList<Long>();
                monkeys.get(monkey.getName()).setItems(emptyItems);
                monkeys.set(monkey.getName(), monkey);

            }
            // System.out.prLongln("********** Round " + k + " **********");
            // for (Monkey monkey : monkeys) {
            //     System.out.prLong("Monkey: " + monkey.getName() + " ");
            //     for (Long item : monkey.getItems()) {
            //         System.out.prLong(item + " ");
            //     }
            //     System.out.prLongln();
            // }
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
                        monkeys.get(counter).setInspectionCount(Long.valueOf(0));
                        break;
                    case "Starting":
                        String[] startingItemsSplit = eachLine.split(" ");
                        ArrayList<Long> startingItems = new ArrayList<Long>();

                        for (int i = 2; i < startingItemsSplit.length; i++) {
                            String item = startingItemsSplit[i].replaceAll(",", "");
                            Long newItem = Long.valueOf(item);
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
                        monkeys.get(counter).setTestDenominator(Long.parseLong(testDenominator));
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
