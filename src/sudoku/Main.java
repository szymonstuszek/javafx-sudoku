package sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent parent = FXMLLoader.load(getClass().getResource("view/sudoku.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Sudoku Solver");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/resources/sudoku.png")));
        primaryStage.setMinWidth(800.0);
        primaryStage.setMinHeight(600.0);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
