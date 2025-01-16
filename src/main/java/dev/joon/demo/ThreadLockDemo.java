package dev.joon.demo;

public class ThreadLockDemo {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    // 의도적인 데드락 발생
    static class DeadlockScenario {
        public static void method1() {
            synchronized (lock1) {
                sleep(100);
                synchronized (lock2) {
                    System.out.println("Method 1");
                }
            }
        }

        public static void method2() {
            synchronized (lock2) {
                sleep(100);
                synchronized (lock1) {
                    System.out.println("Method 2");
                }
            }
        }
    }

    // 개선된 버전 - 락 순서 일관성 유지
    static class ImprovedLocking {
        public static void method1() {
            synchronized (lock1) {
                sleep(100);
                synchronized (lock2) {
                    System.out.println("Method 1");
                }
            }
        }

        public static void method2() {
            synchronized (lock1) {
                sleep(100);
                synchronized (lock2) {
                    System.out.println("Method 2");
                }
            }
        }
    }

    public static void runDeadlockScenario() {
        new Thread(() -> DeadlockScenario.method1()).start();
        new Thread(() -> DeadlockScenario.method2()).start();
        // new Thread(() -> ImprovedLocking.method1()).start();
        // new Thread(() -> ImprovedLocking.method2()).start();
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
