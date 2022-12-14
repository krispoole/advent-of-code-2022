package Day_14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Day_14 {

    public static void main(String[] args) throws NumberFormatException, IOException {

        ArrayList<List<int[]>> eachLineCoordinateList = new ArrayList<List<int[]>>();

        eachLineCoordinateList = getEachLineCoordinateList();

        int[] coordinateArray = getLowestHorizontalCoordinate(eachLineCoordinateList);

        eachLineCoordinateList = updateHorizontalValues(eachLineCoordinateList, coordinateArray[0]);

        String[][] rockGrid = generateGrid(eachLineCoordinateList, coordinateArray);

        rockGrid = addRocksToGrid(eachLineCoordinateList, rockGrid);

        int sandCount = getSandCountToAbyss(rockGrid, coordinateArray);

        System.out.println("Part 1 sandCount: " + sandCount);
   
    }

    private static int getSandCountToAbyss(String[][] rockGrid, int[] coordinateArray) {
        // location that sand is coming from
        int sandDropCoordinate = 500 - coordinateArray[0];

        // drop first piece of sand
        int sandCount = dropSand(rockGrid, coordinateArray, sandDropCoordinate);

        return sandCount;

    }

    private static int dropSand(String[][] rockGrid, int[] coordinateArray, int sandDropCoordinate) {
        
        int horizontalCoordinate = sandDropCoordinate;
        int verticalCoordinate = 0;
        int lastVerticalRow = coordinateArray[2];
        int horizontalLength = rockGrid[0].length;
        int counter = 0;
        String[][] rockGridCopy = new String[rockGrid.length][rockGrid[0].length];


        // change to while later
        while (verticalCoordinate < lastVerticalRow  && horizontalCoordinate < horizontalLength && horizontalCoordinate > 0) {

            rockGrid[verticalCoordinate][horizontalCoordinate] = "o";

            verticalCoordinate = 0;
            horizontalCoordinate = sandDropCoordinate;

            // if left or right is "."
            try {
                while (rockGrid[verticalCoordinate + 1][horizontalCoordinate + 1] == "." || rockGrid[verticalCoordinate + 1][horizontalCoordinate - 1] == "." || rockGrid[verticalCoordinate + 1][horizontalCoordinate] == ".") {
                    if (rockGrid[verticalCoordinate + 1][horizontalCoordinate] == ".") {
                        verticalCoordinate++;
                    }
                    else if (rockGrid[verticalCoordinate + 1][horizontalCoordinate - 1] == ".") {
                        // move left
                        horizontalCoordinate--;
                        verticalCoordinate++;
                    } 
                    else if (rockGrid[verticalCoordinate + 1][horizontalCoordinate + 1] == ".") {
                        // move right
                        horizontalCoordinate++;
                        verticalCoordinate++;
                    }
                }
                counter++;
            } 
                catch (ArrayIndexOutOfBoundsException e) {
            }
        }

        // printGrid(rockGrid);

        rockGrid = rockGridCopy;

        return counter;
    }

    private static String[][] addRocksToGrid(ArrayList<List<int[]>> eachLineCoordinateList, String[][] rockGrid) {
        
        for (int i = 0; i < eachLineCoordinateList.size(); i++) {
            for (int j = 1; j < eachLineCoordinateList.get(i).size(); j++) {
                int[] currentCoordinate = eachLineCoordinateList.get(i).get(j-1);
                int[] nextCoordinate = eachLineCoordinateList.get(i).get(j);

                // if same vertical coordinate
                if (currentCoordinate[1] == nextCoordinate[1]) {
                    if (currentCoordinate[0] < nextCoordinate[0]) {
                        for (int k = currentCoordinate[0]; k <= nextCoordinate[0]; k++) {
                            rockGrid[currentCoordinate[1]][k] = "#";
                        }
                    } else {
                        for (int k = nextCoordinate[0]; k <= currentCoordinate[0]; k++) {
                            rockGrid[currentCoordinate[1]][k] = "#";
                        }
                    }
                }
                // if same horizontal coordinate
                if (currentCoordinate[0] == nextCoordinate[0]) {
                    if (currentCoordinate[1] < nextCoordinate[1]) {
                        for (int k = currentCoordinate[1]; k <= nextCoordinate[1]; k++) {
                            rockGrid[k][currentCoordinate[0]] = "#";
                        }
                    } else {
                        for (int k = nextCoordinate[1]; k <= currentCoordinate[1]; k++) {
                            rockGrid[k][currentCoordinate[0]] = "#";
                        }
                    }
                }
            }
        }
        
        return rockGrid;
    }

    private static ArrayList<List<int[]>> updateHorizontalValues(ArrayList<List<int[]>> eachLineCoordinateList, int i) {

        for (int j = 0; j < eachLineCoordinateList.size(); j++) {
            for (int k = 0; k < eachLineCoordinateList.get(j).size(); k++) {
                eachLineCoordinateList.get(j).get(k)[0] = eachLineCoordinateList.get(j).get(k)[0] - i;
            }
        }

        return eachLineCoordinateList;
    }

    private static int[] getLowestHorizontalCoordinate(ArrayList<List<int[]>> eachLineCoordinateList) {

        int highestHorizontalCoordinate = eachLineCoordinateList.get(0).get(0)[0];
        int lowestHorizontalCoordinate = eachLineCoordinateList.get(0).get(0)[0];
        int highestVerticalCoordinate = eachLineCoordinateList.get(0).get(0)[1];

        for (int i = 0; i < eachLineCoordinateList.size(); i++) {
            for (int j = 0; j < eachLineCoordinateList.get(i).size(); j++) {
                if (eachLineCoordinateList.get(i).get(j)[0] > highestHorizontalCoordinate) {
                    highestHorizontalCoordinate = eachLineCoordinateList.get(i).get(j)[0];
                }
                if (eachLineCoordinateList.get(i).get(j)[0] < lowestHorizontalCoordinate) {
                    lowestHorizontalCoordinate = eachLineCoordinateList.get(i).get(j)[0];
                }
                if (eachLineCoordinateList.get(i).get(j)[1] > highestVerticalCoordinate) {
                    highestVerticalCoordinate = eachLineCoordinateList.get(i).get(j)[1];
                }

            }
        }

        int[] coordinateArray = {lowestHorizontalCoordinate, highestHorizontalCoordinate, highestVerticalCoordinate};
        return coordinateArray;
    }

    private static void printGrid(String[][] rockGrid) {
        for (int i = 0; i < rockGrid.length; i++) {
            for (int j = 0; j < rockGrid[i].length; j++) {
                System.out.print(rockGrid[i][j]);
            }
            System.out.println();
        }
    }

    private static String[][] generateGrid(ArrayList<List<int[]>> eachLineCoordinateList, int[] coordinateArray) {
        int lowestHorizontalCoordinate = coordinateArray[0];
        int highestHorizontalCoordinate = coordinateArray[1];
        int highestVerticalCoordinate = coordinateArray[2];

        String[][] rockGrid = new String[highestVerticalCoordinate + 1][highestHorizontalCoordinate - lowestHorizontalCoordinate + 1];

        for (int i = 0; i < rockGrid.length; i++) {
            for (int j = 0; j < rockGrid[i].length; j++) {
                rockGrid[i][j] = ".";
            }
        }

        return rockGrid;
    }

    private static ArrayList<List<int[]>> getEachLineCoordinateList() throws NumberFormatException, IOException {

        ArrayList<List<int[]>> eachLineCoordinateList = new ArrayList<List<int[]>>();
        
        File stackFile = new File("Day_14/data.txt");
        BufferedReader br = new BufferedReader(new FileReader(stackFile));

        String eachLine;

        while ((eachLine = br.readLine()) != null) {
            List<int[]> coordinatesList = new ArrayList<int[]>();
            String[] coordinateStringArray = eachLine.split(" -> ");

            for (int i = 0; i < coordinateStringArray.length; i++) {
                int firstCoordinate = Integer.parseInt(coordinateStringArray[i].split(",")[0]);
                int secondCoordinate = Integer.parseInt(coordinateStringArray[i].split(",")[1]);
                int[] coordinate = {firstCoordinate, secondCoordinate};
                coordinatesList.add(coordinate);
            }
            eachLineCoordinateList.add(coordinatesList);
        }

        br.close();
        return eachLineCoordinateList;
    }

}