public class Unit1Q14SynchronizationDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> counter.incrementMany(100_000), "T1");
        Thread t2 = new Thread(() -> counter.incrementMany(100_000), "T2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.get());
        System.out.println("done by Krish Devkota");
    }

    static class Counter {
        private int value;

        void incrementMany(int times) {
            for (int i = 0; i < times; i++) {
                increment();
            }
        }

        private synchronized void increment() {
            value++;
        }

        synchronized int get() {
            return value;
        }
    }
}

