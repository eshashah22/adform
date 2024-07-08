package priorityqueue;

public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        newPQ<Integer> queue = new newPQ<>(); 
        
        int numP = 2; 
        int numC = 2; 

        IQProducer<Integer>[] p = new QProducer[numP];
        IQConsumer<Integer>[] c = new QConsumer[numC];

        for (int i = 0; i < numP; i++) {
            p[i] = new QProducer<>(queue);
        }

        for (int i = 0; i < numC; i++) {
            c[i] = new QConsumer<>(queue);
        }

        Thread[] pThreads = new Thread[numP];
        Thread[] cThreads = new Thread[numC];

        for (int i = 0; i < numP; i++) {
            pThreads[i] = new Thread ((Runnable) p[i]);
        }

        for (int i = 0; i < numC; i++) {
            cThreads[i] = new Thread((Runnable) c[i]);
        }

        for (int i = 0; i < numP; i++) {
            pThreads[i].start();
        }

        for (int i = 0; i < numC; i++) {
            cThreads[i].start();
        }

        queue.toString();

        
       

        

    }
}
