package sudoku.gui;


import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class InfoLabel extends Label {

    public InfoLabel() {
        setFont(new Font(20));
        this.getStyleClass().add("info-label");
    }
}
