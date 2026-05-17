public class deadLock {
    public static void main(String[] args) {
        final Object lock1 = new Object();
        final Object lock2 = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Поток 1: Держит замок 1...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                System.out.println("Поток 1: Ожидает замок 2...");
                synchronized (lock2) {
                    System.out.println("Поток 1: Получил замок 2!");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Поток 2: Держит замок 2...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                System.out.println("Поток 2: Ожидает замок 1...");
                synchronized (lock1) {
                    System.out.println("Поток 2: Получил замок 1!");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}