package locks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private static int counter = 0;
    private static ReadWriteLock lock = new ReentrantReadWriteLock();
    public static void main(String[] args) {
        Thread writeThread = new Thread(new WriteTask());
        Thread readThread1 = new Thread(new ReadTask());
        Thread readThread2 = new Thread(new ReadTask());

        writeThread.start();
        readThread1.start();
        readThread2.start();

        try{
            writeThread.join();
            readThread1.join();
            readThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static class WriteTask implements Runnable {

        @Override
        public void run() {
            lock.writeLock().lock();
            try {
                System.out.println("Запись данных");
                counter++;
                Thread.sleep(1000);
                System.out.println("Данные записаны: " + counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    static class ReadTask implements Runnable {
        @Override
        public void run() {
            lock.readLock().lock();
            try{
                Thread.sleep(3000);
                System.out.println("Чтение данных: " + counter);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.readLock().unlock();
            }
        }
    }
}
