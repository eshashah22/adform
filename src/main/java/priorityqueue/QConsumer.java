package priorityqueue;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QConsumer<T> implements IQConsumer<T>, Runnable {

    private newPQ<T>q;
    
    private static final Logger logger = Logger.getLogger(QConsumer.class.getName());

    public QConsumer(newPQ<T>q){
        this.q = q;
       
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.INFO); 
        logger.addHandler(handler);
        logger.setLevel(Level.INFO);
    }
    @Override
    public void run() {
        try {
            consume();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.info("Thread interrupted");
        }
    }

    @Override
    public T consume() {
        T ret = q.take();
       logger.info("Consumed " + ret);
       return ret;
    }
    
}
