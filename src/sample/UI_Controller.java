package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UI_Controller extends BorderPane {

    private Controller controller = new Controller();
    private CSV_Reader csvReader = new CSV_Reader();
    private int[] chartData = new int[5];
    private List<Time_Segment> timeSegmentList = new ArrayList<>();
    private int numParticipants = 20;


    private String vid2String = "file:/C:/Users/Harry/Documents/_ACADS/_THESIS/Videos/media2.mp4";
    private String vid3String = "file:/C:/Users/Harry/Documents/_ACADS/_THESIS/Videos/media3.mp4";
    private String vid4String = "file:/C:/Users/Harry/Documents/_ACADS/_THESIS/Videos/media4.mp4";
    private String vid5String = "file:/C:/Users/Harry/Documents/_ACADS/_THESIS/Videos/media5.mp4";
    private String vid6String = "file:/C:/Users/Harry/Documents/_ACADS/_THESIS/Videos/media6.mp4";
    private String vid7String = "file:/C:/Users/Harry/Documents/_ACADS/_THESIS/Videos/media7.mp4";
    private String vid8String = "file:/C:/Users/Harry/Documents/_ACADS/_THESIS/Videos/media8.mp4";
    private String vid9String = "file:/C:/Users/Harry/Documents/_ACADS/_THESIS/Videos/media9.mp4";
    private String vid10String = "file:/C:/Users/Harry/Documents/_ACADS/_THESIS/Videos/media10.mp4";
    private String vid11String = "file:/C:/Users/Harry/Documents/_ACADS/_THESIS/Videos/media11.mp4";

    private MediaPlayer mp;
    private MediaView mediaView;
    private final boolean repeat = false;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private Duration duration = Duration.ZERO;
    private Slider volumeSlider;
    private HBox mediaBar;
    private HBox mainBar;
    private VBox vBox;

    // Barchart
    private BarChart barChart;
    private XYChart.Series series;

    // Linechart
    private LineChart lineChart;
    private XYChart.Series series1 = new XYChart.Series();
    private XYChart.Series series2 = new XYChart.Series();
    private XYChart.Series series3 = new XYChart.Series();
    private XYChart.Series series4 = new XYChart.Series();

    private LineChart inteLineChart;
    private LineChart hapLineChart;
    private LineChart sadLineChart;
    private LineChart surpLineChart;

    private Slider timeSlider;
    private Label playTime;
    private Label spacer;
    private Label volumeLabel;
    private Label timeLabel;
    final Button playButton  = new Button(">");
    final Button nextButton = new Button("Next Video");

    private int vidCounter = 1;

    private int dur1 = 110000;
    private int dur2 = 29000;
    private int dur3 = 120000;
    private int dur4 = 90000;
    private int dur5 = 67000;
    private int dur6 = 120000;
    private int dur7 = 56000;
    private int dur8 = 60000;
    private int dur9 = 60000;
    private int dur10 = 60000;
    private int dur11 = 63000;

    private Timeline tl = new Timeline();

    public UI_Controller(final MediaPlayer mp, int dgPhase) {

        this.mp = mp;
        setStyle("-fx-background-color: #9b9a9a;");
        mediaView = new MediaView(mp);

        mediaView.setPreserveRatio(false);
        mediaView.setFitWidth(900);
        mediaView.setFitHeight(400);

        mediaBar = new HBox();

        vBox = new VBox();
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.getChildren().add(mediaView);
        BorderPane.setAlignment(vBox, Pos.TOP_LEFT);

        MediaPlayer_Stuff();

        // Creating and Adding UI Elements

        // Play Button
        Add_PlayButton();
        mediaBar.getChildren().add(playButton);

        // Spacer
        spacer = new Label("   ");
        mediaBar.getChildren().add(spacer);

        // Time Label
        Add_TimeLabel();
        mediaBar.getChildren().add(timeLabel);

        // Time Slider
        Add_TimeSlider();
        mediaBar.getChildren().add(timeSlider);

        // Play Label
        Add_PlayLabel();
        mediaBar.getChildren().add(playTime);

        // Volume Label
        Add_VolumeLabel();
        mediaBar.getChildren().add(volumeLabel);

        // Volume Slider
        Add_VolumeSlider();
        mediaBar.getChildren().add(volumeSlider);

        Add_NextButton();
        mediaBar.getChildren().add(nextButton);

        vBox.getChildren().add(mediaBar);

        if(dgPhase == 1){ // Line graphs, 1 graph per emotion, 1 graph per tab

            // Line Chart of data
            Add_LineCharts(dur1);
            Add_ChartVisual();
        } else { // bar chart, combined all emotions in 1 chart

            Add_Chart();
            barChart.setMaxHeight(320);
            barChart.setMinHeight(320);
            barChart.setPrefHeight(320);
            barChart.setHorizontalGridLinesVisible(true);

            vBox.getChildren().add(mediaBar);
            vBox.getChildren().add(barChart);
            setTop(vBox);

        }

        ReadCSV(vidCounter);

        Start_Timeline(dgPhase);

    }

    public void Start_Timeline(int dgPhase){

        double sec;

        if(dgPhase == 1)
            sec = 0.5;
        else
            sec = 3;


        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(sec),
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent actionEvent) {

                        Duration currTime = mp.getCurrentTime();
                        String[] splitParts = currTime.toString().split("\\.");

                        for(int i = 0; i < timeSegmentList.size(); i++){

                            if(currTime.greaterThanOrEqualTo(Duration.millis(timeSegmentList.get(i).getStartTime()).subtract(Duration.seconds(1))) &&
                                    currTime.lessThanOrEqualTo(Duration.millis(timeSegmentList.get(i).getEndTime()).add(Duration.seconds(1)))){

                                if(timeSegmentList.get(i).getEmotion().equals("Interest")){
                                    chartData[0]++;
                                }

                                if(timeSegmentList.get(i).getEmotion().equals("Happiness")){
                                    chartData[1]++;
                                }

                                if(timeSegmentList.get(i).getEmotion().equals("Sadness")){
                                    chartData[2]++;
                                }

                                if(timeSegmentList.get(i).getEmotion().equals("Surprise")){
                                    chartData[3]++;
                                }

                                if(timeSegmentList.get(i).getEmotion().equals("Indifferent")){
                                    chartData[4]++;
                                }
                            }
                        }

                        if(dgPhase == 1){ // Line

                            series1.getData().add(new XYChart.Data(Integer.parseInt(splitParts[0]), Get_Percentage(chartData[0])));
                            series2.getData().add(new XYChart.Data(Integer.parseInt(splitParts[0]), Get_Percentage(chartData[1])));
                            series3.getData().add(new XYChart.Data(Integer.parseInt(splitParts[0]), Get_Percentage(chartData[2])));
                            series4.getData().add(new XYChart.Data(Integer.parseInt(splitParts[0]), Get_Percentage(chartData[3])));

                        } else { // Bar

                            series1.getData().add(new XYChart.Data<>("Interested", Get_Percentage(chartData[0])));
                            series2.getData().add(new XYChart.Data<>("Happiness", Get_Percentage(chartData[1])));
                            series3.getData().add(new XYChart.Data<>("Sadness", Get_Percentage(chartData[2])));
                            series4.getData().add(new XYChart.Data<>("Surprise", Get_Percentage(chartData[3])));

                        }

                        for (int i = 0; i < chartData.length; i++)
                            chartData[i] = 0;

                    }
                }));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();

    }

    private void Add_Chart(){

        // Add a Chart
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Emotions");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Percentage");
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(100);

        barChart = new BarChart(xAxis, yAxis);

        series1.setName("Interested");
        series2.setName("Happiness");
        series3.setName("Sadness");
        series4.setName("Surprise");

        barChart.getData().addAll(series1, series2, series3, series4);
    }

    private void Add_LineCharts(int upBound){

        NumberAxis xAxis = new NumberAxis(0, upBound, 10000);
        xAxis.setLabel("Duration (ms)");
        NumberAxis yAxis = new NumberAxis(0, 100, 10);
        yAxis.setLabel("Percentage");

        NumberAxis xAxis2 = new NumberAxis(0, upBound, 10000);
        xAxis2.setLabel("Duration (ms)");
        NumberAxis yAxis2 = new NumberAxis(0, 100, 10);
        yAxis2.setLabel("Percentage");

        NumberAxis xAxis3 = new NumberAxis(0, upBound, 10000);
        xAxis3.setLabel("Duration (ms)");
        NumberAxis yAxis3 = new NumberAxis(0, 100, 10);
        yAxis3.setLabel("Percentage");

        NumberAxis xAxis4 = new NumberAxis(0, upBound, 10000);
        xAxis4.setLabel("Duration (ms)");
        NumberAxis yAxis4 = new NumberAxis(0, 100, 10);
        yAxis4.setLabel("Percentage");

        inteLineChart = new LineChart(xAxis, yAxis);
        inteLineChart.setCreateSymbols(false);

        hapLineChart = new LineChart(xAxis2, yAxis2);
        hapLineChart.setCreateSymbols(false);

        sadLineChart = new LineChart(xAxis3, yAxis3);
        sadLineChart.setCreateSymbols(false);

        surpLineChart = new LineChart(xAxis4, yAxis4);
        surpLineChart.setCreateSymbols(false);

        series1.setName("Interested");
        series2.setName("Happiness");
        series3.setName("Sadness");
        series4.setName("Surprise");

        inteLineChart.getData().addAll(series1);
        hapLineChart.getData().addAll(series2);
        sadLineChart.getData().addAll(series3);
        surpLineChart.getData().addAll(series4);
    }

    private void Add_PlayButton(){

        // Add listeners to Play Button
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                MediaPlayer.Status status = mp.getStatus();

                if (status == MediaPlayer.Status.UNKNOWN  || status == MediaPlayer.Status.HALTED)
                {
                    // don't do anything in these states
                    return;
                }

                if ( status == MediaPlayer.Status.PAUSED
                        || status == MediaPlayer.Status.READY
                        || status == MediaPlayer.Status.STOPPED)
                {
                    // rewind the movie if we're sitting at the end
                    if (atEndOfMedia) {
                        mp.seek(mp.getStartTime());
                        atEndOfMedia = false;
                    }
                    mp.play();
                } else {
                    mp.pause();
                }
            }
        });

    }

    private void Add_NextButton(){

        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                if(vidCounter == 1){

                    tl.stop();
                    Remove_Charts();
                    Create_NewMediaPlayer(vid2String);
                    Add_LineCharts(dur2);
                    Add_ChartVisual();

                } else if(vidCounter == 2){

                    tl.stop();
                    Remove_Charts();
                    Create_NewMediaPlayer(vid3String);
                    Add_LineCharts(dur3);
                    Add_ChartVisual();

                } else if(vidCounter == 3){

                    tl.stop();
                    Remove_Charts();
                    Create_NewMediaPlayer(vid4String);
                    Add_LineCharts(dur4);
                    Add_ChartVisual();

                } else if(vidCounter == 4){

                    tl.stop();
                    Remove_Charts();
                    Create_NewMediaPlayer(vid5String);
                    Add_LineCharts(dur5);
                    Add_ChartVisual();

                } else if(vidCounter == 5){

                    tl.stop();
                    Remove_Charts();
                    Create_NewMediaPlayer(vid6String);
                    Add_LineCharts(dur6);
                    Add_ChartVisual();

                } else if(vidCounter == 6){

                    tl.stop();
                    Remove_Charts();
                    Create_NewMediaPlayer(vid7String);
                    Add_LineCharts(dur7);
                    Add_ChartVisual();

                } else if(vidCounter == 7){

                    tl.stop();
                    Remove_Charts();
                    Create_NewMediaPlayer(vid8String);
                    Add_LineCharts(dur8);
                    Add_ChartVisual();

                } else if(vidCounter == 8){

                    tl.stop();
                    Remove_Charts();
                    Create_NewMediaPlayer(vid9String);
                    Add_LineCharts(dur9);
                    Add_ChartVisual();

                } else if(vidCounter == 9){

                    tl.stop();
                    Remove_Charts();
                    Create_NewMediaPlayer(vid10String);
                    Add_LineCharts(dur10);
                    Add_ChartVisual();

                } else if(vidCounter == 10){

                    tl.stop();
                    Remove_Charts();
                    Create_NewMediaPlayer(vid11String);
                    Add_LineCharts(dur11);
                    Add_ChartVisual();

                }

                Start_Timeline(1);
            }
        });

    }

    private void Add_TimeLabel(){

        // Add Time Label
        timeLabel = new Label("Time: ");
    }

    private void Add_TimeSlider(){

        // Add Time Slider
        timeSlider = new Slider();
        HBox.setHgrow(timeSlider, Priority.ALWAYS);
        timeSlider.setMinWidth(50);
        timeSlider.setMaxWidth(Double.MAX_VALUE);
        timeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging()) {
                    // multiply duration by percentage calculated by slider position
                    mp.seek(duration.multiply(timeSlider.getValue() / 100.0));
                }
            }
        });
    }

    private void Add_PlayLabel(){

        // Add Play label
        playTime = new Label();
        playTime.setPrefWidth(130);
        playTime.setMinWidth(50);
    }

    private void Add_VolumeLabel(){

        // Add the volume label
        volumeLabel = new Label("Vol: ");
    }

    private void Add_VolumeSlider(){

        // Add Volume slider
        volumeSlider = new Slider();
        volumeSlider.setPrefWidth(70);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.setMinWidth(30);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (volumeSlider.isValueChanging()) {
                    mp.setVolume(volumeSlider.getValue() / 100.0);
                }
            }
        });
    }

    private int Get_Percentage(int pNum){

        int percentage = (int) (pNum * 100.0f) / numParticipants;

        return percentage;
    }

    private void Create_NewMediaPlayer(String pVidString){

        mediaView.getMediaPlayer().setMute(true);
        Media media = new Media(pVidString);
        mp = new MediaPlayer(media);
        mp.setAutoPlay(true);
        mediaView.setMediaPlayer(mp);

        inteLineChart.getData().removeAll(inteLineChart.getData());
        hapLineChart.getData().removeAll(hapLineChart.getData());
        sadLineChart.getData().removeAll(sadLineChart.getData());
        surpLineChart.getData().removeAll(surpLineChart.getData());

        series1 = new XYChart.Series();
        series2 = new XYChart.Series();
        series3 = new XYChart.Series();
        series4 = new XYChart.Series();

        MediaPlayer_Stuff();

        vidCounter++;

        ReadCSV(vidCounter);

    }

    private void MediaPlayer_Stuff(){

        mp.currentTimeProperty().addListener(new InvalidationListener()
        {
            public void invalidated(Observable ov) {
                controller.Controller_Process(duration, playTime,
                        timeSlider, volumeSlider, mp);
            }
        });

        mp.setOnPlaying(new Runnable() {
            public void run() {
                if (stopRequested) {
                    mp.pause();
                    stopRequested = false;
                } else {
                    playButton.setText("||");
                }
            }
        });

        mp.setOnPaused(new Runnable() {
            public void run() {
                playButton.setText(">");
            }
        });

        mp.setOnReady(new Runnable() {
            public void run() {
                duration = mp.getMedia().getDuration();
                controller.Controller_Process(duration, playTime,
                        timeSlider, volumeSlider, mp);
            }
        });

        mp.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
        mp.setOnEndOfMedia(new Runnable() {
            public void run() {
                if (!repeat) {
                    playButton.setText(">");
                    stopRequested = true;
                    atEndOfMedia = true;
                }
            }
        });

    }

    private void ReadCSV(int pVidCounter){

        try {
            timeSegmentList = csvReader.Read(pVidCounter);
        } catch(IOException e){
            System.out.println("Error in CSV Reader.");
        }

    }

    private void Remove_Charts(){

        vBox.getChildren().remove(vBox.getChildren().size()-1);

    }

    private void Add_ChartVisual(){

        inteLineChart.setMaxHeight(320);
        inteLineChart.setMinHeight(320);
        inteLineChart.setPrefHeight(320);

        hapLineChart.setMaxHeight(320);
        hapLineChart.setMinHeight(320);
        hapLineChart.setPrefHeight(320);

        sadLineChart.setMaxHeight(320);
        sadLineChart.setMinHeight(320);
        sadLineChart.setPrefHeight(320);

        surpLineChart.setMaxHeight(320);
        surpLineChart.setMinHeight(320);
        surpLineChart.setPrefHeight(320);

        // Add tabs
        TabPane tabPane = new TabPane();

        Tab inteTab = new Tab();
        inteTab.setText("Interested");
        inteTab.setContent(inteLineChart);

        Tab hapTab = new Tab();
        hapTab.setText("Happiness");
        hapTab.setContent(hapLineChart);

        Tab sadTab = new Tab();
        sadTab.setText("Sadness");
        sadTab.setContent(sadLineChart);

        Tab surpTab = new Tab();
        surpTab.setText("Surprise");
        surpTab.setContent(surpLineChart);

        tabPane.getTabs().add(inteTab);
        tabPane.getTabs().add(hapTab);
        tabPane.getTabs().add(sadTab);
        tabPane.getTabs().add(surpTab);

        // Add elements to largest container
        vBox.getChildren().add(tabPane);
        setTop(vBox);

    }

}