package Day_15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class Day15Part2 {

    public static void main(String[] args) throws NumberFormatException, IOException {

        File stackFile = new File("Day_15/testData.txt");

        int numberOfRows = 12;
        int maxX = 20;
        int rowStart = 9;
        // int row = 10;

        ArrayList<int[]> coordinateList = getEachLineCoordinateList(stackFile);

        int answer = addSpotsWhereBeaconCannotBe(coordinateList, numberOfRows, maxX, rowStart);

        System.out.println("Test Answer: " + answer);    

        stackFile = new File("Day_15/data.txt");

        final int numberOfRows2 = 4000000;
        final int maxX2 = 4000000;
        // int row = 10;

        final ArrayList<int[]> coordinateList2 = getEachLineCoordinateList(stackFile);

        int[] rowStarts = new int[4000000/100000 + 1 ];
        rowStarts[0] = 0;

        List<Thread> threads = new ArrayList<Thread>(); 

        for (int i = 1; i < rowStarts.length; i++) {
            rowStarts[i] = rowStarts[i - 1] + 100000;
        }

        for (final int i : rowStarts) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    int answer = addSpotsWhereBeaconCannotBe(coordinateList2, numberOfRows2, maxX2, i);
                }
            });
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }
        


    }

    private static int addSpotsWhereBeaconCannotBe(ArrayList<int[]> coordinateList, int numberOfRows, int maxX, int rowStart) {
        
        boolean checker = false;
        int row = rowStart;
        int answer = 0;
        
        while (checker == false) {

            int xMin = 0;
            int xMax = maxX;
            String[] rowArray = new String[maxX + 1];
            for (int j = 0; j < rowArray.length; j++) {
                rowArray[j] = ".";
            }

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
                    int positionRight = (x1 + (manhattan - rowDistanceFromY1));

                    if (positionLeft <= 0 && positionRight >= maxX) {
                        checker = false;
                        // calculate how far down positionLeft is less than 0

                        int passRows = 0;
                        int currentRow = row;

                        while (positionLeft < 0 && positionRight > maxX) {
                            int currentRowDistanceFromY1 = Math.abs(currentRow - y1);

                            positionLeft = x1 - (manhattan - currentRowDistanceFromY1);
                            positionRight = x1 + (manhattan - currentRowDistanceFromY1);
                            passRows++;
                        }

                        if (y1 > row){
                            row = row + (2 * passRows);
                        }
                        else {
                            row = row + passRows;
                        }
                        for (int j = 0; j <= rowArray.length; j++) {
                            rowArray[j] = "X";
                        }

                        break;
                    }
                    if (positionLeft <= 0) {
                        positionLeft = 0;
                    }
                    if (positionRight >= maxX) {
                        positionRight = maxX;
                    }
                    if (positionLeft < xMin && positionRight > xMin) {
                        xMin = positionRight;
                    }
                    if (positionRight > xMax && positionLeft < xMax) {
                        xMax = positionLeft;
                    }
                    if (positionLeft >= xMin && positionRight <= xMax) {
                        for (int j = positionLeft; j <= positionRight; j++) {
                            rowArray[j] = "X";
                        }
                    }
                    if (xMin > xMax) {
                        checker = false;
                        break;
                    }
                }

            }


            for (int i = 0; i < rowArray.length; i++) {
                if (rowArray[i] == ".") {
                    checker = true;
                    answer = (i * 4000000) + row;
                    System.out.println("row: " + row + " x: " + i);
                    System.out.println("answer: " + answer);
                }
            }

            int coordinatesListChecker = 0;

            if (checker == false) {
                for (int i = 0; i < coordinateList.size(); i++) {
                    if (coordinateList.get(i)[1] > row) {
                        coordinatesListChecker++;
                    }
                }
            }

            if (coordinatesListChecker == coordinateList.size()) {
                int lowest = coordinateList.get(0)[1];
                int manhattan = Math.abs(coordinateList.get(0)[0]- coordinateList.get(0)[2]) + Math.abs(coordinateList.get(0)[1] - coordinateList.get(0)[3]);

                for (int i = 0; i < coordinateList.size(); i++) {
                    if (coordinateList.get(i)[1] < lowest) {
                        lowest = coordinateList.get(i)[1];
                        manhattan = Math.abs(coordinateList.get(i)[0]- coordinateList.get(i)[2]) + Math.abs(coordinateList.get(i)[1] - coordinateList.get(i)[3]);
                    }
                }

                row = row + (Math.abs(lowest - row) + manhattan);

            } 

            xMin = 0;
            xMax = maxX;
            row++;
            coordinatesListChecker = 0;

        }



        return answer;

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