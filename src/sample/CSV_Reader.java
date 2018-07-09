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

    public List Read() throws  IOException{

        // open file input stream
        BufferedReader reader = new BufferedReader(new FileReader("" +
                "C:/Users/Harry/Documents/_PROGRAMMING/_SourceTree/Thesis Visualizer/src/resources/SegmentsOnlyV2.csv"));

        // read file line by line
        String line = null;
        Scanner scanner = null;

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
