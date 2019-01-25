package sample.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.List;

public class ResetButton extends Button {
    private SudokuGrid sudokuGrid;

    public ResetButton() {
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
        List<SudokuButton> buttons = sudokuGrid.getButtons();
        buttons.forEach(b -> b.setValue(-1));
        buttons.forEach(b -> b.setText(""));
    }
}
