package sample;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import jdk.nashorn.internal.runtime.options.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SudokuGrid extends GridPane {

    private List<SudokuButton> buttons = new ArrayList<>();

    private String selectedField;

    private int selectedColumn = -1;

    private int selectedRow = -1;

    private Algorithm algorithm;

    private ValueGrid valueGrid;

    private Label label;

    public SudokuGrid() {
        initGrid();
        initEventFilter();
    }

    private int getButtonIndex(int column, int row) {
        int elementIndex = row * 9 + column;
        if(elementIndex > -1 && elementIndex < 81) {
            return elementIndex;
        }
        return -1;
    }

    //not return null?
    public SudokuButton getButtonUnderGivenIndex(int index) {
        if(index > -1 && index < 81) {
            return buttons.get(index);
        }
        return null;
    }

    public void setValueOnBoard(int column, int row, int value) {
        int buttonIndex = getButtonIndex(column, row);
        buttons.get(buttonIndex).setText(String.valueOf(value));
        buttons.get(buttonIndex).setValue(value);
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    private void initGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuButton field = new SudokuButton(i, j);
                field.setPrefSize(20.0, 20.0);
                field.setMinSize(50, 50);
                GridPane.setRowIndex(field, i);
                GridPane.setColumnIndex(field, j);

                buttons.add(field);

                this.getChildren().add(field);
            }
        }

    }

    private List<Integer> passIndexesToAlgorithm(int columnIndex, int rowIndex) {
        return algorithm.showValuesUnderGivenIndexes(columnIndex, rowIndex);
    }

    private void initEventFilter() {
        this.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for( Node node: getChildren()) {

                    if( node instanceof SudokuButton) {
                        if( node.getBoundsInParent().contains(event.getSceneX(),  event.getSceneY())) {
                            setSelectedField("Field: " + GridPane.getRowIndex(node) + " " + GridPane.getColumnIndex(node));
                            label.setText(getSelectedField());

                            setSelectedRow(GridPane.getRowIndex(node));
                            setSelectedColumn(GridPane.getColumnIndex(node));

                            int buttonIndex = getButtonIndex(selectedColumn, selectedRow);
                            System.out.println("Button index: " + buttonIndex);

                            SudokuButton button = getButtonUnderGivenIndex(buttonIndex);

                            if(button.getValue() == -1) {
                                List<Integer> availableValues = passIndexesToAlgorithm(GridPane.getColumnIndex(node), GridPane.getRowIndex(node));
                                valueGrid.updateValues(availableValues);
                            } else {
                                valueGrid.disableAllValueButtons();
                            }

                        }
                    }
                }
            }
        });
    }

    public String getSelectedField() {
        return selectedField;
    }

    public void setSelectedField(String selectedField) {
        this.selectedField = selectedField;
    }

    public ValueGrid getValueGrid() {
        return valueGrid;
    }

    public void setValueGrid(ValueGrid valueGrid) {
        this.valueGrid = valueGrid;
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

    public List<SudokuButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<SudokuButton> buttons) {
        this.buttons = buttons;
    }
}
