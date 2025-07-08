package synchronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int threaId = i;
            new Thread(() -> {
                try{
                    System.out.println("Поток: " + threaId + " выполняет работу");
                    Thread.sleep(2000 * threaId);
                    System.out.println("Поток: " + threaId + " достик барьера");
                    cyclicBarrier.await();
                    System.out.println("Поток: " + threaId + " продолжает работу");


                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

    }
}
