package Labb1;

public interface IIntArray {

    double getAverage();

    void insertAt(int pos, int value)throws ArrayExeption;

    void setAt(int pos, int element) throws ArrayExeption;

    int getAt(int pos) throws ArrayExeption;

    int deleteAt(int pos) throws ArrayExeption;

    int[] findPositions(int val) throws ArrayExeption;
}