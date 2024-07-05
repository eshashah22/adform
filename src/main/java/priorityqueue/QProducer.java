package priorityqueue;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QProducer<T> implements IQProducer<T>, Runnable {

    private newPQ<T>q;
    private T ele;
    private int priority;
    private static final Logger logger = Logger.getLogger(QProducer.class.getName());

    public QProducer(newPQ<T>q, T ele, int priority){
        this.q = q;
        this.ele = ele;
        this.priority = priority;
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.INFO); 
        logger.addHandler(handler);
        logger.setLevel(Level.INFO);
    }
    @Override
    public void run() {
        try {
            produce(ele);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.info("Thread interrupted");
        }
        
    }

    @Override
    public void produce(T t) {
        q.offer(t, priority);
        logger.info("Produced " + t);
        
    }
    
}
