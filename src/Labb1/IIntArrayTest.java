package Labb1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IIntArrayTest {

    int[] mainArray = {1, 2, 3, 4};

    @Test
    void getAverage() {
        ClassIntArray testObject = new ClassIntArray(mainArray);
        assertEquals(2.5, testObject.getAverage());
    }

    @Test
    void getAverageEmpty() {
        int[] testArray = {};
        ClassIntArray testObject = new ClassIntArray(testArray);
        assertThrows(ArithmeticException.class, () -> testObject.getAverage());
    }

    @Test
    void appendLast() throws ArrayExeption {

        int[] expected = {1, 2, 3, 4, 3};
        ClassIntArray testObject = new ClassIntArray(mainArray);
        testObject.appendLast(3);
        assertArrayEquals(expected, testObject.array);
    }
    @Test
    void appendLastEmpty() {
        int[] testArray = {};
        ClassIntArray testObject = new ClassIntArray(testArray);
        assertThrows(ArrayExeption.class, () -> testObject.appendLast(1));
    }
    @Test
    void appendLastNegative () {
        ClassIntArray testObject = new ClassIntArray(mainArray);
        assertThrows(ArrayExeption.class, () -> testObject.appendLast(-1));
    }

    @Test
    void insertAt() throws ArrayExeption {
        int[] expected = {1, 2, 5, 3, 4};
        ClassIntArray testObject = new ClassIntArray(mainArray);
        testObject.insertAt(2, 5);
        assertArrayEquals(expected, testObject.array);
    }
    @Test
    void insertAtEmpty () {
        int[] testArray = {};
        ClassIntArray testObject = new ClassIntArray(testArray);
        assertThrows(ArrayExeption.class, () -> testObject.insertAt(1, 5));
    }
    @Test
    void insertAtNegative () {
        ClassIntArray testObject = new ClassIntArray(mainArray);
        assertThrows(ArrayExeption.class, () -> testObject.insertAt(-1, 5));
    }

    @Test
    void setAt() throws ArrayExeption {
        int[] expected = {1, 2, 3, 4};
        ClassIntArray testObject = new ClassIntArray(mainArray);
        testObject.setAt(3, 4);
        assertArrayEquals(expected, testObject.array);
    }
    @Test
    void setAtEmpty () {
        int[] testArray = {};
        ClassIntArray testObject = new ClassIntArray(testArray);
        assertThrows(ArrayExeption.class, () -> testObject.setAt(2, 5));
    }
    @Test
    void setAtNegative () {
        ClassIntArray testObject = new ClassIntArray(mainArray);
        assertThrows(ArrayExeption.class, () -> testObject.setAt(-2, 2));
    }

    @Test
    void getAt() throws ArrayExeption {
        ClassIntArray testObject = new ClassIntArray(mainArray);
        assertEquals(3, testObject.getAt(2));

    }
    @Test
    void getAtEmpty () {
        int[] testArray = {};
        ClassIntArray testObject = new ClassIntArray(testArray);
        assertThrows(ArrayExeption.class, () -> testObject.getAt(3));
    }
    @Test
    void getAtNegative () {
        ClassIntArray testObject = new ClassIntArray(mainArray);
        assertThrows(ArrayExeption.class, () -> testObject.getAt(-5));
    }

    @Test
    void deleteAt() throws ArrayExeption {
        ClassIntArray testObject = new ClassIntArray(mainArray);
        assertEquals(3, testObject.deleteAt(2));
    }
    @Test
    void deleteAtEmpty () {
        int[] testArray = {};
        ClassIntArray testObject = new ClassIntArray(testArray);
        assertThrows(ArrayExeption.class, () -> testObject.deleteAt(1));
    }
    @Test
    void deleteAtNegative () {
        ClassIntArray testObject = new ClassIntArray(mainArray);
        assertThrows(ArrayExeption.class, () -> testObject.deleteAt(-3));
    }

    @Test
    void findPositions() throws ArrayExeption {
        int[] expected = {0, 1, 0, 0};
        ClassIntArray testObject = new ClassIntArray(mainArray);
        assertArrayEquals(expected, testObject.findPositions(2));
    }
    @Test
    void findPositionsEmpty () {
        int[] testArray = {};
        ClassIntArray testObject = new ClassIntArray(testArray);
        assertThrows(ArrayExeption.class, () -> testObject.findPositions(2));
    }
    @Test
    void findPositionsNegative () {
        ClassIntArray testObject = new ClassIntArray(mainArray);
        assertThrows(ArrayExeption.class, () -> testObject.findPositions(-2));
    }
}