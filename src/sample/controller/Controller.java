package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import sample.gui.ResetButton;
import sample.gui.SudokuGrid;
import sample.gui.ValueGrid;
import sample.model.SudokuSolver;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    private SudokuSolver sudokuSolver = new SudokuSolver();

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private SudokuGrid boardGridPane;

    @FXML
    private ValueGrid valueGridPane;

    @FXML
    private Label infoLabel;

    @FXML
    private Button solveButton;

    @FXML
    private ResetButton resetButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardGridPane.setLabel(infoLabel);
        boardGridPane.setValueGrid(valueGridPane);
        boardGridPane.setSudokuSolver(sudokuSolver);

        valueGridPane.setSudokuGrid(boardGridPane);
        valueGridPane.setSudokuSolver(sudokuSolver);

        resetButton.setSudokuGrid(boardGridPane);
    }
}