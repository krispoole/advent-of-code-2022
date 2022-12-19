package Day_18;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SolutionPart2 {
    
    public static void main(String[] args) throws IOException {
        
        File stackFile = new File("Day_18/data.txt");
        
        ArrayList<int[]> cubeList = generateCubeList(stackFile);
        
        // printCubeList(cubeList);
        for (int[] cube : cubeList) {
            for (int i = 0; i < cube.length; i++) {
                System.out.print(cube[i] + " ");
            }
            System.out.println();
        }

        // add up touching sides

        int totalSides = cubeList.size() * 6;

        int touchingSides = getTouchingSides(cubeList);

        int answer = totalSides - touchingSides;

        System.out.println("Answer: " + answer);

    }

    private static int getTouchingSides(ArrayList<int[]> cubeList) {
        int touchCount = 0;

        for (int i = 0; i < cubeList.size(); i++) {
            for (int j = 0; j < cubeList.size(); j++) {
                if (cubeList.get(i)[0] == cubeList.get(j)[0] && cubeList.get(i)[1] == cubeList.get(j)[1] && cubeList.get(i)[2] == cubeList.get(j)[2]) {
                    // do nothing
                } else {
                    // check if touching
                    if (cubeList.get(i)[0] == cubeList.get(j)[0] && cubeList.get(i)[1] == cubeList.get(j)[1] && Math.abs(cubeList.get(i)[2] - cubeList.get(j)[2]) == 1) {
                        // check if touching
                        touchCount++;
                    } else if (cubeList.get(i)[0] == cubeList.get(j)[0] && cubeList.get(i)[2] == cubeList.get(j)[2] && Math.abs(cubeList.get(i)[1] - cubeList.get(j)[1]) == 1) {
                        // check if touching
                        touchCount++;
                    } else if (cubeList.get(i)[1] == cubeList.get(j)[1] && cubeList.get(i)[2] == cubeList.get(j)[2] && Math.abs(cubeList.get(i)[0] - cubeList.get(j)[0]) == 1) {
                        // check if touching
                        touchCount++;
                    }
                }
            }
        }

        return touchCount;
    }

    private static ArrayList<int[]> generateCubeList(File stackFile) throws IOException {
        
        ArrayList<int[]> cubeList = new ArrayList<>();
        
        BufferedReader br = new BufferedReader(new FileReader(stackFile));

        String eachLine;

        while ((eachLine = br.readLine()) != null) {
            String[] splitLine = eachLine.split(",");
            int[] cube = new int[splitLine.length];
            for (int i = 0; i < splitLine.length; i++) {
                cube[i] = Integer.parseInt(splitLine[i]);
            }
            cubeList.add(cube);
        }

        return cubeList;
    }
}
