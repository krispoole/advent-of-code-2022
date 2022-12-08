package Day_7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

// answer: 1667443, 8998590

public class SolutionPartB {
    public static void main(String[] args) throws Exception {

        File stackFile = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_7\\data.txt");

        BufferedReader br = new BufferedReader(new FileReader(stackFile));

        String eachLine;
        String currentDirectoryString = "/";
        String parentDirectoryString = "none";

        int counter = 0;

        ArrayList<Directory> directoryList = new ArrayList<Directory>();

        Directory rootDirectory = new Directory("/");
        rootDirectory.setParentDirectoryString("none");

        while ((eachLine = br.readLine()) != null) {

            // determine if eachLine is a command
            if (eachLine.charAt(0) == '$') {
                counter++;
                System.out.println("counter: " + counter);

                String currentCommandString = eachLine.substring(2,4);

                if (currentCommandString.equals("cd")) {

                    // keeps track of current directory and addes new directory to directoryList as necessary
                    if (!eachLine.substring(5, eachLine.length()).equals("..")) {
                    
                        parentDirectoryString = currentDirectoryString;
                        currentDirectoryString = eachLine.substring(5, eachLine.length());

                        // add new directory to directoryList
                        directoryList.add(new Directory(currentDirectoryString));
                        
                        // add directoryParent to Directory with currentDirectory
                        Directory directory = SolutionService.findDirectoryByCurrentDirectoryString(currentDirectoryString, directoryList);
                        directory.setParentDirectoryString(parentDirectoryString);

                    } else if (eachLine.substring(5, eachLine.length()).equals("..")) {   
                        Directory directory = SolutionService.findDirectoryByCurrentDirectoryString(currentDirectoryString, directoryList);
                        currentDirectoryString = directory.getParentDirectoryString();

                    }

                }
            } else if (eachLine.charAt(0) != 'd') {

                System.out.println("eachLine: " + eachLine);

                Directory directory = SolutionService.findDirectoryByCurrentDirectoryString(currentDirectoryString, directoryList);
                Integer fileSize = Integer.parseInt(eachLine.split(" ")[0]);

                SolutionService.updateDirectorySize(directory, fileSize, directoryList);

            }

        }

        Integer totalSizeOfDirLessThan100000 = 0;

        for (Directory directory : directoryList) {
            if (directory.getDirectorySize() <= 100000) {
                totalSizeOfDirLessThan100000 = totalSizeOfDirLessThan100000 + directory.getDirectorySize();
            }
            System.out.println("directoryNameString: " + directory.getDirectoryNameString() + " directorySize: " + directory.getDirectorySize());
        }

        System.out.println("totalSizeOfDirLessThan100000: " + totalSizeOfDirLessThan100000);
    }
}
