package dev.joon.demo;

public class Main {

    public static void main(String[] args) {
        // 실행 여부를 주석 처리로 조정

        // 1. 멀티스레드 작업 실행 (CPU 점유율 + thread timeline 보기)
        // MultiThreadDemo.executeMultiThreadTasks();

        // 2. 메모리 누수 시뮬레이션 실행 (실시간 분석 추천)
        // MemoryLeakDemo.simulateMemoryLeak();

        // 3. 비효율적인 문자열 처리 성능 비교 실행 - 함수 내부에서 효율/비효율직인 함수 바꿔 실행 가능 - 실시간, thread, memory timeline 보기
        // CPUProfilerDemo.runStringConcatenationTests();

        // 4. 의도적인 메모리 누수와 개선된 캐시 처리 실행
        // MemoryCacheDemo.runMemoryLeakAndImprovedCacheDemo();

        // 5. 데드락 시나리오와 개선된 락 처리 실행
        // ThreadLockDemo.runDeadlockScenario();

        // TODO: 6. 인터넷 확인해서 여러 기술블로그의 문제가 되었던 부분들 간단하게 재현 및 개선 작업
    }
}
