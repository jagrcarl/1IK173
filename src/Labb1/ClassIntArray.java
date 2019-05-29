package Labb1;

import java.util.Arrays;

public class ClassIntArray implements IIntArray {

    protected int[] array;

    public ClassIntArray(int[] testArray) {
        this.array = testArray;
    }

    public double getAverage() throws ArithmeticException {
        double a = 0;
        double b = 0;

        for (int i = 0; i < array.length; i++) {
            b = b + array[i];
        }
        a = b / array.length;

        if (array.length == 0) {
            System.out.println("The Array is missing values.");
            throw new ArithmeticException();
        }
        return a;
    }

    public void appendLast(int value) throws ArrayExeption {
        validateBounds(value);
        int[] copyArray = Arrays.copyOf(array, array.length + 1);
        for (int i = 0; i < copyArray.length; i++) {
            if (i == copyArray.length - 1) {
                copyArray[i] = value;
            }
        }
        for (int k = 0; k < copyArray.length; k++) {
            System.out.println(copyArray[k]);
        }
        array = Arrays.copyOf(copyArray, copyArray.length);

    }

    public void insertAt(int pos, int value) throws ArrayExeption {
        validateBounds(pos);
        int[] copyArray = Arrays.copyOf(array, array.length + 1);
        for (int i = 0; i < pos; i++) {
            copyArray[i] = array[i];
        }
        copyArray[pos] = value;
        for (int k = pos + 1; k < copyArray.length; k++) {
            copyArray[k] = array[k - 1];
        }
        array = Arrays.copyOf(copyArray, copyArray.length);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public int deleteAt(int pos) throws ArrayExeption {
        validateBounds(pos);
        int[] copyArray = Arrays.copyOf(array, array.length - 1);
        for (int i = 0, k = 0; i < array.length; i++) {
            if (i == pos) {
                continue;
            }
            copyArray[k++] = array[i];
        }
        System.out.println(array[pos]);
        return array[pos];
    }

    public void setAt(int pos, int element) throws ArrayExeption {
        validateBounds(pos);
        array[pos] = element;
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public int getAt(int pos) throws ArrayExeption {
        validateBounds(pos);
        for (int i = 0; i < pos; i++) {
            System.out.println(array[pos]);
            return array[pos];
        }
        return 2;
    }

    public int[] findPositions(int val) throws ArrayExeption {
        validateBounds(val);
        int copyArray[] = Arrays.copyOf(array, array.length);
        int antal = 0;

        for (int i = 0; i < copyArray.length; i++) {
            if (copyArray[i] == val) {
                array[antal] = i;
                antal++;
            } else if (copyArray[i] != val) {
                array[antal] = 0;
                antal++;
            }
        }
        return array;
    }

    public void validateBounds(int pos) throws ArrayExeption {
        if (pos < 0 || pos >= array.length) {
            throw new ArrayExeption("Check that the seize of the Array is correct.");
        }
    }
}