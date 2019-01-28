package sudoku.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class StatusLabel extends Label {

    public StatusLabel() {
        this.getStyleClass().add("status-label");
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setAlignment(Pos.CENTER);
    }
}
