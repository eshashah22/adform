package priorityqueue;

import java.util.ArrayList;
import java.util.List;

public class newPQ <T>{

    public List<List<T>>values;
    int size;

    public newPQ(){
        this.values = new ArrayList<>(10); //want 10 "spots" b/c 0-9 priorities

        for(int i = 0; i < 10; i++){
            values.add((List<T>) new ArrayList<>()); //add empty array list to each "spot"
        }
    }

    public void offer(T data, int priority){
        if(priority < 0 || priority > 9){
           return;
        }

        values.get(priority).add(data);
        size++;
        
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
        // System.out.println("Inserting Data");
        // newPQ pq = new newPQ<>();

        // pq.offer("a", 2);
        // pq.offer("c", 3);
        // pq.offer("b", 1);
        // pq.offer("d", 7);
        // pq.offer("g", 1);
        // pq.offer("e", 6);
        // pq.offer("f", 6);
       
        // pq.disp();
        
        // System.out.println("Take");
      
        // System.out.println(pq.take()); //d
        // System.out.println(pq.take()); //e
        // System.out.println(pq.take()); //f
        // System.out.println(pq.take()); //c
        // System.out.println(pq.take()); //a
        // System.out.println(pq.take()); //b
        // System.out.println(pq.take()); //g
      
             
    }

    
}
