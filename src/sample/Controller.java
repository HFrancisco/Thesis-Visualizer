package sample;

import javafx.application.Platform;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Controller{

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


    protected void Controller_Process(Duration pDuration, XYChart.Series pSeries, Label pPlayTime,
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

                    Check_Flags(currentTime);

                    Check_Time(currentTime, pSeries);
                }
            });
        }
    }


    private void Check_Flags(Duration pTime){

        if(flag1 == 0 && pTime.greaterThanOrEqualTo(Duration.ZERO) && pTime.lessThanOrEqualTo(new Duration(109000)))
            flag1 = 1;
        else if(flag2 == 0 && pTime.greaterThanOrEqualTo(new Duration(110000)) && pTime.lessThanOrEqualTo(new Duration(139000)))
            flag2 = 1;
        else if(flag3 == 0 && pTime.greaterThanOrEqualTo(new Duration(140000)) && pTime.lessThanOrEqualTo(new Duration(259000)))
            flag3 = 1;
        else if(flag4 == 0 && pTime.greaterThanOrEqualTo(new Duration(260000)) && pTime.lessThanOrEqualTo(new Duration(349000)))
            flag4 = 1;
        else if(flag5 == 0 && pTime.greaterThanOrEqualTo(new Duration(350000)) && pTime.lessThanOrEqualTo(new Duration(416000)))
            flag5 = 1;
        else if(flag6 == 0 && pTime.greaterThanOrEqualTo(new Duration(417000)) && pTime.lessThanOrEqualTo(new Duration(537000)))
            flag6 = 1;
        else if(flag7 == 0 && pTime.greaterThanOrEqualTo(new Duration(538000)) && pTime.lessThanOrEqualTo(new Duration(593000)))
            flag7 = 1;
        else if(flag8 == 0 && pTime.greaterThanOrEqualTo(new Duration(594000)) && pTime.lessThanOrEqualTo(new Duration(653000)))
            flag8 = 1;
        else if(flag9 == 0 && pTime.greaterThanOrEqualTo(new Duration(654000)) && pTime.lessThanOrEqualTo(new Duration(713000)))
            flag9 = 1;
        else if(flag10 == 0 && pTime.greaterThanOrEqualTo(new Duration(714000)) && pTime.lessThanOrEqualTo(new Duration(774000)))
            flag10 = 1;
        else if(flag11 == 0 && pTime.greaterThanOrEqualTo(new Duration(775000)) && pTime.lessThanOrEqualTo(new Duration(837000)))
            flag11 = 1;

    }

    private void Check_Time(Duration pTime, XYChart.Series pSeries){

        // Update Chart values
        if(flag1 == 1 && pTime.greaterThanOrEqualTo(Duration.ZERO) && pTime.lessThanOrEqualTo(new Duration(109000))){                      // For 1st vid
            flag1 = 2;

            pSeries.setName("Video 1");

            Update_Values(pSeries, 16, 4, 12, 1, 9);

        } else if(flag2 == 1 && pTime.greaterThanOrEqualTo(new Duration(110000)) && pTime.lessThanOrEqualTo(new Duration(139000))){  // For 2nd vid
            flag2 = 2;

            //Doritos

            pSeries.setName("Video 2");

            Update_Values(pSeries, 16, 4, 13, 1, 13);

        } else if(flag3 == 1 && pTime.greaterThanOrEqualTo(new Duration(140000)) && pTime.lessThanOrEqualTo(new Duration(259000))){  // For 3rd vid
            flag3 = 2;

            //PnG

            pSeries.setName("Video 3");

            Update_Values(pSeries, 19, 1, 15, 11, 5);

        } else if(flag4 == 1 && pTime.greaterThanOrEqualTo(new Duration(260000)) && pTime.lessThanOrEqualTo(new Duration(349000))){  // For 4th vid
            flag4 = 2;

            pSeries.setName("Video 4");

            Update_Values(pSeries, 19, 1, 17, 8, 6);

        } else if(flag5 == 1 && pTime.greaterThanOrEqualTo(new Duration(350000)) && pTime.lessThanOrEqualTo(new Duration(416000))){  // For 5th vid
            flag5 = 2;

            //Hornbach

            pSeries.setName("Video 5");

            Update_Values(pSeries, 10, 10, 8, 7, 12);

        } else if(flag6 == 1 && pTime.greaterThanOrEqualTo(new Duration(417000)) && pTime.lessThanOrEqualTo(new Duration(537000))){  // For 6th vid
            flag6 = 2;

            pSeries.setName("Video 6");

            Update_Values(pSeries, 16, 4, 17, 1, 3);

        } else if(flag7 == 1 && pTime.greaterThanOrEqualTo(new Duration(538000)) && pTime.lessThanOrEqualTo(new Duration(593000))){  // For 7th vid
            flag7 = 2;

            pSeries.setName("Video 7");

            Update_Values(pSeries, 4, 16, 2, 1, 1);

        } else if(flag8 == 1 && pTime.greaterThanOrEqualTo(new Duration(594000)) && pTime.lessThanOrEqualTo(new Duration(653000))){  // For 8th vid
            flag8 = 2;

            pSeries.setName("Video 8");

            Update_Values(pSeries, 19, 1, 19, 0, 4);

        } else if(flag9 == 1 && pTime.greaterThanOrEqualTo(new Duration(654000)) && pTime.lessThanOrEqualTo(new Duration(713000))){  // For 9th vid
            flag9 = 2;

            pSeries.setName("Video 9");

            Update_Values(pSeries, 15, 5, 2, 6, 4);

        } else if(flag10 == 1 && pTime.greaterThanOrEqualTo(new Duration(714000)) && pTime.lessThanOrEqualTo(new Duration(774000))){  // For 10th vid
            flag10 = 2;

            pSeries.setName("Video 10");

            Update_Values(pSeries, 12, 8, 13, 1, 9);

        } else if(flag11 == 1 && pTime.greaterThanOrEqualTo(new Duration(775000)) && pTime.lessThanOrEqualTo(new Duration(837000))){  // For 11th vid
            flag11 = 2;

            pSeries.setName("Video 11");

            Update_Values(pSeries, 15, 5, 12, 0, 3);

        }

    }

    private void Update_Values(XYChart.Series pSeries, int pInt, int pInd, int pHap, int pSad, int pSur){

        pSeries.getData().add(new XYChart.Data("Interest", pInt));
        pSeries.getData().add(new XYChart.Data("Indifferent"  , pInd));
        pSeries.getData().add(new XYChart.Data("Happiness"  , pHap));
        pSeries.getData().add(new XYChart.Data("Sadness"  , pSad));
        pSeries.getData().add(new XYChart.Data("Surprise"  , pSur));

    }

    private static String Format_Time(Duration elapsed, Duration duration) {
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
