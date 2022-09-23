package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A collection that hold elements in a FIFO queue data structure.
 *
 * @param <Item> the type parameter
 */
public class CircularQueueA<Item> implements Iterable<Item> {
    private int capacity;
    private int front, rear;
    private Item[] items;
    private int size;

    /**
     * Instantiates a new Circular queue.
     * Capacity of the queue must be positive.
     *
     *
     * @param capacity the capacity of the queue.
     */
    CircularQueueA(int capacity) {
        if (capacity < 1)
            throw new IllegalArgumentException("Capacity must be at least 1.");
        this.capacity = capacity;
        front = -1;
        rear = -1;
        items = (Item[]) new Object[capacity];
    }

    /**
     * Returns true if the queue is full.
     *
     * @return true if the queue is full.
     */
// Check if the queue is full
    public boolean isFull() {
        if (front == 0 && rear == capacity - 1) {
            return true;
        }
        return front == rear + 1;
    }

    /**
     * Returns true if the queue is empty.
     *
     * @return true if the queue is empty.
     */
// Check if the queue is empty
    public boolean isEmpty() {
        return front == -1;
    }

    /**
     * adds an element to the start of the queue.
     *
     * @param element the element
     * @throws UnsupportedOperationException if the queue is full
     */
    public void enqueue(Item element) {
        if (isFull())
            throw new UnsupportedOperationException("cannot add a element to a full queue.");

        if (front == -1)
            front = 0;
        rear = (rear + 1) % capacity;
        items[rear] = element;
        size++;
    }

    /**
     * Removes an element from the end of the queue
     * Returns the element that was removed.
     *
     * @return the item that was removed.
     * @throws NoSuchElementException if the queue is empty
     */
    public Item dequeue() {
        Item element;
        if (isEmpty())
            throw new NoSuchElementException("Cannot dequeue a empty queue.");


        element = items[front];
        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        size--;
        return (element);

    }

    /**
     * Returns the next element to be removed.
     *
     * @return the next element to be removed.
     */
    public Item peek() {
        if (isEmpty())
            throw new NoSuchElementException("Cannot peek an empty array");
        return items[front];
    }

    /**
     * Returns the size of the array.
     *
     * @return the size of the array.
     */
    public int size(){
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item el : this) {
            sb.append(el).append(" ");
        }
        return sb.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new CircularQueueIterator();
    }
    private class CircularQueueIterator implements Iterator<Item> {
        int currentFront = front;
        int i;

        @Override
        public boolean hasNext() {
            return i < size();
        }

        @Override
        public Item next() {
            Item item = items[currentFront];
            currentFront = (currentFront + 1) % capacity;
            i++;
            return item;
        }
    }
}
