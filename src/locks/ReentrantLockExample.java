package locks;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private static int counter = 0;
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new ConcurentTask());
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(counter);
    }
        static class ConcurentTask implements Runnable {
            @Override
            public void run() {
                lock.lock();
                try {
                    for (int i = 0; i < 1000; i++) {
                        counter++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
