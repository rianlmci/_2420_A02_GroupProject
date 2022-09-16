package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularQueue {
    //ARRAY STRUCTURE ERIC
    public class CircularQueueA<Item> implements Iterable<Item> {
        int capacity;
        int front, rear;
        Item[] items;
        int size;

        CircularQueueA(int capacity) {
            if (capacity < 1)
                throw new IllegalArgumentException("Capacity must be at least 1.");
            this.capacity = capacity;
            front = -1;
            rear = -1;
            items = (Item[]) new Object[capacity];
        }

        // Check if the queue is full
        public boolean isFull() {
            if (front == 0 && rear == capacity - 1) {
                return true;
            }
            return front == rear + 1;
        }

        // Check if the queue is empty
        public boolean isEmpty() {
            return front == -1;
        }

        // Adding an element
        public void enqueue(Item element) {
            if (isFull())
                throw new UnsupportedOperationException("cannot add a element to a full queue.");

            if (front == -1)
                front = 0;
            rear = (rear + 1) % capacity;
            items[rear] = element;
            size++;
        }

        // Removing an element
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

        public Item peek() {
            return items[front];
        }

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
        private class CircularQueueIterator implements Iterator<Item>{
            int currentRear = rear;
            int currentFront = front - 1;
            @Override
            public boolean hasNext() {
                //System.out.println("Has next: " + (currentFront != currentRear + 1));
                return currentFront != currentRear && currentFront != -2;
            }

            @Override
            public Item next() {
                //System.out.println("Rear: " + currentRear);
                //System.out.println("Front: " + currentFront);
                currentFront = (currentFront + 1) % capacity;
                return items[currentFront];
            }
        }


    }
    //LINKED NODE STRUCTURE RIANNA
    public class CircularQueueB{
     //TODO
    }
}
