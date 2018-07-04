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

public class Controller extends BorderPane {

    private MediaPlayer mp;
    private MediaView mediaView;
    private final boolean repeat = false;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private Duration duration;
    private Slider timeSlider;
    private Label playTime;
    private Slider volumeSlider;
    private HBox mediaBar;
    private HBox mainBar;

    private int flag1 = 0;
    private int flag2 = 0;
    private int flag3 = 0;
    private int flag4 = 0;
    private int flag5 = 0;
    private int flag6 = 0;
    private int flag7 = 0;
    private int flag8 = 0;
    private int flag9 = 0;
    private int flag10 = 0;
    private int flag11 = 0;

    public Controller(final MediaPlayer mp) {
        /*this.mp = mp;
        setStyle("-fx-background-color: #9b9a9a;");
        mediaView = new MediaView(mp);
        Pane mvPane = new Pane() {                };
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

        // Add a Chart
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Emotions");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Frequency");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        series.setName("Video 1");

        series.getData().add(new XYChart.Data("Interest", 16));
        series.getData().add(new XYChart.Data("Indifferent"  , 4));
        series.getData().add(new XYChart.Data("Happiness"  , 12));
        series.getData().add(new XYChart.Data("Sadness"  , 1));
        series.getData().add(new XYChart.Data("Surprise"  , 9));

        barChart.getData().add(series);

        mainBar.getChildren().add(mvPane);
        mainBar.getChildren().add(barChart);

        final Button playButton  = new Button(">");
        mediaBar.getChildren().add(playButton);
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
                updateValues(barChart, series);
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
                updateValues(barChart, series);
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

        // Add spacer
        Label spacer = new Label("   ");
        mediaBar.getChildren().add(spacer);

        // Add Time label
        Label timeLabel = new Label("Time: ");
        mediaBar.getChildren().add(timeLabel);

        // Add time slider
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
        mediaBar.getChildren().add(timeSlider);

        // Add Play label
        playTime = new Label();
        playTime.setPrefWidth(130);
        playTime.setMinWidth(50);
        mediaBar.getChildren().add(playTime);

        // Add the volume label
        Label volumeLabel = new Label("Vol: ");
        mediaBar.getChildren().add(volumeLabel);

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
        mediaBar.getChildren().add(volumeSlider);

        setTop(mainBar);
        setBottom(mediaBar);*/
    }







