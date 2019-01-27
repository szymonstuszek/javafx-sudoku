package sudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuElement {
    private int value;
    private List<Integer> availableValues = new ArrayList<>();

    public SudokuElement() {
        this.value = -1;
        createValuesForEmptyField();
    }

    private void createValuesForEmptyField() {
        for (int i = 0; i < 9; i++) {
            availableValues.add(i+1);
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Integer> getAvailableValues() {
        return availableValues;
    }

    public boolean isEmpty() {
        return this.value == -1;
    }

    public void removeValueFromAvailableValues(int i) {
        availableValues.remove(Integer.valueOf(i));
    }

    public void markValueForRemoval(int valueToRemoveFromList) {
        if (availableValues.contains(Integer.valueOf(valueToRemoveFromList))) {
            int indexOfValueToRemove = availableValues.indexOf(Integer.valueOf(valueToRemoveFromList));
            availableValues.set(indexOfValueToRemove, 0);
        }
    }

    public void removeMarkedValues() {
        availableValues.removeAll(Collections.singleton(0));
    }

    public void setAvailableValues(List<Integer> availableValues) {
        this.availableValues = availableValues;
    }

    @Override
    public String toString() {
        String fieldCharacter = " ";

        if(value != -1) {
            fieldCharacter = String.valueOf(value);
        }

        return "|" + fieldCharacter;
    }
}
