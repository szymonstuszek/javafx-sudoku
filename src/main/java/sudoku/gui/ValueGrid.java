package sudoku.gui;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import sudoku.model.SudokuBoard;
import sudoku.model.SudokuSolver;

import java.util.ArrayList;
import java.util.List;

public class ValueGrid extends GridPane {
    private List<Button> valueButtons = new ArrayList<>();
    private SudokuGrid sudokuGrid;
    private SudokuSolver sudokuSolver;

    public ValueGrid() {
        this.getStyleClass().add("value-grid");
        initGrid();
        disableAllValueButtons();
    }

    public void disableAllValueButtons() {
        valueButtons.forEach(e -> e.setDisable(true));
    }

    private void passValueToSolver(int selectedColumn, int selectedRow, int value) {
        disableAllValueButtons();

        SudokuBoard sudokuBoard = sudokuSolver.getSudokuBoard();
        sudokuBoard.setValueOnBoard(sudokuGrid.getSelectedColumn(), sudokuGrid.getSelectedRow(), value);
        sudokuSolver.checkIfBoardIsValid();

        int buttonIndex = sudokuGrid.getButtonIndex(selectedColumn, selectedRow);
        SudokuButton button = sudokuGrid.getButtonUnderGivenIndex(buttonIndex);
        button.markButtonAsFilled();

        List<Integer> updatedBoardValues = sudokuBoard.getAllValuesFromBoard();
        sudokuGrid.updateSudokuGrid(updatedBoardValues);
    }

    public void updateValues(List<Integer> availableValues) {
        for (int i = 0; i < 9; i++) {
            if(!availableValues.contains(i+1)) {
                valueButtons.get(i).setDisable(true);
            } else {
                valueButtons.get(i).setDisable(false);
            }
        }
    }

    private void initGrid() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                count++;
                ValueButton valueButton = new ValueButton(String.valueOf(count));
                GridPane.setRowIndex(valueButton, i);
                GridPane.setColumnIndex(valueButton, j);

                valueButton.setOnAction(event -> {
                    int valueFromButton = Integer.valueOf(valueButton.getText());
                    passValueToSolver(sudokuGrid.getSelectedColumn(), sudokuGrid.getSelectedRow(), valueFromButton);
                });

                valueButtons.add(valueButton);
                this.getChildren().add(valueButton);
            }
        }
    }

    public void setSudokuSolver(SudokuSolver sudokuSolver) {
        this.sudokuSolver = sudokuSolver;
    }

    public void setSudokuGrid(SudokuGrid sudokuGrid) {
        this.sudokuGrid = sudokuGrid;
    }
}