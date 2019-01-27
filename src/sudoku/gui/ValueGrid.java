package sudoku.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import sudoku.model.SudokuBoard;
import sudoku.model.SudokuSolver;

import java.util.ArrayList;
import java.util.List;

public class ValueGrid extends GridPane {
    private List<Button> valueButtons = new ArrayList<>();
    private int selectedColumn = 0;
    private int selectedRow = 0;
    private SudokuGrid sudokuGrid;
    private SudokuSolver sudokuSolver;

    public void disableAllValueButtons() {
        valueButtons.forEach(e -> e.setDisable(true));
    }

    private void passValueToSolver(int selectedColumn, int selectedRow, int value) {
        disableAllValueButtons();

        System.out.println("Passing to algorithm col: " + selectedColumn + " row: " + selectedRow + " value: " + value);
        SudokuBoard sudokuBoard = sudokuSolver.getSudokuBoard();
        sudokuBoard.setValueOnBoard(selectedColumn, selectedRow, value);
        sudokuSolver.checkIfBoardIsValid();

        List<Integer> updatedBoardValues = sudokuBoard.getAllValuesFromBoard();

        int buttonIndex = sudokuGrid.getButtonIndex(selectedColumn, selectedRow);
        System.out.println("Button index: " + buttonIndex);

        SudokuButton button = sudokuGrid.getButtonUnderGivenIndex(buttonIndex);

        button.getStyleClass().add("sudoku-button-filled");

       sudokuGrid.updateSudokuGrid(updatedBoardValues);
    }

    public ValueGrid() {
        initGrid();
    }

    public void updateValues(List<Integer> availableValues) {

        if(!availableValues.contains(1)) {
            valueButtons.get(0).setDisable(true);
        } else {
            valueButtons.get(0).setDisable(false);
        }

        if(!availableValues.contains(2)) {
            valueButtons.get(1).setDisable(true);
        } else {
            valueButtons.get(1).setDisable(false);
        }

        if(!availableValues.contains(3)) {
            valueButtons.get(2).setDisable(true);
        } else {
            valueButtons.get(2).setDisable(false);
        }

        if(!availableValues.contains(4)) {
            valueButtons.get(3).setDisable(true);
        } else {
            valueButtons.get(3).setDisable(false);
        }

        if(!availableValues.contains(5)) {
            valueButtons.get(4).setDisable(true);
        } else {
            valueButtons.get(4).setDisable(false);
        }

        if(!availableValues.contains(6)) {
            valueButtons.get(5).setDisable(true);
        } else {
            valueButtons.get(5).setDisable(false);
        }

        if(!availableValues.contains(7)) {
            valueButtons.get(6).setDisable(true);
        } else {
            valueButtons.get(6).setDisable(false);
        }

        if(!availableValues.contains(8)) {
            valueButtons.get(7).setDisable(true);
        } else {
            valueButtons.get(7).setDisable(false);
        }

        if(!availableValues.contains(9)) {
            valueButtons.get(8).setDisable(true);
        } else {
            valueButtons.get(8).setDisable(false);
        }
    }

    private void initGrid() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                count++;
                Button valueButton = new Button(String.valueOf(count));
                valueButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                GridPane.setRowIndex(valueButton, i);
                GridPane.setColumnIndex(valueButton, j);

                valueButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int valueFromButton = Integer.valueOf(valueButton.getText());
                        System.out.println("WHats inside: " + valueFromButton);
                        passValueToSolver(selectedColumn, selectedRow, valueFromButton);
                    }
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

    public int getSelectedColumn() {
        return selectedColumn;
    }

    public void setSelectedColumn(int selectedColumn) {
        this.selectedColumn = selectedColumn;
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }
}
