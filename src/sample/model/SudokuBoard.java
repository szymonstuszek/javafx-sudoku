package sample.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuBoard extends Prototype {
    private List<SudokuRow> rows = new ArrayList<>();

    public SudokuBoard() {
        createBoard();
    }

    private void createBoard() {
        for (int i = 0; i < Constants.SIZE_OF_BOARD; i++) {
            SudokuRow row = new SudokuRow();
            rows.add(row);
        }
    }

    public List<Integer> getAllValuesFromBoard() {
        List<SudokuElement> allElements = getAllElementsOnBoard();
        List<Integer> allValues = allElements.stream()
                .map(e -> e.getValue())
                .collect(Collectors.toList());
        return allValues;
    }

    public void resetBoard() {
        List<SudokuElement> allElements = getAllElementsOnBoard();
        allElements.forEach(e -> e.setValue(-1));

        for (int i = 0; i < 81; i++) {
            SudokuElement element = allElements.get(i);
            resetAvailableValues(element);
        }
    }

    private void resetAvailableValues(SudokuElement sudokuElement) {
            List<Integer> availableValues = new ArrayList<>();
            availableValues.add(1);
            availableValues.add(2);
            availableValues.add(3);
            availableValues.add(4);
            availableValues.add(5);
            availableValues.add(6);
            availableValues.add(7);
            availableValues.add(8);
            availableValues.add(9);
            sudokuElement.setAvailableValues(availableValues);
    }

    public List<SudokuRow> getRows() {
        return rows;
    }

    public SudokuElement getElementUnderGivenIndexes(int column, int row) {
        SudokuRow chosenRow = rows.get(row);
        return chosenRow.getElements().get(column);
    }

    private boolean isFieldEmpty(int column, int row) {
        SudokuElement chosenElement = getElementUnderGivenIndexes(column, row);

        if(chosenElement.isEmpty()) {
            return true;
        } else {
            System.out.println("There is already a value in this field!");
            return false;
        }
    }

    private boolean isValidValue(int column, int row, int value) {
        SudokuElement sudokuElement = getElementUnderGivenIndexes(column, row);

        if (sudokuElement.getAvailableValues().contains(value)) {
            return true;
        } else {
            System.out.println(value + " is not a valid value!");
            return false;
        }
    }

    public void setValueOnBoard(int column, int row, int value) {
        if(isFieldEmpty(column, row) &&
                isValidValue(column, row, value)) {
            SudokuElement chosenElement = getElementUnderGivenIndexes(column, row);
            chosenElement.setValue(value);
        }
    }

    public void checkAvailableValues(int column, int row) {
        SudokuElement chosenElement = getElementUnderGivenIndexes(column, row);
        List<Integer> values = chosenElement.getAvailableValues();

        System.out.println("Count of available values: " + values.size() +
                " at field column " + (column + 1) + " row " + (row + 1));
        values.forEach(System.out::print);
        System.out.println();
    }

    public SudokuBoard deepCopy() throws CloneNotSupportedException {
        SudokuBoard clonedBoard = (SudokuBoard) super.clone();
        clonedBoard.rows = new ArrayList<>();

        for (SudokuRow row : rows) {
            SudokuRow clonedRow = new SudokuRow();
            List<SudokuElement> elements = new ArrayList<>();

            for (SudokuElement element : row.getElements()) {
                SudokuElement clonedElement = new SudokuElement();

                clonedElement.setValue(element.getValue());
                elements.add(clonedElement);
            }

            clonedRow.setElements(elements);
            clonedBoard.getRows().add(clonedRow);
        }

        return clonedBoard;
    }

    private List<SudokuElement> getAllElementsOnBoard() {
        List<SudokuElement> allElementsOnBoard = rows.stream()
                .flatMap(sudokuRow -> sudokuRow.getElements().stream())
                .collect(Collectors.toList());

        return allElementsOnBoard;
    }

    public boolean isAnyElementEmpty() {
        List<SudokuElement> allElements = getAllElementsOnBoard();

        return allElements.stream()
                .anyMatch(e -> e.getValue() == -1);
    }

    public boolean isBoardSolved() {
        List<SudokuElement> allElements = getAllElementsOnBoard();
        boolean allElementsFilledOut;

        allElementsFilledOut = allElements.stream()
                .allMatch(e -> e.getValue() != -1);

        return allElementsFilledOut;
    }

    public List<SudokuElement> getElementsInRow(int row) {
        List<SudokuRow> allRows = getRows();
        return allRows.get(row).getElements();
    }

    public List<SudokuElement> getElementsInColumn(int column) {
        List<SudokuElement> allElementsFromColumn = new ArrayList<>();
        List<SudokuRow> allRows = getRows();

        for (int row = 0; row < Constants.SIZE_OF_BOARD; row++) {
            List<SudokuElement> elementsFromRow = allRows.get(row).getElements();
            SudokuElement elementFromColumn = elementsFromRow.get(column);
            allElementsFromColumn.add(elementFromColumn);
        }

        return allElementsFromColumn;
    }

    public List<SudokuElement> getElementsInBlock(int column, int row) {
        List<SudokuElement> allElementsFromBlock = new ArrayList<>();
        int r = row - row % 3;
        int c = column - column % 3;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                SudokuElement elementInBlock = getElementUnderGivenIndexes(j, i);
                allElementsFromBlock.add(elementInBlock);
            }
        }

        return allElementsFromBlock;
    }

    @Override
    public String toString() {
        String boardString = "";

        for (int i = 0; i < rows.size(); i++) {
            if(i == 8) {
                boardString += rows.get(i).toString();
            } else {
                boardString += rows.get(i).toString() + "\n";
            }
        }
        return "\n-------------------\n" +
                boardString + "\n" +
                "-------------------";
    }
}