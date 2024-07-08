package priorityqueue;

import java.io.IOException;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QProducer<T> implements IQProducer<T>, Runnable {

    private newPQ<T>q;
    private static final Logger logger = Logger.getLogger(QProducer.class.getName()); //find log file
    Random rand = new Random();


    public QProducer(newPQ<T>q){ 
        this.q = q;

        try {
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);

            FileHandler fileHandler = new FileHandler("QProducer.log", true); 
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
            T data = genData();
            produce(data);
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            logger.info("Thread interrupted");
            Thread.currentThread().interrupt();
        }
        
    }


    @Override
    public void produce(T t) {
        int priority = rand.nextInt(10);
        q.offer(t, priority); 
        logger.info("Produced " + t + " with prior " + priority);
        
    }

    
    private T genData(){
        Integer randData = rand.nextInt(100);
        return (T) randData;
        
    }

    @Override
    public String toString() {
        return "QProducer [q=" + q + "]";
    }
    
}
