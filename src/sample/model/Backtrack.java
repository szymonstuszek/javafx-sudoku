package sample.model;

public class Backtrack {
    private final SudokuBoard sudokuBoard;
    private final int column;
    private final int row;
    private int value;

    public Backtrack(SudokuBoard sudokuBoard, int column, int row, int value) {
        this.sudokuBoard = sudokuBoard;
        this.column = column;
        this.row = row;
        this.value = value;
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Backtrack \n" +
                sudokuBoard + "\n" +
                value + " set at column: " + column + " row: " + row;

    }
}
