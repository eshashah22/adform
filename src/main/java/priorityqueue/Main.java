package priorityqueue;

public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        newPQ<String> q = new newPQ<>();

       QProducer<String> p = new QProducer<>(q, "a", 3);
        QConsumer<String> c = new QConsumer<>(q);

        Thread pThread = new Thread(p);
        Thread cThread = new Thread(c);

        pThread.start();
        cThread.start();
    }
}
