package de.hfu;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueueTest {

    @Test
    public void testQueue() {
        int[] sollQ = new int[3];
        Queue q1 = new Queue(3);
        q1.enqueue(12);
        sollQ[0] = 12;
        for(int i = 0; i < 3;i++) {
            assertEquals(sollQ[i], q1.queue[i]);
        }
        q1.enqueue(1);
        sollQ[1] = 1;
        for(int i = 0; i < 3;i++) {
            assertEquals(sollQ[i], q1.queue[i]);
        }
        q1.enqueue(2);
        sollQ[2] = 2;
        for(int i = 0; i < 3;i++) {
            assertEquals(sollQ[i], q1.queue[i]);
        }
        q1.enqueue(3);
        sollQ[2] = 3;
        for(int i = 0; i < 3;i++) {
            assertEquals(sollQ[i], q1.queue[i]);
        }

        assertEquals(12,q1.dequeue());
        assertEquals(1,q1.dequeue());
        assertEquals(3,q1.dequeue());
        q1.enqueue(3);
        sollQ[0] = 3;
        sollQ[1] = 0;
        sollQ[2] = 0;
        for(int i = 0; i < 3;i++) {
            assertEquals(sollQ[i], q1.queue[i]);
        }
    }

}
