package sample.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import sample.model.Algorithm;

import java.util.ArrayList;
import java.util.List;

public class ValueGrid extends GridPane {
    private List<Button> valueButtons = new ArrayList<>();

    private Algorithm algorithm;

    private SudokuGrid sudokuGrid;

    public void disableAllValueButtons() {
        valueButtons.forEach(e -> e.setDisable(true));
    }

    private void passValueToAlgorithm(int value) {
        algorithm.updateBoardAfterSettingNumber(value);

        //to implement passing values to the backend algorithm


        int row = sudokuGrid.getSelectedRow();
        int column = sudokuGrid.getSelectedColumn();

        disableAllValueButtons();

        sudokuGrid.setValueOnBoard(column, row, value);
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
                GridPane.setRowIndex(valueButton, i);
                GridPane.setColumnIndex(valueButton, j);


                valueButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int valueFromButton = Integer.valueOf(valueButton.getText());
                        System.out.println("Passing to algorithm: " + valueFromButton);
                        passValueToAlgorithm(valueFromButton);

                    }
                });


                valueButtons.add(valueButton);
                this.getChildren().add(valueButton);
            }
        }
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public SudokuGrid getSudokuGrid() {
        return sudokuGrid;
    }

    public void setSudokuGrid(SudokuGrid sudokuGrid) {
        this.sudokuGrid = sudokuGrid;
    }
}
