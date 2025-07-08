package collections;

import java.util.concurrent.*;

public class CollectionsExample {
    private static final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
    private static final ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Thread listThread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                list.add("Element: " + i);
                System.out.println("List Thread 1 added: " + i);
            }
        });
        Thread listThread2 = new Thread(() -> {
            for (int i = 10; i < 20; i++) {
                list.add("Element: " + i);
                System.out.println("List Thread 1 added: " + i);
            }
        });

        Thread mapThread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                map.put(i, "Value: " + i);
                System.out.println("Map Thread 1 added: " + i);
            }
        });
        Thread mapThread2 = new Thread(() -> {
            for (int i = 10; i < 20; i++) {
                map.put(i, "Value: " + i);
                System.out.println("Map Thread 2 added: " + i);
            }
        });

        Thread queueProducer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    String message = "Message: " + i;
                    queue.put(message);
                    System.out.println("Queue Thread 1 added: " + message);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread queueConsumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    String message = queue.take();
                    System.out.println("Queue Thread 2 took: " + message);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        queueProducer.start();
        queueConsumer.start();
    }
}
