package Day_7;

import java.util.ArrayList;

public class SolutionService {

    public static boolean checkIfDirectoryExists(String directoryNameString, ArrayList<Directory> directoryList) {
        for (Directory directory : directoryList) {
            if (directory.getDirectoryNameString().equals(directoryNameString)) {
                return true;
            }
        }
        return false;
    }

    public static Directory findDirectoryByCurrentDirectoryString(String currentDirectoryString, ArrayList<Directory> directoryList) {
        for (Directory directory : directoryList) {
            if (directory.getDirectoryNameString().equals(currentDirectoryString)) {
                return directory;
            }
        }
        return null;
    }

    public static ArrayList<Directory> updateDirectorySize(Directory directory, Integer fileSize, ArrayList<Directory> directoryList) {
        
        Integer directorySize = directory.getDirectorySize() + fileSize;
        directory.setDirectorySize(directorySize);

        directory = findDirectoryByCurrentDirectoryString(directory.getParentDirectoryString(), directoryList);

        while (directory.getParentDirectoryString() != "none"  || directory.getParentDirectoryString() != null) {
            System.out.println("directoryNameString: " + directory.getDirectoryNameString() + " directorySize: " + directory.getDirectorySize());
            directorySize = directory.getDirectorySize() + fileSize;
            directory.setDirectorySize(directorySize);

            directory = findDirectoryByCurrentDirectoryString(directory.getParentDirectoryString(), directoryList);
        
        }

        return directoryList;
    }

}
