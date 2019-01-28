package sudoku.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import sudoku.gui.*;
import sudoku.model.SudokuSolver;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    private SudokuSolver sudokuSolver = new SudokuSolver();

    @FXML
    private SudokuBorderPane mainBorderPane;

    @FXML
    private SudokuGrid boardGridPane;

    @FXML
    private ValueGrid valueGridPane;

    @FXML
    private InfoLabel infoLabel;

    @FXML
    private StatusLabel statusLabel;

    @FXML
    private Button solveButton;

    @FXML
    private ResetButton resetButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardGridPane.setLabel(infoLabel);
        boardGridPane.setValueGrid(valueGridPane);
        boardGridPane.setSudokuSolver(sudokuSolver);
        boardGridPane.setStatusLabel(statusLabel);

        valueGridPane.setSudokuGrid(boardGridPane);
        valueGridPane.setSudokuSolver(sudokuSolver);

        resetButton.setSudokuGrid(boardGridPane);
        resetButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        resetButton.setOnAction(event -> {
            sudokuSolver.resetGame();
            List<Integer> clearedList = sudokuSolver.getSudokuBoard().getAllValuesFromBoard();
            boardGridPane.updateSudokuGridAfterReset(clearedList);
            solveButton.setDisable(false);
            statusLabel.setText("");
            infoLabel.setText("Select a field");
            valueGridPane.disableAllValueButtons();
        });

        solveButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        solveButton.setOnAction(event -> {
            boardGridPane.solve();
            solveButton.setDisable(true);
        });


        infoLabel.setText("Select a field");
        infoLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        infoLabel.setAlignment(Pos.CENTER);

        statusLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        statusLabel.setAlignment(Pos.CENTER);

        valueGridPane.disableAllValueButtons();
    }
}