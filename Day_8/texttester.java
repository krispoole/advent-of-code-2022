package Day_8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class texttester {
    
    public static void main(String[] args) throws Exception {
    
        File stackFile = new File("C:\\Users\\krisp\\Code\\advent-of-code-2022\\Day_8\\data.txt");
        BufferedReader br = new BufferedReader(new FileReader(stackFile));
        String eachLine;
        ArrayList<Directory> directoryList = new ArrayList<Directory>();

        boolean listChecker = true;

        while ((eachLine = br.readLine()) != null) {

            if (eachLine.substring(0,4).equals("$ cd")) {
                String directoryNameString = eachLine.substring(5, eachLine.length());
                for (Directory directory : directoryList){
                    if (directory.getDirectoryNameString().equals(directoryNameString)) {
                        directory.setDirectorySize(directory.getDirectorySize() + 1);
                        listChecker = false;
                    }
                }
                if (listChecker) {
                    directoryList.add(new Directory(directoryNameString));
                }
                
            }

        }

        for (Directory directory : directoryList) {
            System.out.println("directoryName: " + directory.getDirectoryNameString() + " directorySize: " + directory.getDirectorySize());
        }
    }
}