    protected void Update_Values(BarChart  pBarChart, XYChart.Series pSeries) {
        if (playTime != null && timeSlider != null && volumeSlider != null) {
            Platform.runLater(new Runnable() {
                public void run() {
                    Duration currentTime = mp.getCurrentTime();
                    playTime.setText(formatTime(currentTime, duration));
                    timeSlider.setDisable(duration.isUnknown());
                    if (!timeSlider.isDisabled()
                            && duration.greaterThan(Duration.ZERO)
                            && !timeSlider.isValueChanging()) {
                        timeSlider.setValue(currentTime.divide(duration).toMillis()
                                * 100.0);
                    }
                    if (!volumeSlider.isValueChanging()) {
                        volumeSlider.setValue((int)Math.round(mp.getVolume()
                                * 100));
                    }

                    if(flag1 == 0 && currentTime.greaterThanOrEqualTo(Duration.ZERO) && currentTime.lessThanOrEqualTo(new Duration(109000)))
                        flag1 = 1;
                    else if(flag2 == 0 && currentTime.greaterThanOrEqualTo(new Duration(110000)) && currentTime.lessThanOrEqualTo(new Duration(139000)))
                        flag2 = 1;
                    else if(flag3 == 0 && currentTime.greaterThanOrEqualTo(new Duration(140000)) && currentTime.lessThanOrEqualTo(new Duration(259000)))
                        flag3 = 1;
                    else if(flag4 == 0 && currentTime.greaterThanOrEqualTo(new Duration(260000)) && currentTime.lessThanOrEqualTo(new Duration(349000)))
                        flag4 = 1;
                    else if(flag5 == 0 && currentTime.greaterThanOrEqualTo(new Duration(350000)) && currentTime.lessThanOrEqualTo(new Duration(416000)))
                        flag5 = 1;
                    else if(flag6 == 0 && currentTime.greaterThanOrEqualTo(new Duration(417000)) && currentTime.lessThanOrEqualTo(new Duration(537000)))
                        flag6 = 1;
                    else if(flag7 == 0 && currentTime.greaterThanOrEqualTo(new Duration(538000)) && currentTime.lessThanOrEqualTo(new Duration(593000)))
                        flag7 = 1;
                    else if(flag8 == 0 && currentTime.greaterThanOrEqualTo(new Duration(594000)) && currentTime.lessThanOrEqualTo(new Duration(653000)))
                        flag8 = 1;
                    else if(flag9 == 0 && currentTime.greaterThanOrEqualTo(new Duration(654000)) && currentTime.lessThanOrEqualTo(new Duration(713000)))
                        flag9 = 1;
                    else if(flag10 == 0 && currentTime.greaterThanOrEqualTo(new Duration(714000)) && currentTime.lessThanOrEqualTo(new Duration(774000)))
                        flag10 = 1;
                    else if(flag11 == 0 && currentTime.greaterThanOrEqualTo(new Duration(775000)) && currentTime.lessThanOrEqualTo(new Duration(837000)))
                        flag11 = 1;

                    // Update Chart values
                    if(flag1 == 1 && currentTime.greaterThanOrEqualTo(Duration.ZERO) && currentTime.lessThanOrEqualTo(new Duration(109000))){                      // For 1st vid
                        flag1 = 2;

                        //pSeries.getData().clear();

                        pSeries.setName("Video 1");

                        pSeries.getData().add(new XYChart.Data("Interest", 16));
                        pSeries.getData().add(new XYChart.Data("Indifferent"  , 4));
                        pSeries.getData().add(new XYChart.Data("Happiness"  , 12));
                        pSeries.getData().add(new XYChart.Data("Sadness"  , 1));
                        pSeries.getData().add(new XYChart.Data("Surprise"  , 9));

                    } else if(flag2 == 1 && currentTime.greaterThanOrEqualTo(new Duration(110000)) && currentTime.lessThanOrEqualTo(new Duration(139000))){  // For 2nd vid
                        flag2 = 2;

                        //pSeries.getData().clear();

                        //Doritos

                        pSeries.setName("Video 2");

                        pSeries.getData().add(new XYChart.Data("Interest", 16));
                        pSeries.getData().add(new XYChart.Data("Indifferent"  , 4));
                        pSeries.getData().add(new XYChart.Data("Happiness"  , 13));
                        pSeries.getData().add(new XYChart.Data("Sadness"  , 1));
                        pSeries.getData().add(new XYChart.Data("Surprise"  , 13));

                        flag1 = 0;

                    } else if(flag3 == 1 && currentTime.greaterThanOrEqualTo(new Duration(140000)) && currentTime.lessThanOrEqualTo(new Duration(259000))){  // For 3rd vid
                        flag3 = 2;

                        //pSeries.getData().clear();

                        //PnG

                        pSeries.setName("Video 3");

                        pSeries.getData().add(new XYChart.Data("Interest", 19));
                        pSeries.getData().add(new XYChart.Data("Indifferent"  , 1));
                        pSeries.getData().add(new XYChart.Data("Happiness"  , 15));
                        pSeries.getData().add(new XYChart.Data("Sadness"  , 11));
                        pSeries.getData().add(new XYChart.Data("Surprise"  , 5));

                        flag2 = 0;

                    } else if(flag4 == 1 && currentTime.greaterThanOrEqualTo(new Duration(260000)) && currentTime.lessThanOrEqualTo(new Duration(349000))){  // For 4th vid
                        flag4 = 2;

                        //pSeries.getData().clear();

                        pSeries.setName("Video 4");

                        pSeries.getData().add(new XYChart.Data("Interest", 19));
                        pSeries.getData().add(new XYChart.Data("Indifferent"  , 1));
                        pSeries.getData().add(new XYChart.Data("Happiness"  , 17));
                        pSeries.getData().add(new XYChart.Data("Sadness"  , 8));
                        pSeries.getData().add(new XYChart.Data("Surprise"  , 6));

                    } else if(flag5 == 1 && currentTime.greaterThanOrEqualTo(new Duration(350000)) && currentTime.lessThanOrEqualTo(new Duration(416000))){  // For 5th vid
                        flag5 = 2;

                        //pSeries.getData().clear();

                        //Hornbach

                        pSeries.setName("Video 5");

                        pSeries.getData().add(new XYChart.Data("Interest", 10));
                        pSeries.getData().add(new XYChart.Data("Indifferent"  , 10));
                        pSeries.getData().add(new XYChart.Data("Happiness"  , 8));
                        pSeries.getData().add(new XYChart.Data("Sadness"  , 7));
                        pSeries.getData().add(new XYChart.Data("Surprise"  , 12));

                    } else if(flag6 == 1 && currentTime.greaterThanOrEqualTo(new Duration(417000)) && currentTime.lessThanOrEqualTo(new Duration(537000))){  // For 6th vid
                        flag6 = 2;

                        //pSeries.getData().clear();

                        pSeries.setName("Video 6");

                        pSeries.getData().add(new XYChart.Data("Interest", 16));
                        pSeries.getData().add(new XYChart.Data("Indifferent"  , 4));
                        pSeries.getData().add(new XYChart.Data("Happiness"  , 17));
                        pSeries.getData().add(new XYChart.Data("Sadness"  , 1));
                        pSeries.getData().add(new XYChart.Data("Surprise"  , 3));

                    } else if(flag7 == 1 && currentTime.greaterThanOrEqualTo(new Duration(538000)) && currentTime.lessThanOrEqualTo(new Duration(593000))){  // For 7th vid
                        flag7 = 2;

                        //pSeries.getData().clear();

                        pSeries.setName("Video 7");

                        pSeries.getData().add(new XYChart.Data("Interest", 4));
                        pSeries.getData().add(new XYChart.Data("Indifferent"  , 16));
                        pSeries.getData().add(new XYChart.Data("Happiness"  , 2));
                        pSeries.getData().add(new XYChart.Data("Sadness"  , 1));
                        pSeries.getData().add(new XYChart.Data("Surprise"  , 1));

                    } else if(flag8 == 1 && currentTime.greaterThanOrEqualTo(new Duration(594000)) && currentTime.lessThanOrEqualTo(new Duration(653000))){  // For 8th vid
                        flag8 = 2;

                        //pSeries.getData().clear();

                        pSeries.setName("Video 8");

                        pSeries.getData().add(new XYChart.Data("Interest", 19));
                        pSeries.getData().add(new XYChart.Data("Indifferent"  , 1));
                        pSeries.getData().add(new XYChart.Data("Happiness"  , 19));
                        pSeries.getData().add(new XYChart.Data("Sadness"  , 0));
                        pSeries.getData().add(new XYChart.Data("Surprise"  , 4));

                    } else if(flag9 == 1 && currentTime.greaterThanOrEqualTo(new Duration(654000)) && currentTime.lessThanOrEqualTo(new Duration(713000))){  // For 9th vid
                        flag9 = 2;

                        //pSeries.getData().clear();

                        pSeries.setName("Video 9");

                        pSeries.getData().add(new XYChart.Data("Interest", 15));
                        pSeries.getData().add(new XYChart.Data("Indifferent"  , 5));
                        pSeries.getData().add(new XYChart.Data("Happiness"  , 2));
                        pSeries.getData().add(new XYChart.Data("Sadness"  , 6));
                        pSeries.getData().add(new XYChart.Data("Surprise"  , 4));

                    } else if(flag10 == 1 && currentTime.greaterThanOrEqualTo(new Duration(714000)) && currentTime.lessThanOrEqualTo(new Duration(774000))){  // For 10th vid
                        flag10 = 2;

                        //pSeries.getData().clear();

                        pSeries.setName("Video 10");

                        pSeries.getData().add(new XYChart.Data("Interest", 12));
                        pSeries.getData().add(new XYChart.Data("Indifferent"  , 8));
                        pSeries.getData().add(new XYChart.Data("Happiness"  , 13));
                        pSeries.getData().add(new XYChart.Data("Sadness"  , 1));
                        pSeries.getData().add(new XYChart.Data("Surprise"  , 9));

                    } else if(flag11 == 1 && currentTime.greaterThanOrEqualTo(new Duration(775000)) && currentTime.lessThanOrEqualTo(new Duration(837000))){  // For 11th vid
                        flag11 = 2;

                        //pSeries.getData().clear();

                        pSeries.setName("Video 11");

                        pSeries.getData().add(new XYChart.Data("Interest", 15));
                        pSeries.getData().add(new XYChart.Data("Indifferent"  , 5));
                        pSeries.getData().add(new XYChart.Data("Happiness"  , 12));
                        pSeries.getData().add(new XYChart.Data("Sadness"  , 0));
                        pSeries.getData().add(new XYChart.Data("Surprise"  , 3));

                    }
                }
            });
        }
    }

    private static String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int)Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
                - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int)Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 -
                    durationMinutes * 60;
            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds,durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d", elapsedHours,
                        elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d",elapsedMinutes,
                        elapsedSeconds);
            }
        }
    }

}
