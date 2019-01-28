package sudoku.gui;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class InfoLabel extends Label {

    public InfoLabel() {
        this.getStyleClass().add("info-label");
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setAlignment(Pos.CENTER);
        setFont(new Font(20));
        setText("Select a field");
    }
}
