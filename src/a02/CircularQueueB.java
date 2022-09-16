package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularQueueB<Item> implements Iterable<Item> {
    int capacity;
    //front of the line or queue
    private Node head;
    //back of the line or queue
    private Node tail;
    private int n;

    //From NumberStack CE
    private class Node{
        private Item item;

        private Node next; //links to the next node

        Node(Item item){ this.item = item;}
    }
    //From NumberStack CE
    CircularQueueB(int capacity){this.capacity= capacity;}
    public boolean isFull(){return n == capacity;}
    public boolean isEmpty(){return n == 0;}
    public int size(){return n;}
    //From WordList.prepend()
    public void enqueue(Item item){
        //TODO Circular
        Node newNode = new Node(item);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        }
        else{
            //creates a holder for the old head for clarity.
            Node oldHead = head;
            //creates the link for the linked list,
            //pointing it to the old head
            // Imagine it like this drawing:
            // newNode -> oldHead (newNode.next = oldHead;)
            newNode.next = oldHead;
            //finally establishes the new node as the head.
            head = newNode;
            // The links look like this now newNode -> oldHead -> ...
        }
        n++;
    }
    public Item dequeue(){
        if (isEmpty()){
            throw new NoSuchElementException("Can't remove an element from an empty queue.");
        }
        Node topNode = head;
        head = head.next;
        n--;
        return topNode.item;}

    public Item peek(){
        if (isEmpty()){
            throw new NoSuchElementException("Can't read front element from an empty queue.");
        }
        return head.item;
    }
    public String toString(){/*TODO*/return null;}

    @Override
    public Iterator<Item> iterator() {
        return new ItemIterator();
    }

    public class ItemIterator implements Iterator<Item> {
        //internal refs to class variables of outer class
        private int count = 0;
        private Node current = head;
        @Override
        public boolean hasNext() {
            return count < n;
        }

        @Override
        public Item next() {
            Item nextItem = current.item;
            current = current.next;
            count++;
            return nextItem;
        }
    }
}

