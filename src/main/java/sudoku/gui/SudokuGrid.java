package sudoku.gui;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sudoku.model.SudokuBoard;
import sudoku.model.SudokuElement;
import sudoku.model.SudokuSolver;

import java.util.ArrayList;
import java.util.List;

public class SudokuGrid extends GridPane {
    private List<SudokuButton> buttons = new ArrayList<>();
    private int selectedColumn = -1;
    private int selectedRow = -1;

    private ValueGrid valueGrid;
    private InfoLabel infoLabel;
    private StatusLabel statusLabel;
    private SolveButton solveButton;

    private SudokuSolver sudokuSolver;


    public SudokuGrid() {
        this.getStyleClass().add("sudoku-grid");
        initGrid();
        initEventFilter();
    }


    public void solve() {
        List<Integer> resolvedBoardValueList = sudokuSolver.solve();
        valueGrid.disableAllValueButtons();
        if(resolvedBoardValueList == null) {
            statusLabel.setText("No solution");
        } else {
            updateSudokuGrid(resolvedBoardValueList);
            statusLabel.setText("Solved!");
        }
    }

    public void updateSudokuGrid(List<Integer> values) {
        for (int i = 0; i < 81; i++) {
            int valueFromAlgorithm = values.get(i);
            buttons.get(i).setValue(valueFromAlgorithm);
            buttons.get(i).updateButtonDisplay();

            if(countNumberOfEmptyFields() == 0) {
                solveButton.setDisable(true);
                statusLabel.setText("Solved!");
            } else {
                statusLabel.setText("Fields remaining: " + countNumberOfEmptyFields());
            }

        }
    }

    public void updateSudokuGridAfterReset(List<Integer> values) {
        for (int i = 0; i < 81; i++) {
            int valueFromAlgorithm = values.get(i);
            buttons.get(i).setValue(valueFromAlgorithm);
            buttons.get(i).updateButtonDisplay();
            buttons.get(i).getStyleClass().clear();
            buttons.get(i).getStyleClass().addAll("sudoku-button", "button");
        }
    }

    public long countNumberOfEmptyFields() {
        long emptyFieldCount = buttons.stream()
                .filter(e -> e.getValue() == -1)
                .count();

        return emptyFieldCount;
    }

    public int getButtonIndex(int column, int row) {
        int elementIndex = row * 9 + column;
        if(elementIndex > -1 && elementIndex < 81) {
            return elementIndex;
        }
        return -1;
    }

    public SudokuButton getButtonUnderGivenIndex(int index) {
        if(index > -1 && index < 81) {
            return buttons.get(index);
        }
        return null;
    }

    private void initGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuButton field = new SudokuButton(i, j);

                field.setPrefSize(20.0, 20.0);
                field.setMinSize(50, 50);

                GridPane.setRowIndex(field, i);
                GridPane.setColumnIndex(field, j);
                GridPane.setFillHeight(field, true);
                GridPane.setFillWidth(field, true);

                buttons.add(field);
                this.getChildren().add(field);
            }
        }
    }

    private void initEventFilter() {
        this.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for( Node node: getChildren()) {

                    if( node instanceof SudokuButton) {
                        if( node.getBoundsInParent().contains(event.getSceneX(),  event.getSceneY())) {
                            infoLabel.setText("Column: " + (GridPane.getColumnIndex(node) + 1) +
                                    " Row: " + (GridPane.getRowIndex(node) + 1));

                            setSelectedRow(GridPane.getRowIndex(node));
                            setSelectedColumn(GridPane.getColumnIndex(node));

                            int buttonIndex = getButtonIndex(selectedColumn, selectedRow);
                            SudokuButton button = getButtonUnderGivenIndex(buttonIndex);

                            if(button.getValue() == -1) {
                                SudokuBoard sudokuBoard = sudokuSolver.getSudokuBoard();
                                SudokuElement sudokuElement = sudokuBoard.getElementUnderGivenIndexes(selectedColumn, selectedRow);
                                List<Integer> availableValues = sudokuElement.getAvailableValues();
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

    public SudokuSolver getSudokuSolver() {
        return sudokuSolver;
    }

    public void setSudokuSolver(SudokuSolver sudokuSolver) {
        this.sudokuSolver = sudokuSolver;
    }

    public Label getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(StatusLabel statusLabel) {
        this.statusLabel = statusLabel;
    }

    public SolveButton getSolveButton() {
        return solveButton;
    }

    public void setSolveButton(SolveButton solveButton) {
        this.solveButton = solveButton;
    }

    public Label getInfoLabel() {
        return infoLabel;
    }

    public void setInfoLabel(InfoLabel infoLabel) {
        this.infoLabel = infoLabel;
    }
}