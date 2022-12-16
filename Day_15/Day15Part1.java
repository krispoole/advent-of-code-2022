package Day_15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day15Part1 {

    public static void main(String[] args) throws NumberFormatException, IOException {

        File stackFile = new File("Day_15/data.txt");

        int row = 2000000;
        // int row = 10;

        ArrayList<int[]> coordinateList = getEachLineCoordinateList(stackFile);

        int answer = addSpotsWhereBeaconCannotBe(coordinateList, row);

        System.out.println("Answer: " + answer);    

    }

    private static ArrayList<int[]> removeCoordinatesThatAreNotNeeded(ArrayList<int[]> coordinateList, int row) {
        // iterate coordinate list and remove coordinates that are not needed

        for (int i = 0; i < coordinateList.size(); i++) {

            int x1 = coordinateList.get(i)[1];
            int x2 = coordinateList.get(i)[3];
            int y1 = coordinateList.get(i)[0];
            int y2 = coordinateList.get(i)[2];

            // if y1 and y2 are greater than row, remove
            if (y1 > row && y2 > row && Math.abs(x2 - x1) > Math.abs(y1 - row)) {
                coordinateList.remove(i);
            }

            // if y1 and y2 are less than row, remove
            if (y1 < row && y2 < row && Math.abs(x2 - x1) > Math.abs(y1 + row)) {
                coordinateList.remove(i);
            }

        }

        return coordinateList;
    }

    private static int addSpotsWhereBeaconCannotBe(ArrayList<int[]> coordinateList, int row) {

        int xMin = 2793338;
        int xMax = 2793338;

        for (int i = 0; i < coordinateList.size(); i++) {

            int x1 = coordinateList.get(i)[0];
            int y1 = coordinateList.get(i)[1];
            int x2 = coordinateList.get(i)[2];
            int y2 = coordinateList.get(i)[3];

            // manhattan distance to beacon
            int manhattan = Math.abs(x1 - x2) + Math.abs(y1 - y2);
            int rowDistanceFromY1 = Math.abs(row - y1);

            if (rowDistanceFromY1 <= manhattan) {

                int positionLeft = (x1 - (manhattan - rowDistanceFromY1));
                int positonRight = (x1 + (manhattan - rowDistanceFromY1));

                if (positionLeft < xMin) {
                    xMin = positionLeft;
                }
                if (positonRight > xMax) {
                    xMax = positonRight;
                }

            }

        }

        List<int[]> beaconList = new ArrayList<int[]>();

        for (int i = 0; i < coordinateList.size(); i++) {

            int x1 = coordinateList.get(i)[0];
            int y1 = coordinateList.get(i)[1];
            int x2 = coordinateList.get(i)[2];
            int y2 = coordinateList.get(i)[3];

            if (y1 == row  && x1 > xMin && x1 < xMax) {
                int[] x1y1 = {x1, y1};
                if (checkIfAlreadyInXValues(beaconList, x1y1)) {
                    beaconList.add(x1y1);
                }
            }
            if (y2 == row && x2 > xMin && x2 < xMax) {
                int[] x2y2 = {x2, y2};
                if (checkIfAlreadyInXValues(beaconList, x2y2)) {
                    beaconList.add(x2y2);
                }
            }

        }

        int sensorBeaconCounter = beaconList.size();
        System.out.println("xMin: " + xMin + " xMax: " + xMax + " sensorBeaconCounter: " + sensorBeaconCounter);

        int answer = Math.abs(xMin) + Math.abs(xMax) - sensorBeaconCounter + 1;

        return answer;

    }

    private static boolean checkIfAlreadyInXValues(List<int[]> xValues, int[] i) {

        for (int j = 0; j < xValues.size(); j++) {
            if (xValues.get(j)[0] == i[0] && xValues.get(j)[1] == i[1]) {
                return false;
            }
        }

        return true;
    }

    private static ArrayList<int[]> getEachLineCoordinateList(File stackFile) throws NumberFormatException, IOException {

        ArrayList<int[]> eachLineCoordinateList = new ArrayList<int[]>();
        
        BufferedReader br = new BufferedReader(new FileReader(stackFile));

        String eachLine;

        while ((eachLine = br.readLine()) != null) {

            eachLine = eachLine.replaceAll("[a-zA-Z]", "");
            eachLine = eachLine.replaceAll(":", "");
            eachLine = eachLine.replaceAll(",", "");
            eachLine = eachLine.replaceAll("=", "");

            String[] coordinateStringArray = eachLine.split(" ");
            
            int counter = 0;
            int[] coordinateIntArray = new int[4];

            for (int i = 0; i < coordinateStringArray.length; i++) {
                try {
                    Integer.parseInt(coordinateStringArray[i]);
                    coordinateIntArray[counter] = Integer.parseInt(coordinateStringArray[i]);
                    counter++;
                } catch (NumberFormatException e) {
                    continue;
                }
            }

            counter = 0;
            eachLineCoordinateList.add(coordinateIntArray);
        }

        br.close();
        return eachLineCoordinateList;
    }
}