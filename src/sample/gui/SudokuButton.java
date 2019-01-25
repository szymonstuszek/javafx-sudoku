package sample.gui;

import javafx.beans.DefaultProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

@DefaultProperty(value = "extension")
public class SudokuButton extends Button {
    private final int rowIndex;
    private final int colIndex;
    private int value = -1;

    public SudokuButton(final int rowIndex, final int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        initField();
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void updateButtonView() {
        if(value == -1) this.setText("");
        if(value == 1) this.setText("");
        if(value == 2) this.setText("");
        if(value == 3) this.setText("");
        if(value == 4) this.setText("");
        if(value == 5) this.setText("");
        if(value == 6) this.setText("");
        if(value == 7) this.setText("");
        if(value == 8) this.setText("");
        if(value == 9) this.setText("");
    }

    private void initField() {
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Element row: " + getRowIndex() + " element col: " + getColIndex());
            }
        });
    }
}
