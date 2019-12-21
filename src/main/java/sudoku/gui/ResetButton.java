package sudoku.gui;

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
        this.setOnAction(event -> resetBoard());
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

    public void setSudokuGrid(SudokuGrid sudokuGrid) {
        this.sudokuGrid = sudokuGrid;
    }
}
