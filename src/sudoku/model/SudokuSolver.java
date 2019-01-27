package sudoku.model;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;

public class SudokuSolver {
    private Random random = new Random();
    private SudokuBoard sudokuBoard = new SudokuBoard();
    private Checker checker = new Checker(sudokuBoard);
    private ArrayDeque<Backtrack> backtrack = new ArrayDeque<>();
    private int backtrackStepsCount = 0;
    private int totalSteps = 0;

    public void resetGame() {
        sudokuBoard.resetBoard();
        checker.setSudokuBoard(sudokuBoard);
        backtrack.clear();
        backtrackStepsCount = 0;
        totalSteps = 0;
    }


    public SudokuSolver() {
    }

    public boolean checkIfBoardIsValid() {
        return checker.checkIfBoardIsValid();
    }


    //return a list of values or if no solution get a message
    //if able to solve -> List<Integer>
    //unable -> error message / null
    public List<Integer> solve() {
        boolean isThereASolution = true;
        boolean isBoardSolved = false;

        while (!isBoardSolved && isThereASolution) {
            boolean isBoardValid = checker.checkIfBoardIsValid();

            if(isBoardValid) {
                guessValue();
            } else {

                if(backtrack.size() > 0 && totalSteps < 1000) {
                    goBack();

                } else {
                    isThereASolution = false;
                    System.out.println("No solution");
                    return null;
                }
            }
            isBoardSolved = sudokuBoard.isBoardSolved();
        }
        System.out.println(sudokuBoard.toString());
        System.out.println("Finished in: " + totalSteps + " steps");

        return sudokuBoard.getAllValuesFromBoard();
    }


    public void guessValue() {
        boolean canSetValueInElement = false;
        List<Integer> availableValues;
        SudokuElement element = null;
        totalSteps++;

        int column = -1, row = -1, guessedValueIndex, guessedValue, countOfAvailableValues;

        if(sudokuBoard.isAnyElementEmpty()) {
            while (!canSetValueInElement) {
                column = random.nextInt(Constants.SIZE_OF_BOARD);
                row = random.nextInt(Constants.SIZE_OF_BOARD);

                element = sudokuBoard.getElementUnderGivenIndexes(column, row);
                canSetValueInElement = element.isEmpty();
            }

            availableValues = element.getAvailableValues();

            if (availableValues.size() > 0) {
                countOfAvailableValues = availableValues.size();
                guessedValueIndex = random.nextInt(countOfAvailableValues);
                guessedValue = availableValues.get(guessedValueIndex);

                SudokuBoard clonedBoard = null;
                try {
                    clonedBoard = sudokuBoard.deepCopy();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

                sudokuBoard.setValueOnBoard(column, row, guessedValue);

                Backtrack backtrackEntry = new Backtrack(clonedBoard, column, row, guessedValue);
                backtrack.add(backtrackEntry);
            }
        }
    }

    public void goBack() {
        backtrackStepsCount++;

        if (backtrack.size() > 0) {
            Backtrack lastBacktrack = backtrack.pollLast();
            SudokuBoard lastBoard = lastBacktrack.getSudokuBoard();
            int column = lastBacktrack.getColumn();
            int row = lastBacktrack.getRow();
            int lastGuessedValue = lastBacktrack.getValue();

            setSudokuBoard(lastBoard);
            checker.setSudokuBoard(lastBoard);

            SudokuElement currentElement = sudokuBoard.getElementUnderGivenIndexes(column, row);
            currentElement.setValue(-1);
            currentElement.removeValueFromAvailableValues(lastGuessedValue);
        }

        if (backtrackStepsCount > 10)  goBackToEntryBoard();
    }

    private void goBackToEntryBoard() {
        SudokuBoard restartBoard = null;

        if (backtrack.size() > 0) {
            try {
                restartBoard = backtrack.getFirst().getSudokuBoard().deepCopy();
            } catch (CloneNotSupportedException e) {
                System.out.println("Could not clone board!");
            }

            setSudokuBoard(restartBoard);
            checker.setSudokuBoard(restartBoard);

            backtrackStepsCount = 0;
        }
    }

    public void setSudokuBoard(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }
}