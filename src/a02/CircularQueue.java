package a02;

import java.util.Iterator;

public class CircularQueue {
    //ARRAY STRUCTURE ERIC
    public class CircularQueueA<Item> implements Iterable<Item> {
        CircularQueueA(int capacity){}
        public boolean isFull(){/*TODO*/ return false;}
        public boolean isEmpty(){/*TODO*/return false;}
        public int size(){/*TODO*/return 0;}
        public void enqueue(/*TODO*/Item item){}
        public Item dequeue(){/*TODO*/return null;}
        public Item peek(){/*TODO*/return null;}
        public String toString(){/*TODO*/return null;}

        @Override
        public Iterator<Item> iterator() {
            return null;
        }
    }
    //LINKED NODE STRUCTURE RIANNA
    public class CircularQueueB{
     //TODO
    }
}
