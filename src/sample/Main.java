package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Thesis Visualizer V2");
        Group root = new Group();
        //Scene scene = new Scene(root, 900, 760); FOR OLD WITH NO SELECTOR
        Scene scene = new Scene(root, 300, 200);

        Start_Window startWin = new Start_Window();

        scene.setRoot(startWin);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
