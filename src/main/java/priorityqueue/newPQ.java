package priorityqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;



public class newPQ <T>{

    public List<List<T>>values;
    int size;

    private static final Logger logger = Logger.getLogger(newPQ.class.getName());



    public newPQ(){
        this.values = new ArrayList<>(10); //want 10 "spots" b/c 0-9 priorities

        for(int i = 0; i < 10; i++){
            values.add((List<T>) new ArrayList<>()); //add empty array list to each "spot"
        }
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.INFO); 
        logger.addHandler(handler);
        logger.setLevel(Level.INFO);
        
    }

    public void offer(T data, int priority){
        if(priority < 0 || priority > 9){
            logger.info("Invalid priority");
            return;
        }

        values.get(priority).add(data);
        size++;
        logger.info("Added element with priority " + priority + ": " + data);
    }


    public T take() {
        for (int i = 9; i >= 0; i--) { 
            List<T> spots = values.get(i);
            if (!spots.isEmpty()) {
                size--;
                return spots.remove(0);
            }
        }
        return null;
    }

    public boolean peek(){
        return size > 0;
    }

    public boolean peek(int timeout){
        while(System.currentTimeMillis() < System.currentTimeMillis() + timeout)
        {
            if(peek()){
                return true;
            }
            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                
            }
        }
        return peek();

        
    }

    // public void disp(){
    //     for(int i = 0; i < 10; i++){
    //         List<T>prior = values.get(i);
    //         for(T data: prior){
    //             System.out.println("Data: " + data + ",Priority: " + i);
    //         }
    //     }  
	        
	// }
	
    

    public static void main(String[] args) {
        
             
    }

    
}
