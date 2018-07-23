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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UI_Controller extends BorderPane {

    private Controller controller = new Controller();
    private CSV_Reader csvReader = new CSV_Reader();
    private int[] chartData = new int[5];
    private List<Time_Segment> timeSegmentList = new ArrayList<>();
    private int numParticipants;


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

    private Slider timeSlider;
    private Label playTime;
    private Label spacer;
    private Label volumeLabel;
    private Label timeLabel;
    final Button playButton  = new Button(">");

    public UI_Controller(final MediaPlayer mp, int dgPhase) {

        this.mp = mp;
        setStyle("-fx-background-color: #9b9a9a;");
        mediaView = new MediaView(mp);

        /*DoubleProperty mvw = mediaView.fitWidthProperty();
        DoubleProperty mvh = mediaView.fitHeightProperty();
        mvw.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mvh.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        mediaView.setPreserveRatio(true);*/

        mediaView.setPreserveRatio(false);
        mediaView.setFitWidth(900);
        mediaView.setFitHeight(400);

        Pane mvPane = new Pane() {};
        //mvPane.getChildren().add(mediaView);
        mvPane.setStyle("-fx-background-color: #9b9a9a;");

        mediaBar = new HBox();
        //mediaBar.setAlignment(Pos.BOTTOM_LEFT);
        //mediaBar.setPadding(new Insets(0, 0, 0, 0));
        //mediaBar.setMaxWidth(950);
        //BorderPane.setAlignment(mediaBar, Pos.BOTTOM_LEFT);

        mainBar = new HBox();
        //mainBar.setAlignment(Pos.TOP_LEFT);
        //mainBar.setMaxWidth(950);
        //BorderPane.setAlignment(mainBar, Pos.TOP_LEFT);

        mainBar.getChildren().add(mvPane);

        vBox = new VBox();
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.getChildren().add(mediaView);
        vBox.getChildren().add(mainBar);
        BorderPane.setAlignment(vBox, Pos.TOP_LEFT);


        // Creating and Adding UI Elements

        // Bar Chart of data
        //Add_Chart();
        //mainBar.getChildren().add(barChart);

        // Play Button
        Add_PlayButton(barChart, series);
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

        //setTop(mainBar);
        //setBottom(mediaBar);

        int tempPhase = dgPhase;

        // Line Chart of data
        Add_LineChart(dgPhase);
        lineChart.setMaxHeight(320);
        lineChart.setMinHeight(320);
        lineChart.setPrefHeight(320);

        vBox.getChildren().add(mediaBar);
        vBox.getChildren().add(lineChart);
        setTop(vBox);


        Start_Timeline(dgPhase);

    }

    public void Start_Timeline(int dgPhase){

        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        Date date = new Date();

        try {
            timeSegmentList = csvReader.Read(dgPhase);
        } catch(IOException e){
            System.out.println("Error in CSV Reader.");
        }

        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5),
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent actionEvent) {

                        Duration currTime = mp.getCurrentTime();
                        System.out.println("Currtime is " +currTime);
                        String[] splitParts = currTime.toString().split("\\.");

                        for(int i = 0; i < timeSegmentList.size(); i++){

                            if(currTime.greaterThanOrEqualTo(Duration.millis(timeSegmentList.get(i).getStartTime()).subtract(Duration.seconds(1))) &&
                                    currTime.lessThanOrEqualTo(Duration.millis(timeSegmentList.get(i).getEndTime()).add(Duration.seconds(1)))){

                                if(dgPhase == 1){

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
                                } else if(dgPhase == 2){

                                    if(timeSegmentList.get(i).getEmotion().equals("+Surprise")){
                                        chartData[0]++;
                                    }

                                    if(timeSegmentList.get(i).getEmotion().equals("-Surprise")){
                                        chartData[1]++;
                                    }

                                    if(timeSegmentList.get(i).getEmotion().equals("Disgust")){
                                        chartData[2]++;
                                    }

                                    if(timeSegmentList.get(i).getEmotion().equals("Amused")){
                                        chartData[3]++;
                                    }
                                }

                            }
                        }

                        series1.getData().add(new XYChart.Data(Integer.parseInt(splitParts[0]), Get_Percentage(chartData[0], dgPhase)));
                        series2.getData().add(new XYChart.Data(Integer.parseInt(splitParts[0]), Get_Percentage(chartData[1], dgPhase)));
                        series3.getData().add(new XYChart.Data(Integer.parseInt(splitParts[0]), Get_Percentage(chartData[2], dgPhase)));
                        series4.getData().add(new XYChart.Data(Integer.parseInt(splitParts[0]), Get_Percentage(chartData[3], dgPhase)));

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
        yAxis.setLabel("Frequency");

        barChart = new BarChart(xAxis, yAxis);

        series = new XYChart.Series();
        series.setName("Video 1");

        // Initial Values
        series.getData().add(new XYChart.Data("Interest", 1));
        series.getData().add(new XYChart.Data("Indifferent"  , 2));
        series.getData().add(new XYChart.Data("Happiness"  , 0));
        series.getData().add(new XYChart.Data("Sadness"  , 0));
        series.getData().add(new XYChart.Data("Surprise"  , 0));

        barChart.getData().add(series);
    }

    private void Add_LineChart(int dgPhase){

        NumberAxis xAxis = new NumberAxis(0, 840000, 10000);
        xAxis.setLabel("Duration (ms)");

        NumberAxis yAxis = new NumberAxis(0, 100, 10);
        yAxis.setLabel("Percentage");

        lineChart = new LineChart(xAxis, yAxis);
        lineChart.setCreateSymbols(false);

        if(dgPhase == 1){
            series1.setName("Interested");
            series2.setName("Happiness");
            series3.setName("Sadness");
            series4.setName("Surprise");
        } else if(dgPhase == 2){
            series1.setName("+Surprise");
            series2.setName("-Surprise");
            series3.setName("Disgust");
            series4.setName("Amused");
        }


        lineChart.getData().addAll(series1, series2, series3, series4);

    }

    private void Add_PlayButton(BarChart  pBarChart, XYChart.Series pSeries){

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

        mp.currentTimeProperty().addListener(new InvalidationListener()
        {
            public void invalidated(Observable ov) {
                controller.Controller_Process(duration, pSeries, playTime,
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
                controller.Controller_Process(duration, pSeries, playTime,
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

    private int Get_Percentage(int pNum, int dgPhase){

        if(dgPhase == 1)
            numParticipants = 20;
        else if(dgPhase == 2)
            numParticipants = 3;

        int percentage = (int) (pNum * 100.0f) / numParticipants;

        return percentage;
    }

}