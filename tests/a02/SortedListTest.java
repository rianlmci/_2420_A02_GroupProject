package a02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortedListTest {

    @Test
    void isEmpty() {
    }

    @Test
    void size() {
    }

    @Test
    void insert_Insert8Returns789() {
        SortedList<Integer> mySortedList = new SortedList<>();
        mySortedList.insert(1);
        mySortedList.insert(3);
        mySortedList.insert(5);
        mySortedList.insert(7);
        mySortedList.insert(9);
        mySortedList.insert(8);

    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void testToString() {
    }
}