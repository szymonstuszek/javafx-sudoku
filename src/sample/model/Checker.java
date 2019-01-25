package sample.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Checker {
    private SudokuBoard sudokuBoard;

    public Checker(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    public boolean checkIfBoardIsValid() {
        updateBoard();
        return !(checkIfThereAreErrors() > 0);
    }

    private void updateBoard() {
        boolean anyActionTaken = true;

        while(anyActionTaken) {
            int actionsCount = 0;

            for (int row = 0;  row < Constants.SIZE_OF_BOARD; row++) {
                for (int column = 0; column < Constants.SIZE_OF_BOARD; column++) {
                    SudokuElement currentElement = sudokuBoard.getElementUnderGivenIndexes(column, row);

                    if (currentElement.isEmpty()) {
                        List<SudokuElement> elementsInRow = sudokuBoard.getElementsInRow(row);
                        List<SudokuElement> elementsInColumn = sudokuBoard.getElementsInColumn(column);
                        List<SudokuElement> elementsInBlock = sudokuBoard.getElementsInBlock(column, row);

                        actionsCount += updateAvailableValuesForElement(currentElement, elementsInRow);
                        actionsCount += updateAvailableValuesForElement(currentElement, elementsInColumn);
                        actionsCount += updateAvailableValuesForElement(currentElement, elementsInBlock);
                    }
                }
            }

            actionsCount += updateElementsWithOneAvailableValueLeft();

            if(actionsCount == 0) {
                anyActionTaken = false;
            }
        }
    }

    private int updateElementsWithOneAvailableValueLeft() {
        int actionsCount = 0;
        for (int row = 0;  row < Constants.SIZE_OF_BOARD; row++) {
            for (int column = 0; column < Constants.SIZE_OF_BOARD; column++) {
                SudokuElement currentElement = sudokuBoard.getElementUnderGivenIndexes(column, row);
                if (currentElement.isEmpty()) {
                    if (currentElement.getAvailableValues().size() == 1) {
                        int lastAvailableValue = currentElement.getAvailableValues().get(0);
                        currentElement.setValue(lastAvailableValue);
                        actionsCount++;
                    }
                }
            }
        }
        return actionsCount;
    }

    private int updateAvailableValuesForElement(SudokuElement element, List<SudokuElement> elementList) {
        List<Integer> availableValues = element.getAvailableValues();
        List<Integer> valuesAssignedInList;
        int actionsCount = 0;

        valuesAssignedInList = elementList.stream()
                .filter(e -> e.getValue() != -1)
                .map(SudokuElement::getValue)
                .collect(Collectors.toList());

        if (availableValues.size() > 0) {
            for (Integer value : availableValues) {
                if (valuesAssignedInList.contains(Integer.valueOf(value))) {
                    element.markValueForRemoval(value);
                    actionsCount++;
                }
            }
            element.removeMarkedValues();
        }

        return actionsCount;
    }

    private int checkIfThereAreErrors() {
        int errorCount = 0;

        errorCount += checkForEmptyFieldsWithNoAvailableValues();
        errorCount += checkForValuesRepeatedInRows();
        errorCount += checkForValuesRepeatedInColumns();
        errorCount += checkForValuesRepeatedInBlocks();

        return errorCount;
    }

    private int checkForEmptyFieldsWithNoAvailableValues() {
        int errorCount = 0;
        for (int row = 0;  row < Constants.SIZE_OF_BOARD; row++) {
            for (int column = 0; column < Constants.SIZE_OF_BOARD; column++) {
                SudokuElement currentElement = sudokuBoard.getElementUnderGivenIndexes(column, row);
                if (currentElement.isEmpty() &&
                        currentElement.getAvailableValues().size() == 0) {
                    errorCount++;
                }
            }
        }

        return errorCount;
    }

    private int checkForValuesRepeatedInRows() {
        int errorCount = 0;

        for (int row = 0; row < Constants.SIZE_OF_BOARD; row++) {
            List<SudokuElement> elementsInRow = sudokuBoard.getElementsInRow(row);
            if (!containsUniqueValues(elementsInRow)) errorCount++;
        }

        return errorCount;
    }

    private int checkForValuesRepeatedInColumns() {
        int errorCount = 0;

        for (int column = 0; column < Constants.SIZE_OF_BOARD; column++) {
            List<SudokuElement> elementsInColumn = sudokuBoard.getElementsInColumn(column);
            if (!containsUniqueValues(elementsInColumn)) errorCount++;
        }

        return errorCount;
    }

    private int checkForValuesRepeatedInBlocks() {
        int errorCount = 0;

        for (int rowBlock = 0; rowBlock < Constants.COUNT_OF_BLOCKS; rowBlock++) {
            for (int columnBlock = 0; columnBlock < Constants.COUNT_OF_BLOCKS; columnBlock++) {
                List<SudokuElement> elementsInBlock =
                        sudokuBoard.getElementsInBlock(columnBlock * 3, rowBlock * 3);

                if (!containsUniqueValues(elementsInBlock)) errorCount++;
            }
        }

        return errorCount;
    }

    private boolean containsUniqueValues(List<SudokuElement> elementList) {
        List<Integer> valueList = elementList.stream()
                .map(e -> e.getValue())
                .filter(value -> value != -1)
                .collect(Collectors.toList());

        Set<Integer> set = new HashSet<>();

        for(Integer i: valueList) {
            if (!set.add(i)) {
                return false;
            }
        }
        return true;
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    public void setSudokuBoard(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }
}