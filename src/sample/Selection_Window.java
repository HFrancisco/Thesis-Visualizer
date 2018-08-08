package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Selection_Window extends BorderPane {

    private static final String MEDIA1_URL = "file:/C:/Users/Harry/Documents/_ACADS/_THESIS/Videos/media1.mp4";
    private static final String MEDIA2_URL = "file:/C:/Users/Harry/Documents/_ACADS/_THESIS/Videos/CompiledAds2.mp4";

    private VBox vBox;
    private Button dg1Button;
    private Button dg2Button;
    private Label instructionLabel;

    public Selection_Window(){

        vBox = new VBox();

        instructionLabel = new Label("Select which to view:");

        dg1Button = new Button("Line Chart");
        dg1Button.setPrefSize(110,30);
        dg1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // create media player
                Media media = new Media(MEDIA1_URL);
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);

                UI_Controller uiController = new UI_Controller(mediaPlayer,1);

                Stage stage = new Stage();
                stage.setTitle("Thesis Visualizer V2");
                stage.setScene(new Scene(uiController, 900,760));
                stage.show();

                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
        });

        dg2Button = new Button("Bar Chart");
        dg2Button.setPrefSize(110,30);
        dg2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // create media player
                Media media = new Media(MEDIA1_URL);
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);

                UI_Controller uiController = new UI_Controller(mediaPlayer,2);

                Stage stage = new Stage();
                stage.setTitle("Thesis Visualizer V2");
                stage.setScene(new Scene(uiController, 900,760));
                stage.show();

                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
        });

        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        vBox.getChildren().add(instructionLabel);
        vBox.getChildren().add(dg1Button);
        vBox.getChildren().add(dg2Button);

        this.setCenter(vBox);
    }

}
