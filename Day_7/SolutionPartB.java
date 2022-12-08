package Day_7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// answer: 1667443, 8998590

public class SolutionPartB {
    public static void main(String[] args) throws Exception {

        // for reading text fild converting each line to a string
        File stackFile = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_7\\data.txt");
        BufferedReader br = new BufferedReader(new FileReader(stackFile));
        String eachLine;

        Integer smallestSizeToMeetRequirement = 7870454;
        Integer smallestDirectorySize = 70000000;

        // create node root directory and current directory
        Node<Directory> rootDirectory = new Node<Directory>(new Directory("/"));
        Node<Directory> currentDirectory = rootDirectory;

        // iterate over each line in the text file
        while ((eachLine = br.readLine()) != null) {

            // determine if eachLine is a command
            if (eachLine.charAt(0) == '$') {

                String currentCommandString = eachLine.substring(2,4);

                if (currentCommandString.equals("cd")) {

                    // keeps track of current directory and addes new directory to directoryList as necessary
                    if (!eachLine.substring(5, eachLine.length()).equals("..")) {
   
                        // add new directory to directoryList
                        if (eachLine.substring(5, eachLine.length()).equals("/")) {
                            // Do Nothing
                        } 
                        else {
                            currentDirectory = currentDirectory.addChild(new Directory(eachLine.substring(5, eachLine.length())));
                            for (Node<Directory> child : currentDirectory.getChildren()) {
                                if (child.getRoot().getDirectoryNameString().equals(eachLine.substring(5, eachLine.length()))) {
                                    currentDirectory = child;
                                }
                            }
                        }

                    } 
                    else if (eachLine.substring(5, eachLine.length()).equals("..")) {  

                        // find all child directories that have filesize less than 100000
                        for (Node<Directory> child : currentDirectory.getChildren()) {
                            if (child.getRoot().getDirectorySize() >= smallestSizeToMeetRequirement) {
                                if (child.getRoot().getDirectorySize() < smallestDirectorySize) {
                                    smallestDirectorySize = child.getRoot().getDirectorySize();
                                }
                            }
                        }

                        currentDirectory = currentDirectory.getParent();
                    }

                }
            }
             else if (eachLine.charAt(0) != 'd') {
                // add file size to current directory
                Integer fileSize = Integer.parseInt(eachLine.substring(0, eachLine.indexOf(" ")));
                currentDirectory.getRoot().setDirectorySize(currentDirectory.getRoot().getDirectorySize() + fileSize);
                Node<Directory> temp = currentDirectory;

                // add file size to each file size in each parent directory
                while (temp.getParent() != null) {
                    temp = temp.getParent();
                    temp.getRoot().setDirectorySize(temp.getRoot().getDirectorySize() + fileSize);
                }

            }

        }
        Node<Directory>temp = currentDirectory;
        while (temp.getParent() != null) {
            temp = temp.getParent();
            if (temp.getRoot().getDirectorySize() >= smallestSizeToMeetRequirement) {
                if (temp.getRoot().getDirectorySize() < smallestDirectorySize) {
                    smallestDirectorySize = temp.getRoot().getDirectorySize();
                }
            } 
        }

        System.out.println("smallestDirectorySize: " + smallestDirectorySize);
    }
}
