package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import sample.model.Algorithm;
import sample.gui.ResetButton;
import sample.gui.SudokuGrid;
import sample.gui.ValueGrid;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    private Algorithm algorithm = new Algorithm();

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private SudokuGrid boardGridPane;

    @FXML
    private Label infoLabel;

    @FXML
    private ValueGrid valueGridPane;

    @FXML
    private Button solveButton;

    @FXML
    private ResetButton resetButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardGridPane.setLabel(infoLabel);
        boardGridPane.setAlgorithm(algorithm);
        boardGridPane.setValueGrid(valueGridPane);
        valueGridPane.setSudokuGrid(boardGridPane);
        valueGridPane.setAlgorithm(algorithm);

        resetButton.setSudokuGrid(boardGridPane);


        solveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boardGridPane.getButtons().forEach(e -> e.setText(String.valueOf(new Random().nextInt(9) + 1)));
            }
        });

    }


}