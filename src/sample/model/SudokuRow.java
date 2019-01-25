package sample.model;

import java.util.ArrayList;
import java.util.List;

public class SudokuRow {
    private List<SudokuElement> elements = new ArrayList<>();

    public SudokuRow() {
        for (int i = 0; i < Constants.SIZE_OF_BOARD; i++) {
            SudokuElement element = new SudokuElement();
            elements.add(element);
        }
    }

    public List<SudokuElement> getElements() {
        return elements;
    }

    public void setElements(List<SudokuElement> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        String row = "";
        for (int i = 0; i < elements.size(); i++) {
            row += elements.get(i).toString();
        }

        return row + "|";
    }
}
