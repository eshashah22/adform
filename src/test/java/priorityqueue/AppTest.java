package priorityqueue;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void pqTest()
    {
        newPQ pq = new newPQ<>();
       
      
        pq.offer("a", 2);
        assertEquals(true, pq.peek(500));
        pq.offer("c", 3);
        pq.offer("b", 1);
        pq.offer("d", 7);
        pq.offer("g", 1);
        pq.offer("e", 6);
        pq.offer("f", 6);
        assertEquals(true, pq.peek());

        assertEquals("d", pq.take());
        assertEquals("e", pq.take());
        assertEquals("f", pq.take());
        assertEquals("c", pq.take());
        assertEquals("a", pq.take());
        assertEquals("b", pq.take());
        assertEquals("g", pq.take());
        assertEquals(0, pq.size);
        assertEquals(false, pq.peek());
       


       
    }
}

