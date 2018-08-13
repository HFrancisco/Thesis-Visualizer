package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSV_Reader {

    BufferedReader reader;

    private String csv1 = "C:/Users/Harry/Documents/_PROGRAMMING/_SourceTree/Thesis Visualizer/src/resources/vid1.csv";
    private String csv2 = "C:/Users/Harry/Documents/_PROGRAMMING/_SourceTree/Thesis Visualizer/src/resources/vid2.csv";
    private String csv3 = "C:/Users/Harry/Documents/_PROGRAMMING/_SourceTree/Thesis Visualizer/src/resources/vid3.csv";
    private String csv4 = "C:/Users/Harry/Documents/_PROGRAMMING/_SourceTree/Thesis Visualizer/src/resources/vid4.csv";
    private String csv5 = "C:/Users/Harry/Documents/_PROGRAMMING/_SourceTree/Thesis Visualizer/src/resources/vid5.csv";
    private String csv6 = "C:/Users/Harry/Documents/_PROGRAMMING/_SourceTree/Thesis Visualizer/src/resources/vid6.csv";
    private String csv7 = "C:/Users/Harry/Documents/_PROGRAMMING/_SourceTree/Thesis Visualizer/src/resources/vid7.csv";
    private String csv8 = "C:/Users/Harry/Documents/_PROGRAMMING/_SourceTree/Thesis Visualizer/src/resources/vid8.csv";
    private String csv9 = "C:/Users/Harry/Documents/_PROGRAMMING/_SourceTree/Thesis Visualizer/src/resources/vid9.csv";
    private String csv10 = "C:/Users/Harry/Documents/_PROGRAMMING/_SourceTree/Thesis Visualizer/src/resources/vid10.csv";
    private String csv11 = "C:/Users/Harry/Documents/_PROGRAMMING/_SourceTree/Thesis Visualizer/src/resources/vid11.csv";

    public List Read(int vidNum) throws  IOException{

        switch (vidNum){

            case 1:
                reader = new BufferedReader(new FileReader(csv1));
                break;
            case 2:
                reader = new BufferedReader(new FileReader(csv2));
                break;
            case 3:
                reader = new BufferedReader(new FileReader(csv3));
                break;
            case 4:
                reader = new BufferedReader(new FileReader(csv4));
                break;
            case 5:
                reader = new BufferedReader(new FileReader(csv5));
                break;
            case 6:
                reader = new BufferedReader(new FileReader(csv6));
                break;
            case 7:
                reader = new BufferedReader(new FileReader(csv7));
                break;
            case 8:
                reader = new BufferedReader(new FileReader(csv8));
                break;
            case 9:
                reader = new BufferedReader(new FileReader(csv9));
                break;
            case 10:
                reader = new BufferedReader(new FileReader(csv10));
                break;
            case 11:
                reader = new BufferedReader(new FileReader(csv11));
                break;
        }

        // read file line by line
        String line;
        Scanner scanner;

        int index = 0;
        List<Time_Segment> timeSegmentList = new ArrayList<>();

        while ((line = reader.readLine()) != null) {

            Time_Segment timeSegment = new Time_Segment();
            scanner = new Scanner(line);
            scanner.useDelimiter(",");

            while (scanner.hasNext()) {

                String data = scanner.next();

                if (index == 0)
                    timeSegment.setStartTime(LocalTime.parse(data));
                else if (index == 1)
                    timeSegment.setEndTime(LocalTime.parse(data));
                else if (index == 2)
                    timeSegment.setEmotion(data);
                else
                    System.out.println("invalid data::" + data);
                index++;
            }

            index = 0;
            timeSegmentList.add(timeSegment);
        }

        //close reader
        reader.close();

        return timeSegmentList;
    }

}
