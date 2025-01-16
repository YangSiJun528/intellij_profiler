package dev.joon.demo;

import java.util.ArrayList;
import java.util.List;

public class MemoryLeakDemo {

    // 메모리 누수를 위한 static 리스트
    private static final List<byte[]> memoryLeakList = new ArrayList<>();

    public static void simulateMemoryLeak() {
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
