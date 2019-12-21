package sudoku.gui;

import javafx.scene.control.Button;

public class SolveButton extends Button {
    private SudokuGrid sudokuGrid;

    public SolveButton() {
        this.getStyleClass().add("solve-button");
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        initSolveButton();
    }

    private void initSolveButton() {
        this.setOnAction(event -> {
            getSudokuGrid().solve();
            setDisable(true);
        });
    }

    public SudokuGrid getSudokuGrid() {
        return sudokuGrid;
    }

    public void setSudokuGrid(SudokuGrid sudokuGrid) {
        this.sudokuGrid = sudokuGrid;
    }
}
