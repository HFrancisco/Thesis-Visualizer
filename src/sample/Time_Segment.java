package sample;

import java.time.*;

public class Time_Segment {

    private int startTime;
    private int endTime;
    private String emotion;

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime pStartTime) {

        String[] splitParts = pStartTime.toString().split(":");

        int temp;

        // Convert LocalTime to Millisecs so can be used to compare with Duration
        //           Convert Minutes                  Convert Seconds
        temp = (Integer.parseInt(splitParts[0]) * 60000) + (Integer.parseInt(splitParts[1]) * 1000);

        this.startTime = temp;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime pEndTime) {

        String[] splitParts = pEndTime.toString().split(":");

        int temp;

        // Convert LocalTime to Millisecs so can be used to compare with Duration
        //           Convert Minutes                  Convert Seconds
        temp = (Integer.parseInt(splitParts[0]) * 60000) + (Integer.parseInt(splitParts[1]) * 1000);

        this.endTime = temp;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {

        switch (emotion) {
            case "inte":
                this.emotion = "Interest";
                break;
            case "indiff":
                this.emotion = "Indifferent";
                break;
            case "happiness":
                this.emotion = "Happiness";
                break;
            case "sadness":
                this.emotion = "Sadness";
                break;
            case "surprise":
                this.emotion = "Surprise";
                break;

        }
    }

}
