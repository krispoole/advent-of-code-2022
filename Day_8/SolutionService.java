package Day_8;

import java.util.ArrayList;

public class SolutionService {

    public static Directory findDirectoryByCurrentDirectoryString(String currentDirectoryString, String currentParentDirecString, ArrayList<Directory> directoryList) {
        for (Directory directory : directoryList) {
            if (directory.getDirectoryNameString().equals(currentDirectoryString)  && directory.getParentDirectoryString().equals(currentParentDirecString)) {
                return directory;
            }
        }
        return null;
    }

    public static ArrayList<Directory> updateDirectorySize(String currentDirectoryString, String currentParentDirecString, ArrayList<Directory> directoryList, Integer fileSize, Integer fileCounter) {

        // retrieves current directory
        Directory directory = SolutionService.findDirectoryByCurrentDirectoryString(currentDirectoryString, directoryList);
        directory = SolutionService.findDirectoryByCurrentDirectoryString(directory.getParentDirectoryString(), directoryList);

        for(int i = 0; i < fileCounter; i++) {

            directory.setDirectorySize(directory.getDirectorySize() + fileSize);
            directory = SolutionService.findDirectoryByCurrentDirectoryString(directory.getParentDirectoryString(), directoryList);

        }

        return directoryList;
    }
}
