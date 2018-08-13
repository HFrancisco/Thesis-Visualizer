package sample;

import javafx.application.Platform;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Controller{

    protected void Controller_Process(Duration pDuration, Label pPlayTime,
                                      Slider pTimeSlider, Slider pVolumeSlider, MediaPlayer pMP) {

        if (pPlayTime != null && pTimeSlider != null && pVolumeSlider != null) {
            Platform.runLater(new Runnable() {
                public void run() {
                    Duration currentTime = pMP.getCurrentTime();
                    pPlayTime.setText(Format_Time(currentTime, pDuration));
                    pTimeSlider.setDisable(pDuration.isUnknown());
                    if (!pTimeSlider.isDisabled()
                            && pDuration.greaterThan(Duration.ZERO)
                            && !pTimeSlider.isValueChanging()) {
                        pTimeSlider.setValue(currentTime.divide(pDuration).toMillis()
                                * 100.0);
                    }
                    if (!pVolumeSlider.isValueChanging()) {
                        pVolumeSlider.setValue((int)Math.round(pMP.getVolume()
                                * 100));
                    }

                }
            });
        }
    }

    public static String Format_Time(Duration elapsed, Duration duration) {
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
