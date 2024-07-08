package priorityqueue;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QConsumer<T> implements IQConsumer<T>, Runnable {

    private newPQ<T>q;
    
    private static final Logger logger = Logger.getLogger(QConsumer.class.getName());

    public QConsumer(newPQ<T>q){
        this.q = q;
       
        try {
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);

            FileHandler fileHandler = new FileHandler("QConsumer.log", true); 
            fileHandler.setLevel(Level.INFO);

            logger.addHandler(consoleHandler);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);
            logger.setUseParentHandlers(false); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            consume();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.info("Thread interrupted");
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public T consume() {
        T ret = q.take();
       logger.info("Consumed " + ret);
       return ret;
    }
    
}
