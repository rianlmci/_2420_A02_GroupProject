package a02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CircularQueueATest {
    private CircularQueueA<String> stringQueue = new CircularQueueA<>(5);
    private CircularQueueA<String> emptyQueue = new CircularQueueA<>(3);
    private CircularQueueA<String> oneElement = new CircularQueueA<>(5);


    @BeforeEach
    void setUp() {
        stringQueue = new CircularQueueA<>(5);
        emptyQueue = new CircularQueueA<>(3);
        oneElement = new CircularQueueA<>(5);
        stringQueue.enqueue("this");
        stringQueue.enqueue("is");
        stringQueue.enqueue("queue");
        stringQueue.enqueue("a's");
        stringQueue.enqueue("test");
        oneElement.enqueue("Test");
    }

    @Test
    void isFull_queueIsEmpty() {
        assertFalse(emptyQueue.isFull());
    }

    @Test
    void isFull_queueHasOne() {
        assertFalse(oneElement.isFull());
    }

    @Test
    void isFull_queueIsFull() {
        assertTrue(stringQueue.isFull());
    }

    @Test
    void isEmpty_queueIsEmpty() {
        assertTrue(emptyQueue.isEmpty());
    }
    @Test
    void isEmpty_queueHasOne() {
        assertFalse(oneElement.isEmpty());
    }
    @Test
    void isEmpty_queueIsFull() {
        assertFalse(stringQueue.isEmpty());
    }

    @Test
    void enqueue_1Element() {
        assertEquals("Test",oneElement.peek());
    }
    @Test
    void enqueue_3Elements() {
        emptyQueue.enqueue("Test0");
        emptyQueue.enqueue("Test1");
        emptyQueue.enqueue("Test2");
        assertEquals("Test0 Test1 Test2 ", emptyQueue.toString());
    }
    @Test
    void enqueue_moreThenMax() {
        emptyQueue.enqueue("Test0");
        emptyQueue.enqueue("Test1");
        emptyQueue.enqueue("Test2");
        assertThrows(UnsupportedOperationException.class, () -> emptyQueue.enqueue("Test3"));
    }

    @Test
    void dequeue_1Element() {
        assertEquals("Test",oneElement.dequeue());
    }
    @Test
    void dequeue_5Elements() {
        assertEquals("this",stringQueue.dequeue());
        assertEquals("is",stringQueue.dequeue());
        assertEquals("queue",stringQueue.dequeue());
        assertEquals("a's",stringQueue.dequeue());
        assertEquals("test",stringQueue.dequeue());
    }
    @Test
    void dequeue_Empty() {
        assertThrows(NoSuchElementException.class, () -> emptyQueue.dequeue());
    }

    @Test
    void peak_1Element() {
        assertEquals("Test",oneElement.peek());
    }
    @Test
    void peak_5Elements() {
        assertEquals("this",stringQueue.peek());
        assertEquals("this",stringQueue.peek());
    }
    @Test
    void peak_Empty() {
        assertThrows(NoSuchElementException.class, () -> emptyQueue.peek());
    }

    @Test
    void size_EmptyQueue() {
        assertEquals(0,emptyQueue.size());
    }

    @Test
    void size_oneElement() {
        assertEquals(1,oneElement.size());
    }

    @Test
    void size_FullQueue() {
        assertEquals(5,stringQueue.size());
    }

    @Test
    void testToString_EmptyQueue() {
        assertEquals("",emptyQueue.toString());
    }

    @Test
    void testToString_OneElement() {
        assertEquals("Test ",oneElement.toString());
    }

    @Test
    void testToString_FullQueue() {
        assertEquals("this is queue a's test ", stringQueue.toString());
    }
}