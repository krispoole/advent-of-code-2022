package Day_8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {

        File stackFile = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_8\\data.txt");
        BufferedReader br = new BufferedReader(new FileReader(stackFile));

        String eachLine;
        String currentDirectoryString = "/";
        String currentParentString = "none";
        Integer fileCounter = 0;
        ArrayList<Directory> directoryList = new ArrayList<Directory>();

        directoryList.add(new Directory(currentDirectoryString));
        Directory directory = SolutionService.findDirectoryByCurrentDirectoryString(currentDirectoryString, currentParentString, directoryList);
        directory.setParentDirectoryString(currentParentString);

        while ((eachLine = br.readLine()) != null) {
            
            // all I care about is cd
            if (eachLine.substring(0, 4).equals("$ cd")) {
                
                if (eachLine.substring(5, eachLine.length()).equals("..")) {
                    fileCounter--;
                    directory = SolutionService.findDirectoryByCurrentDirectoryString(currentDirectoryString, currentParentString, directoryList);
                    currentDirectoryString = directory.getParentDirectoryString();
                    currentParentString = directory.getParentDirectoryString();
                    
                } else {

                    fileCounter++;
                    directoryList.add(new Directory(eachLine.substring(5, eachLine.length())));
                    currentParentString = currentDirectoryString;
                    directory = SolutionService.findDirectoryByCurrentDirectoryString(eachLine.substring(5, eachLine.length()), currentParentString, directoryList);
                    directory.setParentDirectoryString(currentDirectoryString);
                    currentDirectoryString = eachLine.substring(5, eachLine.length());

                }

            } else if (eachLine.charAt(0) != 'd' && eachLine.charAt(0) != '$') {

                Integer fileSize = Integer.parseInt(eachLine.split(" ")[0]);
                directory = SolutionService.findDirectoryByCurrentDirectoryString(currentDirectoryString, directoryList);
                directory.setDirectorySize(directory.getDirectorySize() + fileSize);

                directoryList = SolutionService.updateDirectorySize(currentDirectoryString, directoryList, fileSize, fileCounter);

            }

        }

        br.close();

        Integer totalSizeLessThanInteger = 0;

        for (Directory directory1 : directoryList) {
            if (directory1.getDirectorySize() < 100000) {
                totalSizeLessThanInteger = totalSizeLessThanInteger + directory1.getDirectorySize();
            }
        }

        System.out.println("Total directories with size less than 100000: " + totalSizeLessThanInteger);

    }
}
