package sample;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class UI_Controller extends BorderPane {

    private Controller controller = new Controller();
    private MediaPlayer mp;
    private MediaView mediaView;
    private final boolean repeat = false;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private Duration duration;
    private Slider volumeSlider;
    private HBox mediaBar;
    private HBox mainBar;

    private BarChart barChart;
    private XYChart.Series series;
    private Slider timeSlider;
    private Label playTime;
    private Label spacer;
    private Label volumeLabel;
    private Label timeLabel;
    final Button playButton  = new Button(">");

    public UI_Controller(final MediaPlayer mp) {

        this.mp = mp;
        setStyle("-fx-background-color: #9b9a9a;");
        mediaView = new MediaView(mp);
        Pane mvPane = new Pane() {};
        mvPane.getChildren().add(mediaView);
        mvPane.setStyle("-fx-background-color: #9b9a9a;");
        //setCenter(mvPane);

        mediaBar = new HBox();
        mediaBar.setAlignment(Pos.BOTTOM_LEFT);
        mediaBar.setPadding(new Insets(0, 0, 5, 10));
        mediaBar.setMaxWidth(1280);
        BorderPane.setAlignment(mediaBar, Pos.BOTTOM_LEFT);

        mainBar = new HBox();
        mainBar.setAlignment(Pos.TOP_LEFT);
        //mainBar.setPadding(new Insets(5, 10, 5, 10));
        mainBar.setMaxWidth(1780);
        BorderPane.setAlignment(mainBar, Pos.TOP_LEFT);

        mainBar.getChildren().add(mvPane);

        // Creating and Adding UI Elements

        // Bar Chart of data
        Add_Chart();
        mainBar.getChildren().add(barChart);

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

        setTop(mainBar);
        setBottom(mediaBar);
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
        series.getData().add(new XYChart.Data("Interest", 16));
        series.getData().add(new XYChart.Data("Indifferent"  , 4));
        series.getData().add(new XYChart.Data("Happiness"  , 12));
        series.getData().add(new XYChart.Data("Sadness"  , 1));
        series.getData().add(new XYChart.Data("Surprise"  , 9));

        barChart.getData().add(series);
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
                controller.Update_Values(duration, pSeries, playTime,
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
                System.out.println("onPaused");
                playButton.setText(">");
            }
        });

        mp.setOnReady(new Runnable() {
            public void run() {
                duration = mp.getMedia().getDuration();
                controller.Update_Values(duration, pSeries, playTime,
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

}