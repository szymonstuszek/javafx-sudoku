package sudoku.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sudoku.gui.*;
import sudoku.model.SudokuSolver;

import java.net.URL;
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
    private SolveButton solveButton;

    @FXML
    private ResetButton resetButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boardGridPane.setInfoLabel(infoLabel);
        boardGridPane.setValueGrid(valueGridPane);
        boardGridPane.setSudokuSolver(sudokuSolver);
        boardGridPane.setStatusLabel(statusLabel);
        boardGridPane.setSolveButton(solveButton);

        valueGridPane.setSudokuGrid(boardGridPane);
        valueGridPane.setSudokuSolver(sudokuSolver);

        resetButton.setSudokuGrid(boardGridPane);
        solveButton.setSudokuGrid(boardGridPane);
        System.out.println(infoLabel.getText());
    }
}