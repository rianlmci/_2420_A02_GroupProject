package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Rianna M unless otherwise stated
 * @param <Item>
 */
public class CircularQueueB<Item> implements Iterable<Item> {
    //how much the queue can hold
    int capacity;
    //front of the line or queue
    private Node front;
    //back of the line or queue
    private Node rear;
    //how much the queue is currently holding
    private int size;

    //From NumberStack CE
    private class Node{
        private Item item;

        private Node next; //links to the next node

        public Node getNext(){return next;}
        public Item getItem(){return item;}

        Node(Item item){ this.item = item;}
    }

    /**
     * Constructor for LinkedList CircularQueue
     * Inspired by NumberStack CE
     * @param capacity how much this queue can hold.
     */
    CircularQueueB(int capacity){this.capacity= capacity;}

    /**
     * @return if the linked list queue is full
     */
    public boolean isFull(){return size == capacity;}

    /**
     * @return if the linked list queue is empty
     */
    public boolean isEmpty(){return size == 0;}

    /**
     * @return size of the queue
     */
    public int size(){return size;}

    /**
     * Adds an item to the front of the list.
     * Inspired by CE Wordlist.prepend()
     * @param item we are adding to the queue
     */
    public void enqueue(Item item){
        Node newNode = new Node(item);
        if(isFull()){capacity++;}
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
            front.next = rear;
            rear.next = front;
        }
        else{
            //connects the previous rear to the new rear with a next ->
            rear.next = newNode;
            //Circularly connects new node (rear) back to the front with a next ->
            newNode.next = front;
            //finally establishes the new node as the rear of the queue.
            rear = newNode;
        }
        //dynamically resizes the capacity

        size++;
    }

    /**
     *
     * @return item removed from the front of the queue
     */
    public Item dequeue(){
        //TODO
        if (isEmpty()){
            throw new NoSuchElementException("Can't remove an element from an empty queue.");
        }
        Node topNode = front;
        front = front.next;
        size--;
        if(!isFull()){capacity--;}
        return topNode.item;}

    public Item peek(){
        if (isEmpty()){
            throw new NoSuchElementException("Can't read front element from an empty queue.");
        }
        return front.item;
    }

    /**
     *
     * @return String format of the queue.
     */
    @Override
    public String toString(){

        StringBuilder builtString = new StringBuilder();

        for (Item el : this) {
            builtString.append(el).append(" ");
        }

        return builtString.toString();
    }
    /*
    public String toStringNodeInfo(){

        StringBuilder builtString = new StringBuilder();

        for (Item el : this) {
            builtString.append(el).append(" ");
        }

        return builtString.toString();
    }
     */


    @Override
    public Iterator<Item> iterator() {
        return new ItemIterator();
    }

    public class ItemIterator implements Iterator<Item> {
        //internal refs to class variables of outer class
        private int count = 0;
        private Node current = front;
        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public Item next() {
            Item nextItem = current.item;
            current = current.next;
            count++;
            return nextItem;
        }
    }
    //= = = TEST CLIENT = = =//
    public static void main(String[] args){
        CircularQueueB<String> numberQueue = new CircularQueueB<>(3);
        System.out.println("Adding 1 to circular queue... \n");
        numberQueue.enqueue("1");
        System.out.println("Q: is numberQueue full? A: " + numberQueue.isFull() + "\n");
        System.out.println("Current values of nodes: " + numberQueue+ "\n");
        System.out.println("Called numberQueue.dequeue();"+ "\n");
        numberQueue.dequeue();
        System.out.println("Q: is numberQueue empty? A: " + numberQueue.isEmpty() + "\n");
        System.out.println("Calling enqueue more than capacity"
               + "Called numberQueue.enqueue(''1'');\n"
               + "Called numberQueue.enqueue(''2'');\n"
               + "Called numberQueue.enqueue(''3'');\n"
               + "Called numberQueue.enqueue(''4'');\n"
        );
        numberQueue.enqueue("1");
        numberQueue.enqueue("2");
        numberQueue.enqueue("3");
        numberQueue.enqueue("4");
        System.out.println("Current values of nodes: " + numberQueue + "\n");
        System.out.println("Q: is numberQueue full? A: " + numberQueue.isFull() + "\n");
        System.out.println("Called numberQueue.dequeue(); Dequeue value: " + numberQueue.dequeue() + "\n");
        System.out.println("Current values of nodes: " + numberQueue + "\n");
        System.out.println("Current size of nodes: " + numberQueue.size + "\n");
        System.out.println("Current capacity of nodes: " + numberQueue.capacity + "\n");
    }
}


