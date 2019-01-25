package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Algorithm {
    private Random r = new Random();
    public Algorithm() {

    }

    public int returnValue() {
        return r.nextInt(10);
    }

    public void getSomeText() {
        System.out.println("Algoritm working!");
    }

    public void updateBoardAfterSettingNumber(int value) {
        System.out.println("Updating board!");
    }

    public List<Integer> showValuesUnderGivenIndexes(int column, int row) {
        System.out.println("Showing values under given indexes, column: " + column + " row: " + row);
        List<Integer> availableValues = new ArrayList<>();
        availableValues.add(new Random().nextInt(9) + 1);
        availableValues.add(new Random().nextInt(9) + 1);
        availableValues.add(new Random().nextInt(9) + 1);

        availableValues.forEach(System.out::print);

        return availableValues;
    }

}
