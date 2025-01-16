package dev.joon.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadDemo {
    public static void executeMultiThreadTasks() {
        System.out.println("Starting multi-thread tasks...");
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 5개의 장시간 실행 태스크 제출
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                try {
                    System.out.println("Task " + taskId + " started");
                    // CPU 집약적인 작업 시뮬레이션
                    performLongRunningTask();
                    System.out.println("Task " + taskId + " completed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        // 모든 태스크가 완료될 때까지 대기
        executor.shutdown();
        try {
            executor.awaitTermination(40, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void performLongRunningTask() {
        // 약 N초 동안 CPU를 사용하는 작업
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 30000) {
            // CPU 사용을 위한 더미 계산
            double result = 0;
            for (int i = 0; i < 100000; i++) {
                result += Math.sin(Math.cos(Math.tan(i)));
            }
        }
    }
}
