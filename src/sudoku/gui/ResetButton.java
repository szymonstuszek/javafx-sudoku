package sudoku.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.List;

public class ResetButton extends Button {
    private SudokuGrid sudokuGrid;

    public ResetButton() {
        this.getStyleClass().add("reset-button");
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        initResetButton();
    }

    private void initResetButton() {
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetBoard();
            }
        });
    }

    public void setSudokuGrid(SudokuGrid sudokuGrid) {
        this.sudokuGrid = sudokuGrid;
    }

    private void resetBoard() {
        sudokuGrid.getSudokuSolver().resetGame();
        List<Integer> clearedList = sudokuGrid.getSudokuSolver().getSudokuBoard().getAllValuesFromBoard();
        sudokuGrid.updateSudokuGridAfterReset(clearedList);
        sudokuGrid.getSolveButton().setDisable(false);
        sudokuGrid.getInfoLabel().setText("Select a field");
        sudokuGrid.getStatusLabel().setText("");
        sudokuGrid.getValueGrid().disableAllValueButtons();
    }
}
