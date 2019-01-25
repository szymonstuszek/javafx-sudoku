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
import java.util.List;
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
    private Label statusLabel;

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

        resetButton.setOnAction(event -> {
            sudokuSolver.resetGame();
            List<Integer> clearedList = sudokuSolver.getSudokuBoard().getAllValuesFromBoard();
            boardGridPane.updateSudokuGrid(clearedList);
            solveButton.setDisable(false);
            statusLabel.setText("");
        });

        solveButton.setOnAction(event -> {
            boardGridPane.solve();
            solveButton.setDisable(true);
        });
    }
}