package dev.joon.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    // 메모리 누수를 위한 static 리스트
    private static final List<byte[]> memoryLeakList = new ArrayList<>();

    public static void main(String[] args) {
        // 1. 멀티스레드 작업 실행
        executeMultiThreadTasks(); // CPU 점유율 + thread timeline 보기

        // 2. 메모리 누수 시뮬레이션 실행
        simulateMemoryLeak(); // 이건 실시간 분석으로 보는게 더 직관적임.
    }

    private static void executeMultiThreadTasks() {
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

    private static void simulateMemoryLeak() {
        System.out.println("Starting memory leak simulation...");

        // 무한 루프로 메모리 누수 시뮬레이션
        while (true) {
            try {
                // 1MB 크기의 바이트 배열을 계속 추가
                memoryLeakList.add(new byte[1024 * 1024]);
                // 메모리 상태 출력
                System.out.println("Current memory usage: " +
                        memoryLeakList.size() + "MB");
                Thread.sleep(10); // 10ms 대기
            } catch (OutOfMemoryError | InterruptedException e) {
                System.out.println("Memory leak simulation stopped: " + e.getMessage());
                break;
            }
        }
    }
}
