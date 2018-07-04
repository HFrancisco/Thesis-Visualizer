package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String MEDIA_URL = "file:/C:/Users/Harry/Documents/_ACADS/_THESIS/Videos/CompiledAds.mp4";

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Thesis Visualizer V2");
        Group root = new Group();
        Scene scene = new Scene(root, 1800, 760);

        // create media player
        Media media = new Media(MEDIA_URL);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        UI_Controller uiController = new UI_Controller(mediaPlayer);
        scene.setRoot(uiController);

        /*UI_Controller uiCont = new UI_Controller(mediaPlayer);
        scene.setRoot(uiCont);*/

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
