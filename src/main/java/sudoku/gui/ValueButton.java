package sudoku.gui;

import javafx.scene.control.Button;

public class ValueButton extends Button {

    public ValueButton(String value) {
        this.setText(value);
        getStyleClass().add("value-button");
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }
}
